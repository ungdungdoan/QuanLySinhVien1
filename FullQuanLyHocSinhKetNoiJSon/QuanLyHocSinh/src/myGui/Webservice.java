package myGui;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
public class Webservice {
			public String getLogin() {
				String url = "jdbc:sqlserver://localhost:1433;databasename = doan";
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					Connection con = DriverManager.getConnection(url, "sa", "sapassword");
					Statement st = con.createStatement();
					String sql = "select MaHS,MatKhau from login ";
					ResultSet rs = st.executeQuery(sql);
					JSONObject lg ;
					JSONArray arr = new JSONArray();
					while (rs.next()) {
						lg = new JSONObject();
						lg.put("user", rs.getString("MaHS"));
						lg.put("pass", rs.getString("MatKhau"));
						arr.add(lg);
					}
					JSONObject response = new JSONObject(); 
					response.put("listlg", arr);
					return response.toString();//vay h tao 1 cai nhu vay ma doi vs form lop hoc ha ong//
					//Nói chúng là tất cả giao tiếp qua 1 cái class stub đó.. nó như là bản sao của class webservice á..
					//bên class webservice có j là class stub có hết
					//taij ben kia ms tao cai login a h la phai tao lai cai cua lophoc dung hok.. ừa.. h trong class webservice.. sẽ có hàm load lớp học
					//từ database... bên cái form lớp học lại sử dụng stub để lấy về kết quả từ database đó
					//tui hieu nhung tui hok bik ghi a
					//ong lam giup 1 cai lop hoc nua dc hok,, 
				} catch (Exception e) {
					return e.toString();
				}
			}
			
			public String getLophoc() {
				String url = "jdbc:sqlserver://localhost:1433;databasename = doan";
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					Connection con = DriverManager.getConnection(url, "sa", "sapassword");
					Statement st = con.createStatement();
					String sql = "select *from LopHoc";
					ResultSet rs = st.executeQuery(sql);
					JSONObject lg ;
					JSONArray arr = new JSONArray();
					while (rs.next()) {
						lg = new JSONObject();
						lg.put("malop", rs.getString("maLop"));
						lg.put("tenlop", rs.getString("tenLop"));
						lg.put("giaovien", rs.getString("giaoVienCN"));
						arr.add(lg);
					}
					JSONObject response = new JSONObject(); 
					response.put("listgiaovien", arr);
					return response.toString();//vay h tao 1 cai nhu vay ma doi vs form lop hoc ha ong//
					//Nói chúng là tất cả giao tiếp qua 1 cái class stub đó.. nó như là bản sao của class webservice á..
					//bên class webservice có j là class stub có hết
					//taij ben kia ms tao cai login a h la phai tao lai cai cua lophoc dung hok.. ừa.. h trong class webservice.. sẽ có hàm load lớp học
					//từ database... bên cái form lớp học lại sử dụng stub để lấy về kết quả từ database đó
					//tui hieu nhung tui hok bik ghi a
					//ong lam giup 1 cai lop hoc nua dc hok,, 
				} catch (Exception e) {
					return e.toString();
				}
			}
			
			
			
			public String getHocsinh() {
				String url = "jdbc:sqlserver://localhost:1433;databasename = doan";
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					Connection con = DriverManager.getConnection(url, "sa", "sapassword");
					Statement st = con.createStatement();
					String sql = "select *from HocSinh";
					ResultSet rs = st.executeQuery(sql);
					JSONObject lg ;
					JSONArray arr = new JSONArray();
					while (rs.next()) {
						lg = new JSONObject();
						lg.put("mahs", rs.getString("maHS"));
						lg.put("hoten", rs.getString("hoTen"));
						lg.put("email", rs.getString("email"));
						lg.put("diachi", rs.getString("diaChi"));
						lg.put("malop", rs.getString("maLop"));
						arr.add(lg);
					}
					JSONObject response = new JSONObject(); 
					response.put("listhocsinh", arr);
					return response.toString();//vay h tao 1 cai nhu vay ma doi vs form lop hoc ha ong//
					//Nói chúng là tất cả giao tiếp qua 1 cái class stub đó.. nó như là bản sao của class webservice á..
					//bên class webservice có j là class stub có hết
					//taij ben kia ms tao cai login a h la phai tao lai cai cua lophoc dung hok.. ừa.. h trong class webservice.. sẽ có hàm load lớp học
					//từ database... bên cái form lớp học lại sử dụng stub để lấy về kết quả từ database đó
					//tui hieu nhung tui hok bik ghi a
					//ong lam giup 1 cai lop hoc nua dc hok,, 
				} catch (Exception e) {
					return e.toString();
				}
			}

}
