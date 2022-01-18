package com.yjsh.dto.resp;


import com.yjsh.entity.User;
import lombok.Data;


@Data
public class UserVo {

    private String msg;
    private int cod;

    private User loginUser;
}
