package az.code.unisubapp.controller;

import az.code.unisubapp.dto.CardDto;
import az.code.unisubapp.exceptions.UsernameNotFound;
import az.code.unisubapp.models.AppUser;
import az.code.unisubapp.models.Card;
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

    @DeleteMapping("/{username}")
    public ResponseEntity<AppUser> deleteAppUser(@PathVariable String username) {
        return new ResponseEntity<>(appUserService.deleteUser(username), HttpStatus.OK);
    }

    @PutMapping("/{username}")
    public ResponseEntity<AppUser> updateAppUser(@RequestBody AppUser appUser, @PathVariable String username) {
        return new ResponseEntity<>(appUserService.updateUser(appUser), HttpStatus.OK);
    }

    @GetMapping("/{username}/cards")
    public ResponseEntity<List<Card>> getCards(@PathVariable String username) {
        return new ResponseEntity<>(appUserService.getCards(username), HttpStatus.OK);
    }

    @GetMapping("/{username}/cards/{id}")
    public ResponseEntity<CardDto> getCardById(@PathVariable String username, @PathVariable long id) {
        return new ResponseEntity<>(appUserService.getCard(id), HttpStatus.OK);
    }

    @PostMapping("/{username}/cards")
    public ResponseEntity<Card> newCard(@PathVariable String username, @RequestBody Card card) {
        return new ResponseEntity<>(appUserService.newCard(username, card), HttpStatus.OK);
    }
}
