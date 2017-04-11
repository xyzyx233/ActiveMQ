package 消息中间件;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import messageQueue.*;

import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextField ReceiverJTF;
	private JTextField Username;
	private JTextArea getinfo;
	private JTextArea SendText;
	private JButton SendBut;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setTitle("\u804A\u5929\u5DE5\u5177 \u8BD5\u505A\u578B");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 759, 595);
		contentPane = new JPanel();
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		contentPane.setAlignmentX(Component.RIGHT_ALIGNMENT);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel showinfo = new JPanel();
		contentPane.add(showinfo, BorderLayout.CENTER);
		showinfo.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setAutoscrolls(true);
		showinfo.add(scrollPane);
		
		getinfo = new JTextArea();
		getinfo.setEditable(false);
		scrollPane.setViewportView(getinfo);
		
		JPanel userinfo = new JPanel();
		userinfo.setAutoscrolls(true);
		contentPane.add(userinfo, BorderLayout.EAST);
		userinfo.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_8 = new JPanel();
		userinfo.add(panel_8);
		
		JPanel panel_6 = new JPanel();
		panel_8.add(panel_6);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.Y_AXIS));
		
		JPanel panel_3 = new JPanel();
		panel_6.add(panel_3);
		
		JLabel lblNewLabel_1 = new JLabel("\u767B\u9646\u540D\uFF1A");
		panel_3.add(lblNewLabel_1);
		
		JPanel panel_4 = new JPanel();
		panel_6.add(panel_4);
		
		Username = new JTextField();
		panel_4.add(Username);
		Username.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		panel_6.add(panel_5);
		
		JButton button = new JButton("\u786E\u8BA4");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ReceiverJTF.setEnabled(true);
				SendBut.setEnabled(true);
				SendText.setEnabled(true);
				SendText.setEditable(true);
				button.setEnabled(false);
				Username.setEditable(false);
				Username.setEnabled(false);
				String s=Username.getText();
				new Thread(new Receiver(s,getinfo), "RecandShow").start();
			}
		});
		panel_5.add(button);
		
		JPanel panel_9 = new JPanel();
		userinfo.add(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_9.add(panel_7, BorderLayout.SOUTH);
		panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.PAGE_AXIS));
		
		JLabel coderinfo = new JLabel("\u521B\u4F5C\u4EBA\uFF1A\u8F9B\u4E4B\u593C");
		coderinfo.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel_7.add(coderinfo);
		
		JLabel lblNewLabel_2 = new JLabel("2014012590");
		lblNewLabel_2.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel_7.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u8F6F\u4EF6\u9075\u7167GPL\u534F\u8BAE\u5F00\u653E\u6E90\u4EE3\u7801");
		lblNewLabel_3.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel_7.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel_7.add(lblNewLabel_4);
		
		JPanel sendinfo = new JPanel();
		contentPane.add(sendinfo, BorderLayout.SOUTH);
		sendinfo.setLayout(new GridLayout(4, 1, 0, 0));
		
		JPanel panel = new JPanel();
		sendinfo.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel = new JLabel("Send To: ");
		panel.add(lblNewLabel);
		
		ReceiverJTF = new JTextField();
		ReceiverJTF.setEnabled(false);
		panel.add(ReceiverJTF);
		ReceiverJTF.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setAlignmentX(Component.RIGHT_ALIGNMENT);
		sendinfo.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel label = new JLabel("  \u5185\u5BB9\uFF1A");
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_1.add(label, BorderLayout.WEST);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setEnabled(false);
		sendinfo.add(scrollPane_1);
		
		SendText = new JTextArea();
		SendText.setEnabled(false);
		SendText.setEditable(false);
		SendText.setRows(1);
		scrollPane_1.setViewportView(SendText);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		sendinfo.add(panel_2);
		
		SendBut = new JButton(" \u53D1 \u9001 ");
		SendBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Sender 发送消息 = new Sender();
				发送消息.go(Username.getText()+":  "+SendText.getText(), ReceiverJTF.getText());
			}
		});
		SendBut.setEnabled(false);
		panel_2.add(SendBut);
	}

}
