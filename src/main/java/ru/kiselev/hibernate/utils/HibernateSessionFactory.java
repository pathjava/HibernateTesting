package ru.kiselev.hibernate.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Oleg Kiselev
 */
public class HibernateSessionFactory {

    private static SessionFactory sessionFactory;

    public HibernateSessionFactory() {
    }

    public static Session getSession() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();

                sessionFactory = configuration.buildSessionFactory();
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
        return sessionFactory.openSession();
    }
}
