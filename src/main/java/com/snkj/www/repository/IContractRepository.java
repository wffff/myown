package com.snkj.www.repository;

import com.snkj.www.baseconfig.BaseRepository;
import com.snkj.www.entity.ContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Danny on 2018/8/21.
 */
public interface IContractRepository extends BaseRepository<ContractEntity, Integer>,IContractRepositoryCustom{
    //List<ContractEntity> findAllByCompanyNameLike(String s);


    //@Query("select c from ContractEntity c where c.del=false ")
    //Page<ContractEntity> findAll(Pageable pageable);

    //Page<ContractEntity> findAllByDelFalse(Pageable pageable);
}
