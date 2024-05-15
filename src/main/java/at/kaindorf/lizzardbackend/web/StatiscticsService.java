package at.kaindorf.lizzardbackend.web;

import at.kaindorf.lizzardbackend.database.StatisticRepository;
import at.kaindorf.lizzardbackend.database.UserRepository;
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
    private final UserRepository userRepository;


    // get the stats from a certain user
    // post a new stat


    @GetMapping("/user/{id}")
    public ResponseEntity<List<Statistic>> getStatsFromUserByID(@PathVariable Long id){

        List<Statistic> statisticsList = statisticRepo.statsFromUser(id);
        if (statisticsList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(statisticsList);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<String> addStatistic(@RequestBody Statistic statistic, @PathVariable Long userId){


        System.out.println(statisticRepo.lastDate(userId));
        System.out.println(statistic.getDate());

        if (statisticRepo.lastDate(userId).isAfter(statistic.getDate()) ||
                statisticRepo.lastDate(userId).equals(statistic.getDate())){
            return ResponseEntity.badRequest().body("Wrong Date, to early");
        }



        Optional<User> user = userRepository.findById(userId);

        user.ifPresent(statistic::setUser);


        statisticRepo.save(statistic);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .build()
                .toUri();
        return ResponseEntity.created(location).body("Statistic Posted");
    }



}
