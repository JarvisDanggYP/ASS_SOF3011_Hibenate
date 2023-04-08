package com.example.ass_sof3011_hibenate.repositories;

import com.example.ass_sof3011_hibenate.domain_models.GioHang;
import com.example.ass_sof3011_hibenate.domain_models.NhanVien;
import com.example.ass_sof3011_hibenate.utilities.ConnectDB;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

public class GioHangRepository {
    private Session hSession;

    public GioHangRepository() {
        this.hSession = ConnectDB.getFACTORY().openSession();
    }
    public List<GioHang> findAll() {
        Session session = ConnectDB.getFACTORY().openSession();
        String hql = "SELECT gh FROM GioHang gh ORDER BY gh.ma ASC ";
        TypedQuery<GioHang> query = session.createQuery(hql, GioHang.class);
        return query.getResultList();
    }

    public boolean insert(GioHang gioHang) {
        Transaction transaction = null;
        try {
            transaction = hSession.beginTransaction();
            hSession.persist(gioHang);
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

    public boolean update(GioHang gioHang) {
        Transaction transaction = null;
        try {
            transaction = hSession.beginTransaction();
            hSession.merge(gioHang);
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

    public boolean delete(GioHang gioHang) {
        Transaction transaction = null;
        try {
            transaction = hSession.beginTransaction();
            hSession.delete(gioHang);
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
    public GioHang findByMa(String ma) {
        try {
            String hql = "SELECT cv FROM GioHang cv WHERE cv.ma = ?1";
            TypedQuery<GioHang> query = this.hSession.createQuery(hql, GioHang.class);
            query.setParameter(1, ma);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    public UUID findIdKhachHangByMa(UUID ma) {
        Query query = hSession.createQuery("select kh.khachHang.id from GioHang kh where ma=:ma");
        query.setParameter("ma", ma);
        UUID idKhachHang = (UUID) query.getSingleResult();
        return idKhachHang;
    }
}
