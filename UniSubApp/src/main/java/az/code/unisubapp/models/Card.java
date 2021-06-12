package az.code.unisubapp.models;

import az.code.unisubapp.dto.CardDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.time.LocalDate;
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
    @NaturalId
    private String number;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "expiry_date")
    private LocalDate expiryDate;
    private String type;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference
    private AppUser appUser;

    public Card(CardDto cardDto){
        this.id = cardDto.getId();
        this.number = cardDto.getNumber();
        this.bankName = cardDto.getBankName();
        this.expiryDate = cardDto.getExpiryDate();
        this.type = cardDto.getType();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return number.equals(card.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    public void update(Card card){
        this.setNumber(card.getNumber() != null && card.getNumber().matches("[\\d]+")? card.getNumber() : this.getNumber());
        this.setBankName(card.getBankName() != null ? card.getBankName() : this.getBankName());
        this.setExpiryDate(card.getExpiryDate() != null ? card.getExpiryDate() : this.getExpiryDate());
        this.setType(card.getType() != null ? card.getType() : this.getType());
    }

}
