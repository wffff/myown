package com.snkj.www.service;

import com.snkj.www.baseconfig.BasePage;
import com.snkj.www.baseconfig.ReturnMessage;
import org.bson.Document;

import java.util.List;

public interface IDeviceService {
    ReturnMessage<List<Document>> findAllDevice1(BasePage basePage,String collectName);
}
