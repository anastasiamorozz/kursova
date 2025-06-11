package com.example.kursova.utils.handler;

import jakarta.mail.*;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class EmailHandler extends Handler {

    private final String to = "morozanastasia2005@gmail.com";
    private final String from = "anastasiia.moroz.oi.2024@lpnu.ua";
    private final String host = "smtp.gmail.com"; // або smtp.gmail.com
    private final String username = "anastasiia.moroz.oi.2024@lpnu.ua";
    private final String password = "hgdetnjqhxmiicss";

    @Override
    public void publish(LogRecord record) {
        if (record.getLevel().intValue() >= Level.SEVERE.intValue()) {
            sendEmail(record);
        }
    }

    private void sendEmail(LogRecord record) {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("⚠️ Критична помилка в додатку");

            String content = "Повідомлення: " + record.getMessage() + "\n\n";
            if (record.getThrown() != null) {
                content += "Помилка: " + record.getThrown().toString();
            }

            message.setText(content);
            Transport.send(message);
        } catch (MessagingException e) {
            System.err.println("Не вдалося надіслати e-mail: " + e.getMessage());
        }
    }

    @Override
    public void flush() {}

    @Override
    public void close() throws SecurityException {}
}
