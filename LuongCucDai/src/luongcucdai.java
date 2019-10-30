import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

public class luongcucdai extends JFrame {
	private static final long serialVersionUID = 1L;
	private DefaultTableModel dt;
	private JTextField txtdinhthu;
	private JTextField txtdinhphat;
	private JButton btgiatri;
	private JButton btketqua;
	private JButton btreset;
	private JTable table;
	private JScrollPane sp;
	private JTextField txtketqua;
	private JSpinner sncanh;
	private JSpinner sndinh;
	int canh, dinh;

	/*
	 * Bài toán: cho một mạng luồng ( có thể được nhập vào hoặc đọc từ file). Tìm
	 * giá trị luồng cực đại Dữ liệu nhập vào lần lượt như sau: số đỉnh của đồ thị -
	 * số cạnh - chỉ số đỉnh phát - chỉ số đỉnh thu. Với mỗi cạnh ( m ) thì nhập 3
	 * số thể hiện 2 cung u, v trong mạng với khả năng thông qua là c Dữ liệu ra:
	 * giá trị luồng cực đại bài toán trên
	 * 
	 * -----------------------------------
	 * 
	 * Bài này áp dụng thuật toán Edmonds–Karp Link tìm hiểu thêm:
	 * https://vi.wikipedia.org/wiki/Thu%E1%BA%ADt_to%C3%A1n_Edmonds%E2%80%93Karp +
	 * https://en.wikipedia.org/wiki/Edmonds%E2%80%93Karp_algorithm
	 * 
	 * Độ phức tạp của bài toán: O(n*m^2); n: Số đỉnh m: Số cạnh s: chỉ số đỉnh phát
	 * t: chỉ số đỉnh thu
	 * 
	 * ------------
	 * 
	 * flow: biểu diễn giá trị lưu lượng tối đa. ==> flow[i][j] = giá trị lưu lượng
	 * tối đa tại cạnh i, j dd: để đánh dấu những điểm đã từng đi qua ( nếu đi qua
	 * thì cho = 1, nếu chưa qua thì cho = 0 ) trace: lưu lại dấu vết đường tăng
	 * luồng, sử dụng thuật toán bfs ( tìm kiếm theo chiều rộng ) c : ma trận biểu
	 * diễn khả năng thông qua của các cung trên mạng e : lưu cặp cạnh đồ thị ( đồ
	 * thị này vô hướng )
	 */
	private static final int maxn = 2000;
	static int n, m, s, t, ketqua;
	static int flow[][], dd[], trace[], c[][];
	static Vector<Integer> e[];

	public luongcucdai(String title) {
		super(title);
		canh = 0;
		dinh = 0;
		khoitaoframe();
		khoitaogiaodien();
		pack();

	}

	private void khoitao() {
		n = 0;
		m = 0;
		s = 0;
		t = 0;
		ketqua = 0;
		flow = new int[maxn][maxn];
		dd = new int[maxn];
		trace = new int[maxn];
		c = new int[maxn][maxn];
		e = new Vector[maxn];

		for (int i = 0; i < maxn; i++) {
			e[i] = new Vector<Integer>();
		}
	}

	// hàm tìm kiếm đường đi, cái này dựa trên thuật toán bfs
	private static void findgraph() {		// O(n^2)
		Queue<Integer> q = new LinkedList<Integer>();
		while (!q.isEmpty())	// O(n)
			q.poll();
		dd[s] = 1;
		q.add(s);
		while (!q.isEmpty()) {	//	O(n^2)
			int u = q.poll();	// O(1)

			for (int i = 0; i < e[u].size(); i++) {	// O(n)
				int v = e[u].get(i);	
				if (dd[v] != 0)
					continue;
				if (flow[u][v] >= c[u][v])	
					continue;
				trace[v] = u;
				dd[v] = 1;	

				if (v == t)
					return;

				q.add(v);	
			}
		}
	}

	// hàm tăng flow
	private static void incflow() { 	// O(n)
		int tmp = 1000000000;
		int v = t;
		while (v != s) {	// O(n)
			int u = trace[v];
			tmp = Math.min(tmp, c[u][v] - flow[u][v]);
			v = u;
		}

		v = t;
		while (v != s) {	// O(n)
			int u = trace[v];
			flow[u][v] += tmp;
			flow[v][u] -= tmp;
			v = u;
		}
		ketqua += tmp;
	}

	// hàm nhập dữ liệu
	private void nhapdulieu() {	// O(n^2)
		khoitao();	// khởi tạo dữ liệu mới, tránh bị trùng vector
		if (kiemtra() && dt.getColumnCount() > 0) {	// kiểm tra tính hợp lệ, và kiểm tra xem đã hiển thị table lên hay chưa
			n = canh;
			m = dinh;
			if (kiemtradinh()) {	// kiểm tra đỉnh đúng chuẩn
				s = Integer.parseInt(txtdinhphat.getText());	// mấy cái Integer.parseInt này là để chuyển từ kiểu String thành kiểu nguyên
				t = Integer.parseInt(txtdinhthu.getText());
				
				// O(n^2)
				for (int row = 0; row < canh; row++) {
					int u, v, val;
					for (int column = 1; column < canh; column++) {
						String value = table.getValueAt(row, column).toString();	// lấy dữ liệu từ table
						if (!value.equals("")) {	// nếu dữ liệu không trống thì mình nhập vào
							val = Integer.parseInt(value);
							u = row;
							v = column - 1;
							
							// add vào đồ thị
							c[u][v] = val;
							e[u].add(v);
							e[v].add(u);
						}
					}
				}
			}

		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ dữ liệu");
		}
	}

	private void timketqua() {	
		if (kiemtra() && dt.getColumnCount() > 0) {
			while (true) {	// độ phức tạp số lần tăng luồng * O(n^2)
				Arrays.fill(dd, 0);	// cho tất cả dữ liệu trong mảng dd đều là 0	. O(n)
				Arrays.fill(trace, 0);	// cho tất cả dữ liệu trong mảng trace đều là 0	. O(n)

				findgraph(); // tìm đường đi	O(n^2)
				if (trace[t] == 0) // nếu không tìm thấy đường đi thì in thoát	O(n)
					break;
				incflow(); // tăng flow	O(n)
			}

			txtketqua.setText(String.valueOf(ketqua));	// đưa dữ liệu vào ô kết quả
		}
	}

	private void khoitaogiaodien() {
		JPanel pnchinh = new JPanel();

		pnchinh.setLayout(new BorderLayout());
		add(pnchinh);

		JPanel pntieude = new JPanel();
		pntieude.setBackground(new Color(240, 248, 255));
		JPanel pngiua = new JPanel();
		pngiua.setBackground(new Color(240, 248, 255));

		JPanel pnketqua = new JPanel();
		pnketqua.setBackground(new Color(240, 248, 255));
		JLabel lbketqua = new JLabel("Kết quả: ");
		txtketqua = new JTextField();
		txtketqua.setPreferredSize(new Dimension(400, 30));
		pnketqua.add(lbketqua);
		pnketqua.add(txtketqua);

		pnchinh.add(pntieude, BorderLayout.NORTH);
		pnchinh.add(pngiua, BorderLayout.CENTER);
		pnchinh.add(pnketqua, BorderLayout.SOUTH);

		pngiua.setLayout(new BorderLayout());
		JLabel lbdinh = new JLabel("Đỉnh:");
		JLabel lbcanh = new JLabel("Cạnh:");
		JLabel lbdinhthu = new JLabel("Đỉnh thu:");
		JLabel lbdinhphat = new JLabel("Đỉnh phát:");

		SpinnerModel smcanh = new SpinnerNumberModel(canh, 0, 10000, 1);
		SpinnerModel smdinh = new SpinnerNumberModel(dinh, 0, 10000, 1);
		txtdinhthu = new JTextField();
		txtdinhphat = new JTextField();

		txtdinhthu.setPreferredSize(new Dimension(40, 25));
		txtdinhphat.setPreferredSize(txtdinhthu.getPreferredSize());

		lbdinhthu.setPreferredSize(lbdinhphat.getPreferredSize());
		lbdinh.setPreferredSize(lbdinhphat.getPreferredSize());
		lbcanh.setPreferredSize(lbdinhphat.getPreferredSize());

		sncanh = new JSpinner(smcanh);
		sncanh.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				canh = (int) sncanh.getValue();
			}
		});
		sndinh = new JSpinner(smdinh);
		sncanh.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				dinh = (int) sndinh.getValue();
			}
		});

		JPanel pndinh = new JPanel();
		JPanel pncanh = new JPanel();
		JPanel pndinhthu = new JPanel();
		JPanel pndinhphat = new JPanel();
		pndinh.setBackground(new Color(240, 248, 255));
		pncanh.setBackground(new Color(240, 248, 255));
		pndinhphat.setBackground(new Color(240, 248, 255));
		pndinhthu.setBackground(new Color(240, 248, 255));

		JPanel pnNorthCenter = new JPanel();
		pnNorthCenter.setBackground(new Color(240, 248, 255));
		pngiua.add(pnNorthCenter, BorderLayout.NORTH);

		pnNorthCenter.add(pndinh);
		pnNorthCenter.add(pncanh);
		pnNorthCenter.add(pndinhthu);
		pnNorthCenter.add(pndinhphat);

		pncanh.add(lbcanh);
		pncanh.add(sncanh);

		pndinh.add(lbdinh);
		pndinh.add(sndinh);

		pndinhthu.add(lbdinhthu);
		pndinhthu.add(txtdinhthu);

		pndinhphat.add(lbdinhphat);
		pndinhphat.add(txtdinhphat);

		btgiatri = new JButton("Nhập giá trị");
		btgiatri.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (canh != 0) {
					removedt();
					nhapdt();
				} else {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập cạnh");
				}
			}
		});
		btketqua = new JButton("Kết quả");
		btketqua.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nhapdulieu();
				timketqua();
			}
		});
		btreset = new JButton("Nhập lại");
		// cho dữ liệu reset lại
		btreset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dt.setColumnCount(0);
				txtdinhphat.setText("");
				txtdinhthu.setText("");
				txtketqua.setText("");;
			}
		});
		JPanel pnnut = new JPanel();
		pnnut.setBackground(new Color(240, 248, 255));
		pnnut.add(btgiatri);
		pnnut.add(btketqua);
		pnnut.add(btreset);
		pnNorthCenter.add(pnnut);

		dt = new DefaultTableModel();
		nhapdt();	// nhập dữ liệu cho table

		table = new JTable(dt);
		sp = new JScrollPane(table);
		table.setBackground(new Color(255, 255, 240));

		pngiua.add(sp);

	}

	protected void removedt() {
		int kichco = dt.getRowCount();
		for (int i = kichco - 1; i >= 0; i--) {
			dt.removeRow(i);
		}
	}
	
	// kiểm tra dữ liệu đã nhập vào hay chưa
	private boolean kiemtra() {
		if (canh != 0 && dinh != 0 && !txtdinhphat.getText().equals("") && !txtdinhthu.getText().equals("")) {
			return true;
		}
		return false;
	}

	// kiểm tra dữ liệu nhập vào có bị trống hay không
	private boolean kiemtratrong(String text, String name) {
		if (text.equals("")) {
			JOptionPane.showMessageDialog(null, " vui lòng nhập " + name);
			return true;
		}
		return false;
	}

	// kiểm tra dữ liệu nhập vào có đúng kiểu Int hay chưa
	private boolean kiemtradulieu(String text, String name) {
		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) < '0' || text.charAt(i) > '9') {
				JOptionPane.showMessageDialog(null, "dữ liệu nhập " + name + " sai");
				System.out.println("false");
				return false;
			}
		}
		System.out.println("true");
		return true;
	}

	// kiểm tra 2 đỉnh thu và đỉnh phát có thỏa mãn hay không
	private boolean kiemtradinh() {
		String dinhphatstring = txtdinhphat.getText();
		String dinhthustring = txtdinhthu.getText();
		System.out.println(dinhphatstring);
		System.out.println(dinhthustring);

		if (kiemtratrong(dinhphatstring, "Đỉnh phát") || kiemtratrong(dinhthustring, "Đỉnh thu"))
			return false;

		if (!kiemtradulieu(dinhphatstring, "Đỉnh phát") || !kiemtradulieu(dinhthustring, "Đỉnh thu"))
			return false;

		return true;
	}

	// khởi tạo dữ liệu ban đầu cho table
	private void nhapdt() {
		if (canh != 0) {
			Vector<String> vt = new Vector<>();
			vt.add("");
			for (int i = 0; i < canh; i++)
				vt.add(String.valueOf(i));
			// set lại cột cho table
			dt.setColumnIdentifiers(vt);

			// cài đặt table
			for (int i = 0; i < canh; i++) {
				vt.clear();
				vt.add(String.valueOf(i));
				for (int j = 0; j < canh; j++) {
					vt.add("");
				}
				dt.addRow(vt.toArray());
			}
		}
	}

	private void khoitaoframe() {
		setLocation(300, 100);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new luongcucdai("LUỒNG CỰC ĐẠI");
	}
}
