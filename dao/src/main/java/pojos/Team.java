package pojos;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(exclude = {"events"})
@ToString(exclude = {"events"})
@Table(name = "TEAM")
public class Team implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEAM_ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "WIN_AMOUNT")
    private long winAmount;
    @Column(name = "LOSE_AMOUNT")
    private long loseAmount;
    @ManyToMany
    private List<Event> events = new ArrayList<>();

    public Team(String name,long winAmount,long loseAmount){
        this.name = name;
        this.winAmount = winAmount;
        this.loseAmount = loseAmount;
    }
}
