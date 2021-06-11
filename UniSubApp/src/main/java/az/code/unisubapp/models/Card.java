package az.code.unisubapp.models;

import az.code.unisubapp.models.enums.CardType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private String bank_name;
    private LocalDate expiryDate;
    private CardType type;
    @ManyToOne
    @JoinColumn(name = "appUser_id")
    @JsonBackReference
    private AppUser appUser;

}
