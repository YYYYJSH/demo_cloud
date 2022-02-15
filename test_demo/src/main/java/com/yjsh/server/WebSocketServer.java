package com.yjsh.server;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yjsh.mapper.MessageDao;
import com.yjsh.model.Message;
import com.yjsh.model.MessageExample;
import com.yjsh.utils.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ServerEndpoint("/webSocket/{uid}")
@Component
@Slf4j
//@ConditionalOnClass(value = WebSocketConfig.class)
public class WebSocketServer {

    @Resource
    private MessageDao messageDao;
    /**
     * 统计所有的和服务器连接的用户的数量
     * 基于int基本数据类型，在多线程并发情况下封装的原子类
     */
    private static AtomicInteger onlineNum = new AtomicInteger();

    public static void addOnlineCount() {
        //安全自增
        onlineNum.incrementAndGet();
    }

    public static void subOnlineCount() {
        //安全自减
        onlineNum.decrementAndGet();
    }


    /**
     * 保存已经和用户连接的所有会话
     * 根据用户的id保存这些会话
     * 使用map数据结构来维护
     * 又因为它是一个多线程的场景
     * 所以采用ConcurrentHashMap类型。
     */
    private static ConcurrentHashMap<String,Session> sessionPool=new ConcurrentHashMap<>();
    /**
     * @param session
     * @param uid
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "uid") String uid) {
        //把刚连接的session保存至集合
        sessionPool.put(uid,session);
        //在线人数+1
        addOnlineCount();

        //查数据库，找出uid对应的未推送的消息，一次性推送给前端。
        if(messageDao==null){
            messageDao= SpringContextUtil.getBean(MessageDao.class);
        }

        //查询数据库
        //XXXXExample是样例对象。
        MessageExample example=new MessageExample();
        //样例的条件对象
        MessageExample.Criteria criteria = example.createCriteria();
        //添加uid的条件
        criteria.andUidEqualTo(Long.parseLong(uid));
        //添加state的条件
        criteria.andStateEqualTo(0);
        //设置排序规则
        example.setOrderByClause("create_time");
        //执行查询
//        List<Message> messages = messageDao.selectByExample(example);
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Message::getUid,uid).eq(Message::getState,0);
        queryWrapper.orderByDesc("create_time");
        List<Message> messages = messageDao.selectList(queryWrapper);
        //推送
        //List<Message>  ->  List<JSONObject>
        List<JSONObject> msgs=new ArrayList<>();
        for (int i = 0; i < messages.size(); i++) {
            Message message=messages.get(i);
            JSONObject msg=new JSONObject();
            msg.put("uid",message.getUid());
            msg.put("type",message.getType());
            msg.put("content",message.getContent());
            msgs.add(msg);
        }
        //整个是一个报文，推送至前端
        JSONObject initInfo=new JSONObject();
        initInfo.put("init",true);
        initInfo.put("message",msgs);

        sendMessage(session,JSONObject.toJSONString(initInfo));
        //更新数据库message的state为1
        Message msg=new Message();
        msg.setState(1);
//        messageDao.updateByExampleSelective(msg,example);
        UpdateWrapper<Message> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().eq(Message::getUid,uid).eq(Message::getState,0).set(Message::getState,1);
        messageDao.update(new Message(),updateWrapper);
    }

    @OnMessage
    public void onMessage(String message) {
        log.info(message);
    }

    @OnClose
    public void onClose(@PathParam(value = "uid") String uid) {
        //从集合中删除会话
        sessionPool.remove(uid);
        //在线人数-1
        subOnlineCount();
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
    }


    /**
     * 根据session发送消息
     * @param session 和客户端的会话
     * @param message 给这个会话写入的信息
     */
    private void sendMessage(Session session,String message){
        if(session!=null){
            synchronized (session){
                try {
                    session.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 根据用户id发送消息的方法
     * @param uid 用户id
     * @param message 要发送给前端的消息内容
     * @return 发送是否成功
     */
    public boolean sendInfo(String uid,String message){
        Session session=sessionPool.get(uid);
        if(session==null){
            return false;
        }
        sendMessage(session,message);
        return true;
    }

}
