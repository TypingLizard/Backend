package at.kaindorf.lizzardbackend.data;

import at.kaindorf.lizzardbackend.database.ModeRepository;
import at.kaindorf.lizzardbackend.database.WordRepository;
import at.kaindorf.lizzardbackend.pojos.Mode;
import at.kaindorf.lizzardbackend.pojos.Word;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Project: Typing_Lizard_Backend
 * Author : Alexander Friedl
 * Date : 18.06.2024
 * Time : 12:13
 */


@Component
@RequiredArgsConstructor
@Slf4j
public class InsertData {

    private final ModeRepository modeRepo;
    private final WordRepository wordRepo;

    @PostConstruct
    private void addData() {
        Mode modeNormal = new Mode();
        Mode modeLatin = new Mode();

        modeLatin.setModeId(1L);
        modeLatin.setModeName("LatinMode");
        modeLatin.setModeTime(30.0);

        modeNormal.setModeId(2L);
        modeNormal.setModeName("MostCommonWord");
        modeNormal.setModeTime(30.0);

        modeRepo.save(modeLatin);
        modeRepo.save(modeNormal);




        try {
            List<Word> words = readWordsFromFile(modeNormal, modeLatin);
            log.info(words.toString());
            wordRepo.saveAll(words);
        } catch (IOException e) {
            log.error(e.getMessage());
        }


    }

    public List<Word> readWordsFromFile(Mode modeNormal, Mode modeLatin) throws IOException {
        List<Word> words = new ArrayList<>();
        InputStream is = this.getClass().getResourceAsStream("/testWords.csv");
        if (is == null) {
            throw new IOException("File /testWords.csv not found");
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 4) {
                String wordName = parts[2];
                Long rating = Long.parseLong(parts[3]);
                Long wordId = Long.parseLong(parts[0]);


                 Word word = new Word();
                    word.setWordId(wordId);
                    word.setWordName(wordName);
                    word.setRating(rating);
                    if (wordId <=950) {
                        word.setMode(modeNormal);
                    }else {
                        word.setMode(modeLatin);
                    }
                    words.add(word);

            }
        }
        reader.close();
        return words;
    }
}




