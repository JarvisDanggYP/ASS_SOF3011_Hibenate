/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ass_sof3011_hibenate.repositories;
import com.example.ass_sof3011_hibenate.domain_models.ChiTietSp;
import com.example.ass_sof3011_hibenate.domain_models.HoaDonChiTiet;
import com.example.ass_sof3011_hibenate.utilities.ConnectDB;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

public class HoaDonChiTietRepository {
    private Session hSession;
    public HoaDonChiTietRepository()
    {
        hSession = ConnectDB.getFACTORY().openSession();
    }
    public List<HoaDonChiTiet> findAll() {
        Session session = ConnectDB.getFACTORY().openSession();
        String hql = "SELECT obj FROM HoaDonChiTiet obj";
        TypedQuery<HoaDonChiTiet> query = session.createQuery(hql, HoaDonChiTiet.class);
        return query.getResultList();
    }

    public boolean insert(HoaDonChiTiet hoaDonChiTiet) {
        Transaction transaction = null;
        try {
            transaction = hSession.beginTransaction();
            hSession.persist(hoaDonChiTiet);
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

    public boolean update(HoaDonChiTiet hoaDonChiTiet) {
        Transaction transaction = null;
        try {
            transaction = hSession.beginTransaction();
            hSession.merge(hoaDonChiTiet);
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

    public boolean delete(HoaDonChiTiet hoaDonChiTiet) {
        Transaction transaction = null;
        try {
            transaction = hSession.beginTransaction();
            hSession.delete(hoaDonChiTiet);
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

    public HoaDonChiTiet findByID(UUID id)
    {
        String hql = "SELECT c FROM HoaDonChiTiet c WHERE c.hoaDon.id = ?1";
        TypedQuery<HoaDonChiTiet> query = this.hSession.createQuery(hql, HoaDonChiTiet.class);
        query.setParameter(1, id);
        return query.getSingleResult();
    }

}
