package at.kaindorf.lizzardbackend.web;

import at.kaindorf.lizzardbackend.database.WordRepository;
import at.kaindorf.lizzardbackend.pojos.Mode;
import at.kaindorf.lizzardbackend.pojos.Word;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
@RequestMapping("/word")
@CrossOrigin("*")
@Slf4j
@RequiredArgsConstructor
public class WordService {


    private final WordRepository wordRepo;


    @GetMapping("/")
    public ResponseEntity<List<Word>> getAllWords( ) {


        List<Word> wordList = wordRepo.findAll();
        if (wordList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(wordList);
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<Word> getModeById(@PathVariable Long id){
        Optional<Word> optionalWord = wordRepo.findById(id);

        return ResponseEntity.of(optionalWord);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteMode(@PathVariable Long id) {
        wordRepo.deleteById(id);
        return ResponseEntity.accepted().body(id);
    }


    @PostMapping("/")
    public ResponseEntity<String> addMode(@RequestBody Word word){

        if (wordRepo.idFromName(word.getWordName()) != null) {
            return ResponseEntity.badRequest().body("Already exists");
        }

        wordRepo.save(word);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .build()
                .toUri();
        return ResponseEntity.created(location).body("Word Posted");
    }


    //get Words by mode ID
    //post new words in there?

}
