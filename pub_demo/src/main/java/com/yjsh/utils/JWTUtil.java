package com.yjsh.utils;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.utils.StringUtils;
import com.yjsh.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * JWT的工具类
 */
public class JWTUtil {
    /**
     * token的有效期
     */
    public static final int TOKEN_EXPIRE=1000*60*60*24*2;
    /**
     * token加密算法的盐
     */
    public static final String TOKEN_KEY="makabaka";

    public static final String TOKEN="token";

    /**
     * 生成一个token
     * @param nickname 昵称
     * @param user 用户对象
     * @return token
     */
    public static String generatorToken(String nickname,Object user){
        //产生token
        String token= Jwts.builder()
                //设置用户昵称
                .setSubject(nickname)
                //设置token的当前时间
                .setIssuedAt(new Date())
                //设置token的过期时间
                .setExpiration(new Date(System.currentTimeMillis()+TOKEN_EXPIRE))
                //设置携带信息的对象
                .claim("user", JSONObject.toJSONString(user))
                //设置加密方式
                .signWith(SignatureAlgorithm.HS256,TOKEN_KEY.getBytes())
                //生成token
                .compact();
        return token;
    }

    /**
     * 解析token的方法
     * @param token 用户保存的token
     * @return 取出来token中的user对象
     */
    public static User checkToken(String token){
        Claims body=null;
        if (StringUtils.isEmpty(token)){
            return null;
        }
        try{
            body= Jwts.parser().setSigningKey(TOKEN_KEY.getBytes()).parseClaimsJws(token).getBody();
        }catch(Exception e){
            //token有问题
            //1、token过期了
            //2、token被人篡改过
            e.printStackTrace();
            return null;
        }
        //取出原本放进token中的字符串
        Object userJSONString=body.get("user");
        //把json字符串还原为对象
        User user= JSONObject.parseObject((String)userJSONString, User.class);
        return user;
    }


//    public static void main(String[] args) {
//        //模拟数据库查询出来的对象
//        User userVO=new User();
//        userVO.setName("zhangsan");
//        userVO.setPassword("123456");
//        userVO.setRoleType("qqqqqqqqqqq");
//        //根据这个对象，生成token
//        String token = generatorToken(userVO.getName(), userVO);
//        //这个token 如果是Android iOS需要存到数据库SQLite里
//        //如果是html5网页前端，就要存到localStorage里
//        System.out.println(token);
//        //我们需要知道token对应的用户是谁
//        //于是需要token解析 测试一下git提交的名字啊
//
//        token = "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2Mzg0NTc3NTQsImV4cCI6MTYzODQ2NDk1NCwidXNlciI6IntcImVtYWlsXCI6XCJ4eHh4eHhcIixcImlkXCI6MTM4LFwicGhvbmVcIjpcIjEzMTMxMzEzMTNcIixcInJvbGVJZFwiOjEsXCJyb2xlVHlwZVwiOlwi566h55CG5ZGYXCIsXCJzdGF0dXNcIjoxLFwidXNlck5hbWVcIjpcIuaut-mHkeW4hVwifSJ9.ZxuNNAfhcUiphsNJKvtdm-jCZwvgRzX6J4JhPuA6pIM";
//        System.out.println(checkToken(token));
//    }

}
