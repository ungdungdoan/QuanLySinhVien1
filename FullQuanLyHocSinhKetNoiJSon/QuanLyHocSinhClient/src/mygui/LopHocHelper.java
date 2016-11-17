package mygui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mygui.WebserviceStub.GetLophoc;


public class LopHocHelper {
	public List<LopHoc> getAllLopHoc() {
		List<LopHoc> dslopHoc = new ArrayList<LopHoc>();
		Connection con = GetLophoc.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("Select * from LopHoc");
			rs = stmt.executeQuery();
			while(rs.next()){
				dslopHoc.add(new LopHoc(rs.getString("malop"), rs.getString("tenLop"), rs.getString("giaoVienCN")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.close(rs, stmt);
		}
		return dslopHoc;
	}
}
