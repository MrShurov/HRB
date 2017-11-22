package pojos;

import javafx.beans.DefaultProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EVENT")
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EVENT_ID")
    private Long id;
    @Column(name = "WIN1_COEFFICIENT")
    private double win1Coefficient;
    @Column(name = "DRAW_COEFFICIENT")
    private double drawCoefficient;
    @Column(name = "WIN2_COEFFICIENT")
    private double win2Coefficient;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Team team1;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Team team2;
    @Column(name = "EVENT_RESULT")
    private String eventResult;

    public Event(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
    }
}