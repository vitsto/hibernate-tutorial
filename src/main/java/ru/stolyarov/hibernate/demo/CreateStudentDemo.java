package ru.stolyarov.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.stolyarov.demo.entity.Student;

public class CreateStudentDemo {
    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create session

        try (factory) {
            Session session = factory.getCurrentSession();
            //create a student object
            System.out.println("Creating new student object...");
            Student tempStudent = new Student("Paul", "Wall", "paul@luv2code.com");

            //start a transaction
            session.beginTransaction();

            //save the student object
            System.out.println("Saving student...");
            session.save(tempStudent);

            //commit transaction
            session.getTransaction().commit();

            System.out.println("Done");
        }

    }
}
