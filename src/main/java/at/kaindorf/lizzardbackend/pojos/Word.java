package at.kaindorf.lizzardbackend.pojos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Project: Typing_Lizzard_Backend
 * Author : Alexander Friedl
 * Date : 09.04.2024
 * Time : 14:03
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long wordId;

    @Column(unique = true)
    private String wordName;

    private Long rating;

    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "mode_id")
    private Mode mode;
}