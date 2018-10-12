package com.wanggoudan.www.controller;

import com.wanggoudan.www.baseconfig.BasePage;
import com.wanggoudan.www.baseconfig.ReturnMessage;
import com.wanggoudan.www.entity.MangaEntity;
import com.wanggoudan.www.service.IMangaService;
import com.wanggoudan.www.service.IUploadService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("manga")
public class MangaController {
    @Resource
    private IMangaService iMangaService;
    @Resource
    private IUploadService iUploadService;

    @RequestMapping("main")
    public String main() {
        return "manga/main";
    }

    @RequestMapping("add")
    public String add() {
        return "manga/upmanga";
    }

    @RequestMapping("up/img")
    @ResponseBody
    public ReturnMessage changeAvatar(MultipartFile file) {
        Map m = iUploadService.uploadImg(file);
        return ReturnMessage.success(((String) m.get("url")));
    }

    @RequestMapping("up/img/group")
    @ResponseBody
    public ReturnMessage changeAvatar(MultipartFile[] file) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < file.length; i++) {
            Map m = iUploadService.uploadImg(file[i]);
            list.add(((String) m.get("url")));
        }
        return ReturnMessage.success(list.size(), list);
    }

    @RequestMapping("save")
    @ResponseBody
    public ReturnMessage<MangaEntity> save(String title,String cover,@RequestParam("content[]") List<String> content){
        MangaEntity save = iMangaService.save(title, cover, content);

        return ReturnMessage.success("保存成功");
    }

    @RequestMapping("page")
    @ResponseBody
    public ReturnMessage<List<MangaEntity>> page(BasePage basePage) {
        Page<MangaEntity> page = iMangaService.findAll(basePage);
        return ReturnMessage.success((int) page.getTotalElements(), page.getContent());
    }

    @RequestMapping("search")
    public String page(Model model, Integer id) {
        MangaEntity mangaEntity = iMangaService.findOne(id);
        model.addAttribute("manga", mangaEntity);
        return "manga/search";
    }

}
