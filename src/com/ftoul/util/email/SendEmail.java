package com.ftoul.util.email;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message.RecipientType;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	public static final String HOST = "smtp.exmail.qq.com";  
    public static final String PROTOCOL = "smtp";     
    public static final int PORT = 25;  
    public static final String FROM = "yangzw@ftoul.com";//发件人的email  
    public static final String PWD = "Yzw123456";//发件人密码  
    
    
    public static void send(String toEmail , String content) { 
        try {  
	        InternetAddress fromAddress = new InternetAddress(FROM);  
	        InternetAddress toAddress = new InternetAddress(toEmail);
	        
	        Properties props = new Properties();  
	        props.put("mail.smtp.host", HOST);  
	        //props.put("mail.smtp.starttls.enable","true");//使用 STARTTLS安全连接  
	        //props.put("mail.smtp.port", "25");             //google使用465或587端口  
	        props.put("mail.smtp.auth", "true");        // 使用验证  
	        Session mailSession = Session.getInstance(props,new MyAuthenticator(FROM,PWD));  
	  
	        MimeMessage message = new MimeMessage(mailSession);  
	  
	        message.setFrom(fromAddress);  
	        message.addRecipient(RecipientType.TO, toAddress);  
	        message.setSentDate(new Date());  
	        message.setSubject("邮箱验证邮件");  
	        message.setContent(content , "text/html;charset=utf-8"); 
	        // 第三步：发送消息  
	        Transport transport = mailSession.getTransport("smtp");  
	        transport.send(message, message.getRecipients(RecipientType.TO));  
	        System.out.println("邮件发送到"+toEmail+"，内容："+content);  
        }  
        catch (MessagingException mex) {  
            mex.printStackTrace();  
        }  
    } 
}
class MyAuthenticator extends Authenticator{  
    String userName="";  
    String password="";  
    public MyAuthenticator(){  
          
    }  
    public MyAuthenticator(String userName,String password){  
        this.userName=userName;  
        this.password=password;  
    }  
     protected PasswordAuthentication getPasswordAuthentication(){     
        return new PasswordAuthentication(userName, password);     
     }   
}
