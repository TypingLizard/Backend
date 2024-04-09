package at.kaindorf.lizzardbackend.database;

import at.kaindorf.lizzardbackend.pojos.Mode;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Project: Typing_Lizzard_Backend
 * Author : Alexander Friedl
 * Date : 09.04.2024
 * Time : 14:12
 */
public interface ModeRepository extends JpaRepository<Mode, Long> {
}
