package az.code.unisubapp.models;


import az.code.unisubapp.dto.AppUserDto;
import az.code.unisubapp.dto.UserRegisterDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "AppUser")
@Table(name = "users")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NaturalId
    private String username;
    private String firstname;
    private String lastname;
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @JsonManagedReference
    @OneToMany(mappedBy = "appUser" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<Card> cards = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    List<Subscription> subs = new ArrayList<>();

    @Column(name = "is_inactive")
    private boolean isInactive;

    @Column(name = "remove_date")
    private LocalDate removeDate;

    public AppUser(AppUserDto userDto){
        this.id = userDto.getId();
        this.username = userDto.getUsername();
        this.firstname = userDto.getFirstname();
        this.lastname = userDto.getLastname();
        this.email = userDto.getEmail();
        this.phoneNumber = userDto.getPhoneNumber();
    }

    public AppUser(UserRegisterDto userDto){
        this.id = userDto.getId();
        this.username = userDto.getUsername();
        this.firstname = userDto.getFirstname();
        this.lastname = userDto.getLastname();
        this.email = userDto.getEmail();
        this.phoneNumber = userDto.getPhoneNumber();
        this.password = userDto.getPassword();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return username.equals(appUser.username) && email.equals(appUser.email);
    }
    @Override
    public int hashCode() {
        return Objects.hash(username, email);
    }

    public boolean addCard(Card card){
        if(this.cards.add(card)){
            card.setAppUser(this);
            return true;
        }
        return false;
    }

    public boolean addSub(Subscription sub){
        return this.subs.add(sub);
    }

    public void updateUser(AppUser u){
        this.setFirstname(u.getFirstname() != null ? u.getFirstname() : this.getFirstname());
        this.setLastname(u.getLastname() != null ? u.getLastname() : this.getLastname());
        this.setEmail(u.getEmail() != null ? u.getEmail() : this.getEmail());
        this.setPhoneNumber(u.getPhoneNumber() != null ? u.getPhoneNumber() : this.getPhoneNumber());
    }
}
