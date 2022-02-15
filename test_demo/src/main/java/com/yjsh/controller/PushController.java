package com.yjsh.controller;


import com.yjsh.constant.R;
import com.yjsh.dto.req.MessageDTO;
import com.yjsh.mapper.MessageDao;
import com.yjsh.model.Message;
import com.yjsh.server.WebSocketServer;
import com.yjsh.service.PushService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 处理推送问题
 * 指定用户的id
 * 指定推送的内容
 * 主动给前端数据
 */
@CrossOrigin(origins = "http://192.168.86.154:8086")
@RestController
@RequestMapping("push")
public class PushController {

    @Resource
    private WebSocketServer webSocketServer;

    @Resource
    private PushService pushService;

    @Resource
    private MessageDao messageDao;

    @PostMapping("send")
    public R send(@RequestBody MessageDTO messageDTO){
        Long uid=messageDTO.getUid();
        String message=messageDTO.getMessage();
        Integer type=messageDTO.getType();
        String requestId=messageDTO.getRequestId();
        //判断这个requestId是否在数据库中
        //如果在，就直接返回，请求成功
        //如果不在，再真的处理它。
//        if(pushService.isIdem(requestId)){
//            return R.ok("请求成功");
//        }


        boolean result= webSocketServer.sendInfo(uid+"",message);
        Message msg=new Message();
        msg.setUid(uid);
        msg.setContent(message);
        msg.setType(type);
        if(result){
            msg.setState(1);
            pushService.saveMessage(msg,requestId);
            return R.ok(200,"推送成功");
        }else{
            msg.setState(0);
            pushService.saveMessage(msg,requestId);
            return R.ok(201,"用户离线，推送任务已经设置");
        }
    }
}
