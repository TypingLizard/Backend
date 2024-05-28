package at.kaindorf.lizzardbackend.database;

import at.kaindorf.lizzardbackend.pojos.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Project: Typing_Lizzard_Backend
 * Author : Alexander Friedl
 * Date : 09.04.2024
 * Time : 14:14
 */
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u.userId FROM User u WHERE u.email = :email")
    Long isEmailInUse(String email);
}
