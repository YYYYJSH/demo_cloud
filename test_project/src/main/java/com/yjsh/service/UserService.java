package com.yjsh.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yjsh.dto.resp.UserVo;
import com.yjsh.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户服务
 *
 * @author YYYYJSH
 * @date 2021/12/15
 */
@Component
@RequestMapping("/userFeign")
public interface UserService {

    /**
     * 登录
     *
     * @param param 参数
     * @return {@link UserVo}
     */
    public UserVo login(User param);

    /**
     * 查询的名字或电话
     *
     * @param name  名字
     * @param phone 电话
     * @param page  页面
     * @param limit 限制
     * @return {@link Object}
     */
    @GetMapping("/query")
    public IPage<User> queryByNameOrPhone(@RequestParam("name") String name, @RequestParam("phone") String phone,
                                          @RequestParam("page")Integer page, @RequestParam("limit")Integer limit);
}
