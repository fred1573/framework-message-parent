package com.tomasky.msp.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * 邮件消息
 * Created by 番茄桑 on 2015/5/11.
 */
@Document(collection = "message_mail")
public class MailMessage extends Message{
    //邮件标题
    private String subject;
    //附件
    private List<String> attachments;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<String> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<String> attachments) {
        this.attachments = attachments;
    }
}
