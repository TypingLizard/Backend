package at.kaindorf.lizzardbackend.database;

import at.kaindorf.lizzardbackend.pojos.Word;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

/**
 * Project: Typing_Lizzard_Backend
 * Author : Alexander Friedl
 * Date : 10.04.2024
 * Time : 09:59
 */
public interface WordRepository extends JpaRepository<Word, Long> {

    @Query("SELECT w.wordId FROM Word w WHERE w.wordName = :name")
    Long idFromName(String name);




}
