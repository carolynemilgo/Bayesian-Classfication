package mk.mediain.processData;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.eclipse.wb.swing.FillTable;

import weka.classifiers.misc.SerializedClassifier;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
public class BankClassification {

	private JFrame frmSimpleKmeans;
	private JTable table;
	private String SourceFile;
	FillTable tbl;
	private JTextField txtSingleData;
	Random rnd = new Random(3);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BankClassification window = new BankClassification();
					window.frmSimpleKmeans.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BankClassification() {	
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSimpleKmeans = new JFrame();
		frmSimpleKmeans.setTitle("Bayesian Classification");
		frmSimpleKmeans.setBounds(100, 100, 1000, 690);
		frmSimpleKmeans.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		
		GroupLayout groupLayout = new GroupLayout(frmSimpleKmeans.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 965, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1243, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 558, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
					.addGap(13))
		);
		
		JToggleButton tglbtnAutomatedModeOn = new JToggleButton("Single Send Mode On");
		tglbtnAutomatedModeOn.setToolTipText("Switch Server Mode");
		tglbtnAutomatedModeOn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		JButton btnOpen = new JButton("Open");
		btnOpen.setToolTipText("Open and process file containing bank records");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final JFileChooser csvFile = new JFileChooser();
				int result = csvFile.showOpenDialog(frmSimpleKmeans);
				if(result == JFileChooser.APPROVE_OPTION){
					SourceFile = csvFile.getSelectedFile().toPath().toString();
				}
				try {
					tbl = new FillTable("Target Data", SourceFile);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				scrollPane.setViewportView(tbl.getTable());
			}
		});
		
		JButton btnProcess = new JButton("Send");
		btnProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tglbtnAutomatedModeOn.isSelected()){
					@SuppressWarnings("unused")
					String tData="Transaction Time"+","+"Source Channel"+","+"Target Number"+","+"Trans Country"+","+"Trans City"+","+"Trans Details"+","+"Trans Amount"+","+"Trans Curr"+","+"SIC Code"+","+"Settl Curr"+","+"Settl Amount"+","+"Posting Status"+","+"Outward Status"+","+"Return Code"+","+"Class"+"\n";
					
					   try {
						   String filename= ".//Source//D10CCMfinal.csv";
						    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
						    fw.write('\n'+txtSingleData.getText());//appends the string to the file
						    fw.close();
				           SerializedClassifier cls = new SerializedClassifier();
				           cls.setModelFile(new File(".//Models//BayesClassifier2.model"));
				     //use .getDataSet to return the full data set once it is appended   
				           DataSource source = new DataSource(".//Source//D10CCMfinal.csv");
				           Instances dataI = source.getDataSet();
				           //if (dataI.classIndex() == -1)
				        	 //  dataI.setClassIndex(dataI.numAttributes() - 1);
				           
				           
				         
						double[] d= cls.distributionForInstance(dataI.lastInstance());
						if(d[0]>0.7 && d[1]<0.5){
							ValidateTransaction obj = new ValidateTransaction(rnd);
							obj.setVisible(true);
						}
						else
						{
							JOptionPane.showMessageDialog(new frameMessage(),
								    "The transaction is valid.");
						}
							
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		btnProcess.setToolTipText("Starts the server.");
		
		txtSingleData = new JTextField();
		txtSingleData.setColumns(10);
		
		JLabel label = new JLabel("Double quoted CSV Fromated Data To be sent For Processing");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(277)
					.addComponent(label))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(6)
					.addComponent(btnOpen)
					.addGap(6)
					.addComponent(tglbtnAutomatedModeOn, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(btnProcess)
					.addGap(6)
					.addComponent(txtSingleData, GroupLayout.PREFERRED_SIZE, 694, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(label)
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnOpen)
						.addComponent(tglbtnAutomatedModeOn)
						.addComponent(btnProcess)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(1)
							.addComponent(txtSingleData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
		);
		panel.setLayout(gl_panel);
		
	
		frmSimpleKmeans.getContentPane().setLayout(groupLayout);
		
	}
}
