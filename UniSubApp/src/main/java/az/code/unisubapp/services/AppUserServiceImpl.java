package az.code.unisubapp.services;

import az.code.unisubapp.models.AppUser;
import az.code.unisubapp.repository.AppUserRepository;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService {

    AppUserRepository appUserRepository;

    public AppUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public AppUser getUser(String username) {
        try{
            return appUserRepository.getAppUserByUserName(username);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public AppUser updateUser(AppUser user) {
        return appUserRepository.save(user);
    }

    public AppUser newUser(AppUser user) {
        return appUserRepository.save(user);
    }

    public AppUser deleteUser(String username) {
        AppUser user = getUser(username);
        appUserRepository.delete(user);
        return user;
    }
}
