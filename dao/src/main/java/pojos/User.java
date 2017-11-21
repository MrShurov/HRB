package pojos;

import lombok.*;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(exclude = {"info"})
@ToString(exclude = {"info"})
@Table(name = "USER")
public class User implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "USERNAME")
    @Pattern(regexp="^[A-Z]+[a-z]+$", message="Username must be alphanumeric with no spaces and first capital")
    private String username;
    @Column(name = "PASSWORD")
    @Size(min = 6,max = 30, message = "Password must be min size 6 and max size 30")
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    private Info info;

    public User(String username,String password){
        this.username = username;
        this.password = password;
    }
}
