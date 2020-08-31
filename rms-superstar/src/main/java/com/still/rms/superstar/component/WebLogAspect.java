package com.still.rms.superstar.component;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.still.rms.common.api.CommonResponse;
import com.still.rms.mbg.mapper.LogMapper;
import com.still.rms.mbg.model.AuthUser;
import com.still.rms.mbg.model.Log;
import com.still.rms.superstar.security.AuthUserDetails;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.ClassClassPath;
import org.apache.ibatis.javassist.ClassPool;
import org.apache.ibatis.javassist.CtClass;
import org.apache.ibatis.javassist.CtMethod;
import org.apache.ibatis.javassist.bytecode.CodeAttribute;
import org.apache.ibatis.javassist.bytecode.LocalVariableAttribute;
import org.apache.ibatis.javassist.bytecode.MethodInfo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * @Author FishAndFlower
 * @Description 统一日志处理切面
 * @Date 2020/8/24 22:20
 * @Version 1.0
 */
@Aspect
@Component
@Order(1)
@Slf4j
public class WebLogAspect {
    @Autowired
    private LogMapper logMapper;

    @Pointcut("execution(public * com.still.rms.superstar.controller.*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
    }

    @AfterReturning(value = "log()", returning = "ret")
    public void doAfterReturning(Object ret) throws Throwable {
    }

    @Around("log()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //创建日志请求信息对象
        Log log = new Log();
        //记录进入时间
        long startTime = System.currentTimeMillis();
        //执行请求操作并返回执行结果
        Object result = joinPoint.proceed();
        //记录请求目标信息
        String classType = joinPoint.getTarget().getClass().getName();
        Class<?> clazz = Class.forName(classType);
        String clazzName = clazz.getName();
        log.setClazzName(clazzName);//记录类名
        String methodName = joinPoint.getSignature().getName(); //获取方法名称
        if("queryLogs".equals(methodName) || "queryLogById".equals(methodName)){//跳过日志查询请求
            return result;
        }
        log.setMethodName(methodName);//记录方法名
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method.isAnnotationPresent(ApiOperation.class)) {
            ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
            log.setDescription(apiOperation.value()); //方法说明
        }
        //记录请求参数信息
        log.setParameter(geRequestParams(clazzName, methodName, joinPoint.getArgs()));//记录请求参数
        //获取当前请求对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //记录用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser user = ((AuthUserDetails)authentication.getPrincipal()).getAuthUser();
        log.setUserId(user.getId());
        log.setUsername(user.getUsername());
        log.setNickname(user.getNickname());
        //记录请求源信息
        log.setRemoteUser(request.getRemoteUser());
        log.setRemoteIp(getIpAddress(request));
        log.setRemoteAddr(request.getRemoteAddr());
        log.setRemotePort(request.getRemotePort());
        //记录请求基本信息
        log.setHttpMethod(request.getMethod());//记录HTTP方法，GET/POST/PUT/DELETE/OPTIONS等
        log.setUri(request.getRequestURI());//目标URI
        log.setUrl(request.getRequestURL().toString());//目标URL
        String urlStr = request.getRequestURL().toString();
        log.setBasePath(StrUtil.removeSuffix(urlStr, URLUtil.url(urlStr).getPath()));//请求根地址
        //记录返回结果信息
        if(result instanceof CommonResponse){
            log.setReturnCode(((CommonResponse)result).getCode());
            log.setReturnMsg(((CommonResponse) result).getMessage());
            log.setReturnData(JSONUtil.toJsonStr(((CommonResponse) result).getData()));
        }else{
            log.setReturnData(JSONUtil.toJsonStr(result));
        }
        //记录执行时间
        long endTime = System.currentTimeMillis();
        log.setSpendTime((int) (endTime - startTime));
        log.setStartTime(new Date(startTime));
        logMapper.insert(log);
        return result;
    }

    /**
     * 获取请求参数信息
     * @return
     */
    public String geRequestParams(String clazzName, String methodName, Object[] args) throws Exception {
        Map<String,Object > nameAndArgs = this.getFieldsName(this.getClass(), clazzName, methodName,args);
        //nameAndArgs的两种类型，用实体类接收的类似这样： reqParams=com.ynet.finmall.innermanage.event.SaveOrUpdateRoleReqParam@616b9c0e
        //用Map<String,Object>接收的是这样：menuNo=56473283，遍历这个map区分两种不同，使用不同的取值方式。
        //根据获取到的值所属的不同类型通过两种不同的方法获取参数
        StringBuffer sb = new StringBuffer();
        if(nameAndArgs!=null && nameAndArgs.size()>0){
            for (Map.Entry<String, Object> entry : nameAndArgs.entrySet()) {
//                log.info(">>>>>>>>>>>>>>类型"+entry.getValue().getClass().toString());
                Object object = entry.getValue();
                if (object == null || object instanceof MultipartFile ||object instanceof ServletRequest ||object instanceof ServletResponse) {
                    continue;
                }
                if(object instanceof String || object instanceof Integer || object instanceof Long || object instanceof Double || object instanceof Float){
                    sb.append(JSONObject.toJSONString(entry)).append(",");
                }else{
                    sb.append(JSONObject.toJSONString(entry.getValue())).append(",");
                }
            }
        }
        String log = sb.toString().replace("{","").replace("}", "");
        log = log.substring(0,log.length()-1);
        return "{" + log + "}";
    }

    private Map<String,Object> getFieldsName(Class cls, String clazzName, String methodName, Object[] args) throws Exception {
        Map<String,Object > map = new HashMap<String,Object>();
        ClassPool pool = ClassPool.getDefault();
        ClassClassPath classPath = new ClassClassPath(cls);
        pool.insertClassPath(classPath);

        CtClass cc = pool.get(clazzName);
        CtMethod cm = cc.getDeclaredMethod(methodName);
        MethodInfo methodInfo = cm.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        if (attr == null) {
            return map;
        }
        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
        for (int i = 0; i < cm.getParameterTypes().length; i++){
            map.put( attr.variableName(i + pos),args[i]);//paramNames即参数名
        }
        return map;
    }

    /**
     * 获取请求源IP地址
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteHost();
        }
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if (ip.indexOf(",") != -1) {
                ip = ip.split(",")[0];
            }
        }
        return ip.contains("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }

}
