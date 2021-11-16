package com.zzq.utils;

import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

@Component
public class MailUtil {
    public String mailFrom = "610532673@qq.com";
    public String password="bzcfowneofbzbfgb";
    public String emailSMTAddress = "smtp.qq.com";
    public String mailTo = "1411581508@qq.com";
    
    public void send(){
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", emailSMTAddress);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");

        Session session = Session.getInstance(props);
        session.setDebug(true);
        try {
            //创建一封邮件
            MimeMessage message = createMimeMessage(session, mailFrom, mailTo);

            Transport transport = session.getTransport();
            transport.connect(mailFrom, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    private MimeMessage createMimeMessage(Session session, String mailFrom, String mailTo) {

        try {
            //创建邮件对象
            MimeMessage message = new MimeMessage(session);
            //收件人
            message.setFrom(new InternetAddress(mailFrom, "我的测试邮件_发件人昵称", "UTF-8"));
            //发件人
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailTo, "我的测试邮件_收件人昵称", "UTF-8"));
            //邮件主题
            message.setSubject("丝袜美女");
            /*
            邮件内容
             */
            //创建图片节点
            MimeBodyPart img = new MimeBodyPart();
            DataHandler handler = new DataHandler(new FileDataSource("D:\\zzqsucai\\img\\pic 1.png"));
            img.setDataHandler(handler);
            img.setContentID("pantyImg");

            //创建文本节点
            MimeBodyPart text = new MimeBodyPart();
            text.setContent("这是一张图片<br/><img src='cid:pantyImg'/>", "text/html;charset=UTF-8");

            //文本图片混合节点
            MimeMultipart text_img = new MimeMultipart();
            text_img.addBodyPart(text);
            text_img.addBodyPart(img);
            text_img.setSubType("related");

            //将文本图片混合节点封装为一个普通节点
            MimeBodyPart body_text_img = new MimeBodyPart();
            body_text_img.setContent(text_img);

            //创建附件节点
            MimeBodyPart attachment = new MimeBodyPart();
            DataHandler handler1 = new DataHandler(new FileDataSource("D:\\zzq1.txt"));
            attachment.setDataHandler(handler1);
            attachment.setFileName(MimeUtility.encodeText(handler1.getName()));

            //文本+图片+附件 混合节点
            MimeMultipart text_img_att = new MimeMultipart();
            text_img_att.addBodyPart(body_text_img);
            text_img_att.addBodyPart(attachment);
            text_img_att.setSubType("mixed");

            message.setContent(text_img_att);
            message.saveChanges();
            return message;

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
