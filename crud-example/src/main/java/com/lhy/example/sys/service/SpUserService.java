package com.lhy.example.sys.service;

import com.lhy.common.web.service.SimpleService;
import com.lhy.example.sys.entity.SpUser;
import com.lhy.example.sys.mapper.SpUserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author hyluan
 * @since 2018-08-17
 */
@Service
public class SpUserService extends SimpleService<SpUserMapper, SpUser> {

    @Transactional(rollbackFor = Exception.class)
    public void testTransactional(){
//        SpUser byId = this.getById(1);
//        this.removeById(byId.getId());
//        Assert.isTrue(false,"异常了");
//        SpUser byId1 = this.getById(byId.getId());
    }

}
