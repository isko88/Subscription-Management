package az.code.unisubapp.services;

import az.code.unisubapp.dto.*;
import az.code.unisubapp.exceptions.AlreadyExists;
import az.code.unisubapp.exceptions.UsernameNotFound;
import az.code.unisubapp.models.AppUser;
import az.code.unisubapp.models.Card;
import az.code.unisubapp.models.Subscription;
import az.code.unisubapp.repository.AppUserRepository;
import az.code.unisubapp.repository.CardRepository;
import az.code.unisubapp.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
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
    public AppUserDto getUserDto(String username) {
        try {
            AppUser user = appUserRepository.getAppUserByUsername(username);
            user.setInactive(false);
            user.setRemoveDate(null);
            appUserRepository.save(user);
            return new AppUserDto(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFound();
        }
    }

    @Override
    public AppUserDto login(LoginDto login){
        AppUser user = appUserRepository.
                getAppUserByUsernameAndPasswordEquals(login.getUsername(), login.getPassword());
        if(user == null){
            throw new UsernameNotFound();
        }
        return new AppUserDto(user);
    }
    @Override
    public AppUser getUser(String username) {
        try {
            AppUser user = appUserRepository.getAppUserByUsername(username);
            appUserRepository.save(user);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public AppUserDto updateUserDto(AppUserDto userDto) {
        AppUser u = appUserRepository.getAppUserByUsername(userDto.getUsername());
        System.out.println(userDto);
        u.updateUser(new AppUser(userDto));
        appUserRepository.save(u);
        return new AppUserDto(u);
    }

    @Override
    public AppUserDto newUserDto(UserRegisterDto user) {
        try {
            AppUser newUser = appUserRepository.save(new AppUser(user));
            return new AppUserDto(newUser);
        } catch (Exception e) {
            throw new AlreadyExists();
        }
    }

    @Override
    public AppUserDto deleteUserDto(String username) {
        AppUser user = getUser(username);
        user.setInactive(true);
        user.setRemoveDate(LocalDate.now());
        appUserRepository.save(user);
        return new AppUserDto(user);
    }

    @Override
    public CardDto getCardDto(Long id) {
        Card card = cardRepository.getById(id);
        return new CardDto(card);
    }

    @Override
    public Card getCard(Long id) {
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
    public CardDto newCard(String username, CardDto cardDto) {
        AppUser user = appUserRepository.getAppUserByUsername(username);
        Card card = new Card(cardDto);
        boolean success = user.addCard(card);
        if (success) {
            appUserRepository.save(user);
        } else {
            throw new AlreadyExists();
        }
        return new CardDto(cardRepository.getCardByNumber(card.getNumber()));
    }

    @Override
    public CardDto updateCardDto(long id, CardDto cardDto) {
        Card c = cardRepository.getById(id);
        c.update(new Card(cardDto));
        cardRepository.save(c);
        return new CardDto(c);
    }

    @Override
    public CardDto deleteCardDto(Long id) {
        Card card = cardRepository.getById(id);
        CardDto cardDto = new CardDto(card);
        cardRepository.deleteById(id);
        return cardDto;
    }

    @Override
    public Subscription getSubscription(Long id) {
        return subscriptionRepository.getById(id);
    }

    @Override
    public SubscriptionDto getSubscriptionDto(Long id) {
        return new SubscriptionDto(getSubscription(id));
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
    public SubscriptionDto updateSubscriptionDto(long id, SubscriptionDto subscriptionDto) {
        Subscription subUpdate = new Subscription(subscriptionDto);
        Subscription sub = subscriptionRepository.getById(id);
        sub.update(subUpdate);
        subscriptionRepository.save(sub);
        return new SubscriptionDto(subscriptionRepository.getById(id));
    }

    @Override
    public SubscriptionDto newSubscriptionDto(SubscriptionDto subscriptionDto, String username) {
        AppUser appUser = appUserRepository.getAppUserByUsername(username);
        Subscription newSub = new Subscription(subscriptionDto);
        String plan = newSub.getPlan();
        LocalDate ld = newSub.getSubDate();
        switch (plan.toLowerCase()){
            case("annually"):{
                newSub.setRenewDate(ld.plusYears(1));
                break;
            }
            case("monthly"):{
                newSub.setRenewDate(ld.plusMonths(1));
                break;
            }
            case("weekly"):{
                newSub.setRenewDate(ld.plusDays(7));
                break;
            }
        }
        Card card = cardRepository.getById(subscriptionDto.getCardId());
        newSub.setCard(card);
        newSub = subscriptionRepository.save(newSub);
        appUser.addSub(newSub);
        appUserRepository.save(appUser);
        return new SubscriptionDto(subscriptionRepository.getById(newSub.getId()));
    }

    @Override
    public SubscriptionDto deleteSubscriptionDto(Long id) {
        Subscription sub = subscriptionRepository.getById(id);
        sub.setDeactivated(true);
        sub.setDeactivatedDate(LocalDate.now());
        subscriptionRepository.save(sub);
        return new SubscriptionDto(sub);
    }

    @Override
    public List<SubscriptionDto> getSubscriptionDtoSocials(String username) {
        AppUser user = appUserRepository.getAppUserByUsername(username);
        List<SubscriptionDto> socialSubs = user.getSubs().stream().filter(s -> {
            String item = s.getItem().toLowerCase();
            if(item.equals("facebook") || item.equals("google") || item.equals("instagram") || item.equals("twitter")){
                return true;
            }
            else{
                return false;
            }
        }).map(SubscriptionDto::new).collect(Collectors.toList());
        return socialSubs;
    }

    @Override
    public List<AppUser> getAllUsers(){
        return appUserRepository.getAllUsers();
    }

}
