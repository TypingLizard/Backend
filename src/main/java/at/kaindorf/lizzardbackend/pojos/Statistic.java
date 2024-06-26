package at.kaindorf.lizzardbackend.pojos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Project: Typing_Lizard_Backend
 * Author : Alexander Friedl
 * Date : 09.04.2024
 * Time : 14:02
 */


// users get a statistic after every game which is saved
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Double wpm;

    private Double accuracy;

    private String testType;

    private Double typingTime;

    @Column(unique = true, nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime date;


    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private User user;

}
