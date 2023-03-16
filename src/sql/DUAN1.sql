﻿CREATE DATABASE DUAN1_NHOM8
USE DUAN1_NHOM8;

CREATE TABLE IMEI(
 IDIMEI UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
 MaIMEI NVARCHAR(50) UNIQUE,
 NGAYNHAP date,
 GHICHU NVARCHAR(100),
 TRANGTHAI INT

);
CREATE TABLE DUNGLUONG(
 IDDL UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
 MADL NVARCHAR(10) UNIQUE,
 SOLUONG INT,
 NGAYNHAP DATE,
 
);
CREATE TABLE MAUSAC(
 IDMS UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
 MAMAU NVARCHAR(10) UNIQUE,
 TENMau NVARCHAR(50),
 NGAYTAO DATE,
 NGAYSUA DATE,
);
CREATE TABLE NSX(
 IDNSX UNIQUEIDENTIFIER  PRIMARY KEY DEFAULT NEWID(),
 MANSX NVARCHAR(10) UNIQUE,
 TENNSX NVARCHAR(50),
  NGAYTAO DATE,
 NGAYSUA DATE,
);
CREATE TABLE CHUCVU(
 IDCV UNIQUEIDENTIFIER  PRIMARY KEY DEFAULT NEWID(),
 MACV NVARCHAR(10) UNIQUE,
 TENCV NVARCHAR(50),
 NGAYTAO DATE,
 NGAYSUA DATE,
 );
 CREATE TABLE NHAPHANPHOI(
	IDNPP UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
	MANHAPHANPHOI NVARCHAR(10) UNIQUE,
	TENNHAPHANPHOI NVARCHAR(100),
	DIACHI NVARCHAR(100),
	EMAIL VARCHAR(50),
	SDT VARCHAR(10),
	NGAYTAO DATE,
	NGAYSUA DATE,
);
 CREATE TABLE COUPON(
  IDCP UNIQUEIDENTIFIER  PRIMARY KEY DEFAULT NEWID(),
  MACOUPON NVARCHAR(10) UNIQUE,
  HANSUDUNG DATE,
  HINHTHUC NVARCHAR(50),
  GIAMGIA FLOAT,
  NGAYTAO DATE,
  NGAYSUA DATE,
 );
CREATE TABLE KHACHHANG(
  IDKH UNIQUEIDENTIFIER  PRIMARY KEY DEFAULT NEWID(),
  SDT NVARCHAR(11),
  HOTEN NVARCHAR(50),
  DIACHI NVARCHAR(100),
  GIOITINH NVARCHAR(10),
  EMAIL NVARCHAR(50),
  NGAYSINH DATE,
  NGAYTAO DATE,
  NGAYSUA DATE,
);
CREATE TABLE CTKHUYENMAI(
  IDCTKM UNIQUEIDENTIFIER  PRIMARY KEY DEFAULT NEWID(),
  MAKM NVARCHAR(10) UNIQUE,
  TENKM NVARCHAR(50),
  TGBATDAU DATE,
  TGKETTHUC DATE,
  HINHTHUC NVARCHAR(50),
    NGAYTAO DATE,
  NGAYSUA DATE,

);
CREATE TABLE DOITRA(
 IDDOITRA UNIQUEIDENTIFIER  PRIMARY KEY DEFAULT NEWID(),
 IDHD UNIQUEIDENTIFIER,
 MADOITRA NVARCHAR(10) UNIQUE,
 NGAYDOITRA DATE,
 HINHTHUC INT,
 MOTA NVARCHAR(50),
 NGAYTAO DATE,
 NGAYSUA DATE,
  
);
 CREATE TABLE HOADON(
 IDHD UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
 IDKH UNIQUEIDENTIFIER,
 IDNV UNIQUEIDENTIFIER,
 IDCP UNIQUEIDENTIFIER,
 MAHD NVARCHAR(10) UNIQUE,
 THANHTIEN FLOAT,
 HINHTHUCTT INT,
 NGAYTT DATE,
 TRANGTHAI INT,
  NGAYTAO DATE,
  NGAYSUA DATE,
 );
CREATE TABLE SANPHAM(
 IDSP UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
 IDNSX UNIQUEIDENTIFIER,
 MASP NVARCHAR(50) UNIQUE,
 TENSP NVARCHAR(50) NOT NULL,
 MOTA NVARCHAR(100),
 NGAYTAO DATE,
  NGAYSUA DATE,

);
CREATE TABLE NHANVIEN(
 IDNV UNIQUEIDENTIFIER  PRIMARY KEY DEFAULT NEWID(),
 IDCV UNIQUEIDENTIFIER,
 MANV NVARCHAR(10) UNIQUE,
 HOTEN NVARCHAR(50),
 GIOTINH NVARCHAR(10),
 SDT NVARCHAR(11),
 NGAYSINH DATE,
 DIACHI NVARCHAR(100),
 EMAIL NVARCHAR(50),
 MATKHAU NVARCHAR(50),
 TRANGTHAI INT,
 HINHANH NVARCHAR(50),
  NGAYTAO DATE,
  NGAYSUA DATE,
);
 CREATE TABLE CTSANPHAM(
  IDCTSP UNIQUEIDENTIFIER  PRIMARY KEY DEFAULT NEWID(),
  IDIMEI UNIQUEIDENTIFIER,
  IDMS UNIQUEIDENTIFIER,
  IDCTKM UNIQUEIDENTIFIER,
  IDSP UNIQUEIDENTIFIER,
  IDDL UNIQUEIDENTIFIER,
  MACTSP NVARCHAR(10) UNIQUE,
  MAQR NVARCHAR(100),
  SOLUONGTON INT,
  HINHANH NVARCHAR(50),
  NAMBH INT,
   NGAYTAO DATE,
  NGAYSUA DATE,
 );
CREATE TABLE PHIEUCHI(
	IDPC UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
	IDPN UNIQUEIDENTIFIER,
	IDNV UNIQUEIDENTIFIER,
	MAPHIEUCHI NVARCHAR(10),
	STTPHIEUCHI INT,
	GIA float,
	NGAYCHI DATE,
 NGAYTAO DATE,
  NGAYSUA DATE,
);
CREATE TABLE PHIEUNHAP(
	ID UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
	IDNPP UNIQUEIDENTIFIER,
	IDNV UNIQUEIDENTIFIER,
	STTPHIEUNHAP INT,
	MAPN NVARCHAR(10),
	TONGGIA FLOAT,
	NGAYTAO DATE
);
CREATE TABLE CHITIETPHIEUNHAP(
	IDCTSP UNIQUEIDENTIFIER,
	IDPN UNIQUEIDENTIFIER,
	STTPHIEUNHAP INT,
	SOLUONG INT,
	DONGIA FLOAT
	PRIMARY KEY (IDCTSP,IDPN),
	FOREIGN KEY(IDCTSP) REFERENCES dbo.CTSANPHAM(IDCTSP),
	FOREIGN KEY(IDPN) REFERENCES dbo.PHIEUNHAP(ID)
);
 CREATE TABLE HOADONCHITIET(
 IDHD UNIQUEIDENTIFIER,
 IDCTSP UNIQUEIDENTIFIER,
 DONGIA FLOAT,
 SOLUONG INT,
 THANHTIEN FLOAT,
 NGAYTAO DATE,
 NGAYSUA DATE,
CONSTRAINT PK_HOADONCT PRIMARY KEY (IDHD,IDCTSP),
CONSTRAINT FK1_HDCT FOREIGN KEY(IDHD) REFERENCES dbo.HOADON(IDHD),
CONSTRAINT FK2_HDCT FOREIGN KEY(IDCTSP) REFERENCES dbo.CTSANPHAM(IDCTSP)
 );
-- LIÊN KẾT 
ALTER TABLE dbo.PHIEUNHAP ADD FOREIGN KEY (IDNPP) REFERENCES dbo.NHAPHANPHOI(IDNPP);
ALTER TABLE dbo.SANPHAM ADD FOREIGN KEY (IDNSX) REFERENCES dbo.NSX(IDNSX);
ALTER TABLE dbo.CTSANPHAM ADD FOREIGN KEY(IDSP) REFERENCES dbo.SANPHAM(IDSP);
ALTER TABLE dbo.PHIEUNHAP ADD FOREIGN KEY (IDNV) REFERENCES dbo.NHANVIEN(IDNV);
ALTER TABLE dbo.PHIEUCHI ADD FOREIGN KEY (IDNV) REFERENCES dbo.NHANVIEN (IDNV);
ALTER TABLE dbo.PHIEUCHI ADD FOREIGN KEY (IDPN) REFERENCES dbo.PHIEUNHAP (ID);

ALTER TABLE dbo.NHANVIEN ADD FOREIGN KEY (IDCV) REFERENCES dbo.CHUCVU(IDCV);
ALTER TABLE dbo.CTSANPHAM ADD FOREIGN KEY(IDIMEI) REFERENCES dbo.IMEI(IDIMEI);
ALTER TABLE dbo.CTSANPHAM ADD FOREIGN KEY(IDDL) REFERENCES dbo.DUNGLUONG(IDDL);
ALTER TABLE dbo.CTSANPHAM ADD FOREIGN KEY(IDMS) REFERENCES dbo.MAUSAC(IDMS);
ALTER TABLE dbo.CTSANPHAM ADD FOREIGN KEY(IDCTKM) REFERENCES dbo.CTKHUYENMAI(IDCTKM);


ALTER TABLE dbo.HOADON ADD FOREIGN KEY (IDNV) REFERENCES dbo.NHANVIEN(IDNV);
ALTER TABLE dbo.HOADON ADD FOREIGN KEY(IDCP) REFERENCES dbo.COUPON(IDCP);
ALTER TABLE dbo.HOADON ADD FOREIGN KEY(IDKH) REFERENCES dbo.KHACHHANG(IDKH);
ALTER TABLE dbo.DOITRA ADD FOREIGN KEY (IDHD) REFERENCES dbo.HOADON(IDHD);
ALTER TABLE dbo.CTSANPHAM ADD giaNhap float, giaBan FLOAT
ALTER TABLE dbo.KHACHHANG ADD MAKH NVARCHAR(10)  

SELECT IDKH,MAKH,HOTEN,SDT,DIACHI,GIOITINH,EMAIL,NGAYSINH,NGAYTAO,NGAYSUA FROM dbo.KHACHHANG
-- Add 


INSERT INTO CHUCVU 
VALUES (NEWID(),
		'CV01',
		N'Giám đốc',
		'2023-03-13',
		null)

SELECT * FROM MAUSAC
INSERT INTO MAUSAC
VALUES (NEWID(),
	'MS01',
	N'Màu hồng',
	'2023-03-13',
	null)

	SELECT * FROM COUPON
	SELECT * FROM NHANVIEN