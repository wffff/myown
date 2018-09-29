package com.snkj.www.repository;

import com.snkj.www.baseconfig.BasePage;
import com.snkj.www.entity.ContractEntity;
import org.springframework.data.domain.Page;

public interface IContractRepositoryCustom {
    Page<ContractEntity> page(BasePage basePage,String companyName ,String title);
}
