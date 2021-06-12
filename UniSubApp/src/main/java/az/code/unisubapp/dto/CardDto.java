package az.code.unisubapp.dto;

import az.code.unisubapp.models.Card;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {
    private Long id;
    private String number;
    private String bankName;
    private LocalDate expiryDate;
    private String type;

    public CardDto(Card card) {
        this.id = card.getId();
        this.bankName = card.getBankName();
        this.expiryDate = card.getExpiryDate();
        this.type = card.getType();
        this.number = maskCardNumber(card.getNumber());
    }
    private String maskCardNumber(String number){
        return ("*").repeat(12) + number.substring(number.length() - 4);
    }

}
