package az.code.unisubapp.services;

import az.code.unisubapp.dto.AppUserDto;
import az.code.unisubapp.dto.CardDto;
import az.code.unisubapp.dto.SubscriptionDto;
import az.code.unisubapp.models.AppUser;
import az.code.unisubapp.models.Card;
import az.code.unisubapp.models.Subscription;

import java.util.List;

public interface AppUserService {

    AppUserDto getUserDto(String username);

    AppUser getUser(String username);

    AppUserDto updateUserDto(AppUserDto appUserDto);

    AppUserDto newUserDto(AppUserDto appUserDto);

    AppUserDto deleteUserDto(String username);

    Card getCard(Long id);

    CardDto getCardDto(Long id);

    List<CardDto> getCards(String username);

    CardDto newCard(String username, CardDto card);

    CardDto updateCardDto(CardDto cardDto);

    CardDto deleteCardDto(Long id);

    Subscription getSubscription(Long id);

    SubscriptionDto getSubscriptionDto(Long id);

    List<SubscriptionDto> getSubscriptions(String username);

    SubscriptionDto updateSubscriptionDto(SubscriptionDto subscriptionDto);

    SubscriptionDto newSubscriptionDto(SubscriptionDto subscriptionDto, String username);

    SubscriptionDto deleteSubscriptionDto(Long id);

}
