package at.kaindorf.lizzardbackend.pojos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Project: Typing_Lizzard_Backend
 * Author : Alexander Friedl
 * Date : 09.04.2024
 * Time : 14:02
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mode {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long modeId;

    private String modeName;

    private Double modeTime;

    private Double modeWords;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mode", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Word> wordList;

}