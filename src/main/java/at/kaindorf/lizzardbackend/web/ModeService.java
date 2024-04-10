package at.kaindorf.lizzardbackend.web;

import at.kaindorf.lizzardbackend.database.ModeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project: Typing_Lizzard_Backend
 * Author : Alexander Friedl
 * Date : 10.04.2024
 * Time : 11:07
 */
@RestController
@RequestMapping("/mode")
@CrossOrigin("*")
@Slf4j
@RequiredArgsConstructor
public class ModeService {

    private final ModeRepository modeRepo;
}
