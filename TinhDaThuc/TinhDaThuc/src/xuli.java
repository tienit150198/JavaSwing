
import java.util.LinkedList;
import java.util.List;

public class xuli {

	// kiểm tra có phải là phép tính hay không
	public boolean toantu(String text) {
		if (text.equals("+") || text.equals("-") || text.equals("*"))
			return true;
		return false;
	}

	public boolean kiemtraMu(String text) {
		return (text.equals("^"));
	}

	// hàm để chuẩn hóa xâu thừa
	public String chuanHoaXau(String text) {
		StringBuilder ketqua = new StringBuilder();
		for (int i = 0; i < text.length(); i++) {
			char kitu = text.charAt(i);
			if (kitu != ' ')
				ketqua.append(kitu);
		}
		return ketqua.toString().trim();
	}

	public LinkedList<Node> tachString(String text) {
		LinkedList<Node> list = new LinkedList<Node>();

		// tách xâu thành xác string riêng
		List<String> listText = new LinkedList<String>();
		StringBuilder number = new StringBuilder();

		boolean kiemtra = false;
		for (int i = 0; i < text.length(); i++) {
			String tam = String.valueOf(text.charAt(i));
			if (tam.equals("(")) {
				if (i + 1 < text.length()) {
					tam = String.valueOf(text.charAt(i + 1));
					number.append(tam);
					i++;
					continue;
				}
			}
			if (tam.equals(")"))
				continue;
			if (toantu(tam) == false) {
				number.append(tam);
			} else {
				if (number.length() > 0) {
					listText.add(number.toString());
					number.delete(0, number.length());
				}
				if (toantu(tam) == true) {
					if (tam.equals("-"))
						number.append("-");
				}

			}
		}
		// lấy số cuối cùng (nếu có)
		if (number.length() > 0) {
			listText.add(number.toString());
			number.delete(0, number.length());
			kiemtra = false;
		}
//		System.out.println("0 = " + listText);

		/* thực hiện chuỗi vào danh sách liên kết */
		StringBuilder giatri = new StringBuilder();
		StringBuilder heso = new StringBuilder();
		String string = listText.get(0);

		int i = 0;
		kiemtra = false;

		/* bắt đầu lấy số đầu tiên */
		if (string.equals("x")) {
			giatri.append(1);
			heso.append(1);
		} else {
			if (string.equals("x")) {
				giatri.append(1);
				heso.append(1);
			} else if (string.equals("-x")) {
				giatri.append(-1);
				heso.append(1);
			} else {
				while (i < string.length()) {
					if (string.charAt(i) == '(' || string.charAt(i) == ')') {
						i++;
						continue;
					}

					if (string.charAt(i) == 'x') {
						kiemtra = true;
						if (i + 1 >= string.length())
							break;
						i++;
						if (kiemtraMu(String.valueOf(string.charAt(i))))
							i++;
						continue;
					}
					if (kiemtra == false) {
						giatri.append(string.charAt(i));
					} else {
						heso.append(string.charAt(i));
					}
					i++;
				}
			}

		}
		if (heso.length() == 0 && giatri.length() != 0) {
			if (kiemtra == false)
				heso.append(0);
			else
				heso.append(1);
		}
		if (giatri.length() == 0 || giatri.toString().equals("-")) {
			giatri.append(1);
		}
		if (heso.length() == 0) {
			heso.append(0);
		}
		list.add(new Node(Integer.parseInt(heso.toString()), Integer.parseInt(giatri.toString())));
		/* kết thúc lấy số đầu tiên */
		for (int j = 1; j < listText.size(); j++) {
			heso.delete(0, heso.length());
			giatri.delete(0, giatri.length());
			string = listText.get(j);

			i = 0;
			kiemtra = false;
			if (string.equals("x")) {
				giatri.append(1);
				heso.append(1);
			} else if (string.equals("-x")) {
				giatri.append(-1);
				heso.append(1);
			} else {
				while (i < string.length()) {
					if (string.charAt(i) == '(' || string.charAt(i) == ')') {
						i++;
						continue;
					}
					if (string.charAt(i) == 'x') {
						kiemtra = true;
						if (i + 1 >= string.length())
							break;
						i++;
						if (kiemtraMu(String.valueOf(string.charAt(i))))
							i++;
						continue;
					}
					if (kiemtra == false) {
						giatri.append(string.charAt(i));
					} else {
						heso.append(string.charAt(i));
					}
					i++;
				}
			}

			if (heso.length() == 0 && giatri.length() != 0) {
				if (kiemtra == false)
					heso.append(0);
				else
					heso.append(1);
			}
			if (giatri.length() == 0 || giatri.toString().equals("-")) {
				giatri.append(1);
			}
			list.add(new Node(Integer.parseInt(heso.toString()), Integer.parseInt(giatri.toString())));
		}

//		System.out.println("1 = " + list);
		list = sapxep(list);
		for (i = 0; i < list.size() - 1; i++) {
			Node node = list.get(i);
			Node node1 = list.get(i + 1);

			for (int j = node.getHeso() + 1; j < node1.getHeso(); j++) {
				list.add(new Node(i, 0));
			}
		}
		list = sapxep(list);
		list = donHeSo(list);
		/* kết thúc đưa chuỗi vào danh sách liên kết */
//		System.out.println("2 = " + list);

		return list;
	}

	public LinkedList<Node> tinhCong(LinkedList<Node> list1, LinkedList<Node> list2) {
		LinkedList<Node> ketqua = new LinkedList<Node>();
		// thực hiện sắp xếp
//x + x^6 + 3x^2
//		5x^3 - 2

// 3+2x+4x^2+5x+6x^5
//2x+1x+8x^4+9x		

//x^6+4x^2-2x
// x-1
		Node n1 = list1.get(0);
		Node n2 = list2.get(0);
		Node nKetqua = new Node();
		int i = 0, j = 0;
		/* thực hiện tính cộng */

		while (i < list1.size() && j < list2.size()) {
			n1 = list1.get(i);
			n2 = list2.get(j);
			if (n1.getHeso() == n2.getHeso()) {
				nKetqua = n1.cong(n2);
				ketqua.add(nKetqua);
				i++;
				j++;
			} else if (n1.getHeso() > n2.getHeso()) {
				ketqua.add(n1);
				i++;
			} else {
				ketqua.add(n2);
				j++;
			}
		}
		while (i < list1.size()) {
			ketqua.add(list1.get(i));
			i++;
		}
		while (j < list2.size()) {
			ketqua.add(list2.get(j));
			j++;
		}
//		System.out.println("Cong = \n" + ketqua);

		return ketqua;
	}

	public LinkedList<Node> tinhTru(LinkedList<Node> list1, LinkedList<Node> list2) {
		LinkedList<Node> ketqua = new LinkedList<Node>();
		// thực hiện sắp xếp
		list1 = sapxep(list1);
		list2 = sapxep(list2);
		Node n1 = list1.get(0);
		Node n2 = list2.get(0);
		Node nKetqua = new Node();
		int i = 0, j = 0;
		/* thực hiện tính trừ */
		while (i < list1.size() && j < list2.size()) {
			n1 = list1.get(i);
			n2 = list2.get(j);
			if (n1.getHeso() == n2.getHeso()) {
				nKetqua = n1.tru(n2);
				ketqua.add(nKetqua);
				i++;
				j++;
			} else if (n1.getHeso() > n2.getHeso()) {
				ketqua.add(n1);
				i++;
			} else {
				nKetqua = n2;
				nKetqua.setGiatri(-n2.getGiatri());
				ketqua.add(nKetqua);
				j++;
			}
		}
		while (i < list1.size()) {
			ketqua.add(list1.get(i));
			i++;
		}
		while (j < list2.size()) {
			n2 = list2.get(j);
			nKetqua = n2;
			nKetqua.setGiatri(-n2.getGiatri());
			ketqua.add(nKetqua);
			j++;
		}
//		System.out.println("Tru = \n" + ketqua);

		return ketqua;
	}

	public LinkedList<Node> sapxep(LinkedList<Node> list) {
		LinkedList<Node> ketqua = list;
		/* thực hiện sắp xếp lại kết quả vừa nhân */
		for (int i = 0; i < ketqua.size() - 1; i++) {
			Node n1 = ketqua.get(i);
			for (int j = i + 1; j < ketqua.size(); j++) {
				Node n2 = ketqua.get(j);
				if (n1.heso < n2.heso) {
					ketqua.set(j, n1);
					ketqua.set(i, n2);
					n1 = ketqua.get(i);
				}
			}
		}
		return ketqua;
	}

	public LinkedList<Node> donHeSo(LinkedList<Node> list) {
		LinkedList<Node> ketqua = list;
		/* thực hiện sắp xếp lại kết quả vừa nhân */
		for (int i = 0; i < ketqua.size(); i++) {
			Node n1 = ketqua.get(i);
			for (int j = i + 1; j < ketqua.size(); j++) {
				Node n2 = ketqua.get(j);
				if (n1.heso == n2.heso) {
					n1 = n1.cong(n2);
					ketqua.set(i, n1);
					ketqua.remove(j);
					j--;
				}
			}
		}
		return ketqua;
	}

//x^2-x+1
// -x
	public LinkedList<Node> tinhNhan(LinkedList<Node> list1, LinkedList<Node> list2) {
		LinkedList<Node> ketqua = new LinkedList<Node>();
		/* thực hiện tính nhân */
		for (int i = 0; i < list1.size(); i++) {
			Node n1 = list1.get(i);
			for (int j = 0; j < list2.size(); j++) {
				Node n2 = list2.get(j);
				Node nKetqua = n1.nhan(n2);
				ketqua.add(nKetqua);
			}
		}

		ketqua = sapxep(ketqua);

		/* thực hiện cộng những chuỗi cùng hệ số lại với nhau */
		for (int i = 0; i < ketqua.size(); i++) {
			Node n1 = ketqua.get(i);
			for (int j = i + 1; j < ketqua.size(); j++) {
				Node n2 = ketqua.get(j);
				if (n1.heso == n2.heso) {
					n1 = n1.cong(n2);
					ketqua.set(i, n1);
					ketqua.remove(j);
					j--;
				}
			}
		}
//		System.out.println("Nhan = \n" + ketqua);

		return ketqua;
	}

	// đưa danh sách liên kết về lại thành string
	public String getKetqua(LinkedList<Node> list) {
		StringBuilder ketqua = new StringBuilder();

		// lấy số đầu tiên
		Node node = list.get(0);
		if (node.getGiatri() != 0) {
			if (node.getGiatri() < 0) {
				ketqua.append("-");
			}
			if (node.getHeso() != 0) {
				if (Math.abs(node.getGiatri()) != 1)
					ketqua.append(Math.abs(node.getGiatri()));
				ketqua.append("x");
			} else {
				ketqua.append(Math.abs(node.getGiatri()));
			}
			if (node.getHeso() != 0 && node.getHeso() != 1) {
				ketqua.append("^");
				ketqua.append(node.getHeso());
			}
		}
		/* kết thúc lấy số đầu tiên */

		for (int i = 1; i < list.size(); i++) {

			node = list.get(i);
			if (node.getGiatri() == 0)
				continue;
			if (node.getGiatri() != 0) {
				if (node.getGiatri() < 0) {
					if (!ketqua.toString().equals(""))
						ketqua.append("-");
					else
						ketqua.append(" - ");
				} else {
					if (!ketqua.toString().equals(""))
						ketqua.append(" + ");
				}
				if (node.getHeso() != 0) {
					if (Math.abs(node.getGiatri()) != 1)
						ketqua.append(Math.abs(node.getGiatri()));
					ketqua.append("x");
				} else {
					ketqua.append(Math.abs(node.getGiatri()));
				}

				if (node.getHeso() != 0 && node.getHeso() != 1) {
					ketqua.append("^");
					ketqua.append(node.getHeso());
				}
			}

		}
		if (ketqua.toString().endsWith("-")) {
			ketqua.delete(ketqua.length() - 1, ketqua.length());
		}
		if (ketqua.toString().equals(""))
			ketqua.append(0);
		return ketqua.toString();
	}
}
