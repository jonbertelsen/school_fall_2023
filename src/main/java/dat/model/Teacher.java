package dat.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Teacher
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer Id;
    @Column(name="first_name", length = 50)
    private String firstName;
    @Column(name="last_name", length = 100)
    private String lastName;

    @ManyToMany(mappedBy = "teachers")
    private Set<Semester> semesters = new HashSet<>();

    public Teacher(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
