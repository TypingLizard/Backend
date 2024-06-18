package at.kaindorf.lizzardbackend.database;

import at.kaindorf.lizzardbackend.pojos.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Project: Typing_Lizard_Backend
 * Author : Alexander Friedl
 * Date : 09.04.2024
 * Time : 14:13
 */
public interface StatisticRepository extends JpaRepository<Statistic, Long> {

    @Query("SELECT MAX(s.date) FROM Statistic s WHERE s.user.userId = :id")
    LocalDateTime lastDate(Long id);

    @Query("SELECT s FROM Statistic s WHERE s.user.userId = :id ")
    List<Statistic> statsFromUser(Long id);
}
