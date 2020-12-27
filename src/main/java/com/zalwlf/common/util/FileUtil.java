package com.zalwlf.common.util;

import com.zalwlf.common.exception.ApiException;
import com.zalwlf.common.exception.ExceptionUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import lombok.extern.log4j.Log4j2;

/**
 * platform
 * <p>文件操作API</p>
 *
 * @author : lcq
 * @date : 2020-09-13 23:05
 **/
@Log4j2
public class FileUtil {

    public static void writeString(String filePath, String content) {
        try(FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(content.getBytes());
        } catch (IOException e) {
            log.error("fail to write to file : {}, exception : {}", filePath, ExceptionUtils.getFullStackTrace(e));
           throw new ApiException("fail to write to file : "+filePath, e);
        }
    }


    public static String readString(String filePath){
        File file = new File(filePath);
        if(!file.exists()){
            return null;
        }
        try(FileInputStream inputStream = new FileInputStream(file)) {
            byte[] bytes = new byte[inputStream.available()];
            int read = inputStream.read(bytes);
            if (log.isInfoEnabled()){
                log.info("read file : {}, length : {} ",filePath,read);
            }
            return new String(bytes, StandardCharsets.UTF_8);
        }catch (IOException e){
            log.error("fail to read file : {}, exception : {}",filePath, ExceptionUtils.getFullStackTrace(e));
            throw new ApiException("fail to read file : "+filePath,e);
        }
    }

}
