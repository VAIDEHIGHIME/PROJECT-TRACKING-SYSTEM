/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author h p
 */
public class sendemail {
    
    public static void sendMail(String myAccount, String pass, String recepient, String subject, String content) throws MessagingException
    {
        
        Properties properties = new Properties();
        
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        
       
        
        Session session = Session.getInstance(properties, new Authenticator()
        {
                @Override
                protected PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication(myAccount,pass);
                }   
                    
        });
       Message msg = prepareMessage(session,myAccount,recepient,subject,content);
       Transport.send(msg);
    }
    
    private static Message prepareMessage(Session session, String myAccount, String recepient, String subject, String content)
    {         
     try{
        Message msg = new MimeMessage(session);
 
        msg.setFrom(new InternetAddress(myAccount));
        //InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        msg.setText(content);
 
        // sends the e-mail
       return msg;
    }
     catch(MessagingException e)
     {
        // Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, e);
     }
        return null;
    }
    
    
    
}
