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
    @OneToOne
    private Event event;
    @Column(name = "USER_ID")
    private Long userId;
    @DecimalMin(value = "1.0", message = "Coefficient must be a greater than 1")
    @Column(name = "COEFFICIENT")
    private double coefficient;
    @DecimalMin(value = "0", message = "Bet must be a greater than 0")
    @Column(name = "BET")
    private double bet;
    @Column(name = "VALUE_BET")
    private String valueBet;

    public Bet(Event event, Long userId, double coefficient, double bet, String valueBet) {
        this.event = event;
        this.userId = userId;
        this.coefficient = coefficient;
        this.bet = bet;
        this.valueBet = valueBet;
    }
}
