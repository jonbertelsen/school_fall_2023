package dat;

import dat.config.HibernateConfig;
import dat.dao.IStudentDAO;
import dat.dao.StudentDAOImpl;
import dat.model.Semester;
import dat.model.Student;
import dat.model.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class Main
{
    private static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
    private static IStudentDAO studentDAO = StudentDAOImpl.getInstance(emf);

    public static void main(String[] args)
    {
        System.out.println("Hello school!");
        populate();
        List<Student> studentList = studentDAO.findAllStudentsByFirstName("Egon");
        studentList.forEach(stud -> System.out.println(stud.getLastName()));
        System.out.println("Antal på semester: " + studentDAO.findTotalNumberOfStudentsBySemester("Backendprogrammering"));
        emf.close();

    }

    private static void populate()
    {
        try (EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            Student st1 = new Student("Egon","Olsen");
                em.persist(st1);
                Student st2 =  new Student("Benny","Hansen");
                em.persist(st2);
                Semester sem1 = new Semester("Backendprogrammering","På 3. semester");
                em.persist(sem1);
                Semester sem2 = new Semester("Frontendprogrammering","På 3. semester");
                em.persist(sem2);
                Teacher t1 = new Teacher("Jörg", "Oertel");
                em.persist(t1);
                Teacher t2 = new Teacher("Thomas", "Hartmann");
                em.persist(t2);
                Teacher t3 = new Teacher("Jon", "Bertelsen");
                em.persist(t3);

                sem1.addTeacher(t1);
                sem1.addTeacher(t2);
                sem2.addTeacher(t3);

                sem1.addStudent(st1);
                sem1.addStudent(st2);


            em.getTransaction().commit();



        }

    }
}