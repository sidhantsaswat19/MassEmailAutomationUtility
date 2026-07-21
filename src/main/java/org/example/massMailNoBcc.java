package org.example;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

public class massMailNoBcc {
    public static void main(String[] args) {
        String host = "smtp.gmail.com";
        String port = "587";
        String username = "example@gmail.com";
        String password = "16 digit password";
        String fromEmail = "fromEmailID";
        String csvFilePath = "contacts.csv";
        String documentPath = "Mydocx.docx";

        File attachmentFile = new File(documentPath);

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props,new Authenticator() {
            @Override
            protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                return new jakarta.mail.PasswordAuthentication(username, password);
            }
        });
        try (Transport transport = session.getTransport("smtp")) {
            System.out.println("Connecting to SMTP server...");
            transport.connect();
            System.out.println("Connected to SMTP server.");

            Path path = Paths.get(csvFilePath);
            List<String> lines = Files.readAllLines(path);

            boolean isFirstLine = true;
            int successCount = 0;
            for (String line : lines) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] data = line.split(",");
                if(data.length >= 2) {
                    String toEmail = data[0].trim();
                    String name = data[1].trim();

                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(fromEmail));
                    message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

                    message.setSubject("Application for Manager MEPF - Suresh Sahoo");

                    Multipart multipart = new MimeMultipart();
                    MimeBodyPart textPart = new MimeBodyPart();
                    String emailBody = "EmailBody";

                    textPart.setText(emailBody);
                    multipart.addBodyPart(textPart);

                    MimeBodyPart attachmentPart = new MimeBodyPart();
                    attachmentPart.attachFile(attachmentFile);
                    multipart.addBodyPart(attachmentPart);
                    message.setContent(multipart);

                    transport.sendMessage(message, message.getAllRecipients());
                    System.out.println("Email sent successfully to: " + toEmail);
                    successCount++;

                    Thread.sleep(500);
                }
            }
            System.out.println("Total emails sent successfully: " + successCount);
        }
        catch (MessagingException | IOException|InterruptedException e){
            System.err.println("Error occurred while sending email: " + e.getMessage());
            e.printStackTrace();
        }
    }
}