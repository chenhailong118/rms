# 前后端分离的资源管理系统

## 项目演示地址（支持游客登录）：http://175.24.75.121/#/login

## 技术选型
### 后端技术

| 技术                 | 说明                | 官网                                                 |
| -------------------- | ------------------- | ---------------------------------------------------- |
| SpringBoot           | 项目基础结构        | https://spring.io/projects/spring-boot               |
| SpringSecurity       | 认证和授权框架      | https://spring.io/projects/spring-security           |
| MyBatis              | ORM框架             | http://www.mybatis.org/mybatis-3/zh/index.html       |
| MyBatisGenerator     | 数据层代码生成      | http://www.mybatis.org/generator/index.html          |
| PageHelper           | MyBatis分页插件     | http://git.oschina.net/free/Mybatis_PageHelper       |
| Swagger-UI           | 文档生产工具        | https://github.com/swagger-api/swagger-ui            |
| Hibernator-Validator | 验证框架            | http://hibernate.org/validator                       |
| Redis                | 分布式缓存          | https://redis.io/                                    |
| Druid                | 数据库连接池        | https://github.com/alibaba/druid                     |
| JWT                  | JWT登录支持         | https://github.com/jwtk/jjwt                         |
| Lombok               | 简化对象封装工具    | https://github.com/rzwitserloot/lombok               |

### 前端技术

| 技术       | 说明                  | 官网                                   |
| ---------- | --------------------- | -------------------------------------- |
| Vue        | 前端框架              | https://vuejs.org/                     |
| Vue-router | 路由框架              | https://router.vuejs.org/              |
| Vuex       | 全局状态管理框架      | https://vuex.vuejs.org/                |
| Element    | 前端UI框架            | https://element.eleme.io               |
| Axios      | 前端HTTP框架          | https://github.com/axios/axios         |
| v-charts   | 基于Echarts的图表框架 | https://v-charts.js.org/               |
| Js-cookie  | cookie管理工具        | https://github.com/js-cookie/js-cookie |
| nprogress  | 进度条控件            | https://github.com/rstacruz/nprogress  |

## 环境搭建

### 开发工具

| 工具          | 说明                | 官网                                            |
| ------------- | ------------------- | ----------------------------------------------- |
| IDEA          | 开发IDE             | https://www.jetbrains.com/idea/download         |
| RedisDesktop  | redis客户端连接工具 | https://redisdesktop.com/download               |
| X-shell       | Linux远程连接工具   | http://www.netsarang.com/download/software.html |
| Navicat       | 数据库连接工具      | http://www.formysql.com/xiazai.html             |
| PowerDesigner | 数据库设计工具      | http://powerdesigner.de/                        |
| ProcessOn     | 流程图绘制工具      | https://www.processon.com/                      |

### 开发环境

| 工具          | 版本号 | 下载                                                         |
| ------------- | ------ | ------------------------------------------------------------ |
| JDK           | 1.8    | https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html |
| Mysql         | 5.7    | https://www.mysql.com/                                       |
| Redis         | 3.2    | https://redis.io/download                                    |
| Nginx         | 1.10   | http://nginx.org/en/download.html                            |

## 项目部署

### Windows本地环境安装及部署
   1、 安装Nginx，修改配置文件nginx.conf
   ![Image text](https://github.com/STIll-clx/img-folder/blob/main/rms/nginx.png) 
    
   2、 安装Redis
    
   3、 安装Mysql
    
   4、 安装JDK
    
   5、 修改域名，打开文件夹：C:\Windows\System32\drivers\etc\hosts,增加两条记录
   ![Image text](https://github.com/STIll-clx/img-folder/blob/main/rms/host.png)
    
   6、 IDEA打开rms工程，顺序启动所有EurekaPeer1,EurekaPeer2(服务注册与发现),Auth(权限管理系统),Superstar(资源管理系统),ApiGateway(API网关)
   ![Image text](https://github.com/STIll-clx/img-folder/blob/main/rms/rms.png)
   ![Image text](https://github.com/STIll-clx/img-folder/blob/main/rms/startup.png)
   