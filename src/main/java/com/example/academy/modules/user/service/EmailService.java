package com.example.academy.modules.user.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService{

    private final JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String text) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }

    public void sendPasswordResetEmail(String to, String token) {
        String resetLink = "http://lockalhost/reset-password?token=" + token;
        String emailContent = "<html><body>"
                + "<p>Dear User,</p>"
                + "<p>You have requested a password reset. Click the link below to reset your password:</p>"
                + "<p><a href='" + resetLink + "'>Reset Password</a></p>"
                + "<p>If you did not request this, please ignore this email.</p>"
                + "<p>Best regards,<br>Your Company</p>"
                + "</body></html>";
        sendEmail(to, "Password Reset Request", emailContent);
    }
}

