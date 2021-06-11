package az.code.unisubapp.repository;

import az.code.unisubapp.models.AppUser;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    AppUser getAppUserByUsername(String username);
}
