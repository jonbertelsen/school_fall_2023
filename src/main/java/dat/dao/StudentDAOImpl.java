package dat.dao;

import dat.model.Semester;
import dat.model.Student;
import dat.model.StudentInfo;
import dat.model.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class StudentDAOImpl implements IStudentDAO
{
    private static EntityManagerFactory emf;
    private static StudentDAOImpl studentDAO;

    private StudentDAOImpl(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    public static StudentDAOImpl getInstance(EntityManagerFactory emf)
    {
        if (studentDAO == null)
        {
            studentDAO = new StudentDAOImpl(emf);
        }
        return studentDAO;
    }

    @Override
    public List<Student> findAllStudentsByFirstName(String firstName)
    {
        try (EntityManager em = emf.createEntityManager())
        {
            TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s WHERE s.firstName = :firstName", Student.class);
            query.setParameter("firstName", firstName);
            return query.getResultList();
        }
    }

    @Override
    public List<Student> findAllStudentsByLastName(String lastName)
    {
        try (EntityManager em = emf.createEntityManager())
        {
            TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s WHERE s.lastName = :lastName", Student.class);
            query.setParameter("lastName", lastName);
            return query.getResultList();
        }
    }

    @Override
    public long findTotalNumberOfStudentsBySemester(String semesterName)
    {
        try (EntityManager em = emf.createEntityManager())
        {
            TypedQuery<Long> query = em.createQuery("SELECT count(stud) FROM Semester sem JOIN sem.students stud WHERE sem.name = :semesterName", Long.class );
            query.setParameter("semesterName", semesterName);
            return query.getSingleResult();
        }
    }

    @Override
    public long findTotalNumberOfStudentsByTeacher(Teacher teacher)
    {
        return 0;
    }

    @Override
    public Teacher findTeacherWithMostSemesters()
    {
        return null;
    }

    @Override
    public Semester findSemesterWithFewestStudents()
    {
        return null;
    }

    @Override
    public StudentInfo getAllStudentInfo(int id)
    {
        return null;
    }
}
