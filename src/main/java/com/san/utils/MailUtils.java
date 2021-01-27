package com.san.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

/**
 * @Auther: Gxyx
 * @Date: 2020/12/11/19:04
 * 邮件发送工具类
 */
@Component
public class MailUtils {
    @Autowired
    private JavaMailSenderImpl mailSender;
    @Value("${spring.mail.username}")
    private String mailfrom;

    // 发送简单邮件
    public void sendSimpleEmail(String mailto, String title, String content) {
        //  定制邮件发送内容
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailfrom);
        message.setTo(mailto);
        message.setSubject(title);
        message.setText(content);
        // 发送邮件
        mailSender.send(message);
    }
}
