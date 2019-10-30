import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

import javax.swing.BoxLayout;
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

public class Main extends JFrame {
	
	private static Queue<isObject> queueAddTable;	// đây là một cái Queue để đưa dữ liệu vào table
	private static Queue<String> queueMoPhongKhiChay;
	private static final long serialVersionUID = 1L;
	private JTextField tfHauto;
	private JTextField tfKetqua;
	private JTextField tfBieuthuc;
	private static DefaultTableModel dt;
	private JTable tb;
	private static int delay = 500;	// thời gian delay
	private int step = 1;	// các bước hiển thị
	private JSpinner spnDelay;
	private JButton btnNext;
	private JButton btnKetqua;
	private JButton btnStep;
	private static JTextField tfMophong;
	private static boolean next;	// có next nữa hay không. false là dừng next, true là next
	private static int choose = 0;	// chế độ chọn. 0 là chưa chọn, 1 là chọn hiển thị kết quả, 2 là chọn từng bước
	private JButton btnReset;

	// class này chứa dữ liệu được đưa vào bảng
	public class isObject {
		String buoc, pheptinh, stack, out;

		isObject(String buoc, String pheptinh, String stack, String out) {
			this.buoc = buoc;
			this.pheptinh = pheptinh;
			this.stack = stack;
			this.out = out;
		}
		// lấy dữ liệu đưa vào bảng
		public String[] getValue() {
			String[] string = new String[4];
			string[0] = this.buoc;
			string[1] = this.pheptinh;
			string[2] = this.stack;
			string[3] = this.out;
			return string;
		}
	}

	// hàm này để chuẩn hóa xâu, phòng trường hợp nhập dữ liệu khoảng trống nhiều
	public synchronized String chuanHoaXau(String text) {
		StringBuilder ketqua = new StringBuilder();
		for (int i = 0; i < text.length(); i++) {
			char kitu = text.charAt(i);
			if (kitu != ' ')
				ketqua.append(kitu);
		}
		return ketqua.toString().trim();
	}

	// lấy độ ưu tiên toán tử
	public synchronized int getPriority(String text) {
		if (text.equals("*") || text.equals("/"))
			return 2;
		if (text.equals("+") || text.equals("-"))
			return 1;
		return 0;
	}
	
	// kiểm tra xem có phải toán tử hay không. 0 là số, 1 là toán tử đóng mở ngoặc, 2 là toán tử cộng trừ nhân chia
	public synchronized int toanTu(String text) {
		if (getPriority(text) == 0) {
			if (!text.equals("(") && !text.equals(")"))
				return 0;
			return 1;
		}
		return 2;
	}

	// chuyển dữ liệu từ Stack sang String
	public synchronized String getStack(Stack<String> st) {
		Stack<String> stt = new Stack<String>();
		stt.addAll(st);
		StringBuilder bd = new StringBuilder();

		// liên tục lấy dữ liệu từ stack ra để đưa vào string
		while (!stt.isEmpty()) {	// nếu dữ liệu còn trống thì tiếp tục lấy
			bd.append(stt.pop());
			bd.append(" ");
		}

		bd = bd.reverse();	// do dữ liệu là stack nên phải đảo dữ liệu lại
		if (bd.toString().equals(""))
			bd.append("Empty");

		return bd.toString();
	}
	// Chuyển dữ liệu từ Queue thành String
	public static synchronized String getQueue(Queue<String> qq) {
		Queue<String> queue = new LinkedList<String>();
		queue.addAll(qq);
		StringBuilder bd = new StringBuilder();

		while (!queue.isEmpty()) {
			bd.append(queue.poll());
		}

		return bd.toString();
	}
	
	// chuyển dữ liệu kiểu Vector thành String
	public synchronized String getVector(Vector<String> vt) {
		StringBuilder bd = new StringBuilder();

		for (String string : vt) {
			bd.append(string);
			bd.append(" ");
		}

		if (bd.toString().equals(""))
			bd.append("Empty");
		return bd.toString();
	}

	// chuyển dữ liệu kiểu Vector thành isObject
	public synchronized isObject VectorToOjbect(Vector<String> vt) {
		isObject object = new isObject(vt.get(0), vt.get(1), vt.get(2), vt.get(3));
		return object;
	}

	// lấy các con số
	public synchronized Queue<String> getNumberFromText(String text) {
		StringBuilder number = new StringBuilder();
		Queue<String> out = new LinkedList<String>();

		int i = 0;
		// kiểm tra nếu số đầu tiên là số âm hoặc dương
		if (text.charAt(0) == '-') {	
			number.append("-");
			i++;
		} else if (text.charAt(0) == '+')
			i++;

		boolean ok = false;	// dữ liệu là ( thì đánh dấu false để sau đó khi gặp ) thì bỏ qua
		for (; i < text.length(); i++) {
			String tam = String.valueOf(text.charAt(i));
			if (toanTu(tam) == 0)	// đưa dữ liệu vào number phòng trường hợp 2 số hoặc số thực
				number.append(tam);
			else if (toanTu(tam) == 1) {
				if (number.length() > 0) {
					out.add(number.toString());
					number.delete(0, number.length());
				}
				if (tam.equals("(") && i < text.length()) {
					String tam1 = String.valueOf(text.charAt(i + 1));
					if (toanTu(tam1) == 2) {
						number.append(tam1);
						i++;
						ok = true;
					} else
						out.add(tam);

				} else if (tam.equals(")")) {
					if (ok)
						continue;
					else
						out.add(tam);

				}
			} else {
				if (number.length() > 0) {
					out.add(number.toString());
					number.delete(0, number.length());
				}
				out.add(tam);
			}
		}
		// kiểm tra nếu cuối cùng còn dữ liệu thì thêm vào
		if (number.length() > 0)
			out.add(number.toString());

		return out;
	}

	// chuyển trung tố thành hậu tố
	public synchronized Vector<String> chuyenDoi(String text) {
		text = chuanHoaXau(text);	// gọi chuẩn hóa xâu
		
		Queue<String> queueMophong = new LinkedList<String>();
		queueMophong = getNumberFromText(text);
		queueMoPhongKhiChay.addAll(queueMophong);
		
		
		Stack<String> st = new Stack<String>();
		Vector<String> out = new Vector<String>();

		// vector để lấy dữ liệu đưa vào bảng
		Vector<String> vectorQueue = new Vector<String>();
		tfMophong.setText(getQueue(queueMophong));
		String pheptinh = "";

		while (!queueMophong.isEmpty()) {
			vectorQueue.clear();
			vectorQueue.add(String.valueOf(step));
			step++;
			pheptinh = queueMophong.poll();
			vectorQueue.add(pheptinh);

			if (toanTu(pheptinh) == 0) {
				out.add(pheptinh);

				vectorQueue.add(getStack(st));
				vectorQueue.add(getVector(out));
			} else if (toanTu(pheptinh) == 1) {
				if (pheptinh.equals("(")) {
					st.push(pheptinh);

					vectorQueue.add(getStack(st));
					vectorQueue.add(getVector(out));
				} else {
					while (!st.isEmpty() && !st.peek().equals("(")) {
						out.add(st.pop());
					}
					if (!st.isEmpty())
						st.pop();

					vectorQueue.add(getStack(st));
					vectorQueue.add(getVector(out));
				}
			} else {
				while (!st.isEmpty() && getPriority(st.peek()) >= getPriority(pheptinh))
					out.add(st.pop());
				st.push(pheptinh);

				vectorQueue.add(getStack(st));
				vectorQueue.add(getVector(out));
			}
			queueAddTable.add(VectorToOjbect(vectorQueue));
		}
		// nếu stack còn thì đưa dữ liệu từ stack vào out
		if (!st.isEmpty()) {
			vectorQueue.clear();
			vectorQueue.add(String.valueOf(step));
			vectorQueue.add("");
			while (!st.isEmpty())
				out.add(st.pop());

			vectorQueue.add(getStack(st));
			vectorQueue.add(getVector(out));

			queueAddTable.add(VectorToOjbect(vectorQueue));
		}

		return out;
	}

	// đảo dữ liệu
	public synchronized String reverseString(String text) {
		StringBuilder tmp = new StringBuilder(text);
		tmp = tmp.reverse();
		return tmp.toString();
	}
	
	// chuyển hậu tố thành kết quả
	public synchronized String tinhToan(Vector<String> isList) {
		Stack<String> res = new Stack<String>();
		for (String string : isList) {	// duyệt vòng lặp
			if (toanTu(string) == 0)	// nếu dữ liệu là số thì đưa vào res
				res.push(string);
			else {
				String textB = res.pop();	// lấy dữ liệu B trước, A sau vì nó là stack ( lấy ngược)
				String textA = res.pop();
				if (textA.length() > 1) {	// nếu dữ liệu > 1 dòng thì đổi chiều nó
					reverseString(textA);
				}
				if (textB.length() > 1) {	// nếu dữ liệu > 1 dòng thì đổi chiều nó
					reverseString(textB);
				}

				float a = Float.valueOf(textA);	// đổi dữ liệu thành số thực
				float b = Float.valueOf(textB);	// đổi dữ liệu thành số thực
				
				// nếu là các phép toán thì cộng vào rồi đưa vào dữ liệu res, nếu không thì đưa vào lại
				if (string.equals("+"))
					res.push(String.valueOf(a + b));
				else if (string.equals("-"))
					res.push(String.valueOf(a - b));
				else if (string.equals("*"))
					res.push(String.valueOf(a * b));
				else if (string.equals("/"))
					res.push(String.valueOf(a / b));
				else {
					res.push(textA);	
					res.push(textB);
				}
			}
		}
		String tmp = res.toString();
		return tmp.substring(1, tmp.length() - 1);
	}

	public Main(String title) {
		super(title);
		khoitao();
		hienthicuaso();
		this.pack();
	}

	private synchronized void hienthicuaso() {
		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BorderLayout());
		JPanel pnCenter = new JPanel();
		JPanel pnNorth = new JPanel();
		JPanel pnBieuthuc = new JPanel();
		JPanel pnKetqua = new JPanel();
		JPanel pnHauto = new JPanel();
		JPanel pnButton = new JPanel();

		pnNorth.setLayout(new BoxLayout(pnNorth, BoxLayout.Y_AXIS));

		JLabel lbBieuthuc = new JLabel("Phép tính: ");
		JLabel lbKetqua = new JLabel("Kết quả: ");
		JLabel lbHauto = new JLabel("Hậu tố: ");

		lbKetqua.setPreferredSize(lbBieuthuc.getPreferredSize());
		lbHauto.setPreferredSize(lbBieuthuc.getPreferredSize());

		tfBieuthuc = new JTextField();
		tfKetqua = new JTextField();
		tfKetqua.setEditable(false);
		tfHauto = new JTextField();
		tfHauto.setEditable(false);

		tfBieuthuc.setPreferredSize(new Dimension(400, 25));
		tfKetqua.setPreferredSize(tfBieuthuc.getPreferredScrollableViewportSize());
		tfHauto.setPreferredSize(tfBieuthuc.getPreferredScrollableViewportSize());

		pnBieuthuc.add(lbBieuthuc);
		pnBieuthuc.add(tfBieuthuc);
		pnKetqua.add(lbKetqua);
		pnKetqua.add(tfKetqua);
		pnHauto.add(lbHauto);
		pnHauto.add(tfHauto);

		pnNorth.add(pnBieuthuc);
		pnNorth.add(pnKetqua);
		pnNorth.add(pnHauto);
		pnNorth.add(pnButton);

		btnKetqua = new JButton("Kết quả");
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				reset();
			}

		});
		btnKetqua.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String text = tfBieuthuc.getText();
				if (!text.equals("")) {
					if (choose == 0) {
						removeDt();
						showKetqua(text);
					}
					choose = 1;
				} else {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập biểu thức");
				}
			}

		});
		btnStep = new JButton("Mô phỏng");
		btnStep.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String text = tfBieuthuc.getText();
				if (!text.equals("")) {
					if (choose == 0) {
						removeDt();
						showKetqua(text);
					}
					btnNext.setVisible(true);
					choose = 2;
				} else {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập biểu thức");
				}
			}

		});
		btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				next = true;
			}
		});
		btnNext.setVisible(false);

		pnButton.add(btnKetqua);
		pnButton.add(btnReset);
		pnButton.add(btnStep);
		pnButton.add(btnNext);

		JLabel lbLine = new JLabel();
		lbLine.setPreferredSize(new Dimension(100, 10));

		JLabel lbDelay = new JLabel("Delay: ");
		SpinnerModel jspDelay = new SpinnerNumberModel(delay, 0, 5000, 1);
		spnDelay = new JSpinner(jspDelay);
		spnDelay.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				delay = (int) spnDelay.getValue();
				System.out.println(delay);
			}
		});

		pnButton.add(lbLine);
		pnButton.add(lbDelay);
		pnButton.add(spnDelay);

		pnMain.add(pnNorth, BorderLayout.NORTH);
		pnMain.add(pnCenter, BorderLayout.CENTER);
		add(pnMain);

		tfMophong = new JTextField();
		tfMophong.setPreferredSize(new Dimension(400, 40));
		tfMophong.setEditable(false);
		tfMophong.setFont(new Font("Arial", Font.BOLD, 30));
		dt = new DefaultTableModel();
		setDt();
		tb = new JTable(dt);

		JScrollPane jsp = new JScrollPane(tb);

		pnCenter.setLayout(new BorderLayout());
		pnCenter.add(tfMophong, BorderLayout.NORTH);
		pnCenter.add(jsp, BorderLayout.CENTER);
	}

	protected void mophong(String text) {
		chuyenDoi(text);
	}

	public synchronized void setDt() {
		Vector<String> vector = new Vector<String>();
		vector.add("Bước");
		vector.add("Phép tính");
		vector.add("Stack");
		vector.add("Output");
		dt.setColumnIdentifiers(vector);
	}

	protected synchronized void removeDt() {
		int kichco = dt.getRowCount();
		for (int i = kichco - 1; i >= 0; i--) {
			dt.removeRow(i);
		}
	}
	private void reset() {
		
		tfBieuthuc.setText("");
		tfHauto.setText("");
		tfKetqua.setText("");
		tfMophong.setText("");
		queueAddTable.clear();
		queueMoPhongKhiChay.clear();
		next = false;
		choose = 0;
		step = 1;
		removeDt();
	}

	private synchronized void showKetqua(String text) {
		Vector<String> vectorHauTo = chuyenDoi(text);
		StringBuilder hauto = new StringBuilder();
		for (String string : vectorHauTo) {
			hauto.append(string);
			hauto.append(" ");
		}
		String ketqua = tinhToan(vectorHauTo);

		tfKetqua.setText(ketqua);
		tfHauto.setText(hauto.toString());
	}

	private void khoitao() {
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	protected boolean kiemtrasaidulieu(String textNhap) {
		for (int i = 0; i < textNhap.length(); i++) {
			char x = textNhap.charAt(i);
			if (x >= 'a' && x <= 'z' || x >= 'A' && x <= 'Z')
				return true;
		}
		return false;
	}

	public static void main(String[] args) {
		queueAddTable = new LinkedList<Main.isObject>();
		queueMoPhongKhiChay = new LinkedList<String>();
		next = false;
		
		Thread thread1 = new Thread(new Runnable() {

			@Override
			public void run() {
				Main main = new Main("Chuyển trung tố thành hậu tố");
				main.reset();
			}
		});
		Thread thread2 = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					if (!queueAddTable.isEmpty()) {
						if (choose == 2) {
							if (next) {
								queueMoPhongKhiChay.poll();
								dt.addRow(queueAddTable.poll().getValue());
								next = false;
								tfMophong.setText(getQueue(queueMoPhongKhiChay));
							}
						} else if (choose == 1) {
							queueMoPhongKhiChay.poll();
							dt.addRow(queueAddTable.poll().getValue());
							tfMophong.setText(getQueue(queueMoPhongKhiChay));
						}
						if(queueMoPhongKhiChay.isEmpty() && queueAddTable.isEmpty()) {
							JOptionPane.showMessageDialog(null, "Mô phỏng hoàn thành");
						}
					}
					try {
						Thread.sleep(delay);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		});

		thread1.start();
		thread2.start();

	}
}
