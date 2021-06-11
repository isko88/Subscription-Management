package az.code.unisubapp.repository;

import az.code.unisubapp.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
