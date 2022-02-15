package com.yjsh.dto.req;

public class MessageDTO {
    private Long uid;
    private String message;
    private Integer type;
    private String requestId;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "uid=" + uid +
                ", message='" + message + '\'' +
                ", type=" + type +
                ", requestId='" + requestId + '\'' +
                '}';
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
