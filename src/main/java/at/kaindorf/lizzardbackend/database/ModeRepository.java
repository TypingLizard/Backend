package at.kaindorf.lizzardbackend.database;

import at.kaindorf.lizzardbackend.pojos.Mode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * Project: Typing_Lizard_Backend
 * Author : Alexander Friedl
 * Date : 09.04.2024
 * Time : 14:12
 */
public interface ModeRepository extends JpaRepository<Mode, Long> {


    @Query("SELECT m.modeId FROM Mode m WHERE m.modeName = :name")
    Long idFromName(String name);


}
