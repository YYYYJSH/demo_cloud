package com.yjsh.service;


import com.yjsh.model.Message;

/**
 * 网页推送模块的方法定义
 * @author xuzhong
 */
public interface PushService {
    /**
     * 保存一个消息
     * @param message 推送的内容
     * @param requestId 保持幂等的请求id
     */
    int saveMessage(Message message, String requestId);

    /**
     * 查询requestId是否存在
     * @param requestId 保持幂等的请求id
     */
    boolean isIdem(String requestId);

}
