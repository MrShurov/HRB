package pojos;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(exclude = {"event"})
@ToString(exclude = {"event"})
@Table(name = "BET")
public class Bet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "EVENT_ID")
    private Long event;
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "POSSIBLE_WIN")
    private double possibleWin;
    @Column(name = "VALUE_BET")
    private String valueBet;
    @Column(name = "STATUS")
    private String status;

    public Bet(Long event, Long userId, double possibleWin, String valueBet) {
        this.event = event;
        this.userId = userId;
        this.possibleWin = possibleWin;
        this.valueBet = valueBet;
    }
}
