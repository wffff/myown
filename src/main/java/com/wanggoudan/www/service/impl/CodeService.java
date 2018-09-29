package com.wanggoudan.www.service.impl;

import com.wanggoudan.www.baseconfig.BasePage;
import com.wanggoudan.www.baseconfig.util.SecurityUserUtils;
import com.wanggoudan.www.entity.CodeEntity;
import com.wanggoudan.www.repository.ICodeRepository;
import com.wanggoudan.www.service.ICodeService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Danny on 2018/8/21.
 */
@Service
public class CodeService implements ICodeService {

    @Resource
    private ICodeRepository iCodeRepository;

    @Override
    public Page<CodeEntity> page(BasePage basePage,String title) {
        return iCodeRepository.page(basePage,title);
    }

    @Override
    public CodeEntity save(String title, String content) {
        CodeEntity c = new CodeEntity();
        c.setTitle(title);
        c.setContent(content);
        c.setUserId(SecurityUserUtils.getUserId());
        return iCodeRepository.save(c);
    }

    @Override
    public CodeEntity update(Integer id, String title, String content) {
        CodeEntity c;
        c = iCodeRepository.findOne(id);
        c.setTitle(title);
        c.setContent(content);
        return iCodeRepository.update(c);
    }

    @Override
    public CodeEntity findById(Integer id) {
        return iCodeRepository.findOne(id);
    }

    public void delete(Integer id) {
        iCodeRepository.remove(id);
    }

   /* @Override
    public List<ContractEntity> findAllByNameLike(String key) {
        return iContractRepository.findAllByCompanyNameLike("%" + key + "%");
    }
*/
}
