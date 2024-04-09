package at.kaindorf.lizzardbackend.pojos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private Long user_id;

    private String userName;

    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Statistic> statisticList;
}
