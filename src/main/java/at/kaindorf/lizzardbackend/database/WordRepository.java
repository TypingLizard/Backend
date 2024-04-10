package at.kaindorf.lizzardbackend.database;

import at.kaindorf.lizzardbackend.pojos.Word;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Project: Typing_Lizzard_Backend
 * Author : Alexander Friedl
 * Date : 10.04.2024
 * Time : 09:59
 */
public interface WordRepository extends JpaRepository<Word, Long> {
}
