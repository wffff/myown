package com.snkj.www.controller;

import com.snkj.www.baseconfig.ReturnMessage;
import com.snkj.www.entity.AttachmentEntity;
import com.snkj.www.service.IAttachmentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Danny on 2018/8/21.
 */
@RestController
@RequestMapping("attachment")
public class AttachmentController {
    @Resource
    private IAttachmentService iAttachmentService;

    @RequestMapping("byId")
    @ResponseBody
    public ReturnMessage<List<AttachmentEntity>> findById(Integer id) {
        List<AttachmentEntity> a = iAttachmentService.findAllByContractId(id);
        return ReturnMessage.success(a.size(), a);
    }


    @RequestMapping("save")
    @ResponseBody
    public ReturnMessage<AttachmentEntity> save(String type,String content,String url,Integer contractId) {
        AttachmentEntity a = iAttachmentService.save(type,content,url,contractId);
        return ReturnMessage.success(0, a);
    }


    @RequestMapping("delete")
    @ResponseBody
    public ReturnMessage delete(@RequestParam("id") List<Integer> ids) {
        Iterator it = ids.iterator();
        while (it.hasNext()) {
            Integer id = (Integer) it.next();
            iAttachmentService.delete(id);
        }
        return ReturnMessage.success();
    }

}
