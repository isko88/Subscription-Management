package az.code.unisubapp.repository;

import az.code.unisubapp.models.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}
