package com.wanggoudan.www.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by Danny on 2018/8/22.
 */
public interface IUploadService {
    Map uploadImg(MultipartFile file);
}
