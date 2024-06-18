package at.kaindorf.lizzardbackend.database;

import at.kaindorf.lizzardbackend.pojos.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

/**
 * Project: Typing_Lizard_Backend
 * Author : Alexander Friedl
 * Date : 09.04.2024
 * Time : 14:14
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);



}
