package az.code.unisubapp.services;

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
            return user;
        } catch (Exception e) {
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
        user.setInactive(true);
        user.setRemoveDate(LocalDate.now());
        return user;
    }

    public Card getCard(Long id) {
        return cardRepository.getById(id);
    }

    public List<Card> getCards(String username) {
        return appUserRepository.getAppUserByUsername(username).getCards();
    }

    public Card newCard(String username, Card card) {
        AppUser user = appUserRepository.getAppUserByUsername(username);
        user.getCards().add(card);
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
