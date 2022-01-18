package com.yjsh.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * tghy_user
 * @author 
 */
@Data
@TableName("tghy_user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable{
    private Integer id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色id1.管理员2.开发,3.测试
     */
    private Integer roleId;

    /**
     * 角色类型名
     */
    private String roleType;

    /**
     * 电话
     */
    private String phone;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 真实姓名
     */
    private String name;

    /**
     * 状态  1.有效 2.  无效
     */
    private Integer status;

    private Date createTime;

    private Date updateTime;

    /**
     * 关联的项目id
     */
    private Integer relationProjectId;

    /**
     * 项目名称
     */
    private String projectName;

    private static final long serialVersionUID = 1L;
}