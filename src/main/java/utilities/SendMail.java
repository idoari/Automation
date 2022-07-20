package utilities;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {

    private static String to;
    private static String from;
    private static String filename;
    private static String subject;
    private static String message_content;

    //constructor
    public SendMail(String To , String From, String Subject , String MessageContent, String Filename) {
        to = To;
        from = From;
        filename=Filename;
        subject=Subject;
        message_content = MessageContent;
    }

    public void GenerateMessage() {
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, "pzobbimmjfguiamj");
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);

            Multipart multipart = new MimeMultipart();
            MimeBodyPart attachmentPart = new MimeBodyPart();
            MimeBodyPart textPart = new MimeBodyPart();

            try {

                File f =new File(System.getProperty("user.dir") + "/" +  filename);

                attachmentPart.attachFile(f);
                textPart.setText(message_content);
                multipart.addBodyPart(textPart);
                multipart.addBodyPart(attachmentPart);

            } catch (IOException e) {
                e.printStackTrace();
            }

            message.setContent(multipart);

            System.out.println("Sending Report...");
            Transport.send(message);
            System.out.println("Report Sent successfully....");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

}