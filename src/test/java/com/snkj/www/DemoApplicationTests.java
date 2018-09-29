package com.snkj.www;

import com.snkj.www.entity.OrganizationEntity;
import com.snkj.www.service.IOrganizationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Resource
    private IOrganizationService iOrganizationService;
    @Test
    public void contextLoads() {
        Integer id=1;
        List<Integer> integers = iOrganizationService.listIdsByConditions(id, false, true, true);
        for (OrganizationEntity organizationEntity : iOrganizationService.listDevice(integers)) {
            System.out.println(organizationEntity.getDeviceName());
        }
    }
    @Test
    public  void test(){

    }

}
