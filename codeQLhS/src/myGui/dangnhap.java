package myGui;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import MyPackage.Database;





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

	private FrmLopHoc frmlh;





	public dangnhap(){
		setTitle("FORM Đăng Nhập");
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
		
		b2.add(lblMaSV = new JLabel("Mã số học sinh: ", JLabel.RIGHT));b2.add(txtMaHS = new JTextField());
		b3.add(lblMK = new JLabel("Mật khẩu: "));b3.add(txtMatKhau = new JPasswordField());
		
		lblMaSV.setPreferredSize(lblMaSV.getPreferredSize());
		lblMK.setPreferredSize(lblMaSV.getPreferredSize());
		
		b4.add(Box.createHorizontalStrut(70));
		b4.add(btnDangNhap = new JButton("Đăng Nhập"));
		b4.add(btnThoat = new JButton("Thoát"));
	
		btnDangNhap.addActionListener(this);
		btnThoat.addActionListener(this);
		
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnDangNhap))
		{
			java.sql.Statement stmt=null;
			Connection con = Database.getConnection();
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
                             frmlh=new FrmLopHoc();
                             frmlh.show();
                             
                             
                             
                             
                             //lblname.setText("Ban nhập Đúng");
                              //lblname.setForeground(Color.GREEN);  
                        	// Login successful DO SOMETHING HERE

                           // btn_One.setEnabled(true);
                            //btn_Two.setEnabled(true);
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

        }else if(o.equals(btnThoat))
		{
			System.out.println("Kết thúc");	
			System.exit(0);
		}
		

}
}
