package az.code.unisubapp.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true)
    private String phone;

    @JsonManagedReference
    @OneToMany(targetEntity = Card.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Card> cards = new ArrayList<>();

    @OneToMany(targetEntity = Subscription.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Subscription> subs = new ArrayList<>();

    @Column(name = "is_inactive")
    private boolean isInactive;

    @Column(name = "remove_date")
    private LocalDate removeDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return id.equals(appUser.id) && username.equals(appUser.username) && email.equals(appUser.email);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, username, email);
    }
}
