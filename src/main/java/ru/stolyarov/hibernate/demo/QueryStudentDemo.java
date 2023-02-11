package ru.stolyarov.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.stolyarov.demo.entity.Student;

import java.util.List;

public class QueryStudentDemo {
    public static void main(String[] args) {
        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try (factory) {
            //start a transaction
            session.beginTransaction();

            //query students
            List<Student> students = session.createQuery("from Student").getResultList();

            //display the students
            students.forEach(System.out::println);

            //query students: lasName='Doe'
            students = session.createQuery("from Student s where s.lastName='Doe'").getResultList();

            //display the students
            System.out.printf("\n\nStudents who have last name of Doe");
            students.forEach(System.out::println);

            //query students: lasName='Doe' OR firstName='Daffy'
            students = session.createQuery("from Student s where s.lastName='Doe' OR s.firstName='Daffy'")
                    .getResultList();

            //display the students
            System.out.printf("\n\nStudents who have last name of Doe or first name of Daffy");
            students.forEach(System.out::println);

            //query students where email LIKE '%luv2code.com'
            students = session.createQuery("from Student s where s.email LIKE '%luv2code.com'").getResultList();

            //display the students
            System.out.printf("\n\nStudents whose email end with luv2code.com\n");
            students.forEach(System.out::println);

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        }

    }
}
