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
    private String number;
    private String bankName;
    private LocalDate expiryDate;
    private CardType type;
    @ManyToOne(targetEntity = AppUser.class)
    @JoinColumn(name = "appUser_id")
    @JsonBackReference
    private AppUser appUser;
}
