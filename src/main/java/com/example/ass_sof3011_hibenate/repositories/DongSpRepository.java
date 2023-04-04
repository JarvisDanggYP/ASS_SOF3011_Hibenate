package com.example.ass_sof3011_hibenate.repositories;
import com.example.ass_sof3011_hibenate.domain_models.DongSp;
import com.example.ass_sof3011_hibenate.utilities.ConnectDB;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class DongSpRepository {

    private Session hSession;

    public DongSpRepository() {
        this.hSession = ConnectDB.getFACTORY().openSession();
    }
    public List<DongSp> findAll() {
        String hql = "SELECT cv FROM DongSp cv ORDER BY cv.ma ASC ";
        TypedQuery<DongSp> query = hSession.createQuery(hql, DongSp.class);
        return query.getResultList();
    }

    public boolean insert(DongSp dongSp) {
        Transaction transaction = null;
        try {
            transaction = hSession.beginTransaction();
            hSession.persist(dongSp);
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

    public boolean update(DongSp dongSp) {
        Transaction transaction = null;
        try {
            transaction = hSession.beginTransaction();
            hSession.merge(dongSp);
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

    public boolean delete(DongSp dongSp) {
        Transaction transaction = null;
        try {
            transaction = hSession.beginTransaction();
            hSession.delete(dongSp);
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

    public DongSp findByMa(String ma) {
        try {
            String hql = "SELECT cv FROM DongSp cv WHERE cv.ma = ?1";
            TypedQuery<DongSp> query = this.hSession.createQuery(hql, DongSp.class);
            query.setParameter(1, ma);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    public DongSp findById(String id)
    {
        Session session = ConnectDB.getFACTORY().openSession();
        return session.find(DongSp.class,id);
    }
}
