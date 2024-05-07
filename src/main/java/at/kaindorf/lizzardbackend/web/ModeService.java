package at.kaindorf.lizzardbackend.web;

import at.kaindorf.lizzardbackend.database.ModeRepository;
import at.kaindorf.lizzardbackend.pojos.Mode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

/**
 * Project: Typing_Lizard_Backend
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



    @GetMapping("/id/{id}")
    public ResponseEntity<Mode> getModeById(@PathVariable Long id){
        Optional<Mode> optionalMode = modeRepo.findById(id);
        return ResponseEntity.of(optionalMode);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Mode> getModebyName(@PathVariable String name){
        Long id = modeRepo.idFromName(name);
        Optional<Mode> optionalMode = modeRepo.findById(id);
        return ResponseEntity.of(optionalMode);
    }

    @PostMapping("/")
    public ResponseEntity<String> addMode(@RequestBody Mode mode){


        modeRepo.save(mode);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .build()
                .toUri();
        return ResponseEntity.created(location).body("Mode Posted");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteMode(@PathVariable Long id) {
        modeRepo.deleteById(id);
        return ResponseEntity.accepted().body(id);
    }


    @PatchMapping("/patch/{id}")
    public ResponseEntity<Boolean> changeMode(@PathVariable Long id, @RequestBody Mode updateddMode) {
        Optional<Mode> optionalMode = modeRepo.findById(id);
        if (optionalMode.isPresent()) {

            modeRepo.save(updateddMode);

            return ResponseEntity.accepted().body(true);
        }
        return ResponseEntity.notFound().build();
    }

    // get the mode
    // post a new mode
    // change a mode
}
