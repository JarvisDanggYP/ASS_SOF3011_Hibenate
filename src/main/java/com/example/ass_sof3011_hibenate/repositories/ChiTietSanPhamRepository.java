package com.example.ass_sof3011_hibenate.repositories;

import com.example.ass_sof3011_hibenate.domain_models.*;
import com.example.ass_sof3011_hibenate.utilities.ConnectDB;
import org.hibernate.Session;
import org.hibernate.Transaction;

import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.List;


public class ChiTietSanPhamRepository {

    public boolean insert(ChiTietSp chiTietSp) {
        Transaction transaction = null;
        try (Session session = ConnectDB.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.save(chiTietSp);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

//    public boolean updateSoLuong(int soLuong, String id) {
//        Transaction transaction = null;
//        try (Session session = ConnectDB.getFACTORY().openSession()) {
//            transaction = session.beginTransaction();
//            Query query = session.createQuery("UPDATE ChiTietSp set soLuongTon =: soLuong" +
//                    " where id=:idsp");
//            query.setParameter("soLuong", soLuong);
//            query.setParameter("idsp", id);
//            query.executeUpdate();
//            transaction.commit();
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }


    public boolean update(String id, ChiTietSp ctsp) {
        Transaction transaction = null;
        try (Session session = ConnectDB.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update ChiTietSp set sanPham.id =: idSp,dongSp.id = :idDong," +
                    "nsx.id =:idNSX,mauSac.id =: idMau" +
                    ",namBaoHanh =: namBh, " +
                    "moTa =:mota,soLuongTon =:sl,giaNhap =: gianhap,giaBan =:giaban" +
                    " where id=:id");
            query.setParameter("idSp", ctsp.getSanPham().getId());
            query.setParameter("idDong", ctsp.getDongSp().getId());
//            query.setParameter("idMau", ctsp.getMauSac().getId());
            query.setParameter("idNSX", ctsp.getNsx().getId());
            query.setParameter("namBh", ctsp.getNamBaoHanh());
            query.setParameter("mota", ctsp.getMoTa());
            query.setParameter("sl", ctsp.getSoLuongTon());
            query.setParameter("gianhap", ctsp.getGiaNhap());
            query.setParameter("giaban", ctsp.getGiaBan());
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(ChiTietSp ctsp) {
        Transaction transaction = null;
        try (Session session = ConnectDB.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.delete(ctsp);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<SanPham> loadCbbSanPham() {
        String hql = "select s from  SanPham s";
        Session session = ConnectDB.getFACTORY().openSession();
        List<SanPham> list = session.createQuery(hql).getResultList();
        return list;
    }

    public List<DongSp> loadCbbDongSP() {
        String hql = "select d from  DongSp d";
        Session session = ConnectDB.getFACTORY().openSession();
        List<DongSp> list = session.createQuery(hql).getResultList();
        return list;
    }

    public List<MauSac> loadCbbMauSac() {
        String hql = "select m from  MauSac m";
        Session session = ConnectDB.getFACTORY().openSession();
        List<MauSac> list = session.createQuery(hql).getResultList();
        return list;
    }

    public List<NSX> loadCbbNSX() {
        String hql = "select n from  NSX n";
        Session session = ConnectDB.getFACTORY().openSession();
        List<NSX> list = session.createQuery(hql).getResultList();
        return list;
    }

//    public ArrayList<ChiTietSanPhamViewModel> getListDB() {
//        Session session = ConnectDB.getFACTORY().openSession();
//        Query query = session.createQuery(
//                "select  new com.QuanLiBanHang.ViewModels.ChiTietSanPhamViewModel("
//                + "ct.sanPham.maSP,ct.sanPham.tenSp,ct.mauSac.ten,ct.dongSp.ten,ct.nsx.ten,ct.namBaoHanh,ct.moTa,ct.soLuongTon,ct.giaNhap,ct.giaBan,ct.id) "
//                + " from com.QuanLiBanHang.DomainModels.ChiTietSp ct");
//        ArrayList<ChiTietSanPhamViewModel> list = (ArrayList<ChiTietSanPhamViewModel>) query.getResultList();
//
//        return list;
//    }

//    public ArrayList<ChiTietSanPhamViewModel> timKiem(String ten) {
//        Session session = ConnectDB.getFACTORY().openSession();
//        Query query = session.createQuery(
//                "select  new com.QuanLiBanHang.ViewModels.ChiTietSanPhamViewModel("
//                + "ct.sanPham.maSP,ct.sanPham.tenSp,ct.mauSac.ten,ct.dongSp.ten,ct.nsx.ten,ct.namBaoHanh,ct.moTa,ct.soLuongTon,ct.giaNhap,ct.giaBan,ct.id) "
//                + " from com.QuanLiBanHang.DomainModels.ChiTietSp ct where ct.sanPham.tenSp =:ten or ct.sanPham.maSP=:ten");
//                query.setParameter("ten",ten);
//        ArrayList<ChiTietSanPhamViewModel> list = (ArrayList<ChiTietSanPhamViewModel>) query.getResultList();
//
//        return list;
}




