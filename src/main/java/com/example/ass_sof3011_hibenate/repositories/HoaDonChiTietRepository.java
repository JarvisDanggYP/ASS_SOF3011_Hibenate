///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.example.ass_sof3011_hibenate.repositories;
//import com.example.ass_sof3011_hibenate.entities.HoaDonChiTiet;
//import com.example.ass_sof3011_hibenate.utilities.ConnectDB;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//
//import javax.persistence.Query;
//import java.util.List;
//
///**
// *
// * @author anhkon
// */
//public class HoaDonChiTietRepository {
//
//    public boolean them(HoaDonChiTiet hdct) {
//        Transaction transaction = null;
//        try ( Session session = ConnectDB.getFACTORY().openSession()) {
//            transaction = session.beginTransaction();
//            session.save(hdct);
//            transaction.commit();
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    public List<HoaDonChiTiet> getLista(String id) {
//        Session session = ConnectDB.getFACTORY().openSession();
//        Query query = session.createQuery("select  p from HoaDonChiTiet p where hoaDon.id=:id ");
//        query.setParameter("id",id);
//        List<HoaDonChiTiet> lst = query.getResultList();
//        return lst;
//
//    }
//
//}
