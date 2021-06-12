package az.code.unisubapp.dto;

import az.code.unisubapp.models.Card;
import az.code.unisubapp.models.Subscription;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;


@Data
public class SubscriptionDto {
    private Long id;
    private String item;
    private String plan;
    private BigDecimal price;
    private long card;
    private LocalDate subDate;
    private URL website;

    public SubscriptionDto(Subscription sub) {
        this.id = sub.getId();
        this.item = sub.getItem();
        this.plan = sub.getPlan();
        this.price = sub.getPrice();
        this.card = sub.getCard().getId();
        this.subDate = sub.getSubDate();
        this.website = sub.getWebsite();
    }
}
