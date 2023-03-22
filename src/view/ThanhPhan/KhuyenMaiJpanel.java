/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.ThanhPhan;

import DomainModels.CTKhuyenMai;
import DomainModels.CTSanPham;
import DomainModels.NSX;
import Service.CTKhuyenMaiService;
import Service.CTSanPhamService;
import Service.Interface.ICTKhuyenMaiService;
import Service.Interface.ICTSanPhamService;
import Service.Interface.INSXService;
import Service.NSXService;
import ViewModel.CTKhuyenMaiModel;
import ViewModel.CTSanPhamModel;
import ViewModel.NSXModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class KhuyenMaiJpanel extends javax.swing.JPanel {

    private ICTKhuyenMaiService ctkm = new CTKhuyenMaiService();
    private DefaultTableModel dtm = new DefaultTableModel();
    private DefaultTableModel dtmSP = new DefaultTableModel();
    private DefaultComboBoxModel cbb = new DefaultComboBoxModel();
    private ICTSanPhamService ctsp = new CTSanPhamService();
    private INSXService nsx = new NSXService();

    /**
     * Creates new form KhuyenMaiJpanel
     */
    public KhuyenMaiJpanel() {
        initComponents();
        dtm = (DefaultTableModel) tblBang.getModel();
        dtmSP = (DefaultTableModel) tblSanPham.getModel();
        cbbHang.setModel(cbb);
        this.loadTable();
        tblBang.getColumnModel().getColumn(0).setPreferredWidth(30);
        tblBang.getColumnModel().getColumn(1).setPreferredWidth(120);
        tblBang.getColumnModel().getColumn(4).setPreferredWidth(60);
        loadSP();
        loadTKHang();
//              TableActionEvent event = new TableActionEvent() {
//            @Override
//            public void onDelete(int row) {
//                DefaultTableModel dtm=(DefaultTableModel) tblSanPham.getModel();
//                dtm.getRowCount();
//            }
//        };
//           tblSanPham.getColumnModel().getColumn(5).setCellRenderer(new SpinneAction());
//        tblSanPham.getColumnModel().getColumn(5).setCellEditor(new SipnerEditor(event));
    }

    public void loadTable() {
        ArrayList<CTKhuyenMaiModel> list = ctkm.getAllCTKM();
        dtm.setRowCount(0);
        Collections.sort(list, Comparator.comparing(CTKhuyenMai -> CTKhuyenMai.getMa()));
        for (CTKhuyenMaiModel x : list) {
            Object[] rowData = {
                x.getMa(), x.getTen(), x.getThoiGianBatDau(), x.getThoiGianKetThuc(), x.getHinhThuc(), x.getNgayTao(), x.getNgaySua()
            };
            dtm.addRow(rowData);

        }
    }

    private void loadSP() {
        ArrayList<CTSanPhamModel> listSP = ctsp.getAllCTSanPham();
        dtmSP.setRowCount(0);
        for (int i = 0; i < listSP.size(); ++i) {
            dtmSP.addRow(new Object[]{
                i + 1,
                listSP.get(i).getMa(),
                listSP.get(i).getSp().getTen() + " " + listSP.get(i).getDl().getSoDungLuong() + " " + listSP.get(i).getMs().getTen(),
                listSP.get(i).getSoLuongTon(),
                listSP.get(i).getCtkm()
            });
        }

    }

    private void loadTKHang() {
        ArrayList<NSXModel> list = nsx.getAllNSX();
        for (NSXModel x : list) {
            cbb.addElement(new NSX(x.getId(), x.getMa(), x.getTen(), x.getNgayTao(), x.getNgaySua()));
        }
    }

    public void loadTable1(String tim) {
        ArrayList<CTKhuyenMaiModel> list = ctkm.getTimTen(tim);
        dtm.setRowCount(0);
        Collections.sort(list, Comparator.comparing(CTKhuyenMai -> CTKhuyenMai.getMa()));
        for (CTKhuyenMaiModel x : list) {
            Object[] rowData = {
                x.getMa(), x.getTen(), x.getThoiGianBatDau(), x.getThoiGianKetThuc(), x.getHinhThuc(), x.getNgayTao(), x.getNgaySua(),false
            };
            dtm.addRow(rowData);

        }
    }

    public void clearFrom() {
        txtMa.setText("");
        txtTen.setText("");
        txtHinhThuc.setText("");
        txtBD.setDate(null);
        txtKetThuc.setDate(null);
    }

    private CTKhuyenMaiModel getFormData() {
        String ma = txtMa.getText().trim();
        String ten = txtTen.getText().trim();
        String hinhthuc = txtHinhThuc.getText().trim();
        Date ngayBD = txtBD.getDate();
        Date ngayKT = txtKetThuc.getDate();

        if (ma.length() == 0) {
            JOptionPane.showMessageDialog(this, "Không được để trống mã");
            txtMa.requestFocus();
            return null;
        }
        if (ten.length() == 0) {
            JOptionPane.showMessageDialog(this, "Không được để trống tên");
            txtTen.requestFocus();
            return null;
        }

        if (ngayBD == null) {
            JOptionPane.showMessageDialog(this, "Không được để trống ngày bắt đầu");
            txtBD.requestFocus();
            return null;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-dd-MM");
            String date = sdf.format(txtBD.getDate());
        }
        if (ngayKT == null) {
            JOptionPane.showMessageDialog(this, "Không được để trống ngày kết thúc");
            txtBD.requestFocus();
            return null;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-dd-MM");
            String date = sdf.format(txtKetThuc.getDate());
        }
        if (hinhthuc.length() == 0) {
            JOptionPane.showMessageDialog(this, "Không được để trống hình thức");
            txtHinhThuc.requestFocus();
            return null;
        }
        return new CTKhuyenMaiModel(null, ma, ten, ngayBD, ngayKT, hinhthuc, null, null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JKhuyenMai = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        txtHinhThuc = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        txtBD = new com.toedter.calendar.JDateChooser();
        jLabel53 = new javax.swing.JLabel();
        txtKetThuc = new com.toedter.calendar.JDateChooser();
        btnSua = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnSua1 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        txtTimKM = new javax.swing.JTextField();
        btnTim = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblBang = new javax.swing.JTable();
        jLabel58 = new javax.swing.JLabel();
        txtTKSanPham = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        cbbHang = new javax.swing.JComboBox<>();
        cbHoatDong = new javax.swing.JCheckBox();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        btnSuaCTKM = new javax.swing.JButton();
        btnXoaCTKM = new javax.swing.JButton();

        JKhuyenMai.setBackground(new java.awt.Color(238, 232, 170));

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setOpaque(false);

        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/sale.png"))); // NOI18N

        jLabel48.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel48.setText("Mã khuyến mãi     :");

        jLabel49.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel49.setText("Tên khuyến mãi    :");

        jLabel51.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel51.setText("Hình thức");

        jLabel52.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel52.setText("Ngày bắt đầu");

        jLabel53.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel53.setText("Ngày kết thúc");

        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSua1.setText("Clear");
        btnSua1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel48)
                                        .addComponent(jLabel49))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(46, 46, 46)
                                            .addComponent(txtBD, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtHinhThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addComponent(txtKetThuc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55)
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(btnSua1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(202, 202, 202)
                        .addComponent(jLabel47)))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnSua, btnThem});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel49))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel52)
                    .addComponent(txtBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel53)
                    .addComponent(txtKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel51)
                    .addComponent(txtHinhThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(74, 74, 74)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(181, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnSua, btnThem});

        jPanel11.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel11.setOpaque(false);

        jLabel55.setText("Tìm khuyến mãi");

        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        tblBang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã", "Tên", "Ngày bắt đầu", "Ngày KT", "Hình thức", "Ngày tạo", "Ngày sửa"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBangMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblBang);

        jLabel58.setText("Tìm sản phẩm");

        txtTKSanPham.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTKSanPhamCaretUpdate(evt);
            }
        });

        jLabel59.setText("Hãng");

        cbbHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbHangActionPerformed(evt);
            }
        });

        cbHoatDong.setText("Hoạt động");

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã", "Tên SP", "Số lượng tồn", "CTKM", "Check"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tblSanPham);

        btnSuaCTKM.setText("Thêm/Sửa CTKM");
        btnSuaCTKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaCTKMActionPerformed(evt);
            }
        });

        btnXoaCTKM.setText("Xóa CTKM");
        btnXoaCTKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaCTKMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(txtTimKM, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTKSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(155, 155, 155)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addComponent(cbbHang, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(32, 32, 32)
                                        .addComponent(cbHoatDong, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(btnSuaCTKM)
                        .addGap(110, 110, 110)
                        .addComponent(btnXoaCTKM)))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jPanel11Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnSuaCTKM, btnXoaCTKM});

        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel55)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTim))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(jLabel59))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTKSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbHoatDong))
                .addGap(36, 36, 36)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSuaCTKM)
                    .addComponent(btnXoaCTKM))
                .addContainerGap(79, Short.MAX_VALUE))
        );

        jPanel11Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnSuaCTKM, btnXoaCTKM});

        javax.swing.GroupLayout JKhuyenMaiLayout = new javax.swing.GroupLayout(JKhuyenMai);
        JKhuyenMai.setLayout(JKhuyenMaiLayout);
        JKhuyenMaiLayout.setHorizontalGroup(
            JKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JKhuyenMaiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(770, Short.MAX_VALUE))
        );
        JKhuyenMaiLayout.setVerticalGroup(
            JKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JKhuyenMaiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(100, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(JKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 74, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(JKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        CTKhuyenMaiModel nv = getFormData();
        if (nv == null) {
            return;
        }
        if (ctkm.insertCTKM(nv) != null) {
            JOptionPane.showMessageDialog(this, "Thêm thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại do trùng mã");
        }
        loadTable();
        clearFrom();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        int row = tblBang.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Chọn dòng cần sửa");
            return;
        }
        CTKhuyenMaiModel nv = getFormData();
        if (nv == null) {
            return;
        }
        String ma = tblBang.getValueAt(row, 0).toString();

        nv.setId(ma);
        if (ctkm.updateCTKM(nv) != null) {
            JOptionPane.showMessageDialog(this, "Sửa thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Sửa thất bại");
        }
        loadTable();
        clearFrom();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void tblBangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangMouseClicked
        try {
            int row = this.tblBang.getSelectedRow();
            if (row == -1) {
                return;
            }
            txtMa.setText(tblBang.getValueAt(row, 0).toString());
            txtTen.setText(tblBang.getValueAt(row, 1).toString());
            txtHinhThuc.setText(tblBang.getValueAt(row, 4).toString());
            String ngaybd = tblBang.getValueAt(row, 2).toString();
            String ngay = tblBang.getValueAt(row, 3).toString();

            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(ngaybd);
            Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(ngaybd);
            txtBD.setDate(date);
            txtKetThuc.setDate(date2);

        } catch (ParseException ex) {

        }

    }//GEN-LAST:event_tblBangMouseClicked

    private void btnSua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua1ActionPerformed
        clearFrom();
    }//GEN-LAST:event_btnSua1ActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        String tim = txtTimKM.getText().trim();
        if (tim.length() == 0) {
            JOptionPane.showMessageDialog(this, "không đc để trống tìm");
            txtTimKM.requestFocus();
        }
        if (ctkm.getTimTen(tim).size() > 0) {
            JOptionPane.showMessageDialog(this, "tìm thành công");
        } else {
            JOptionPane.showMessageDialog(this, "tìm thất bại");
        }
        loadTable1(tim);
        txtTimKM.setText("");
    }//GEN-LAST:event_btnTimActionPerformed

    private void txtTKSanPhamCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTKSanPhamCaretUpdate
        String timKiem = txtTKSanPham.getText();
        ArrayList<CTSanPhamModel> list = ctsp.getAllCTSanPham();
        ArrayList<CTSanPhamModel> listNEW = new ArrayList<>();
        String ten = "";
        for (CTSanPhamModel x : list) {
            ten = x.getSp().getTen() + " " + x.getDl().getSoDungLuong() + " " + x.getMs().getTen();
            if (ten.toLowerCase().contains(timKiem.toLowerCase())) {
                listNEW.add(x);
            }
        }
        dtmSP.setRowCount(0);
        for (int i = 0; i < listNEW.size(); ++i) {
            dtmSP.addRow(new Object[]{
                i + 1,
                listNEW.get(i).getMa(),
                listNEW.get(i).getSp().getTen() + " " + listNEW.get(i).getDl().getSoDungLuong() + " " + listNEW.get(i).getMs().getTen(),
                listNEW.get(i).getSoLuongTon(),
                listNEW.get(i).getCtkm()
            });
        }
    }//GEN-LAST:event_txtTKSanPhamCaretUpdate

    private void cbbHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbHangActionPerformed
        NSX n = (NSX) cbb.getSelectedItem();
        ArrayList<CTSanPhamModel> list = ctsp.getAllCTSanPham();
        ArrayList<CTSanPhamModel> listNEW = new ArrayList<>();
        for (CTSanPhamModel x : list) {
            if (x.getSp().getNsx() != null && x.getSp().getNsx().getTen().equals(n.getTen())) {
                listNEW.add(x);
            }
        }
        dtmSP.setRowCount(0);
        for (int i = 0; i < listNEW.size(); ++i) {
            dtmSP.addRow(new Object[]{
                i + 1,
                listNEW.get(i).getMa(),
                listNEW.get(i).getSp().getTen() + " " + listNEW.get(i).getDl().getSoDungLuong() + " " + listNEW.get(i).getMs().getTen(),
                listNEW.get(i).getSoLuongTon(),
                listNEW.get(i).getCtkm()
            });
        }
    }//GEN-LAST:event_cbbHangActionPerformed

    private void btnSuaCTKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaCTKMActionPerformed
        int indexCTKM = tblBang.getSelectedRow();
        String maCTKM = tblBang.getValueAt(indexCTKM, 0).toString();
        String idCTKM = "";
        int indexSP = tblSanPham.getSelectedRow();
        String maSP = tblSanPham.getValueAt(indexSP, 1).toString();
        Boolean check=(Boolean) tblSanPham.getValueAt(indexCTKM, 5);
        if(check==true){
            
        ArrayList<CTKhuyenMaiModel> listCTKM = ctkm.getAllCTKM();
        CTKhuyenMai ctkm = null;
        for (CTKhuyenMaiModel x : listCTKM) {
            if (x.getMa() != null && x.getMa().equals(maCTKM)) {
                ctkm = new CTKhuyenMai(x.getId(), x.getMa(), x.getTen(), x.getThoiGianBatDau(), x.getThoiGianKetThuc(), x.getHinhThuc(), x.getNgayTao(), x.getNgaySua());
            }
        }
        if (indexCTKM < 0) {
            JOptionPane.showMessageDialog(this, "Mời chọn chương trình khuyến mãi");
            return;
        }
        if (indexSP < 0) {
            JOptionPane.showMessageDialog(this, "Mời chọn dòng sản phẩm");
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm / sửa chương trình khuyến mãi không?", "Thông báo", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
            return;
        }
                ArrayList<CTSanPhamModel> listSP = ctsp.getAllCTSanPham();
        CTSanPhamModel c = getCTSPByMa(maSP);
        c.setCtkm(ctkm);
        System.out.println(c.getCtkm().getId());
        }

        ArrayList<CTKhuyenMaiModel> listCTKM = ctkm.getAllCTKM();
        CTKhuyenMai ctkm = null;
        for (CTKhuyenMaiModel x : listCTKM) {
            if (x.getMa() != null && x.getMa().equals(maCTKM)) {
                ctkm = new CTKhuyenMai(x.getId(), x.getMa(), x.getTen(), x.getThoiGianBatDau(), x.getThoiGianKetThuc(), x.getHinhThuc(), x.getNgayTao(), x.getNgaySua());
            }
        }
        if (indexCTKM < 0) {
            JOptionPane.showMessageDialog(this, "Mời chọn chương trình khuyến mãi");
            return;
        }
        if (indexSP < 0) {
            JOptionPane.showMessageDialog(this, "Mời chọn dòng sản phẩm");
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm / sửa chương trình khuyến mãi không?", "Thông báo", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
            return;
        }
                ArrayList<CTSanPhamModel> listSP = ctsp.getAllCTSanPham();
        CTSanPhamModel c = getCTSPByMa(maSP);
        c.setCtkm(ctkm);
        System.out.println(c.getCtkm().getId());
        if (c == null) {
            JOptionPane.showMessageDialog(this, "Thêm chương trình thất bại");
            return;
        }
        if (ctsp.updateCTKMSanPham(c) != null) {
            JOptionPane.showMessageDialog(this, "Thêm chương trình thành công");
            loadSP();
        }
    }//GEN-LAST:event_btnSuaCTKMActionPerformed

    private void btnXoaCTKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaCTKMActionPerformed
        int index = tblSanPham.getSelectedRow();
        CTSanPhamModel c = getCTSPByMa(tblSanPham.getValueAt(index, 1).toString());
        if (index < 0){
            JOptionPane.showMessageDialog(this, "Mời bạn chọn dòng cần xóa");
            return;
        }
        if (c== null){
            JOptionPane.showMessageDialog(this, "Xóa thất bại");
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không?","Thông báo",JOptionPane.YES_NO_OPTION)!=JOptionPane.YES_OPTION){
            return;
        }
        if (ctsp.deleteCTKM(c)!=null){
            JOptionPane.showMessageDialog(this, "Xóa thành công");
            loadSP();
        }
    }//GEN-LAST:event_btnXoaCTKMActionPerformed
    private CTSanPhamModel getCTSPByMa(String ma) {
        ArrayList<CTSanPhamModel> listSP = ctsp.getAllCTSanPham();
        for (CTSanPhamModel z : listSP) {
            if (z.getMa() != null && z.getMa().equals(ma)) {
                return new CTSanPhamModel(z.getId(), z.getMs(), z.getCtkm(), z.getSp(), z.getDl(), z.getMa(), z.getMaQR(), z.getSoLuongTon(), z.getHinhAnh(), z.getNamBH(), z.getNgayTao(), z.getNgaySua(), z.getGiaNhap(), z.getGiaBan());
            }
        }
        return null;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JKhuyenMai;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSua1;
    private javax.swing.JButton btnSuaCTKM;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoaCTKM;
    private javax.swing.JCheckBox cbHoatDong;
    private javax.swing.JComboBox<String> cbbHang;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable tblBang;
    private javax.swing.JTable tblSanPham;
    private com.toedter.calendar.JDateChooser txtBD;
    private javax.swing.JTextField txtHinhThuc;
    private com.toedter.calendar.JDateChooser txtKetThuc;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtTKSanPham;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTimKM;
    // End of variables declaration//GEN-END:variables
}
