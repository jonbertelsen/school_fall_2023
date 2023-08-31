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
public class Semester
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer Id;
    @Column(name="name", length = 50)
    private String name;
    @Column(name="description", length = 100)
    private String description;

    @ManyToMany
    private Set<Teacher> teachers = new HashSet<>();

    @OneToMany(mappedBy = "semester")
    private Set<Student> students = new HashSet<>();

    public void addStudent(Student student)
    {
        // Fjern evt. andre tilknytninger først
        if (student.getSemester() != null)
        {
            student.getSemester().students.remove(student);
        }
        students.add(student);
        if (student != null)
        {
            student.setSemester(this);
        }
    }

    public void addTeacher(Teacher teacher)
    {
        teachers.add(teacher);
        if (teacher != null)
        {
            teacher.getSemesters().add(this);
        }
    }


    public Semester(String name, String description)
    {
        this.name = name;
        this.description = description;
    }
}
