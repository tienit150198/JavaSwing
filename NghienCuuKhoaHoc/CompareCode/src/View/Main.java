/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package View;

/*
 * CardLayoutDemo.java
 *
 */
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main implements ItemListener {
	JPanel cards; // a panel that uses CardLayout
	final static String COMPARE_FILE = "Compare 2 File Code";
	final static String COMPARE_FOLDER = "Compare Folder";
	private JPanel card1;
	private JPanel card2;
	private JFileChooser jfc;

	public void initialization() {
		jfc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("file code cpp or c", "cpp", "c");
		jfc.setFileFilter(filter);
		
	//	MySystemManager instance = new MySystemManager();
	}

	public void addComponent_CompareFile() {
		initialization();
		card1 = new JPanel();
		ButtonGroup buttonGroup = new ButtonGroup();

		card1.setLayout(new BorderLayout());
		JToolBar toolBar = new JToolBar();
		card1.add(toolBar, BorderLayout.NORTH);

		JButton btnNewButton_1 = new JButton("New button");
		toolBar.add(btnNewButton_1);

		JPanel pnBottom = new JPanel();
		card1.add(pnBottom, BorderLayout.SOUTH);
		pnBottom.setLayout(new GridLayout(1, 0, 8, 0));

		JPanel pnBottomLeft = new JPanel();
		pnBottom.add(pnBottomLeft);
		pnBottomLeft.setLayout(new BoxLayout(pnBottomLeft, BoxLayout.Y_AXIS));

		JTextField tfResult = new JTextField();
		tfResult.setForeground(Color.BLACK);
		tfResult.setEditable(false);
		pnBottomLeft.add(tfResult);
		tfResult.setColumns(10);

		JPanel panel_3 = new JPanel();
		pnBottomLeft.add(panel_3);

		JButton btnDetail = new JButton("Detail");
		panel_3.add(btnDetail);

		JButton btnAccept = new JButton("Accept");
		panel_3.add(btnAccept);

		JButton btnClear = new JButton("Clear");
		panel_3.add(btnClear);

		JPanel pnBottomRight = new JPanel();
		pnBottom.add(pnBottomRight);
		pnBottomRight.setLayout(new BoxLayout(pnBottomRight, BoxLayout.Y_AXIS));

		pnBottomRight
				.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.blue), "Algorithm"));

		JRadioButton rdbtnEditDistance = new JRadioButton("Edit Distance");
		rdbtnEditDistance.setSelected(true);
		buttonGroup.add(rdbtnEditDistance);
		pnBottomRight.add(rdbtnEditDistance);

		JRadioButton rdbtnKMP = new JRadioButton("KMP");
		buttonGroup.add(rdbtnKMP);
		pnBottomRight.add(rdbtnKMP);

		JRadioButton rdbtnHashAndNGram = new JRadioButton("Hash and N-Gram");
		buttonGroup.add(rdbtnHashAndNGram);
		pnBottomRight.add(rdbtnHashAndNGram);

		JPanel pnCenter = new JPanel();
		card1.add(pnCenter, BorderLayout.CENTER);
		pnCenter.setLayout(new GridLayout(1, 0, 8, 0));

		JPanel pnLeft = new JPanel();
		pnCenter.add(pnLeft);
		pnLeft.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		pnLeft.add(scrollPane, BorderLayout.CENTER);

		JPanel pnScrollLeft = new JPanel();
		scrollPane.setColumnHeaderView(pnScrollLeft);
		pnScrollLeft.setLayout(new BorderLayout(0, 0));

		JTextField tfFilePathLeft = new JTextField();
		tfFilePathLeft.setEditable(false);
		tfFilePathLeft.setColumns(10);
		pnScrollLeft.add(tfFilePathLeft, BorderLayout.CENTER);

		JButton btnFileLeft = new JButton("...");
		btnFileLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int returnValue = jfc.showOpenDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();
					System.out.println(selectedFile.getAbsolutePath());
					tfFilePathLeft.setText(selectedFile.getAbsolutePath());
					
					
					
					
				}
			}
		});
		btnFileLeft.setToolTipText("select files");
		pnScrollLeft.add(btnFileLeft, BorderLayout.EAST);

		JTextArea textArea = new JTextArea();
		textArea.setRows(20);
		textArea.setColumns(30);
		scrollPane.setViewportView(textArea);

		JPanel pnRight = new JPanel();
		pnCenter.add(pnRight);
		pnRight.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		pnRight.add(scrollPane_1, BorderLayout.CENTER);

		JPanel pnScrollRight = new JPanel();
		scrollPane_1.setColumnHeaderView(pnScrollRight);
		pnScrollRight.setLayout(new BorderLayout(0, 0));

		JTextField tfFilePathRight = new JTextField();
		tfFilePathRight.setEditable(false);
		tfFilePathRight.setColumns(10);
		pnScrollRight.add(tfFilePathRight, BorderLayout.CENTER);

		JButton btnFileRight = new JButton("...");
		btnFileRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int returnValue = jfc.showOpenDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();
					System.out.println(selectedFile.getAbsolutePath());
					tfFilePathRight.setText(selectedFile.getAbsolutePath());
				}
			}
		});
		btnFileRight.setToolTipText("select files");
		pnScrollRight.add(btnFileRight, BorderLayout.EAST);

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setRows(20);
		scrollPane_1.setViewportView(textArea_1);
	}

	public void addComponentToPane(Container pane) {
		JPanel comboBoxPane = new JPanel(); // use FlowLayout
		String comboBoxItems[] = { COMPARE_FILE, COMPARE_FOLDER };
		JComboBox<?> cb = new JComboBox<Object>(comboBoxItems);
		cb.setEditable(false);
		cb.addItemListener(this);
		comboBoxPane.add(cb);

		/* 
		  *****************
		 */
		addComponent_CompareFile();

		card2 = new JPanel();
		card2.add(new JTextField("TextField", 20));

		// Create the panel that contains the "cards".
		cards = new JPanel(new CardLayout());
		cards.add(card1, COMPARE_FILE);
		cards.add(card2, COMPARE_FOLDER);

		pane.add(comboBoxPane, BorderLayout.PAGE_START);
		pane.add(cards, BorderLayout.CENTER);
	}

	public void itemStateChanged(ItemEvent evt) {
		CardLayout cl = (CardLayout) (cards.getLayout());
		cl.show(cards, (String) evt.getItem());
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be invoked
	 * from the event dispatch thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("CardLayoutDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setResizable(false);
		// Create and set up the content pane.
		Main demo = new Main();
		demo.addComponentToPane(frame.getContentPane());

		// Display the window.
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		/* Use an appropriate Look and Feel */
		try {
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		/* Turn off metal's use of bold fonts */
		UIManager.put("swing.boldMetal", Boolean.FALSE);

		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
