package mk.mediain.processData;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;

public class ServerUnit extends JFrame {

	private JPanel contentPane;
	private Socket       socket = null;
	private ServerSocket server = null;
	private Thread       thread = null;
	private DataInputStream  streamIn  =  null;
	private String line="13:33:54"+","+"Our POS"+","+"4874610016539075"+","+"KENYA"+","+"NAIROBI"+","+"BARAKA SERVICE STN"+","+"3800"+","+"KES"+","+"5541 Service Stations"+","+"KES"+","+"3800"+","+"Rejected"+","+"Closed"+","+"Do not Honour";
	private int port=55601;
	private Thread servThread;
	String []  array;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerUnit frame = new ServerUnit();
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
	public ServerUnit() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1052, 754);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
	
	
		 
	}
	

}
