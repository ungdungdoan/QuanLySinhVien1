package mygui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
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


import mygui.WebserviceStub.GetLoginResponse;
import mygui.WebserviceStub.GetLophocResponse;



public class FormLophoc extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4057349644354832605L;
	private JTextField txtMaLop;
	private JTextField txtTenLop;
	private JTextField txtGVCN;
	private JButton btnDau;
	private JButton btnTruoc;
	private JButton btnSau;
	private JButton btnCuoi;
	private JButton btnThem;
	private JButton btnLuu;
	private JButton btnSua;
	private JButton btnXoa;
	private JLabel lblMauTin;
	private DefaultTableModel dataModel;
	private JTable table;
	private JButton btnXemDSSV;

	private int tongSoMauTin;
	private int mauTinHienHanh;
	//private LopHocHelper lopHocHelper;
	private mygui.LopHocHelper lopHocHelper;
	

	public FormLophoc() {//cháº¡y form lá»›p há»�c lÃªn coi
		setTitle("THÔNG TIN LỚP HỌC");
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

	/*	addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				DbUtils.close(Database.getConnection());
				System.exit(0);
			}
		});*/
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "Do you want to close?", "Notify",
						JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		Box b, b1, b2, b3, b4, b5, b6, b7;
		//DÃƒÂ¹ng Box layout
		add(b = Box.createVerticalBox()); //Box theo chiÃ¡Â»ï¿½u dÃ¡Â»ï¿½c
		b.add(Box.createVerticalStrut(10)); //TÃ¡ÂºÂ¡o khoÃ¡ÂºÂ£ng cÃƒÂ¡ch theo chiÃ¡Â»ï¿½u dÃ¡Â»ï¿½c
		b.add(b1 = Box.createHorizontalBox()); b.add(Box.createVerticalStrut(10)); //b1 -> b7: Box theo chiÃ¡Â»ï¿½u ngang
		b.add(b2 = Box.createHorizontalBox()); b.add(Box.createVerticalStrut(10)); 
		b.add(b3 = Box.createHorizontalBox()); b.add(Box.createVerticalStrut(10)); 
		b.add(b4 = Box.createHorizontalBox()); b.add(Box.createVerticalStrut(10)); 
		b.add(b5 = Box.createHorizontalBox()); b.add(Box.createVerticalStrut(10)); 
		b.add(b6 = Box.createHorizontalBox()); b.add(Box.createVerticalStrut(10)); 
		b.add(b7 = Box.createHorizontalBox()); b.add(Box.createVerticalStrut(10)); 

		JLabel lblTieuDe, lblMaLop, lblTenLop, lblGVCN;
		b1.add(lblTieuDe = new JLabel("THÔNG TIN LỚP HỌC", JLabel.CENTER));
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 26));

		b2.add(lblMaLop = new JLabel("Mã lớp: ", JLabel.RIGHT)); b2.add(txtMaLop = new JTextField());
		b3.add(lblTenLop = new JLabel("Tên lớp: ", JLabel.RIGHT)); b3.add(txtTenLop = new JTextField());
		b4.add(lblGVCN = new JLabel("Giáo viên chủ nhiệm: ", JLabel.RIGHT)); b4.add(txtGVCN = new JTextField());

		lblMaLop.setPreferredSize(lblGVCN.getPreferredSize());
		lblTenLop.setPreferredSize(lblGVCN.getPreferredSize());

		b5.add(Box.createHorizontalStrut(50));
		b5.add(btnDau = new JButton(new ImageIcon("hinh/1.png")));
		//b5.add(btnTruoc = new JButton(new ImageIcon("hinh/3.png")));
		b5.add(lblMauTin = new JLabel());
		b5.add(btnSau = new JButton(new ImageIcon("hinh/2.png")));
		//b5.add(btnCuoi = new JButton(new ImageIcon("hinh/4.png")));
		b5.add(Box.createHorizontalStrut(50));
		b5.add(btnThem = new JButton("Thêm"));
		b5.add(btnLuu= new JButton("Lưu"));
		b5.add(btnSua = new JButton("Sửa"));
		b5.add(btnXoa = new JButton("Xóa"));

		String[] headers = {"Mã lớp", "Tên lớp", "Giáo viên chủ nhiệm"};
		dataModel = new DefaultTableModel(headers , 0);
		JScrollPane scroll;
		b6.add(scroll = new JScrollPane(table = new JTable(dataModel){
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
		scroll.setBorder(BorderFactory.createTitledBorder("Danh sách lớp học"));

		b7.add(Box.createHorizontalStrut(600));
		b7.add(btnXemDSSV = new JButton("Xem danh sách lớp sinh viên hiện tại"));
		btnXemDSSV.setForeground(Color.red);
		
		moKhoaTextfields(false);
		moKhoaControls(true);
		btnLuu.setEnabled(false);
		
		lopHocHelper = new LopHocHelper();
		table.setRowHeight(25);
		for(LopHoc lh : lopHocHelper.getAllLopHoc()){
			Object[] rowData = {lh.getMaLop(), lh.getTenLop(), lh.getGiaoVienCN()};
			dataModel.addRow(rowData);
		}
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				if(row >= 0){
					mauTinHienHanh = row;
					capNhatThongTinMauTin(mauTinHienHanh);
				}
			}
		});
		
		mauTinHienHanh = -1;
		tongSoMauTin = table.getRowCount();
		if(tongSoMauTin > 0){
			mauTinHienHanh = 0; //Khá»Ÿi táº¡o lÃ  máº«u tin Ä‘áº§u tiÃªn
			capNhatThongTinMauTin(mauTinHienHanh);
		}
		
		
		btnDau.addActionListener(this);
		//btnTruoc.addActionListener(this);
		btnSau.addActionListener(this);
		//btnCuoi.addActionListener(this);
		btnThem.addActionListener(this);
		btnLuu.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXemDSSV.addActionListener(this);
	
	}

	public static void main(String[] args) throws ParseException, RemoteException {
		//Giáº£ sá»­ Ä‘Ã¢y lÃ  fform lá»›p há»�c... tui sáº½ láº¥y toÃ n bá»� káº¿t quáº£ báº£ng lá»›p há»�c tráº£ vÃ¨ Ä‘Ã¢y.. xá»­ lÃ­ sao tuuyf Ã´ng ha.. cho lÃªn báº£ng hay lÃ m j Ä‘Ã³ thÃ¬ tuuyf..
		//// tá»›i chá»— nÃ y hiá»ƒu ko 
		// oke hieeur cho nay r ma
		 //doi xi ma bay gio toi mun ghi su kien cua form dang nhap copy bo vao ben dang nhpa do dung ko
		// Ä‘ung r.. code nhÆ° nhau.. bá»¯a sau nhÃ³m tui há»�c tui gá»�i Ã´ng Ä‘i.. mÃ¬nh Ã´ng Ä‘i thui nha.. oke khi nao hoc ong call nha
		// h lÃ m tÆ°Æ¡ng tá»± cÃ¡i file client Ã¡..oke // resart láº¡i Ä‘á»ƒ nÃ³ cáº­p nháº­t cÃ¡i method getlop hoc má»‘i thÃªm vÃ o..
		//
		new FormLophoc().setVisible(true);
		WebserviceStub stub = new WebserviceStub();
		WebserviceStub.GetLophoc getlophoc = new WebserviceStub.GetLophoc();
		GetLophocResponse ress1=stub.getLophoc(getlophoc);
		String str = ress1.get_return();
		JSONParser parser = new JSONParser();
		JSONObject obj =(JSONObject)parser.parse(str);
		JSONArray arr = (JSONArray)obj.get("listgiaovien");
		JSONObject obj1 = new JSONObject();
		for (int i = 0; i < arr.size(); i++) {
			obj1 = (JSONObject) arr.get(i);
			System.out.println("MaLop = "+  obj1.get("malop") + " tenLop =" + obj1.get("tenlop") + " giaoVienCN =" +obj1.get("giaovien"));
		}
		new FormLophoc().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnThem)){
			if(btnThem.getText().equalsIgnoreCase("Thêm")){
				moKhoaTextfields(true);
				moKhoaControls(false);
				btnLuu.setEnabled(true);
				btnThem.setEnabled(true);
				xoaRongTextfields();
				btnThem.setText("Hủy");
			}else if(btnThem.getText().equalsIgnoreCase("Hủy")){
				moKhoaTextfields(false);
				moKhoaControls(true);
				btnLuu.setEnabled(false);
				btnThem.setText("Thêm");
				napLopHocVaoTextfields();
			}

		}else if(o.equals(btnSua)){
			if(btnSua.getText().equalsIgnoreCase("Sửa")){
				moKhoaTextfields(true);
				txtMaLop.setEditable(false);
				moKhoaControls(false);
				btnLuu.setEnabled(true);
				btnSua.setEnabled(true);
				btnSua.setText("Hủy");
			}else if(btnSua.getText().equalsIgnoreCase("Hủy")){
				moKhoaTextfields(false);
				moKhoaControls(true);
				btnLuu.setEnabled(false);
				btnSua.setText("Sửa");
				napLopHocVaoTextfields();
			}
		}else if(o.equals(btnLuu)){
			if(btnThem.getText().equalsIgnoreCase("Hủy")){
				LopHoc lh = new LopHoc(txtMaLop.getText(), txtTenLop.getText(), txtGVCN.getText());
				if(lh.create()){
					Object[] rowData = {txtMaLop.getText(), txtTenLop.getText(), txtGVCN.getText()};
					JOptionPane.showMessageDialog(FormLophoc.this, "Information has been saved.", "save successfuly", JOptionPane.INFORMATION_MESSAGE);
					dataModel.addRow(rowData);
					
					moKhoaTextfields(false);
					moKhoaControls(true);
					btnLuu.setEnabled(false);
					btnThem.setText("Thêm");

					tongSoMauTin ++;
					mauTinHienHanh = tongSoMauTin - 1;
					capNhatThongTinMauTin(mauTinHienHanh);
				}
			}else if(btnSua.getText().equalsIgnoreCase("Hủy")){
				int row = table.getSelectedRow();
				if(row >= 0){
					LopHoc lh = new LopHoc(txtMaLop.getText(), txtTenLop.getText(), txtGVCN.getText());
					if(lh.update()){
						JOptionPane.showMessageDialog(FormLophoc.this, "Information has been corrected.", "Fix successfuly", JOptionPane.INFORMATION_MESSAGE);
						table.setValueAt(lh.getTenLop(), row, 1);
						table.setValueAt(lh.getGiaoVienCN(), row, 2);
						moKhoaTextfields(false);
						moKhoaControls(true);
						btnLuu.setEnabled(false);
						btnSua.setText("Sửa");
					}
				}
			}
		}else if(o.equals(btnXoa)){
			int row = table.getSelectedRow();
			if(row >= 0){
				String maLop = (String) table.getValueAt(row, 0);
				LopHoc lh = new LopHoc(maLop);
				if(lh.delete()){
					JOptionPane.showMessageDialog(FormLophoc.this, "Information has been deleted.", "Delete successfuly", JOptionPane.INFORMATION_MESSAGE);
					dataModel.removeRow(row);
					xoaRongTextfields();
					
					tongSoMauTin --;
					if(tongSoMauTin != 0){
						if(row == tongSoMauTin)
							mauTinHienHanh = 0;
						else
							mauTinHienHanh = row;
						capNhatThongTinMauTin(mauTinHienHanh);
					}else
						lblMauTin.setText("");
				}
			}
		}else if(o.equals(btnXemDSSV)){
			int row = table.getSelectedRow();
			if(row >= 0){
				LopHoc lh = new LopHoc(txtMaLop.getText(), txtTenLop.getText(), txtGVCN.getText());
				lh.read();
				FormHocsinh frm = new FormHocsinh(lh);
				frm.setVisible(true);
			}
		}else if(tongSoMauTin > 0){ //Cho cÃƒÂ¡c nÃƒÂºt duyÃ¡Â»â€¡t
			if(o.equals(btnDau))
				mauTinHienHanh = 0;
			else if(o.equals(btnCuoi))
				mauTinHienHanh = tongSoMauTin - 1;
			else if(o.equals(btnSau) && mauTinHienHanh < tongSoMauTin - 1)
				mauTinHienHanh ++;
			else if(o.equals(btnTruoc) && mauTinHienHanh > 0)
				mauTinHienHanh --;
			capNhatThongTinMauTin(mauTinHienHanh);
		}
		
	}

	private void capNhatThongTinMauTin(int n) {
		table.setRowSelectionInterval(n, n);
		lblMauTin.setText(mauTinHienHanh + 1 + "/" + tongSoMauTin);
		napLopHocVaoTextfields();
		
	}

	private void napLopHocVaoTextfields() {
		int row = table.getSelectedRow();
		if(row >= 0){
			txtMaLop.setText((String) table.getValueAt(row, 0));
			txtTenLop.setText((String) table.getValueAt(row, 1));
			txtGVCN.setText((String) table.getValueAt(row, 2));
		}
		
	}

	private void xoaRongTextfields() {
		txtMaLop.setText("");
		txtTenLop.setText("");
		txtGVCN.setText("");
		txtMaLop.requestFocus();
		
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
		btnXemDSSV.setEnabled(b);
		
	}

	private void moKhoaTextfields(boolean b) {
		txtMaLop.setEditable(b);
		txtTenLop.setEditable(b);
		txtGVCN.setEditable(b);
		
	}



	

}
