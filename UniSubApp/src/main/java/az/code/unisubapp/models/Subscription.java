package az.code.unisubapp.models;

import az.code.unisubapp.models.enums.Plan;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity(name = "Subs")
@Table(name = "subscriptions")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String item;
    private Plan plan;
    private BigDecimal price;
    @JsonManagedReference
    @ManyToOne(targetEntity = Card.class, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Card card;
    private LocalDate subDate;
    private URL website;
    private boolean isActive;
    private LocalDate deactivatedDate;
}
