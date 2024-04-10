package at.kaindorf.lizzardbackend.web;

import at.kaindorf.lizzardbackend.database.StatisticRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.spi.ServiceRegistry;

/**
 * Project: Typing_Lizzard_Backend
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
}
