package com.snkj.www.service.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.snkj.www.baseconfig.BasePage;
import com.snkj.www.baseconfig.ReturnMessage;
import com.snkj.www.baseconfig.constant.MongoConstants;
import com.snkj.www.baseconfig.util.SecurityUserUtils;
import com.snkj.www.entity.OrganizationEntity;
import com.snkj.www.service.IDeviceService;
import com.snkj.www.service.IOrganizationService;
import org.bson.Document;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceService implements IDeviceService {
    @Resource
    private IOrganizationService iOrganizationService;

    @Override
    public ReturnMessage<List<Document>> findAllDevice1(BasePage basePage,String collectName) {
        //最后查询结果的集合
        List<Document> list = new ArrayList<>();
        //连接MONGODB
        MongoClient mongoClient = new MongoClient(MongoConstants.HOST, MongoConstants.PORT);
        MongoDatabase db = mongoClient.getDatabase(MongoConstants.DATABASENAME);
        MongoCollection<Document> collection = db.getCollection(collectName);

        //查询条件
        if (SecurityUserUtils.isAdmin()) {
            long count = collection.count();
            FindIterable<Document> documents = collection.find().skip((basePage.getPage() - 1) * basePage.getLimit()).limit(basePage.getLimit()).sort(new BasicDBObject(SortProConvert(basePage.getSortProperty()), SortConvert(basePage.getSortType())));
            for (Document document : documents) {
                list.add(document);
            }
            mongoClient.close();
            return ReturnMessage.success((int) count, list);
        } else {
            Integer organizationId = SecurityUserUtils.getSecurityUser().getOrganizationId();
            if (null == organizationId) {
                return ReturnMessage.success();
            }
            List<Integer> integers = iOrganizationService.listIdsByConditions(organizationId, false, true, true);
            List<OrganizationEntity> device = iOrganizationService.listDevice(integers);
            List<String> name = new ArrayList<>();
            for (OrganizationEntity or : device) {
                if (null != or.getDeviceName()) {
                    name.add(or.getDeviceName());
                }
            }
            long count = collection.count(Filters.and(Filters.in("name", name)));

            FindIterable<Document> documents = collection.find(Filters.and(Filters.in("name", name))).skip((basePage.getPage() - 1) * basePage.getLimit()).limit(basePage.getLimit()).sort(new BasicDBObject(SortProConvert(basePage.getSortProperty()), SortConvert(basePage.getSortType())));
            for (Document document : documents) {
                list.add(document);
            }
            mongoClient.close();
            return ReturnMessage.success((int) count, list);
        }
    }

    private int SortConvert(Sort.Direction sortType) {
        if (null==sortType){
            return -1;
        }
        if (sortType.equals(Sort.Direction.DESC)){
            return -1;
        }else if (sortType.equals(Sort.Direction.ASC)){
            return 1;
        }
        return -1;
    }
    private String SortProConvert(String field) {
        if (null==field){
            return "_id";
        }
        return field;
    }
}
