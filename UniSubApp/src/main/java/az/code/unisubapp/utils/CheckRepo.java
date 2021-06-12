package az.code.unisubapp.utils;

import az.code.unisubapp.models.AppUser;
import az.code.unisubapp.repository.AppUserRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CheckRepo {

    AppUserRepository appUserRepository;
    MailSender mailSender;
    List<String> emails = new ArrayList<>();

    public CheckRepo(AppUserRepository appUserRepository, MailSender mailSender) {
        this.appUserRepository = appUserRepository;
        this.mailSender = mailSender;
    }

    public void checkDate() {
        List<AppUser> appUserList = appUserRepository.getAppUserByInactive();
        for (AppUser user : appUserList) {
            emails.add(user.getEmail());
        }
        sendMail();
    }

    private void sendMail() {
        for (String s : emails) {
            mailSender.sendNotificationEmail(s, "Three days");
        }
        emails.clear();
    }

}
