package ru.voronasever.voronaStore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import ru.voronasever.voronaStore.model.RemindPass;
import ru.voronasever.voronaStore.model.User;
import ru.voronasever.voronaStore.secuirty.encoder.MD5Encoder;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.Random;

@Service
public class MailService {
    @Value("${client.app}")
    String clientPath;

    @Autowired
    SimpleMailMessage simpleMail;
    @Autowired
    MailSender mailSender;
    @Autowired
    UserService userService;
    @Autowired
    RemindPassService remindPassService;

    public void sendRemindPasswordMessageToEmail(String email) {
        simpleMail.setFrom("fs_belayavorona@mail.ru");
        simpleMail.setTo(email);
        simpleMail.setSubject("Восстановление учетной записи на voronasever.ru");

        String link = encodeLinkToChangePassword(email);
        simpleMail.setText("По вашему адресу был сделан запрос на восстановление учетной записи на ВоронаСевер.Ру" +
                " (www.voronasever.ru).\n" +
                "\n" + "Для получения новых данных пройдите по этой ссылке: " + link);
        mailSender.send(simpleMail);
    }
    private String encodeLinkToChangePassword(String email){
        int AUTO_INCREMENT = 0;
        String emailEnc = MD5Encoder.md5Custom(email);
        String hash = MD5Encoder.md5Custom(new Random(Long.MAX_VALUE).nextLong());
        Date date = new Date();
        long expire = new Date(date.getTime() + 3600000).getTime(); //one hour
        //TODO SOLID
        remindPassService.save(new RemindPass(AUTO_INCREMENT, email, emailEnc, hash, expire));
        return clientPath + "remind/" + "?key=" + hash + "&eenc=" + emailEnc;
    }
}
