package MyEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import MyPackage.Database;
import MyPackage.DbUtils;



public class LopHoc {
	private String maLop;
	private String tenLop;
	private String giaoVienCN;
	//relations
	private List<HocSinh> dsHocSinh;
	/**
	 * @param maLop
	 * @param tenLop
	 * @param giaoVienCN
	 */
	public LopHoc(String maLop, String tenLop, String giaoVienCN) {
		this.maLop = maLop;
		this.tenLop = tenLop;
		this.giaoVienCN = giaoVienCN;
		dsHocSinh = new ArrayList<HocSinh>();
	}
	/**
	 * @param maLop
	 */
	public LopHoc(String maLop) {
		this(maLop, "tên lớp", "gvcn");
	}
	
	public LopHoc() {
		this("mã lớp");
	}
	/**
	 * @return the maLop
	 */
	public String getMaLop() {
		return maLop;
	}
	/**
	 * @param maLop the maLop to set
	 */
	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}
	/**
	 * @return the tenLop
	 */
	public String getTenLop() {
		return tenLop;
	}
	/**
	 * @param tenLop the tenLop to set
	 */
	public void setTenLop(String tenLop) {
		this.tenLop = tenLop;
	}
	/**
	 * @return the giaoVienCN
	 */
	public String getGiaoVienCN() {
		return giaoVienCN;
	}
	/**
	 * @param giaoVienCN the giaoVienCN to set
	 */
	public void setGiaoVienCN(String giaoVienCN) {
		this.giaoVienCN = giaoVienCN;
	}
	/**
	 * @return the dsSinhVien
	 */
	public List<HocSinh> getDsSinhVien() {
		return dsHocSinh;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maLop == null) ? 0 : maLop.hashCode());
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
		LopHoc other = (LopHoc) obj;
		if (maLop == null) {
			if (other.maLop != null)
				return false;
		} else if (!maLop.equalsIgnoreCase(other.maLop))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LopHoc [tenLop=" + tenLop + "]";
	}
	
	//C: Create
	public boolean create() {
		Connection con = Database.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into LopHoc values(?, ?, ?)");
			stmt.setString(1, maLop);
			stmt.setString(2, tenLop);
			stmt.setString(3, giaoVienCN);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(stmt);
		}
		return n > 0; 
	}
	
	//U: Update
	public boolean update() {
		Connection con = Database.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update LopHoc "
					+ "set tenLop = ?,"
					+ "giaovienCN = ? "
					+ "where maLop = ?");
			stmt.setString(1, tenLop);
			stmt.setString(2, giaoVienCN);
			stmt.setString(3, maLop);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(stmt);
		}
		return n > 0; 
	}
	
	//D: Delete
	public boolean delete() {
		Connection con = Database.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from LopHoc where maLop = ?");
			stmt.setString(1, maLop);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(stmt);
		}
		return n > 0; 
	}	
	
	//R: Read - Retrieve
	public void read() {
		Connection con = Database.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("Select * from HocSinh where maLop = ?");
			stmt.setString(1, maLop);
			rs = stmt.executeQuery();
			while(rs.next()){
				HocSinh sv  = new HocSinh(rs.getString("maHS"), rs.getString("hoTen"), rs.getString("email"), rs.getString("diaChi"), this);
				dsHocSinh.add(sv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(stmt);
		}
	}
}
