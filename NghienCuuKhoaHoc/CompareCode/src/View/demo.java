package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

public class demo {

	private JFrame frame;
	private JTextField tfRatio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					demo window = new demo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public demo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(8, 8));
		
		JPanel pnEast = new JPanel();
		panel.add(pnEast, BorderLayout.EAST);
		
//		pnEast.setBorder(BorderFactory.createEmptyBorder(100, 10, 0, 10));
		GridBagLayout gbl_pnEast = new GridBagLayout();
		gbl_pnEast.columnWidths = new int[] {20, 8};
		gbl_pnEast.rowHeights = new int[] {100, 23, 30, 23, 99};
		gbl_pnEast.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_pnEast.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		pnEast.setLayout(gbl_pnEast);
		
		JButton btnAdd = new JButton("Add");
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAdd.insets = new Insets(0, 0, 5, 0);
		gbc_btnAdd.gridx = 0;
		gbc_btnAdd.gridy = 1;
		pnEast.add(btnAdd, gbc_btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDelete.gridx = 0;
		gbc_btnDelete.gridy = 3;
		pnEast.add(btnDelete, gbc_btnDelete);
		
		
		
		JPanel pnBottom = new JPanel();
		panel.add(pnBottom, BorderLayout.SOUTH);
		
		JButton btnAccept = new JButton("Accept");
		pnBottom.add(btnAccept);
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		btnAdd.setPreferredSize(btnDelete.getPreferredSize());
		JList<?> list = new JList<Object>();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"a", "a", "a", "a", "a", "a"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane.setViewportView(list);
		
		
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setHgap(10);
		panel.add(panel_1, BorderLayout.WEST);
	}

}
