/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service.Interface;

import DomainModels.HoaDon;
import ViewModel.HoaDonChiTietModel;
import ViewModel.HoaDonModel;
import java.util.ArrayList;

/**
 *
 * @author duong
 */
public interface IHoaDonChiTietService {

    public ArrayList<HoaDonChiTietModel> getAllHoaDonCT();

    public HoaDonChiTietModel insertHDCT(HoaDonChiTietModel hdct);

    public Integer deleteHDCT(String id);

    public ArrayList<HoaDonChiTietModel> getAllHoaDonCTBYIDHD(String id);

    public Integer delete(String ghiChu, String idHD);
}
