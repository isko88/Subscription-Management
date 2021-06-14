package az.code.unisubapp.services;

import az.code.unisubapp.dto.*;
import az.code.unisubapp.models.AppUser;
import az.code.unisubapp.models.Card;
import az.code.unisubapp.models.Subscription;

import java.util.List;

public interface AppUserService {

    AppUserDto login(LoginDto login);

    AppUserDto getUserDto(String username);

    AppUser getUser(String username);

    AppUserDto updateUserDto(AppUserDto appUserDto);

    AppUserDto newUserDto(UserRegisterDto appUserDto);

    AppUserDto deleteUserDto(String username);

    Card getCard(Long id);

    CardDto getCardDto(Long id);

    List<CardDto> getCards(String username);

    CardDto newCard(String username, CardDto card);

    CardDto updateCardDto(long id, CardDto cardDto);

    CardDto deleteCardDto(Long id);

    Subscription getSubscription(Long id);

    SubscriptionDto getSubscriptionDto(Long id);

    List<SubscriptionDto> getSubscriptions(String username);

    SubscriptionDto updateSubscriptionDto(long id, SubscriptionDto subscriptionDto);

    SubscriptionDto newSubscriptionDto(SubscriptionDto subscriptionDto, String username);

    SubscriptionDto deleteSubscriptionDto(Long id);

    List<SubscriptionDto> getSubscriptionDtoSocials(String username);

    List<AppUser> getAllUsers();
}
