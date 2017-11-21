package pojos;

import enums.Status;
import enums.UserRole;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"user"})
@ToString(exclude = {"user"})
@Entity
@Table(name = "info")
public class Info implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ROLE")
    private String role = UserRole.USER.getRole();
    @Column(name = "STATUS")
    private String status = Status.ACTIVE.getStatus();
    @Column(name = "BALANCE")
    private Double balance;
    @OneToOne(mappedBy = "info")
    @PrimaryKeyJoinColumn
    private User user;

    public Info(Double balance, User user){
        this.balance = balance;
        this.user = user;
    }
}
