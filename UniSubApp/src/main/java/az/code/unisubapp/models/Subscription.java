package az.code.unisubapp.models;

import az.code.unisubapp.dto.SubscriptionDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;

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
    private String plan;
    private BigDecimal price;
    @ManyToOne(targetEntity = Card.class, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    private Card card;

    @Column(name = "sub_date")
    private LocalDate subDate;
    private URL website;
    @Column(name = "is_active")
    private boolean isActive;
    @Column(name = "deactivated_date")
    private LocalDate deactivatedDate;

    public Subscription(SubscriptionDto subDto){
        this.id = subDto.getId();
        this.item = subDto.getItem();
        this.plan = subDto.getPlan();
        this.price = subDto.getPrice();
        this.subDate = subDto.getSubDate();
        this.website = subDto.getWebsite();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscription that = (Subscription) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void update(Subscription sub){
        this.setItem(sub.getItem() != null ? sub.getItem(): this.getItem());
        this.setPlan(sub.getPlan() != null ? sub.getPlan(): this.getPlan());
        this.setPrice(sub.getPrice() != null ? sub.getPrice(): this.getPrice());
        this.setCard(sub.getCard() != null ? sub.getCard(): this.getCard());
        this.setPrice(sub.getPrice() != null ? sub.getPrice(): this.getPrice());
    }
}
