package com.example.mavbackend.service.implementation;

import com.example.mavbackend.exception.MAVValidationException;
import com.example.mavbackend.service.interfac.ISendEmailService;
import com.example.mavbackend.util.IConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@Service
public class SendEmailService implements ISendEmailService {
    @Value("${app.mail.username}")
    private String usuarioMail;

    @Value("${app.mail.password}")
    private String passwordMail;

    @Async
    @Override
    public void sendSimpleEmail(String asunto, String texto, String footer, List<String> destinatarios,
                                Map<String, String> mapInlineImages){
        try{
            var properties = new Properties();
            var usuarioCorreo = this.usuarioMail;
            var claveCorreo = this.passwordMail;
            if (usuarioCorreo != null && claveCorreo != null && !usuarioCorreo.trim().equals("")
                    && !claveCorreo.trim().equals("")) {
                properties.setProperty("mail.smtp.host", IConstants.NOMBRE_SERVIDOR_CORREO);
                properties.setProperty("mail.smtp.starttls.enable", "true");
                properties.setProperty("mail.smtp.port", String.valueOf(IConstants.PUERTO_SERIDOR_CORREO));
                properties.setProperty("mail.smtp.user", usuarioCorreo);
                properties.setProperty("mail.smtp.auth", "true");
                var mailSesion = Session.getDefaultInstance(properties);
                mailSesion.setDebug(false);
                texto += footer;
                var msg = new MimeMessage(mailSesion);
                msg.setSubject(asunto, "UTF-8");
                msg.setFrom(new InternetAddress(usuarioCorreo, this.usuarioMail));
                msg.setSentDate(new java.util.Date());


                var messageBodyPart = new MimeBodyPart();
                messageBodyPart.setContent(texto, "text/html; charset=UTF-8");

                var multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);

                if(mapInlineImages != null && mapInlineImages.size() > 0) {
                    Set<String> setImageID = mapInlineImages.keySet();

                    for(String contentId : setImageID) {
                        MimeBodyPart imagePart = new MimeBodyPart();
                        imagePart.setHeader("Content-ID", "<" + contentId + ">");
                        imagePart.setDisposition(MimeBodyPart.INLINE);

                        String imageFilePath = mapInlineImages.get(contentId);
                        try {
                            imagePart.attachFile(new ClassPathResource(imageFilePath).getFile());
                        }catch (IOException ex) {
                            throw new MAVValidationException();
                        }

                        multipart.addBodyPart(imagePart);
                    }
                }

                msg.setContent(multipart);

                var address = new InternetAddress[destinatarios.size()];
                var i = 0;
                for (String c : destinatarios) {
                    address[i] = new InternetAddress(c);
                    i++;
                }
                msg.setRecipients(Message.RecipientType.TO, address);
                var transport = mailSesion.getTransport("smtp");
                transport.connect(IConstants.NOMBRE_SERVIDOR_CORREO, IConstants.PUERTO_SERIDOR_CORREO,
                        usuarioCorreo, claveCorreo);
                transport.sendMessage(msg, msg.getAllRecipients());
                transport.close();
            }
        } catch (Exception e){
            throw new MAVValidationException();
        }
    }


}
