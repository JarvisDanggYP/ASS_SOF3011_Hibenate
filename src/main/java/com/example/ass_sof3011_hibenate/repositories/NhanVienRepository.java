package com.example.ass_sof3011_hibenate.repositories;

import com.example.ass_sof3011_hibenate.domain_models.NSX;
import com.example.ass_sof3011_hibenate.domain_models.NhanVien;
import com.example.ass_sof3011_hibenate.utilities.ConnectDB;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import jakarta.persistence.Query;

import java.util.List;

public class NhanVienRepository {
    private Session hSession;

    public NhanVienRepository() {
        this.hSession = ConnectDB.getFACTORY().openSession();
    }
    public List<NhanVien> findAll() {
        String hql = "SELECT cv FROM NhanVien cv ORDER BY cv.ma ASC ";
        TypedQuery<NhanVien> query = hSession.createQuery(hql, NhanVien.class);
        return query.getResultList();
    }

    public boolean insert(NhanVien nhanVien) {
        Transaction transaction = null;
        try {
            transaction = hSession.beginTransaction();
            hSession.persist(nhanVien);
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

    public boolean update(NhanVien nhanVien) {
        Transaction transaction = null;
        try {
            transaction = hSession.beginTransaction();
            hSession.merge(nhanVien);
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

    public boolean delete(NhanVien nhanVien) {
        Transaction transaction = null;
        try {
            transaction = hSession.beginTransaction();
            hSession.delete(nhanVien);
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

    public NhanVien findByMa(String ma) {
        try {
            String hql = "SELECT cv FROM NhanVien cv WHERE cv.ma = ?1";
            TypedQuery<NhanVien> query = this.hSession.createQuery(hql, NhanVien.class);
            query.setParameter(1, ma);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    public NhanVien findById(String id)
    {
        Session session = ConnectDB.getFACTORY().openSession();
        return session.find(NhanVien.class,id);
    }
    public NhanVien login(String ma, String matKhau) {
        String hql = "SELECT nv FROM NhanVien nv " +
                "WHERE nv.ma = ?1 AND nv.matKhau = ?2";
        TypedQuery<NhanVien> q = this.hSession.createQuery(hql, NhanVien.class);
        q.setParameter(1, ma);
        q.setParameter(2, matKhau);

        try {
            NhanVien nhanVien = q.getSingleResult();
            return nhanVien;
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
}
