///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.example.ass_sof3011_hibenate.repositories;
//import com.example.ass_sof3011_hibenate.entities.HoaDon;
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
//public class HoaDonRepository {
//
//    public List<HoaDon> getList() {
//        Session session = ConnectDB.getFACTORY().openSession();
//        Query query = session.createQuery(" select h from HoaDon h");
//        List<HoaDon> lst = query.getResultList();
//        return lst;
//
//    }
//    public List<HoaDon> timKiemChoThanhToan() {
//        Session session = ConnectDB.getFACTORY().openSession();
//        Query query = session.createQuery(" select h from HoaDon h where h.trangThai =: tt");
//        query.setParameter("tt",0);
//        List<HoaDon> lst = query.getResultList();
//        return lst;
//
//    }
//    public List<HoaDon> timKiemDaThanhToan() {
//        Session session = ConnectDB.getFACTORY().openSession();
//        Query query = session.createQuery(" select h from HoaDon h where h.trangThai =: tt");
//        query.setParameter("tt",1);
//        List<HoaDon> lst = query.getResultList();
//        return lst;
//
//    }
//
//    public List<String> getIdHoaDon(String ma) {
//        Session session = ConnectDB.getFACTORY().openSession();
//        Query query = session.createQuery(" select h.id from HoaDon h where h.ma=:ma");
//        query.setParameter("ma", ma);
//        List<String> results = query.getResultList();
//        return results;
//
//    }
//
//    public boolean them(HoaDon hoaDon) {
//        Transaction transaction = null;
//        try ( Session session = ConnectDB.getFACTORY().openSession()) {
//            transaction = session.beginTransaction();
//            session.save(hoaDon);
//            transaction.commit();
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//
//
//    public boolean sua(String ma, HoaDon hoaDon) {
//        Transaction transaction = null;
//        try ( Session session = ConnectDB.getFACTORY().openSession()) {
//            transaction = session.beginTransaction();
//            Query query = session.createQuery("update HoaDon set  trangThai =:trangThai where ma = :ma");
//            query.setParameter("trangThai", hoaDon.getTrangThai());
//            query.setParameter("ma", ma);
//            query.executeUpdate();
//            transaction.commit();
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//}
