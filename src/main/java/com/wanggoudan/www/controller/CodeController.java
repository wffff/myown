package com.wanggoudan.www.controller;

import com.wanggoudan.www.baseconfig.BasePage;
import com.wanggoudan.www.baseconfig.ReturnMessage;
import com.wanggoudan.www.baseconfig.util.RegexUtils;
import com.wanggoudan.www.entity.CodeEntity;
import com.wanggoudan.www.service.ICodeService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Danny on 2018/8/21.
 */
@Controller
@RequestMapping("code")
public class CodeController {
    @Resource
    private ICodeService iCodeService;

    @RequestMapping("page")
    @ResponseBody
    public ReturnMessage<List<CodeEntity>> page(BasePage basePage, String title) {
        Page<CodeEntity> p = iCodeService.page(basePage, title);
        return ReturnMessage.success((int) p.getTotalElements(), p.getContent());
    }

    @RequestMapping("save")
    @ResponseBody
    public ReturnMessage<CodeEntity> save(String title, String content) {
        if (!RegexUtils.notNull(title)||!RegexUtils.notNull(content)){
            return ReturnMessage.failed("请输入标题或正文");
        }
        CodeEntity c = iCodeService.save(title, content);
        if (c != null) {
            return ReturnMessage.success(0, c);
        } else {
            return ReturnMessage.failed("保存失败");
        }
    }

    @RequestMapping("update")
    @ResponseBody
    public ReturnMessage<CodeEntity> update(Integer id, String title, String content) {
        if (!RegexUtils.notNull(title)||!RegexUtils.notNull(content)){
            return ReturnMessage.failed("请输入标题或正文");
        }
        CodeEntity c = iCodeService.update(id, title, content);
        if (c != null) {
            return ReturnMessage.success(0, c);
        } else {
            return ReturnMessage.failed("修改失败");
        }
    }

    @RequestMapping("detail")
    @ResponseBody
    public ReturnMessage<CodeEntity> detail(Integer id) {
        CodeEntity c = iCodeService.findById(id);
        return ReturnMessage.success(1, c);
    }

    @RequestMapping("delete")
    @ResponseBody
    public ReturnMessage delete(Integer id) {
        iCodeService.delete(id);
        return ReturnMessage.success();
    }

}
