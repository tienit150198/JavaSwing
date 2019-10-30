import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static LinkedList<Node> list1 = new LinkedList<Node>();
	static LinkedList<Node> list2 = new LinkedList<Node>();
	static LinkedList<Node> listKetqua = new LinkedList<Node>();
	xuli t = new xuli();
	private JTextField tfKetqua;
	private JButton btnCong;
	private JLabel lbDathuc2;
	private JTextField tfDathuc1;
	private JButton btnTru;
	private JButton btnNhan;

	public Main() {
		khoitao();
		hienthi();
		this.pack();
	}

	private void hienthi() {
		JPanel pnchinh = new JPanel();
		pnchinh.setBackground(new Color(240, 255, 240));
		pnchinh.setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 255, 240));

		ImageIcon iconThienNhien = new ImageIcon("image/anhchinh.png");
		panel.setLayout(new BorderLayout(0, 0));
		JLabel lblNewLabel = new JLabel(iconThienNhien);
		panel.add(lblNewLabel);

		JPanel pnGiua = new JPanel();
		pnGiua.setLayout(new BoxLayout(pnGiua, BoxLayout.Y_AXIS));
		JPanel pnDaThuc1 = new JPanel();
		JLabel lbDathuc1 = new JLabel("Đa thức 1");
		tfDathuc1 = new JTextField();
		tfDathuc1.setPreferredSize(new Dimension(400, 20));
		pnDaThuc1.add(lbDathuc1);
		pnDaThuc1.add(tfDathuc1);

		JPanel pnDaThuc2 = new JPanel();
		lbDathuc2 = new JLabel("Đa thức 2");
		JTextField tfDathuc2 = new JTextField();
		tfDathuc2.setPreferredSize(new Dimension(400, 20));
		pnDaThuc2.add(lbDathuc2);
		pnDaThuc2.add(tfDathuc2);

		JPanel pnPhepTinh = new JPanel();
		btnCong = new JButton("+");
		btnCong.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String text1 = tfDathuc1.getText();
				String text2 = tfDathuc2.getText();

				if (text1.equals("")) {
					JOptionPane.showMessageDialog(null, "Mời bạn nhập đa thức 1");
				} else if (text2.equals("")) {
					JOptionPane.showMessageDialog(null, "Mời bạn nhập đa thức 2");
				} else {
					text1 = t.chuanHoaXau(text1);
					text2 = t.chuanHoaXau(text2);
					try {
						list1 = t.tachString(text1);
						list2 = t.tachString(text2);
						listKetqua = t.tinhCong(list1, list2);
						tfKetqua.setText(t.getKetqua(listKetqua));
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Nhập đa thức sai định dạng");
					}
				}
			}
		});
		btnTru = new JButton("-");
		btnTru.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String text1 = tfDathuc1.getText();
				String text2 = tfDathuc2.getText();

				if (text1.equals("")) {
					JOptionPane.showMessageDialog(null, "Mời bạn nhập đa thức 1");
				} else if (text2.equals("")) {
					JOptionPane.showMessageDialog(null, "Mời bạn nhập đa thức 2");
				} else {
					text1 = t.chuanHoaXau(text1);
					text2 = t.chuanHoaXau(text2);
					try {
						list1 = t.tachString(text1);
						list2 = t.tachString(text2);
						listKetqua = t.tinhTru(list1, list2);
						tfKetqua.setText(t.getKetqua(listKetqua));
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Nhập đa thức sai định dạng");
					}
				}
			}
		});
		btnNhan = new JButton("*");
		btnNhan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String text1 = tfDathuc1.getText();
				String text2 = tfDathuc2.getText();

				if (text1.equals("")) {
					JOptionPane.showMessageDialog(null, "Mời bạn nhập đa thức 1");
				} else if (text2.equals("")) {
					JOptionPane.showMessageDialog(null, "Mời bạn nhập đa thức 2");
				} else {
					text1 = t.chuanHoaXau(text1);
					text2 = t.chuanHoaXau(text2);
					try {
						list1 = t.tachString(text1);
						list2 = t.tachString(text2);
						listKetqua = t.tinhNhan(list1, list2);
						tfKetqua.setText(t.getKetqua(listKetqua));
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Nhập đa thức sai định dạng");
					}
				}

			}
		});

		pnPhepTinh.add(btnCong);
		pnPhepTinh.add(btnTru);
		pnPhepTinh.add(btnNhan);

		pnGiua.add(pnDaThuc1);
		pnGiua.add(pnDaThuc2);
		pnGiua.add(pnPhepTinh);

		JPanel pnDuoi = new JPanel();
		JLabel lbKetqua = new JLabel("Kết quả");
		tfKetqua = new JTextField();
		tfKetqua.setPreferredSize(new Dimension(400, 20));
		pnDuoi.add(lbKetqua);
		pnDuoi.add(tfKetqua);
		tfKetqua.setEditable(false);

		pnDaThuc1.setBackground(new Color(240, 255, 240));
		pnDaThuc2.setBackground(new Color(240, 255, 240));
		pnPhepTinh.setBackground(new Color(240, 255, 240));
		pnGiua.setBackground(new Color(240, 255, 240));
		pnDuoi.setBackground(new Color(240, 255, 240));

		pnchinh.add(panel, BorderLayout.NORTH);
		pnchinh.add(pnGiua, BorderLayout.CENTER);
		pnchinh.add(pnDuoi, BorderLayout.SOUTH);
		this.add(pnchinh);
	}

	private void khoitao() {
		this.setLocation(330, 150);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new Main();
	}

}