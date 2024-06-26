package at.kaindorf.lizzardbackend.pojos;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Project: Typing_Lizard_Backend
 * Author : Alexander Friedl
 * Date : 09.04.2024
 * Time : 14:02
 */


// every word is inside a mode and if you get the mod to get all the words
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mode {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long modeId;

    @Column(unique = true)
    private String modeName;

    private Double modeTime;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mode", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Word> wordList;

}
