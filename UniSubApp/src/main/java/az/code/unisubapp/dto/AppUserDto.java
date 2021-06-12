package az.code.unisubapp.dto;

import az.code.unisubapp.models.AppUser;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDto {
    private long id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;

    Set<CardDto> cards = new HashSet<>();
    List<SubscriptionDto> subs = new ArrayList<>();

    public AppUserDto(AppUser user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.email = user.getEmail();
        this.phoneNumber = user.getFirstname();

        user.getCards().stream().map(CardDto::new).forEach(this.cards::add);
        user.getSubs().stream().map(SubscriptionDto::new).forEach(s -> this.subs.add(s));
    }
}
