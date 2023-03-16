/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitory;


import DomainModels.IMEI;
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
public class IMEIResponsitory {
        public ArrayList<IMEI> getAllIMEI() {
        ArrayList<IMEI> list = new ArrayList<>();
        String sql="SELECT* FROM dbo.IMEI";
        ResultSet rs = JDBC_Helper.excuteQuery(sql);
        try {
            while (rs.next()) {
                list.add(new IMEI(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getInt(5)));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(IMEIResponsitory.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return list;
    }
       public IMEI getIMEIByID(String id){
        
        String sql="SELECT * FROM IMEI WHERE IDIMEI=?";
        ResultSet rs=JDBC_Helper.excuteQuery(sql,id);
        try {
            while(rs.next()){
                return new IMEI(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getInt(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(IMEIResponsitory.class.getName()).log(Level.SEVERE, null, ex);
        }
       return null;
    }
    public IMEI insertIMEI(IMEI i){
        String sql= "INSERT INTO IMEI VALUES(NEWID(),?,GETDATE(),?,?)";
       JDBC_Helper.excuteUpdate(sql, i.getMa(),i.getGhiChu(),i.getTrangThai());
        return i;
    }
    public IMEI updateIMEI(IMEI i){
        String sql= "UPDATE dbo.IMEI SET GHICHU=?,TRANGTHAI=? WHERE MAIMEI=?";
       JDBC_Helper.excuteUpdate(sql, i.getGhiChu(),i.getTrangThai(),i.getMa());
        return i;
    }
    public Integer deleteIMEI(String ma){
        String sql="DELETE dbo.IMEI WHERE MAIMEI =?";
        int row=JDBC_Helper.excuteUpdate(sql,ma);
        return row;
    }
  
}
