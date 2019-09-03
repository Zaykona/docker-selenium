package db.dao;

import db.HibernateUtil;
import models.Addresses;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.stream.Collectors;

public class AddressDao {

    public void saveAddress(Addresses address) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(address);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Addresses> getAddresses() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("From Addresses", Addresses.class).list();
        }
    }

    public List<Addresses> getInvalidAddresses() {
        return getAddresses().stream().
                filter(addresses -> addresses.getValid().equals(false)).collect(Collectors.toList());

    }

    public List<Addresses> getValidAddresses() {
        return getAddresses().stream().
                filter(addresses -> addresses.getValid().equals(true)).collect(Collectors.toList());

    }


}
