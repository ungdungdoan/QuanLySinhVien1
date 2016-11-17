package mygui;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.axis2.AxisFault;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import mygui.WebserviceStub.GetLogin;
import mygui.WebserviceStub.GetLoginResponse;

public class dangnhap extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField txtMatKhau;
	private JButton btnDangNhap;
	private JButton btnThoat;
	Connection con;
	

	private JTextField txtMaHS;

	private FormLophoc frmlh;


	//private FrmLopHoc frmlh;





	public dangnhap(){
		setTitle("FORM ĐĂNG NHẬP");
		setSize(450,200);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Box b, b1,b2,b3,b4;
		add(b = Box.createVerticalBox());
		b.add(Box.createVerticalStrut(10));b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));b.add(b2 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));b.add(b3 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));b.add(b4 = Box.createHorizontalBox());
	
		
		JLabel lblTieuDe, lblMaSV;
		JLabel lblMK;
		b1.add(lblTieuDe = new JLabel("FORM ĐĂNG NHẬP", JLabel.CENTER));
		lblTieuDe.setFont(new Font("Arila", Font.BOLD, 26));
		
		b2.add(lblMaSV = new JLabel("Mã học sinh: ", JLabel.RIGHT));b2.add(txtMaHS = new JTextField());
		b3.add(lblMK = new JLabel("Mật khâu: "));b3.add(txtMatKhau = new JPasswordField());
		
		lblMaSV.setPreferredSize(lblMaSV.getPreferredSize());
		lblMK.setPreferredSize(lblMaSV.getPreferredSize());
		
		b4.add(Box.createHorizontalStrut(70));
		b4.add(btnDangNhap = new JButton("ĐăngNhập"));
		b4.add(btnThoat = new JButton("Thoát"));
	
		btnDangNhap.addActionListener(this);
		btnThoat.addActionListener(this);
		
	}
	public static void main(String[] args) throws ParseException, RemoteException {
		
	
	WebserviceStub stub= new WebserviceStub(); //khá»Ÿi táº¡o stubwwebservice
	//khá»Ÿi táº¡o stubwwebservice
		// TODO Auto-generated catch block
	WebserviceStub.GetLogin getlogin = new WebserviceStub.GetLogin();//láº¥y ra Method tÃªn getlogin bÃªn sáº»vice
	GetLoginResponse ress = stub.getLogin(getlogin);// táº¡o 1 biáº¿n... biáº¿n nÃ y sáº½ láº¥y nhá»¯ng j tá»« webservice gá»­i vá»�// response nghÄ©a lÃ  pháº£n há»“i Ä‘Ã³

	String str = ress.get_return();
	JSONParser parser = new JSONParser();
	JSONObject obj = (JSONObject)parser.parse(str);


	JSONArray arr = (JSONArray)obj.get("listlg");
	JSONObject obj_ = new JSONObject();
	for (int i = 0; i < arr.size(); i++) {
		obj_ = (JSONObject) arr.get(i);
		System.out.println("username = "+  obj_.get("user") + " Password =" + obj_.get("pass") );	
	}
	new dangnhap().setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnDangNhap))
		{
			java.sql.Statement stmt=null;
			Connection con = GetLogin.getConnection();
			if (txtMaHS.getText().equals("") || new String(((JPasswordField) txtMatKhau).getPassword()).equals("")) {
                JOptionPane.showMessageDialog(dangnhap.this, "Username and password must not empty.", "Invalid", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                //ResultSet res = con.createStatement().executeQuery(String.format("select * from login where maHS = '%s' and MatKhau = '%s'", txtMaHS.getText(), new String(((JPasswordField) txtMatKhau).getPassword())));
            	String query = "select * from login where maHS = '" + txtMaHS.getText() + "' and MatKhau = '" + new String(((JPasswordField) txtMatKhau).getPassword()) + "'";
                stmt = con.createStatement();
                ResultSet res =  stmt.executeQuery(query);
                if (res != null) {
                    if (res.next()) {
                        if (res.getString("MatKhau").equals(new String(((JPasswordField) txtMatKhau).getPassword()))) {
                             //JOptionPane.showMessageDialog(dangnhap.this, "You has been login successful.", "login successfuly", JOptionPane.INFORMATION_MESSAGE);
                             this.hide();
                             frmlh=new FormLophoc();
                             frmlh.show();
                        } else {
                            JOptionPane.showMessageDialog(dangnhap.this, "Password is match case.", "Login failed", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(dangnhap.this, "Wrong username or password.", "Login failed", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
		}else if(o.equals(btnThoat)){
			System.exit(0);
		}
		
		
	}
	

	

}


