package az.code.unisubapp.models;

import az.code.unisubapp.models.enums.CardType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.description.method.ParameterList;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Card")
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String number;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "expiry_date")
    private LocalDate expiryDate;
    private CardType type;
    @ManyToOne(targetEntity = AppUser.class)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private AppUser appUser;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return id.equals(card.id) && number.equals(card.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number);
    }
}
