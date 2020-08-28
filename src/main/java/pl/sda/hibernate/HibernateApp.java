package pl.sda.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.sda.hibernate.entity.Course;
import pl.sda.hibernate.entity.Student;
import pl.sda.hibernate.entity.Teacher;

public class HibernateApp {

    public static void main(String[] args) {

        final SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Teacher.class)
                .buildSessionFactory();

    }
}
