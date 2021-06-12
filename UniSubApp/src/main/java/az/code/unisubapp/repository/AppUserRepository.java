package az.code.unisubapp.repository;

import az.code.unisubapp.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser getAppUserByUsername(String username);

    @Query("select a from AppUser a where a.isInactive=true")
    List<AppUser> getAppUserByInactive();

}
