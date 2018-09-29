package com.snkj.www.controller;

import com.snkj.www.baseconfig.BasePage;
import com.snkj.www.baseconfig.ReturnMessage;
import com.snkj.www.baseconfig.constant.MongoConstants;
import com.snkj.www.service.IDeviceService;
import com.snkj.www.service.IOrganizationService;
import org.bson.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("device")
public class DeviceController {
    @Resource
    private IDeviceService iDeviceService;

    @RequestMapping("page")
    @ResponseBody
    public ReturnMessage<List<Document>> page(BasePage basePage) {
        return iDeviceService.findAllDevice1(basePage, MongoConstants.DEVICE1);
    }

}
