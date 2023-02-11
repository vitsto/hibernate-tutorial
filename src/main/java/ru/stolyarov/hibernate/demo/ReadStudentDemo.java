package ru.stolyarov.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.stolyarov.demo.entity.Student;

import javax.swing.text.Style;

public class ReadStudentDemo {
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
            Student tempStudent = new Student("Daffy", "Duck", "daffy@luv2code.com");

            //start a transaction
            session.beginTransaction();

            //save the student object
            System.out.println("Saving student...");
            System.out.println(tempStudent);
            session.save(tempStudent);

            //commit transaction
            session.getTransaction().commit();


            //find out the student's id: primary key
            System.out.println("Saved student. Generated id: " + tempStudent.getId());

            //now get a new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            //retrieve student based on id: primary key
            System.out.println("\nGetting student with id: " + tempStudent.getId());

            Student myStudent = session.get(Student.class, tempStudent.getId());

            System.out.println("Get complete: " + myStudent);

            //commit the transaction
            session.getTransaction().commit();

            System.out.println("Done");
        }

    }
}
