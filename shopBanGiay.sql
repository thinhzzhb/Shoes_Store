Create database shoes_store

go 
use shoes_store
go



CREATE TABLE Khach_Hang(

	idKH INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	maKH varchar(30) not null,
	tenKH NVARCHAR(50) NOT NULL,
	diaChi NVARCHAR(30) NOT NULL,
	soDienThoai varchar(10) NOT NULL

)

CREATE TABLE Nhan_Vien(
	
	idNV INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	maNV VARCHAR(10),
	tenNV NVARCHAR(30),
	diaChi NVARCHAR(50),
	SDT VARCHAR(13),
	username varchar(30) not null,
	[password] varchar(30) not null,
	vai_tro int

)

CREATE TABLE Voucher(

	idVoucher int identity(1,1) NOT NULL PRIMARY KEY,
	maKM varchar(50),
	ten_khuyen_mai nvarchar(50) not null,
	ngayBatDau DATE,
	ngayKetThuc DATE,
	soTienApDung money not null,
	soTienGiam money not null,
	tinh_trang int not null

)

CREATE TABLE Size(

	idSize int identity(1,1) primary key not null,
	kich_co varchar(3)

)

CREATE TABLE MauSac(

	idMau int identity(1,1) primary key not null,
	Mau nvarchar(20)

)

CREATE TABLE HangGiay(

	idHang int identity(1,1) primary key not null,
	Hang nvarchar(20)

)

CREATE TABLE SanPham(

	idSP int identity(1,1) not null primary key,
	tenSanPham nvarchar(50) not null

)

CREATE TABLE SanPhamCT(

	idSPCT int identity(1,1) not null primary key,
	soLuong int not null,
	idSize int references Size(idSize),
	idHang int references HangGiay(idHang),
	idMau int references MauSac(idMau),
	idSanPham int not null references SanPham(idSP),
	giaTien money

)

CREATE TABLE Coupon(
	
	idCoupon int identity(1,1) not null primary key,
	maCoupon varchar(30) not null,
	idSPCT int references SanPhamCT(idSPCT),
	tenCoupon nvarchar(50) not null,
	ngayBatDau date not null,
	ngayKetThuc date not null,
	phanTramGiam int not null

)
CREATE TABLE Hoa_don(
	
	idHoaDon int identity(1,1) not null primary key,
	idKH int not null references Khach_Hang(idKH),
	idNV int not null references Nhan_Vien(idNV),
	ngayTaoHD date,
	idVoucher int not null references Voucher(idVoucher)

)

CREATE TABLE HoaDonCT(

	idHDCT int identity(1,1) not null primary key,
	idHD int not null references Hoa_Don(idHoaDon),
	idSPCT int not null references SanPhamCT(idSPCT),
	idCou int references Coupon(idCoupon),
	gia money,
	soLuong int,
	tongTien money,

)


