create database doan
go
use doan
create table login(MaHS varchar(50) primary key,MatKhau varchar(50))

insert into login values('123',N'abc')
insert into login values('124',N'abd')
insert into login values('125',N'abe')
insert into login values('admin',N'a1')
select *from login

create table LopHoc(
	maLop varchar(30) primary key,
	tenLop nvarchar(100) unique not null,
	giaoVienCN nvarchar(100)
)
go

create table HocSinh(
	maHS varchar(20) primary key,
	hoTen nvarchar(100) not null,
	email varchar(100),
	diaChi nvarchar(100),
	maLop varchar(30) foreign key references LopHoc(maLop)
)
go

select *from LopHoc
select *from HocSinh
drop table HocSinh
drop table LopHoc