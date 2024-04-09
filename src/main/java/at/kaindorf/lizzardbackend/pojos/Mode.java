package at.kaindorf.lizzardbackend.pojos;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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




}
