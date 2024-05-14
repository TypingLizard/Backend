package at.kaindorf.lizzardbackend.web;

import at.kaindorf.lizzardbackend.database.StatisticRepository;
import at.kaindorf.lizzardbackend.pojos.Statistic;
import at.kaindorf.lizzardbackend.pojos.User;
import at.kaindorf.lizzardbackend.pojos.Word;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.imageio.spi.ServiceRegistry;
import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * Project: Typing_Lizard_Backend
 * Author : Alexander Friedl
 * Date : 10.04.2024
 * Time : 11:08
 */
@RestController
@RequestMapping("/statistic")
@CrossOrigin("*")
@Slf4j
@RequiredArgsConstructor
public class StatiscticsService {

    private final StatisticRepository statisticRepo;

    // get the stats from a certain user
    // post a new stat


    @GetMapping("/user/{id}")
    public ResponseEntity<List<Statistic>> getStatsFromUserByID(@PathVariable Long id){

        List<Statistic> statisticsList = statisticRepo.findAll();
        if (statisticsList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(statisticsList);
    }

    @PostMapping("/")
    public ResponseEntity<String> addStatistic(@RequestBody Statistic statistic){

        if (statisticRepo.lastDate(statistic.getId()).isAfter(statistic.getDate()) ||
                statisticRepo.lastDate(statistic.getId()) == (statistic.getDate())){
            return ResponseEntity.badRequest().body("Already exists");
        }

        statisticRepo.save(statistic);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .build()
                .toUri();
        return ResponseEntity.created(location).body("Statistic Posted");
    }



}
