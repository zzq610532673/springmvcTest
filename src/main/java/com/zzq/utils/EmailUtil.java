package com.zzq.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;

@Component
public class EmailUtil {
    static {
        System.setProperty("mail.mime.splitlongparameters", "false");
        System.setProperty("mail.mime.charset", "UTF-8");
    }
    
    private static final Logger logger = LoggerFactory.getLogger(EmailUtil.class);
    
    @Resource
    private JavaMailSender javaMailSender;
    @Resource
    private SimpleMailMessage simpleMailMessage;

    /**
     * 发送普通文本
     * @param subject
     * @param content
     * @param toMail
     */
    public void sendSimpleMail(String subject, String content, String toMail) {
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        simpleMailMessage.setTo(toMail);
        javaMailSender.send(simpleMailMessage);
        logger.info("邮件发送成功。。。。");
    }

    /**
     * 
     * @param subject
     * @param content
     * @param toMail
     */
    public void sendHtmlMail(String subject,String content,String toMail){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        try {
            mimeMessageHelper.setFrom("610532673@qq.com");
            mimeMessageHelper.setTo(toMail);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText("<html><head></head><body><h1>"+content+"</h1></body></html>",true);
            javaMailSender.send(mimeMessage);
            logger.info("邮件发送成功");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendPictureMail(String subject, String content, String toMail, String picturePath) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom("610532673@qq.com");
            mimeMessageHelper.setTo(toMail);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content);
            mimeMessageHelper.addInline("pantyHuose",new File(picturePath));
            javaMailSender.send(mimeMessage);
            /*//准备正文
            MimeBodyPart text = new MimeBodyPart();
            text.setContent("context",content);
            //准备图片
            MimeBodyPart img = new MimeBodyPart();
            DataHandler dh = new DataHandler(new FileDataSource(picturePath));
            img.setDataHandler(dh);
            img.setContentID("pantyHouse");
            //描述数据关系
            MimeMultipart mp = new MimeMultipart();
            mp.addBodyPart(text);
            mimeMessage.setContent(mp);*/
            logger.info("邮件发送成功");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
