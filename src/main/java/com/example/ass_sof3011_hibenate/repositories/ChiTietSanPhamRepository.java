package com.example.ass_sof3011_hibenate.repositories;

import com.example.ass_sof3011_hibenate.domain_models.*;
import com.example.ass_sof3011_hibenate.utilities.ConnectDB;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class ChiTietSanPhamRepository {
    private Session hSession;
    public ChiTietSanPhamRepository()
    {
        hSession = ConnectDB.getFACTORY().openSession();
    }
    public List<ChiTietSp> findAll() {
        Session session = ConnectDB.getFACTORY().openSession();
        String hql = "SELECT obj FROM ChiTietSp obj";
        TypedQuery<ChiTietSp> query = session.createQuery(hql, ChiTietSp.class);
        return query.getResultList();
    }

    public boolean insert(ChiTietSp chiTietSp) {
        Transaction transaction = null;
        try {
            transaction = hSession.beginTransaction();
            hSession.persist(chiTietSp);
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

    public boolean update(ChiTietSp chiTietSp) {
        Transaction transaction = null;
        try {
            transaction = hSession.beginTransaction();
            hSession.merge(chiTietSp);
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

    public boolean delete(ChiTietSp chiTietSp) {
        Transaction transaction = null;
        try {
            transaction = hSession.beginTransaction();
            hSession.delete(chiTietSp);
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

    public ChiTietSp findByID(UUID id)
    {
        String hql = "SELECT c FROM ChiTietSp c WHERE c.id = ?1";
        TypedQuery<ChiTietSp> query = this.hSession.createQuery(hql, ChiTietSp.class);
        query.setParameter(1, id);
        return query.getSingleResult();
    }
    public UUID findIdSanPhamById(UUID id) {
        Query query = hSession.createQuery("select ctsp.sanPham.id from ChiTietSp ctsp where id=:id");
        query.setParameter("id", id);
        UUID idSanPham = (UUID) query.getSingleResult();
        return idSanPham;
    }
    public UUID findIdNSXById(UUID id) {
        Query query = hSession.createQuery("select ctsp.nsx.id from ChiTietSp ctsp where id=:id");
        query.setParameter("id", id);
        UUID idNSX = (UUID) query.getSingleResult();
        return idNSX;
    }
    public UUID findIdMauSacById(UUID id) {
        Query query = hSession.createQuery("select ctsp.mauSac.id from ChiTietSp ctsp where id=:id");
        query.setParameter("id", id);
        UUID idMauSac = (UUID) query.getSingleResult();
        return idMauSac;
    }
    public UUID findIdDongSpById(UUID id) {
        Query query = hSession.createQuery("select ctsp.dongSp.id from ChiTietSp ctsp where id=:id");
        query.setParameter("id", id);
        UUID idDongSP = (UUID) query.getSingleResult();
        return idDongSP;
    }

}




