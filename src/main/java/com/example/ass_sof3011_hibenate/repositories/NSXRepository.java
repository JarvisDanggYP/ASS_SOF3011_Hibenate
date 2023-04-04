package com.example.ass_sof3011_hibenate.repositories;

import com.example.ass_sof3011_hibenate.domain_models.NSX;
import com.example.ass_sof3011_hibenate.utilities.ConnectDB;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import jakarta.persistence.Query;
import java.util.List;


public class NSXRepository {
    private Session hSession;

    public NSXRepository() {
        this.hSession = ConnectDB.getFACTORY().openSession();
    }
    public List<NSX> findAll() {
        String hql = "SELECT cv FROM NSX cv ORDER BY cv.ma ASC ";
        TypedQuery<NSX> query = hSession.createQuery(hql, NSX.class);
        return query.getResultList();
    }

    public boolean insert(NSX nsx) {
        Transaction transaction = null;
        try {
            transaction = hSession.beginTransaction();
            hSession.persist(nsx);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            return false;
        }
    }

    public boolean update(NSX nsx) {
        Transaction transaction = null;
        try {
            transaction = hSession.beginTransaction();
            hSession.merge(nsx);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            return false;
        }
    }

    public boolean delete(NSX nsx) {
        Transaction transaction = null;
        try {
            transaction = hSession.beginTransaction();
            hSession.delete(nsx);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            return false;
        }
    }

    public NSX findByMa(String ma) {
        try {
            String hql = "SELECT cv FROM NSX cv WHERE cv.ma = ?1";
            TypedQuery<NSX> query = this.hSession.createQuery(hql, NSX.class);
            query.setParameter(1, ma);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    public NSX findById(String id)
    {
        Session session = ConnectDB.getFACTORY().openSession();
        return session.find(NSX.class,id);
    }

}
