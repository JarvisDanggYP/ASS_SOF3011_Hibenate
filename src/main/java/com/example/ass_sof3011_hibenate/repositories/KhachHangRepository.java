package com.example.ass_sof3011_hibenate.repositories;

import com.example.ass_sof3011_hibenate.domain_models.KhachHang;
import com.example.ass_sof3011_hibenate.utilities.ConnectDB;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class KhachHangRepository {
    private Session hSession;

    public KhachHangRepository() {
        this.hSession = ConnectDB.getFACTORY().openSession();
    }

    public List<KhachHang> findAll() {
        String hql = "SELECT kh FROM KhachHang kh ORDER BY kh.ma ASC ";
        TypedQuery<KhachHang> query = hSession.createQuery(hql, KhachHang.class);
        return query.getResultList();
    }

    public boolean insert(KhachHang khachHang) {
        Transaction transaction = null;
        try {
            transaction = hSession.beginTransaction();
            hSession.persist(khachHang);
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

    public boolean update(KhachHang khachHang) {
        Transaction transaction = null;
        try {
            transaction = hSession.beginTransaction();
            hSession.merge(khachHang);
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

    public boolean delete(KhachHang khachHang) {
        Transaction transaction = null;
        try {
            transaction = hSession.beginTransaction();
            hSession.delete(khachHang);
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

    public KhachHang findById(String id) {
        return this.hSession.find(KhachHang.class, id);
    }

    public KhachHang findByMa(String ma) {
        try {
            String hql = "SELECT kh FROM KhachHang kh WHERE kh.ma = ?1";
            TypedQuery<KhachHang> query = this.hSession.createQuery(hql, KhachHang.class);
            query.setParameter(1, ma);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
