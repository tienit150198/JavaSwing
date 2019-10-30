

public class Node {
	int heso, giatri;

	public Node() {
	}

	public Node(int heso, int giatri) {
		this.heso = heso;
		this.giatri = giatri;
	}

	public Node compareTo(Node b) {
		return this.compareTo(b);
	}

	public Node cong(Node n) {
		Node ketqua = new Node();
		ketqua.setGiatri(this.getGiatri() + n.getGiatri());
		ketqua.setHeso(n.getHeso());
		return ketqua;
	}

	public int getGiatri() {
		return giatri;
	}

	public int getHeso() {
		return heso;
	}

	public Node nhan(Node n) {
		Node ketqua = new Node();
		ketqua.setGiatri(this.getGiatri() * n.getGiatri());
		ketqua.setHeso(n.getHeso() + this.getHeso());
		return ketqua;
	}

	public void setGiatri(int giatri) {
		this.giatri = giatri;
	}
	
	public void setHeso(int heso) {
		this.heso = heso;
	}
	
	@Override
	public String toString() {
		return "Node [heso=" + heso + ", giatri=" + giatri + "]";
	}

	public Node tru(Node n) {
		Node ketqua = new Node();
		ketqua.setGiatri(this.getGiatri() - n.getGiatri());
		ketqua.setHeso(n.getHeso());
		return ketqua;
	}
}
