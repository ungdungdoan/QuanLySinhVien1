package MyEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import MyPackage.Database;

public class login {
private String MaHS;
private String MatKhau;
public login(String maHS, String matKhau) {
	super();
	MaHS = maHS;
	MatKhau = matKhau;
}
public login(String maHS) {
	super();
	MaHS = maHS;
}
public String getMaHS() {
	return MaHS;
}
public void setMaHS(String maHS) {
	MaHS = maHS;
}
public String getMatKhau() {
	return MatKhau;
}
public void setMatKhau(String matKhau) {
	MatKhau = matKhau;
}
@Override
public String toString() {
	return "login [MaHS=" + MaHS + ", MatKhau=" + MatKhau + "]";
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((MaHS == null) ? 0 : MaHS.hashCode());
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	login other = (login) obj;
	if (MaHS == null) {
		if (other.MaHS != null)
			return false;
	} else if (!MaHS.equalsIgnoreCase(other.MaHS))
		return false;
	return true;
}

public boolean create(){
	 Connection con= Database.getConnection();
	 PreparedStatement prstmt=null;
	 int n=0;
	 try {
		prstmt= con.prepareStatement("insert into login values (?,?)");
		prstmt.setString(1,MaHS);
		prstmt.setString(2,MatKhau);
		n=prstmt.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return n>0; 
}

}
