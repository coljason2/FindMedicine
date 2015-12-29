package com.findmedicine;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JToolBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class main {
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;
	private readExcelFile excel;
	private JFrame frame;
	private JFileChooser chooser = new JFileChooser();
	FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel File",
			"xls", "xlsx");
	private JMenuItem menuItem_talk;
	private JMenuItem menuItem_about;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main window = new main();
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
	public main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("查詢中文藥名");
		frame.setResizable(false);
		frame.setBounds(100, 100, 400, 206);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JButton btnchoose = new JButton("選擇檔案");
		btnchoose.setFont(new Font("新細明體", Font.PLAIN, 20));
		btnchoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					excel = new readExcelFile(chooser.getSelectedFile()
							.getAbsolutePath());
				}
			}
		});
		btnchoose.setBounds(136, 38, 125, 35);
		frame.getContentPane().add(btnchoose);

		JButton btnNewButton = new JButton("產生");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					createResult output = new createResult();
					output.setInfiles(excel.getInfiles());

					output.createExcel();
					JOptionPane.showMessageDialog(null, "產生完成!!!");
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}

			}
		});
		menuBar = new JMenuBar();
		menu = new JMenu("功能");
		menuBar.add(menu);

		menuItem_talk = new JMenuItem("說明");
		menuItem_talk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"欄位說明如下：\n說明院碼\n院內名稱\n	成份\n健保碼\n新健保價\n進價\n供應商\n製造商\n票期",
						"說明", 1);

			}
		});

		menu.add(menuItem_talk);

		menuItem_about = new JMenuItem("關於");
		menuItem_about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"關於本程式\n版本V1.00\n作者：JunChiChen", "關於", 1);
			}
		});

		menu.add(menuItem_about);
		frame.setJMenuBar(menuBar);
		btnNewButton.setFont(new Font("新細明體", Font.PLAIN, 20));
		btnNewButton.setBounds(136, 91, 125, 35);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().add(btnNewButton);
	}
}
