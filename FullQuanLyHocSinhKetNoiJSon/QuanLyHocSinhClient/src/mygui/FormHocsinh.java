package mygui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.apache.axis2.AxisFault;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import mygui.WebserviceStub.GetHocsinhResponse;

public class FormHocsinh extends JDialog implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1554680235689968471L;

	private JTextField txtHoTen;
	private JTextField txtEmail;
	private JTextField txtDiaChi;
	private JButton btnDau;
	private JButton btnTruoc;
	private JButton btnSau;
	private JButton btnCuoi;
	private JLabel lblMauTin;
	private JButton btnThem;
	private JButton btnLuu;
	private JButton btnSua;
	private JButton btnXoa;
	private DefaultTableModel dataModel;
	private JTable table;
	private LopHoc lh;
	private JScrollPane scroll;
	private int mauTinHienTai;
	private int tongSoMauTin;
	private JTextField txtMaHS;

	public FormHocsinh() {
		setTitle("THÔNG TIN HỌC SINH");
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	
		Box b, b1,b2,b3,b4,b5,b6, b7;
		add(b = Box.createVerticalBox());
		b.add(Box.createVerticalStrut(10));b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));b.add(b2 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));b.add(b3 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));b.add(b4 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));b.add(b5 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));b.add(b6 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));b.add(b7 = Box.createHorizontalBox());
		
		JLabel lblTieuDe, lblMaSV, lblHoTen, lblEmail, lblDiaChi;
		b1.add(lblTieuDe = new JLabel("THÔNG TIN HỌC SINH", JLabel.CENTER));
		lblTieuDe.setFont(new Font("Arila", Font.BOLD, 26));
		
		b2.add(lblMaSV = new JLabel("Mã học sinh: ", JLabel.RIGHT));b2.add(txtMaHS = new JTextField());
		b3.add(lblHoTen = new JLabel("Họ tên: ", JLabel.RIGHT));b3.add(txtHoTen = new JTextField());
		b4.add(lblEmail = new JLabel("Email: ", JLabel.RIGHT));b4.add(txtEmail = new JTextField());
		b5.add(lblDiaChi = new JLabel("Địa chỉ: ", JLabel.RIGHT));b5.add(txtDiaChi = new JTextField());
		
		lblHoTen.setPreferredSize(lblMaSV.getPreferredSize());
		lblEmail.setPreferredSize(lblMaSV.getPreferredSize());
		lblDiaChi.setPreferredSize(lblMaSV.getPreferredSize());
		
		b6.add(Box.createHorizontalStrut(70));
		b6.add(btnDau = new JButton(new ImageIcon("hinh/1.png")));
		//b6.add(btnTruoc = new JButton(new ImageIcon("hinh/go-previous.png")));
		b6.add(lblMauTin = new JLabel());
		b6.add(btnSau = new JButton(new ImageIcon("hinh/2.png")));
		//b6.add(btnCuoi = new JButton(new ImageIcon("hinh/go-last.png")));
		b6.add(Box.createHorizontalStrut(70));
		b6.add(btnThem = new JButton("Thêm"));
		b6.add(btnLuu = new JButton("Lưu"));
		b6.add(btnSua = new JButton("Sửa"));
		b6.add(btnXoa = new JButton("Xóa"));
		
		String[] tieuDe = {"Mã học sinh", "Họ tên", "Email", "Địa chỉ"};
		b7.add(scroll = new JScrollPane(table = new JTable(dataModel = new DefaultTableModel(tieuDe , 0)){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		}));
		scroll.setBorder(BorderFactory.createTitledBorder("Danh sách học sinh"));
		
		
	}
	public FormHocsinh(LopHoc lh){
		this();
		this.lh = lh;
		
		scroll.setBorder(BorderFactory.createTitledBorder("Danh sách học sinh trong lớp: " + lh.getTenLop()));
		for(HocSinh sv : lh.getDsSinhVien()){
			Object[] rowData = {sv.getMaHS(), sv.getHoTen(), sv.getEmail(), sv.getDiaChi()};
			dataModel.addRow(rowData);
		}
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				napDuLieuTextfields();
			}

		
		});
		//CÃ¡ÂºÂ­p nhÃ¡ÂºÂ­t thÃƒÂ´ng tin mÃ¡ÂºÂ«u tin
		mauTinHienTai = -1;
		tongSoMauTin = table.getRowCount();
		if(tongSoMauTin > 0)
		{
			mauTinHienTai = 0;//KhÃ¡Â»Å¸i tÃ¡ÂºÂ¡o lÃƒÂ  mÃ¡ÂºÂ«u tin Ã„â€˜Ã¡ÂºÂ§u tiÃƒÂªn
			capNhatThongTinMauTin();
		}
		
		moKhoaTextfields(false);
		moKhoaControls(true);
		btnLuu.setEnabled(false);
		
		btnDau.addActionListener(this);
		//btnTruoc.addActionListener(this);
		btnSau.addActionListener(this);
		//btnCuoi.addActionListener(this);
		btnThem.addActionListener(this);
		btnLuu.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
	}
	private void capNhatThongTinMauTin() {
		table.setRowSelectionInterval(mauTinHienTai, mauTinHienTai);
		lblMauTin.setText(mauTinHienTai + 1 + "/" + tongSoMauTin);
		napDuLieuTextfields();
		
	}
	private void napDuLieuTextfields() {
		int row = table.getSelectedRow();
		if(row >= 0){
			txtMaHS.setText((String) table.getValueAt(row, 0));
			txtHoTen.setText((String) table.getValueAt(row, 1));
			txtEmail.setText((String) table.getValueAt(row, 2));
			txtDiaChi.setText((String) table.getValueAt(row, 3));
		}	
	}
	private void moKhoaTextfields(boolean b) {
		txtMaHS.setEditable(b);
		txtHoTen.setEditable(b);
		txtEmail.setEditable(b);
		txtDiaChi.setEditable(b);	
	}
	private void moKhoaControls(boolean b) {
		btnDau.setEnabled(b);
		//btnTruoc.setEnabled(b);
		btnSau.setEnabled(b);
		//btnCuoi.setEnabled(b);
		btnThem.setEnabled(b);
		btnLuu.setEnabled(b);
		btnSua.setEnabled(b);
		btnXoa.setEnabled(b);
	}
	public static void main(String[] args) throws RemoteException, ParseException {
		new FormLophoc().setVisible(true);
		WebserviceStub stub = new WebserviceStub();
		WebserviceStub.GetHocsinh gethocsinh = new WebserviceStub.GetHocsinh();
		GetHocsinhResponse ress1=stub.getHocsinh(gethocsinh);
		String str = ress1.get_return();
		JSONParser parser = new JSONParser();
		JSONObject obj =(JSONObject)parser.parse(str);
		JSONArray arr = (JSONArray)obj.get("listhocsinh");
		JSONObject obj1 = new JSONObject();
		for (int i = 0; i < arr.size(); i++) {
			obj1 = (JSONObject) arr.get(i);
			System.out.println("Mã học sinh = "+  obj1.get("mahs") + " Họ tên = " + obj1.get("hoten") + " Email = " +obj1.get("email")+ " Địa chỉ = " + obj1.get("diachi") + " Mã lớp = " +obj1.get("malop"));
		}
		//new FormHocsinh().setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnThem)){
			if(btnThem.getText().equalsIgnoreCase("Thêm")){
				moKhoaControls(false);
				btnLuu.setEnabled(true);
				btnThem.setEnabled(true);
				btnThem.setText("Hủy");
				moKhoaTextfields(true);
				xoaRongTextfields();
			}else if(btnThem.getText().equalsIgnoreCase("Hủy")){
				moKhoaControls(true);
				btnLuu.setEnabled(false);
				btnThem.setText("Thêm");
				moKhoaTextfields(false);
				napDuLieuTextfields();
			}
		}else if(o.equals(btnSua)){
			if(btnSua.getText().equalsIgnoreCase("Sửa")){
				moKhoaControls(false);
				btnLuu.setEnabled(true);
				btnSua.setEnabled(true);
				btnSua.setText("Hủy");
				moKhoaTextfields(true);
				txtMaHS.setEditable(false);
			}else if(btnSua.getText().equalsIgnoreCase("Hủy")){
				moKhoaControls(true);
				btnLuu.setEnabled(false);
				btnSua.setText("Sửa");
				moKhoaTextfields(false);
			}
		}else if(o.equals(btnXoa)){
			int row = table.getSelectedRow();
			if(row >= 0){
				HocSinh hs = new HocSinh(txtMaHS.getText());
				if(hs.delete()){
					JOptionPane.showMessageDialog(FormHocsinh.this, "Information has been deleted.", "Delete successfuly", JOptionPane.INFORMATION_MESSAGE);
					dataModel.removeRow(row);
					xoaRongTextfields();
					
					tongSoMauTin --;
					if(tongSoMauTin > 0){
						if(row == tongSoMauTin)
							mauTinHienTai = 0;
						else
							mauTinHienTai = row;
						capNhatThongTinMauTin();
					}else
						lblMauTin.setText("");
				}
			}
		}else if(o.equals(btnLuu)){
			if(btnThem.getText().equalsIgnoreCase("Hủy")){
				HocSinh sv = new HocSinh(txtMaHS.getText(), txtHoTen.getText(), txtEmail.getText(), txtDiaChi.getText(), lh);
				if(sv.create()){//ThÃªm thÃ nh cÃ´ng
					Object []rowData = {txtMaHS.getText(), txtHoTen.getText(), txtEmail.getText(), txtDiaChi.getText()};
					JOptionPane.showMessageDialog(FormHocsinh.this, "Information has been saved.", "save successfuly", JOptionPane.INFORMATION_MESSAGE);
					dataModel.addRow(rowData);
					
					tongSoMauTin ++;
					mauTinHienTai = tongSoMauTin - 1;
					capNhatThongTinMauTin();
					
					moKhoaControls(true);
					btnLuu.setEnabled(false);
					btnThem.setText("Thêm");
					moKhoaTextfields(false);
				}
			}else if(btnSua.getText().equalsIgnoreCase("Hủy")){
				int row = table.getSelectedRow();
				if(row >= 0){
					HocSinh sv = new HocSinh(txtMaHS.getText(), txtHoTen.getText(), txtEmail.getText(), txtDiaChi.getText(), lh);
					if(sv.update()){
						JOptionPane.showMessageDialog(FormHocsinh.this, "Information has been corrected.", "Fix successfuly", JOptionPane.INFORMATION_MESSAGE);
						table.setValueAt(sv.getHoTen(), row, 1);
						table.setValueAt(sv.getEmail(), row, 2);
						table.setValueAt(sv.getDiaChi(), row, 3);
						moKhoaControls(true);
						btnLuu.setEnabled(false);
						btnSua.setText("Sửa");
						moKhoaTextfields(false);
					}
				}
			}
		}else if(tongSoMauTin > 0){
			if(o.equals(btnDau))
				mauTinHienTai = 0;
			else if(o.equals(btnCuoi))
				mauTinHienTai = tongSoMauTin - 1;
			else if(o.equals(btnSau) && mauTinHienTai < tongSoMauTin - 1)
				mauTinHienTai ++;
			else if(o.equals(btnTruoc) && mauTinHienTai > 0)
				mauTinHienTai --;
			capNhatThongTinMauTin();
		}
		
	}
	private void xoaRongTextfields() {
		txtMaHS.setText("");
		txtHoTen.setText("");
		txtEmail.setText("");
		txtDiaChi.setText("");
		txtMaHS.requestFocus();	
	}
	
	
}
