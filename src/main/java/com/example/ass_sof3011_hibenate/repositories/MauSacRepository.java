
package com.example.ass_sof3011_hibenate.repositories;
import com.example.ass_sof3011_hibenate.domain_models.KhachHang;
import com.example.ass_sof3011_hibenate.domain_models.MauSac;
import com.example.ass_sof3011_hibenate.utilities.ConnectDB;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import jakarta.persistence.Query;
import java.util.List;

public class MauSacRepository {
    private Session hSession;

    public MauSacRepository() {
        this.hSession = ConnectDB.getFACTORY().openSession();
    }
    public List<MauSac> findAll() {
        String hql = "SELECT ms FROM MauSac ms ORDER BY ms.ma ASC";
        TypedQuery<MauSac> query = hSession.createQuery(hql, MauSac.class);
        return query.getResultList();
    }

    public boolean insert(MauSac mauSac) {
        Transaction transaction = null;
        try {
            transaction = hSession.beginTransaction();
            hSession.persist(mauSac);
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
    public boolean update(MauSac mauSac) {
        Transaction transaction = null;
        try {
            transaction = hSession.beginTransaction();
            hSession.merge(mauSac);
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

    public boolean delete(MauSac mauSac) {
        Transaction transaction = null;
        try {
            transaction = hSession.beginTransaction();
            hSession.delete(mauSac);
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

    public MauSac findById(String id) {
        return this.hSession.find(MauSac.class, id);
    }

    public MauSac findByMa(String ma) {
        try {
            String hql = "SELECT kh FROM MauSac kh WHERE kh.ma = ?1";
            TypedQuery<MauSac> query = this.hSession.createQuery(hql, MauSac.class);
            query.setParameter(1, ma);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
