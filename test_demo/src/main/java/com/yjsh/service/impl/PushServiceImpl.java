package com.yjsh.service.impl;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.yjsh.mapper.IdemDao;
import com.yjsh.mapper.MessageDao;
import com.yjsh.model.Idem;
import com.yjsh.model.IdemExample;
import com.yjsh.model.Message;
import com.yjsh.service.PushService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@DS("talk")
public class PushServiceImpl implements PushService {

    @Resource
    private MessageDao messageDao;

    @Resource
    private IdemDao idemDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int saveMessage(Message message, String requestId) {
        Idem idem=new Idem();
        idem.setRequestid(requestId);
//        idemDao.insert(idem);
        return messageDao.insert(message);
    }

    @Override
    public boolean isIdem(String requestId) {
        IdemExample example=new IdemExample();
        IdemExample.Criteria criteria = example.createCriteria();
        criteria.andRequestidEqualTo(requestId);
        List<Idem> idems = idemDao.selectByExample(example);
        if(idems!=null&&idems.size()>0){
            return true;
        }
        return false;
    }
}
