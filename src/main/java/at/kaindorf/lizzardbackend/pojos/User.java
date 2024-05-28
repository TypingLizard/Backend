package at.kaindorf.lizzardbackend.pojos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Player")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userId;

    private String userName;

    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Statistic> statisticList;
}
