/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ass_sof3011_hibenate.repositories;
import com.example.ass_sof3011_hibenate.domain_models.ChucVu;
import com.example.ass_sof3011_hibenate.domain_models.SanPham;
import com.example.ass_sof3011_hibenate.utilities.ConnectDB;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class SanPhamRepository {

    private Session hSession;

    public SanPhamRepository() {
        this.hSession = ConnectDB.getFACTORY().openSession();
    }
    public List<SanPham> findAll() {
        String hql = "SELECT cv FROM SanPham cv ORDER BY cv.ma ASC ";
        TypedQuery<SanPham> query = hSession.createQuery(hql, SanPham.class);
        return query.getResultList();
    }

    public boolean insert(SanPham sanPham) {
        Transaction transaction = null;
        try {
            transaction = hSession.beginTransaction();
            hSession.persist(sanPham);
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

    public boolean update(SanPham sanPham) {
        Transaction transaction = null;
        try {
            transaction = hSession.beginTransaction();
            hSession.merge(sanPham);
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

    public boolean delete(SanPham sanPham) {
        Transaction transaction = null;
        try {
            transaction = hSession.beginTransaction();
            hSession.delete(sanPham);
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

    public SanPham findByMa(String ma) {
        try {
            String hql = "SELECT cv FROM SanPham cv WHERE cv.ma = ?1";
            TypedQuery<SanPham> query = this.hSession.createQuery(hql, SanPham.class);
            query.setParameter(1, ma);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    public SanPham findById(String id)
    {
        Session session = ConnectDB.getFACTORY().openSession();
        return session.find(SanPham.class,id);
    }
}
