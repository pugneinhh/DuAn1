/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitory;

import DomainModels.CTKhuyenMai;
import DomainModels.CTSanPham;
import DomainModels.DungLuong;
import DomainModels.MauSac;
import DomainModels.SanPham;
import Utilites.JDBC_Helper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duong
 */
public class CTSanPhamResponsitory {
    MauSacResponsitory ms = new MauSacResponsitory();
    CTKhuyenMaiResponsitory ctkm = new CTKhuyenMaiResponsitory();
    SanPhamResponsitory sp = new SanPhamResponsitory();
    DungLuongResponsitory dl = new DungLuongResponsitory();
        public ArrayList<CTSanPham> getAllCTSanPham() {
        ArrayList<CTSanPham> list = new ArrayList<>();
        String sql="SELECT* FROM dbo.CTSANPHAM";
        ResultSet rs = JDBC_Helper.excuteQuery(sql);
        try {
            while (rs.next()) {
                MauSac mauSac = ms.getMSByID(rs.getString(2));
            CTKhuyenMai ctKhuyenMai = ctkm.getCVCTKMID(rs.getString(3));
                SanPham sanPham = sp.getSPByID(rs.getString(4));
                DungLuong dungLuong = dl.getDLByID(rs.getString(5));
                list.add(new CTSanPham(rs.getString(1), mauSac, ctKhuyenMai, sanPham, dungLuong, rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getInt(10), rs.getDate(11), rs.getDate(12), rs.getFloat(13), rs.getFloat(14)));
//                list.add(new CTSanPham(sql, ms, ctkm, sp, dl, sql, sql, 0, sql, 0, 0, 0, ngayTao, ngayNhap));

                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CTSanPhamResponsitory.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return list;
    }
       public CTSanPham getCTSanPhamByID(String id){
        
        String sql="SELECT * FROM CTSANPHAM WHERE IDCTSP=?";
        ResultSet rs=JDBC_Helper.excuteQuery(sql,id);
        try {
            while(rs.next()){
                MauSac mauSac = ms.getMSByID(rs.getString(2));
            CTKhuyenMai ctKhuyenMai = ctkm.getCVCTKMID(rs.getString(3));
                SanPham sanPham = sp.getSPByID(rs.getString(4));
                DungLuong dungLuong = dl.getDLByID(rs.getString(5));         
                return new CTSanPham(rs.getString(1), mauSac, ctKhuyenMai, sanPham, dungLuong, rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getInt(10), rs.getDate(11), rs.getDate(12), rs.getFloat(13), rs.getFloat(14));
//                return new ChucVu(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getDate(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CTSanPhamResponsitory.class.getName()).log(Level.SEVERE, null, ex);
        }
       return null;
    }
    public CTSanPham insertCTSanPham(CTSanPham ctsp){
        String sql= "INSERT INTO CTSANPHAM(IDCTSP,IDMS,IDCTKM,IDSP,IDDL,MACTSP,MAQR,SOLUONGTON,HINHANH,NAMBH,NGAYTAO,NGAYSUA,GIANHAP,GIABAN) VALUES(NEWID(),?,null,?,?,?,?,?,?,?,GETDATE(),null,?,?)";
       JDBC_Helper.excuteUpdate(sql, ctsp.getMs().getId(),ctsp.getSp().getId(),ctsp.getDl().getId(),ctsp.getMa(),ctsp.getMaQR(),ctsp.getSoLuongTon(),ctsp.getHinhAnh(),ctsp.getNamBH(),ctsp.getGiaNhap(),ctsp.getGiaBan());
        return ctsp;
    }
    public CTSanPham updateCTSanPham(CTSanPham ctsp){
        String sql= "UPDATE dbo.CTSANPHAM SET IDMS=?,IDSP=?,IDDL=?,MACTSP=?,MAQR=?,SOLUONGTON=?,HINHANH=?,NAMBH=?,NGAYSUA=GETDATE(),GIANHAP=?,GIABAN=? WHERE IDCTSP=?";
       JDBC_Helper.excuteUpdate(sql, ctsp.getMs().getId(),ctsp.getSp().getId(),ctsp.getDl().getId(),ctsp.getMa(),ctsp.getMaQR(),ctsp.getSoLuongTon(),ctsp.getHinhAnh(),ctsp.getNamBH(),ctsp.getGiaNhap(),ctsp.getGiaBan(),ctsp.getId());
        return ctsp;
    }
    public Integer deleteCTSanPham(String ma){
        String sql="DELETE dbo.CTSANPHAM WHERE MACTSP =?";
        int row=JDBC_Helper.excuteUpdate(sql,ma);
        return row;
    }
  

}
