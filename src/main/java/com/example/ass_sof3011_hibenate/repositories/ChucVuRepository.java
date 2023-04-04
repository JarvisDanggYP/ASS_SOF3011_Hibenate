package com.example.ass_sof3011_hibenate.repositories;

import com.example.ass_sof3011_hibenate.domain_models.ChucVu;
import com.example.ass_sof3011_hibenate.domain_models.KhachHang;
import com.example.ass_sof3011_hibenate.utilities.ConnectDB;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.Query;
import java.util.List;

public class ChucVuRepository {
    private Session hSession;

    public ChucVuRepository() {
        this.hSession = ConnectDB.getFACTORY().openSession();
    }
    public List<ChucVu> findAll() {
        String hql = "SELECT cv FROM ChucVu cv ORDER BY cv.ma ASC ";
        TypedQuery<ChucVu> query = hSession.createQuery(hql, ChucVu.class);
        return query.getResultList();
    }

    public boolean insert(ChucVu chucVu) {
        Transaction transaction = null;
        try {
            transaction = hSession.beginTransaction();
            hSession.persist(chucVu);
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

    public boolean update(ChucVu chucVu) {
        Transaction transaction = null;
        try {
            transaction = hSession.beginTransaction();
            hSession.merge(chucVu);
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

    public boolean delete(ChucVu chucVu) {
        Transaction transaction = null;
        try {
            transaction = hSession.beginTransaction();
            hSession.delete(chucVu);
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

    public ChucVu findByMa(String ma) {
        try {
            String hql = "SELECT cv FROM ChucVu cv WHERE cv.ma = ?1";
            TypedQuery<ChucVu> query = this.hSession.createQuery(hql, ChucVu.class);
            query.setParameter(1, ma);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    public ChucVu findById(String id)
    {
        Session session = ConnectDB.getFACTORY().openSession();
        return session.find(ChucVu.class,id);
    }

}
