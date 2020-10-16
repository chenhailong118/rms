package com.still.rms.common.utils;

import com.google.common.io.Files;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.time.Instant;

public class FileUtil {
  
  
  public static String saveToLocal(MultipartFile file, String filePath) throws IOException {
    String subPath = "/" + Instant.now().getEpochSecond() +"/"+file.getOriginalFilename();
    File newfile =   new File(filePath + subPath);
    if (!newfile.exists()) {
         newfile.getParentFile().mkdirs();
         newfile.createNewFile();
    }
    Files.write(file.getBytes(), newfile);
    return subPath;
  }

    /**
     * 互联网资源下载
     * @param url
     * @param filePath
     * @param fileName
     * @param method
     * @return
     * @throws Exception
     */
    public static boolean downloadByUrl(String url, String filePath, String fileName, String method, String proxyIp, Integer proxyPort){
        boolean isSuccess = false;
        //判断文件的保存路径后面是否以/结尾
        if (!filePath.endsWith("/")) {
            filePath += "/";
        }
        //文件存在则不下载
        if(new File(filePath + fileName).exists()){
            return isSuccess;
        }
        //创建不同的文件夹目录
        File file = new File(filePath);
        //判断文件夹是否存在
        if (!file.exists()) {
            //如果文件夹不存在，则创建新的的文件夹
            file.mkdirs();
        }
        FileOutputStream fileOut = null;
        HttpURLConnection conn = null;
        InputStream inputStream = null;

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try{
            // 建立链接
//            System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
            URL httpUrl = new URL(url);
            if(!StringUtils.isEmpty(proxyIp) && proxyPort != null){
                Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyIp, proxyPort));
                conn = (HttpURLConnection) httpUrl.openConnection(proxy);
//                InputStream in = httpUrl.openStream();
            }else{
                conn = (HttpURLConnection) httpUrl.openConnection();
            }
            conn.setRequestProperty("Upgrade-Insecure-Requests","1");
            conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.75 Safari/537.36");
            //请求方法，默认为get
            if (!StringUtils.isEmpty(method)) {
                conn.setRequestMethod(method);
            }
            conn.setDoInput(true);
            conn.setDoOutput(true);
            // post方式不能使用缓存
            conn.setUseCaches(false);
            conn.setConnectTimeout(6000);
            conn.setReadTimeout(12000);
            //连接指定的资源
            conn.connect();
            //获取网络输入流
            inputStream = conn.getInputStream();
            bis = new BufferedInputStream(inputStream);

            //写入到文件（注意文件保存路径的后面一定要加上文件的名称）
            fileOut = new FileOutputStream(filePath + fileName);
            bos = new BufferedOutputStream(fileOut);

            byte[] buf = new byte[4096];
            int length = bis.read(buf);
            //保存文件
            while (length != -1) {
                bos.write(buf, 0, length);
                length = bis.read(buf);
            }
            isSuccess = true;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("下载失败,URL:" + url);
        }finally {
            if(bos != null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bis != null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null){
                conn.disconnect();
            }
        }
        return isSuccess;
    }

}
