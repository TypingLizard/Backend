package at.kaindorf.lizzardbackend.web;

import at.kaindorf.lizzardbackend.database.ModeRepository;
import at.kaindorf.lizzardbackend.database.WordRepository;
import at.kaindorf.lizzardbackend.pojos.Mode;
import at.kaindorf.lizzardbackend.pojos.Word;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

/**
 * Project: Typing_Lizard_Backend
 * Author : Alexander Friedl
 * Date : 10.04.2024
 * Time : 11:08
 */
@RestController
@RequestMapping("/word")
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
@RequiredArgsConstructor
public class WordService {


    private final WordRepository wordRepo;
    private final ModeRepository modeRepo;

    private static final int PAGE_SIZE = 10;



    @GetMapping("/")
    public ResponseEntity<Page<Word>> getAllWords(@RequestParam(name = "pageNo", required = false, defaultValue = "0") Integer pageNo) {

        Pageable pageable = PageRequest.of(pageNo, PAGE_SIZE);


        Page<Word> wordList = wordRepo.findAll(pageable);
        if (wordList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(wordList);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteWordById(@PathVariable Long id) {
        wordRepo.deleteById(id);
        return ResponseEntity.accepted().body(id);
    }


    @PostMapping("/{modeId}")
    public ResponseEntity<String> addMode(@RequestBody Word word, @PathVariable Long modeId){

        if (wordRepo.idFromName(word.getWordName()) != null) {
            return ResponseEntity.badRequest().body("Already exists");
        }

        System.out.println(modeId);

        Optional<Mode> mode = modeRepo.findById(modeId);

        mode.ifPresent(word::setMode);

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
