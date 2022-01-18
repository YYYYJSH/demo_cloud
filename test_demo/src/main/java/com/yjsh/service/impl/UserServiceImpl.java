package com.yjsh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjsh.dto.resp.UserVo;
import com.yjsh.entity.User;
import com.yjsh.feign.UserFeign;
import com.yjsh.mapper.UserDao;
import com.yjsh.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 殷金帅
 * @date 2021-12-15 10:16
 **/
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userMapper;


    @Override
    public UserVo login(User param) {
        UserVo vo = new UserVo();
        if (StringUtils.isEmpty(param.getUserName()) || StringUtils.isEmpty(param.getPassword())) {
            vo.setCod(-1);
            vo.setMsg("用户名和密码不能为空,请重新登录");
        } else {
            //查询用户是否存在
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(User::getUserName,param.getUserName());
            List<User> users = userMapper.selectList(queryWrapper);
            if (null == users || users.size() == 0) {
                vo.setCod(501);
                vo.setMsg("登录失败:用户不存在");
                return vo;
            }
            User user = users.get(0);
            if (!users.get(0).getPassword().equals(param.getPassword())) {
                vo.setCod(502);
                vo.setMsg("登陆失败:用户名或密码错误");
                return vo;
            }
            user.setPassword(null);
            vo.setCod(200);
            vo.setMsg("登录成功");
            vo.setLoginUser(user);
        }
        return vo;
    }

    @Override
    public IPage<User> queryByNameOrPhone(String name,String phone,Integer page,Integer limit) {
        Page<User> pageInfo = new Page<>(page, limit);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(phone)){
            queryWrapper.lambda().eq(User::getPhone,phone);
        }
        if (StringUtils.isNotEmpty(name)){
            queryWrapper.lambda().like(User::getName,name);
        }



        return userMapper.selectPage(pageInfo, queryWrapper);
    }
}
