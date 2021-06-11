package az.code.unisubapp.services;

import az.code.unisubapp.models.AppUser;

public interface AppUserService {
    

    AppUser updateUser(AppUser appUser);

    AppUser newUser(AppUser appUser);

    AppUser deleteUser(String username);

}
