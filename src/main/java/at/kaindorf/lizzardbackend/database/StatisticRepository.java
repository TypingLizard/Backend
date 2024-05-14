package at.kaindorf.lizzardbackend.database;

import at.kaindorf.lizzardbackend.pojos.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

/**
 * Project: Typing_Lizzard_Backend
 * Author : Alexander Friedl
 * Date : 09.04.2024
 * Time : 14:13
 */
public interface StatisticRepository extends JpaRepository<Statistic, Long> {

    @Query("SELECT s.date FROM Statistic s WHERE s.id = :id " +
            "AND s.date = (SELECT MAX(s.date) FROM Statistic s WHERE s.id = :id)")
    LocalDateTime lastDate(Long id);
}
