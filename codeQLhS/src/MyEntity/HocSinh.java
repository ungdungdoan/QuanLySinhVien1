package MyEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import MyPackage.Database;
import MyPackage.DbUtils;



public class HocSinh {
	private String maHS;
	private String hoTen;
	private String email;
	private String diaChi;
	//relations
	private LopHoc lopHoc;
	/**
	 * @param maSV
	 * @param hoTen
	 * @param email
	 * @param diaChi
	 * @param lopHoc
	 */
	public HocSinh(String maHS, String hoTen, String email, String diaChi, LopHoc lopHoc) {
		this.maHS = maHS;
		this.hoTen = hoTen;
		this.email = email;
		this.diaChi = diaChi;
		this.lopHoc = lopHoc;
	}
	/**
	 * @param maSV
	 */
	public HocSinh(String maHS) {
		this(maHS, "họ tên", "email", "địa chỉ", new LopHoc());
	}
	
	public HocSinh() {
		this("Mã hs");
	}
	/**
	 * @return the maSV
	 */
	public String getMaHS() {
		return maHS;
	}
	/**
	 * @param maSV the maSV to set
	 */
	public void setMaSV(String maHS) {
		this.maHS = maHS;
	}
	/**
	 * @return the hoTen
	 */
	public String getHoTen() {
		return hoTen;
	}
	/**
	 * @param hoTen the hoTen to set
	 */
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the diaChi
	 */
	public String getDiaChi() {
		return diaChi;
	}
	/**
	 * @param diaChi the diaChi to set
	 */
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	/**
	 * @return the lopHoc
	 */
	public LopHoc getLopHoc() {
		return lopHoc;
	}
	/**
	 * @param lopHoc the lopHoc to set
	 */
	public void setLopHoc(LopHoc lopHoc) {
		this.lopHoc = lopHoc;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maHS == null) ? 0 : maHS.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HocSinh other = (HocSinh) obj;
		if (maHS == null) {
			if (other.maHS != null)
				return false;
		} else if (!maHS.equals(other.maHS))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SinhVien [maSV=" + maHS + ", hoTen=" + hoTen + ", email=" + email + ", diaChi=" + diaChi + ", lopHoc="
				+ lopHoc + "]";
	}
	
	public boolean create() {
		Connection con = Database.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into HocSinh values(?, ?,?,?,?)");
			stmt.setString(1, maHS);
			stmt.setString(2, hoTen);
			stmt.setString(3, email);
			stmt.setString(4, diaChi);
			stmt.setString(5, lopHoc.getMaLop());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(stmt);
		}
		return n > 0;
	}
	
	public boolean update() {
		Connection con = Database.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update HocSinh set hoten = ?, email = ?, diachi = ? where maHS = ?");
			stmt.setString(1, hoTen);
			stmt.setString(2, email);
			stmt.setString(3, diaChi);
			stmt.setString(4, maHS);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(stmt);
		}
		return n > 0;
	}
	
	public boolean delete() {
		Connection con = Database.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from HocSinh where maHS = ?");
			stmt.setString(1, maHS);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(stmt);
		}
		return n > 0;
	}
}
