package az.code.unisubapp.services;

import az.code.unisubapp.dto.CardDto;
import az.code.unisubapp.dto.SubscriptionDto;
import az.code.unisubapp.exceptions.AlreadyExists;
import az.code.unisubapp.models.AppUser;
import az.code.unisubapp.models.Card;
import az.code.unisubapp.models.Subscription;
import az.code.unisubapp.repository.AppUserRepository;
import az.code.unisubapp.repository.CardRepository;
import az.code.unisubapp.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppUserServiceImpl implements AppUserService {

    AppUserRepository appUserRepository;
    CardRepository cardRepository;
    SubscriptionRepository subscriptionRepository;

    public AppUserServiceImpl(AppUserRepository appUserRepository,
                              CardRepository cardRepository,
                              SubscriptionRepository subscriptionRepository) {
        this.appUserRepository = appUserRepository;
        this.cardRepository = cardRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
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

    @Override
    public AppUser updateUser(AppUser user) {
        AppUser u = appUserRepository.getAppUserByUsername(user.getUsername());
        u.updateUser(user);
        appUserRepository.save(u);
        return u;
    }

    @Override
    public AppUser newUser(AppUser user) {
        try{
            return appUserRepository.save(user);
        }
        catch (Exception e){
            throw new AlreadyExists();
        }
    }

    @Override
    public AppUser deleteUser(String username) {
        AppUser user = getUser(username);
        user.setInactive(true);
        user.setRemoveDate(LocalDate.now());
        appUserRepository.save(user);
        return user;
    }

    @Override
    public CardDto getCardDto(Long id) {
        Card card = cardRepository.getById(id);
        return new CardDto(card);
    }

    public Card getCard(Long id){
        return cardRepository.getById(id);
    }

    @Override
    public List<CardDto> getCards(String username) {
        return appUserRepository
                .getAppUserByUsername(username)
                .getCards()
                .stream()
                .map(CardDto::new)
                .collect(Collectors.toList());
    }

    @Override
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

    @Override
    public Card updateCard(Card card) {
        Card c = cardRepository.getById(card.getId());
        c.update(card);
        return cardRepository.save(card);
    }

    @Override
    public Card deleteCard(Card card) {
        cardRepository.delete(card);
        return card;
    }

    @Override
    public Subscription getSubscription(Long id) {
        return subscriptionRepository.getById(id);
    }

    @Override
    public List<SubscriptionDto> getSubscriptions(String username) {
        return appUserRepository
                .getAppUserByUsername(username)
                .getSubs()
                .stream()
                .map(SubscriptionDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public Subscription updateSubscription(Subscription subscription) {
        Subscription sub = subscriptionRepository.getById(subscription.getId());
        sub.update(subscription);
        return subscriptionRepository.save(sub);
    }

    @Override
    public Subscription newSubscription(Subscription subscription) {
        return null;
    }

    @Override
    public Subscription deleteSubscription(String username, Long id) {
        return null;
    }
}
