package com.snkj.www.controller;

import com.snkj.www.baseconfig.BasePage;
import com.snkj.www.baseconfig.ReturnMessage;
import com.snkj.www.entity.ContractEntity;
import com.snkj.www.service.IContractService;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Danny on 2018/8/21.
 */
@RestController
@RequestMapping("contract")
public class ContractController {
    @Resource
    private IContractService iContractService;

    @RequestMapping("page")
    @ResponseBody
    public ReturnMessage<List<ContractEntity>> page(BasePage basePage, String companyName , String title) {
        Page<ContractEntity> p = iContractService.page(basePage,companyName,title);
        return ReturnMessage.success((int) p.getTotalElements(), p.getContent());
    }

    @RequestMapping("save")
    @ResponseBody
    public ReturnMessage<ContractEntity> save(String content, String companyName, String contactMan, String phone, String fax, String saleman, Double amount, @DateTimeFormat(pattern = "yyyy-MM-dd") Date time, String payMethod, String title, String remarks) {
        ContractEntity c = iContractService.save(content, companyName, contactMan, phone, fax, saleman, amount, time, payMethod, title, remarks);
        if (c != null) {
            return ReturnMessage.success(0, c);
        } else {
            return ReturnMessage.failed("保存失败");
        }
    }

    @RequestMapping("update")
    @ResponseBody
    public ReturnMessage<ContractEntity> update(Integer id, String content, String companyName, String contactMan, String phone, String fax, String saleman, Double amount, @DateTimeFormat(pattern = "yyyy-MM-dd") Date time, String payMethod, String title, String remarks) {
        ContractEntity c = iContractService.update(id, content, companyName, contactMan, phone, fax, saleman, amount, time, payMethod, title, remarks);
        if (c != null) {
            return ReturnMessage.success(0, c);
        } else {
            return ReturnMessage.failed("修改失败");
        }
    }

    @RequestMapping("detail")
    @ResponseBody
    public ReturnMessage<ContractEntity> detail(Integer id) {
        ContractEntity c = iContractService.findById(id);
        return ReturnMessage.success(1, c);
    }

    @RequestMapping("delete")
    @ResponseBody
    public ReturnMessage delete(@RequestParam("id") List<Integer> ids) {
        Iterator it = ids.iterator();
        while (it.hasNext()) {
            Integer id = (Integer) it.next();
            iContractService.delete(id);
        }
        return ReturnMessage.success();
    }

    /*@RequestMapping("select")
    @ResponseBody
    public ReturnMessage<List<ContractEntity>> select(String companyName) {


        List<ContractEntity> c = iContractService.findAllByNameLike(companyName);
        return ReturnMessage.success(1, c);
    }*/


}
