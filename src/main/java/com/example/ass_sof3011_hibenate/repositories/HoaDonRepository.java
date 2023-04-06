/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ass_sof3011_hibenate.repositories;
import com.example.ass_sof3011_hibenate.domain_models.HoaDon;
import com.example.ass_sof3011_hibenate.utilities.ConnectDB;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class HoaDonRepository {
    private Session hSession;
    public HoaDonRepository()
    {
        this.hSession = ConnectDB.getFACTORY().openSession();
    }

    public List<HoaDon> findAll() {
        Session session = ConnectDB.getFACTORY().openSession();
        String hql = "SELECT obj FROM HoaDon obj";
        TypedQuery<HoaDon> query = session.createQuery(hql, HoaDon.class);
        return query.getResultList();
    }

    public boolean insert(HoaDon hoaDon) {
        Transaction transaction = null;
        try {
            transaction = hSession.beginTransaction();
            hSession.persist(hoaDon);
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

    public boolean update(HoaDon hoaDon) {
        Transaction transaction = null;
        try {
            transaction = hSession.beginTransaction();
            hSession.merge(hoaDon);
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

    public boolean delete(HoaDon hoaDon) {
        Transaction transaction = null;
        try {
            transaction = hSession.beginTransaction();
            hSession.delete(hoaDon);
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

    public HoaDon findByMa(String ma) {
        try {
            String hql = "SELECT hd FROM HoaDon hd WHERE hd.ma = ?1";
            TypedQuery<HoaDon> query = this.hSession.createQuery(hql, HoaDon.class);
            query.setParameter(1, ma);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    public HoaDon findById(String id)
    {
        Session session = ConnectDB.getFACTORY().openSession();
        return session.find(HoaDon.class,id);
    }
}
