package com.wanggoudan.www.service.impl;

import com.wanggoudan.www.service.IUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Danny on 2018/8/22.
 */
@Service
public class UploadService implements IUploadService {
    private static String accessKeyId = "AKIDY6aIyVgGOK4c3Wp00qbpvrgKp8nKLdUR";
    private static String accessKeySecret = "3BIDFCVggUJPRJ81zycl0FaxMlDObq5K";
    private static String bucketName = "wffff-1256777784";
    private static String region = "ap-shanghai";

    @Override
    public Map uploadImg(MultipartFile file) {
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取后缀
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 文件上传的路径
        String filePath = "C:/upload/";
        // fileName处理
        fileName = filePath + UUID.randomUUID() + fileName;
        // 文件对象
        File dest = new File(fileName);
        // 创建路径
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }

        try {
            file.transferTo(dest);
            String url = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
            Map m = new HashMap();
            m.put("code", 0);
            m.put("uploaded", 1);
            m.put("fileName", file.getOriginalFilename());
            m.put("url", "http://localhost/file/download/" + url);
            return m;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
