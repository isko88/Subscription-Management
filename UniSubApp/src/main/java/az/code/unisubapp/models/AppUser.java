package az.code.unisubapp.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, name = "username")
    private String userName;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phone;
    @JsonManagedReference
    @OneToMany(mappedBy = "appUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Card> cards = new ArrayList<>();

}
