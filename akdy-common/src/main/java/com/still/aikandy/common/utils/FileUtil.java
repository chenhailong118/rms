package com.still.aikandy.common.utils;

import com.google.common.io.Files;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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

}
