package az.code.unisubapp.controller;

import az.code.unisubapp.exceptions.UsernameNotFound;
import az.code.unisubapp.models.AppUser;
import az.code.unisubapp.services.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    AppUserService appUserService;

    public UserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<AppUser> getAppUserByUsername(@PathVariable String username) {
        AppUser appUser = appUserService.getUser(username);
        if (appUser == null) {
            throw new UsernameNotFound();
        }
        return new ResponseEntity<>(appUser, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<AppUser> saveAppUser(@RequestBody AppUser appUser) {
        return new ResponseEntity<>(appUserService.newUser(appUser), HttpStatus.OK);
    }
}
