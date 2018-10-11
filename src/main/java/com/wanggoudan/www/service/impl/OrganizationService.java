package com.wanggoudan.www.service.impl;

import com.wanggoudan.www.baseconfig.util.RegexUtils;
import com.wanggoudan.www.entity.OrganizationEntity;
import com.wanggoudan.www.enums.OrganizationTypeEnum;
import com.wanggoudan.www.repository.IOrganizationNativeSqlRepository;
import com.wanggoudan.www.repository.IOrganizationRepository;
import com.wanggoudan.www.service.IOrganizationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Danny on 2018/7/13.
 */
@Service
public class OrganizationService implements IOrganizationService {
    @Resource
    private IOrganizationNativeSqlRepository iOrganizationNativeSqlRepository;
    @Resource
    private IOrganizationRepository iOrganizationRepository;

    @Override
    public List<Integer> listIdsByConditions(Integer id, boolean up, boolean self, boolean recursive) {
        return iOrganizationNativeSqlRepository.listIdsByConditions(id, up, self, recursive);
    }

    @Override
    public List<Integer> listOrgTree(Integer id) {
        List<Integer> integers = iOrganizationNativeSqlRepository.listIdsByConditions(id, true, false, true);
        List<Integer> integers1 = iOrganizationNativeSqlRepository.listIdsByConditions(id, false, true, true);
        for (Integer i:integers1){
            integers.add(i);
        }
        return integers;
    }

    @Override
    public List<OrganizationEntity> listOrgByConditions(Integer id, boolean up, boolean self, boolean recursive) {
        List<Integer> integers = listIdsByConditions(id, up, self, recursive);
        List<OrganizationEntity> all = iOrganizationRepository.findAll(integers);
        return all;
    }

    @Override
    public List<OrganizationEntity> listDevice(List<Integer> integers) {
        return iOrganizationRepository.listDevice(integers);
    }

    @Override
    public List<OrganizationEntity> findAll() {
        return iOrganizationRepository.findAllByDelFalse();
    }

    @Override
    public OrganizationEntity save(Integer pid, OrganizationTypeEnum organizationType, String name, String deviceName) {
        OrganizationEntity org = new OrganizationEntity(pid, organizationType, name, deviceName);
        return iOrganizationRepository.save(org);
    }

    @Override
    public boolean remove(Integer id) {
        List<Integer> ids = iOrganizationNativeSqlRepository.listIdsByConditions(id, false, true, true);
        return iOrganizationRepository.removeAll(ids);
    }

    @Override
    public OrganizationEntity update(Integer id, String name, String deviceName) {
        OrganizationEntity one = iOrganizationRepository.findOne(id);
        if (RegexUtils.notNull(name)) {
            one.setName(name);
        }
        if (deviceName==""){
            one.setDeviceName(null);
        }
        one.setDeviceName(deviceName);
        return iOrganizationRepository.update(one);
    }
}
