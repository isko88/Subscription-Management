package az.code.unisubapp.controller;

import az.code.unisubapp.dto.*;
import az.code.unisubapp.models.AppUser;
import az.code.unisubapp.services.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    AppUserService appUserService;

    public UserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> allUsers() {
        return new ResponseEntity<>(appUserService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AppUserDto> loginUser(@RequestBody LoginDto loginInfo) {
        AppUserDto user = appUserService.login(loginInfo);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<AppUserDto> registerNewUser(@RequestBody UserRegisterDto newUser) {
        return new ResponseEntity<>(appUserService.newUserDto(newUser), HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<AppUser> getUSer(@PathVariable String username) {
        return new ResponseEntity<>(appUserService.getUser(username), HttpStatus.OK);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<AppUserDto> deleteAppUser(@PathVariable String username) {
        return new ResponseEntity<>(appUserService.deleteUserDto(username), HttpStatus.OK);
    }

    @PutMapping("/{username}")
    public ResponseEntity<AppUserDto> updateAppUser(@RequestBody AppUserDto appUser, @PathVariable String username) {
        return new ResponseEntity<>(appUserService.updateUserDto(appUser), HttpStatus.OK);
    }

    @GetMapping("/{username}/cards")
    public ResponseEntity<List<?>> getCards(@PathVariable String username) {
        return new ResponseEntity<>(appUserService.getCards(username), HttpStatus.OK);
    }

    @GetMapping("/{username}/cards/{id}")
    public ResponseEntity<CardDto> getCardById(@PathVariable String username, @PathVariable Long id) {
        return new ResponseEntity<>(appUserService.getCardDto(id), HttpStatus.OK);
    }

    @PostMapping("/{username}/cards")
    public ResponseEntity<CardDto> newCard(@PathVariable String username, @RequestBody CardDto cardDto) {
        return new ResponseEntity<>(appUserService.newCard(username, cardDto), HttpStatus.OK);
    }

    @PutMapping("/{username}/cards/{id}")
    public ResponseEntity<CardDto> updateCard(@PathVariable String username,
                                              @PathVariable long id,
                                              @RequestBody CardDto cardDto) {
        return new ResponseEntity<>(appUserService.updateCardDto(id, cardDto), HttpStatus.OK);
    }

    @DeleteMapping("/{username}/cards/{id}")
    public ResponseEntity<CardDto> deleteCardById(@PathVariable String username, @PathVariable long id) {
        return new ResponseEntity<>(appUserService.deleteCardDto(id), HttpStatus.OK);
    }


    @GetMapping("/{username}/subs")
    public ResponseEntity<List<SubscriptionDto>> getSubs(@PathVariable String username) {
        return new ResponseEntity<>(appUserService.getSubscriptions(username), HttpStatus.OK);
    }

    @GetMapping("/{username}/subs/{id}")
    public ResponseEntity<SubscriptionDto> getSubs(@PathVariable String username, Long id) {
        return new ResponseEntity<>(appUserService.getSubscriptionDto(id), HttpStatus.OK);
    }

    @GetMapping("/{username}/subs/socials")
    public ResponseEntity<List<SubscriptionDto>> getSubsSocials(@PathVariable String username) {
        return new ResponseEntity<>(appUserService.getSubscriptionDtoSocials(username), HttpStatus.OK);
    }


    @PostMapping("/{username}/subs")
    public ResponseEntity<SubscriptionDto> newSubs(@PathVariable String username,
                                                   @RequestBody SubscriptionDto subscriptionDto) {
        return new ResponseEntity<>(appUserService.newSubscriptionDto(subscriptionDto, username), HttpStatus.OK);
    }

    @DeleteMapping("/{username}/subs/{id}")
    public ResponseEntity<SubscriptionDto> deleteSubsById(@PathVariable String username,
                                                          @PathVariable long id) {
        return new ResponseEntity<>(appUserService.deleteSubscriptionDto(id), HttpStatus.OK);
    }

    @PutMapping("/{username}/subs/{id}")
    public ResponseEntity<SubscriptionDto> updateSub(@PathVariable String username,
                                                      @PathVariable long id,
                                                      @RequestBody SubscriptionDto subscriptionDto) {
        return new ResponseEntity<>(appUserService.updateSubscriptionDto(id, subscriptionDto), HttpStatus.OK);
    }

}
