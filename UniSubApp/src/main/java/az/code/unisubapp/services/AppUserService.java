package az.code.unisubapp.services;

import az.code.unisubapp.dto.CardDto;
import az.code.unisubapp.models.AppUser;
import az.code.unisubapp.models.Card;
import az.code.unisubapp.models.Subscription;

import java.util.List;

public interface AppUserService {

    AppUser getUser(String username);

    AppUser updateUser(AppUser appUser);

    AppUser newUser(AppUser appUser);

    AppUser deleteUser(String username);

    CardDto getCard(Long id);

    List<Card> getCards(String username);

    Card newCard(String username, Card card);

    Card updateCard(Card card);

    Card deleteCard(Card card);

}
