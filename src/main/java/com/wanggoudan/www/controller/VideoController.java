package com.wanggoudan.www.controller;

import com.wanggoudan.www.baseconfig.BasePage;
import com.wanggoudan.www.baseconfig.ReturnMessage;
import com.wanggoudan.www.baseconfig.util.RegexUtils;
import com.wanggoudan.www.entity.MangaEntity;
import com.wanggoudan.www.entity.VideoEntity;
import com.wanggoudan.www.service.IUploadService;
import com.wanggoudan.www.service.IVideoService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("video")
public class VideoController {
    @Resource
    private IVideoService iVideoService;
    @Resource
    private IUploadService iUploadService;

    @RequestMapping("main")
    public String main() {
        return "video/main";
    }

    @RequestMapping("add")
    public String add() {
        return "video/upvideo";
    }

    @RequestMapping("page")
    @ResponseBody
    public ReturnMessage<List<VideoEntity>> page(BasePage basePage) {
        Page<VideoEntity> page = iVideoService.page(basePage);
        return ReturnMessage.success((int) page.getTotalElements(), page.getContent());

    }

    @RequestMapping("up")
    @ResponseBody
    public ReturnMessage<Map> changeAvatar(MultipartFile file) {
        Map m = iUploadService.uploadImg(file);
        return ReturnMessage.success(0,m);
    }

    @RequestMapping("save")
    @ResponseBody
    public ReturnMessage<VideoEntity> save(String title, String url,String address) {
        if (RegexUtils.notNull(address)&&!RegexUtils.notNull(url)){
            url=address;
        }
        VideoEntity save = iVideoService.save(title, url);
        return ReturnMessage.success("保存成功");
    }

    @RequestMapping("search")
    public String page(Model model, Integer id) {
        VideoEntity videoEntity = iVideoService.findOne(id);
        model.addAttribute("video", videoEntity);
        return "video/search";
    }
}
