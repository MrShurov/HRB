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
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Event> events = new ArrayList<>();

    public Team(String name,long winAmount,long loseAmount){
        this.name = name;
        this.winAmount = winAmount;
        this.loseAmount = loseAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;
        if (!super.equals(o)) return false;

        Team team = (Team) o;

        if (getWinAmount() != team.getWinAmount()) return false;
        if (getLoseAmount() != team.getLoseAmount()) return false;
        if (getId() != null ? !getId().equals(team.getId()) : team.getId() != null) return false;
        if (getName() != null ? !getName().equals(team.getName()) : team.getName() != null) return false;
        return getEvents() != null ? getEvents().equals(team.getEvents()) : team.getEvents() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (int) (getWinAmount() ^ (getWinAmount() >>> 32));
        result = 31 * result + (int) (getLoseAmount() ^ (getLoseAmount() >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", winAmount=" + winAmount +
                ", loseAmount=" + loseAmount +
                '}';
    }
}
