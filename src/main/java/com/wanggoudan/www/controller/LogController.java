package com.wanggoudan.www.controller;

import com.wanggoudan.www.baseconfig.ReturnMessage;
import com.wanggoudan.www.entity.Log;
import com.wanggoudan.www.repository.ILogRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("log")
public class LogController {
    @Resource
    private ILogRepository iLogRepository;
    @RequestMapping("/add")
    @ResponseBody
    public ReturnMessage<Log> add(String value){
        Log log=new Log();
        log.setValue(value);
        log = iLogRepository.save(log);
        return ReturnMessage.success(1,log);
    }
    @RequestMapping("/findAll")
    @ResponseBody
    public ReturnMessage<List<Log>> findAll(){
        List<Log> all = iLogRepository.findAll();
        return ReturnMessage.success(all.size(),all);
    }
}
