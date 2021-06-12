package az.code.unisubapp.services;

import az.code.unisubapp.dto.CardDto;
import az.code.unisubapp.exceptions.AlreadyExists;
import az.code.unisubapp.models.AppUser;
import az.code.unisubapp.models.Card;
import az.code.unisubapp.repository.AppUserRepository;
import az.code.unisubapp.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService {

    AppUserRepository appUserRepository;
    CardRepository cardRepository;

    public AppUserServiceImpl(AppUserRepository appUserRepository, CardRepository cardRepository) {
        this.appUserRepository = appUserRepository;
        this.cardRepository = cardRepository;
    }

    public AppUser getUser(String username) {
        try {
            AppUser user = appUserRepository.getAppUserByUsername(username);
            user.setInactive(false);
            user.setRemoveDate(null);
            appUserRepository.save(user);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public AppUser updateUser(AppUser user) {
        AppUser u = appUserRepository.getAppUserByUsername(user.getUsername());
        u.updateUser(user);
        appUserRepository.save(u);
        return u;
    }

    public AppUser newUser(AppUser user) {
        try{
            return appUserRepository.save(user);
        }
        catch (Exception e){
            throw new AlreadyExists();
        }
    }

    public AppUser deleteUser(String username) {
        AppUser user = getUser(username);
        user.setInactive(true);
        user.setRemoveDate(LocalDate.now());
        appUserRepository.save(user);
        return user;
    }

    public CardDto getCard(Long id) {
        System.out.println("BBBBB");
        Card card = cardRepository.getById(id);
        System.out.println(card.getBankName());
        System.out.println("AAaaaaaaaaaaaaa");
        return new CardDto(card);
    }

    public List<Card> getCards(String username) {
        return List.copyOf(appUserRepository.getAppUserByUsername(username).getCards());
    }

    public Card newCard(String username, Card card) {
        AppUser user = appUserRepository.getAppUserByUsername(username);
        boolean success = user.addCard(card);
        if(success){
            appUserRepository.save(user);
        }
        else{
            throw new AlreadyExists();
        }
        return card;
    }

    public Card updateCard(Card card) {
        return cardRepository.save(card);
    }

    public Card deleteCard(Card card) {
        cardRepository.delete(card);
        return card;
    }
}
