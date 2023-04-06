package com.example.ass_sof3011_hibenate.repositories;

import com.example.ass_sof3011_hibenate.domain_models.CuaHang;
import com.example.ass_sof3011_hibenate.domain_models.NhanVien;
import com.example.ass_sof3011_hibenate.utilities.ConnectDB;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CuaHangRepository {
    private Session hSession;

    public CuaHangRepository()
    {
        this.hSession = ConnectDB.getFACTORY().openSession();
    }

    public boolean insert(CuaHang cuaHang) {
        Transaction transaction = null;
        try {
            transaction = hSession.beginTransaction();
            hSession.persist(cuaHang);
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

    public boolean update(CuaHang cuaHang) {
        Transaction transaction = null;
        try {
            transaction = hSession.beginTransaction();
            hSession.merge(cuaHang);
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

    public boolean delete(CuaHang cuaHang) {
        Transaction transaction = null;
        try {
            transaction = hSession.beginTransaction();
            hSession.delete(cuaHang);
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

    public CuaHang findById(String id)
    {
        return this.hSession.find(CuaHang.class, id);
    }

    public List<CuaHang> findAll()
    {
        Session session = ConnectDB.getFACTORY().openSession();
        String hql = "SELECT ch FROM CuaHang ch";
        TypedQuery<CuaHang> query = session.createQuery(hql, CuaHang.class);
        return query.getResultList();
    }

    public CuaHang findByMa(String ma)
    {
        String hql = "SELECT ch FROM CuaHang ch WHERE ch.ma = ?1";
        TypedQuery<CuaHang> query = this.hSession.createQuery(hql, CuaHang.class);
        query.setParameter(1, ma);
        try {
            CuaHang cuaHang = query.getSingleResult();
            return cuaHang;
        } catch (NoResultException e){
            e.printStackTrace();
            return null;
        }
    }
}
