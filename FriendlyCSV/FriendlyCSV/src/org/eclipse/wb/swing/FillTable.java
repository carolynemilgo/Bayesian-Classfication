package org.eclipse.wb.swing;
import java.awt.BorderLayout;
import java.io.*;
import java.net.URL;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import weka.*;
import weka.classifiers.misc.SerializedClassifier;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.*;
import weka.core.converters.ConverterUtils.DataSource;
import weka.datagenerators.classifiers.classification.BayesNet;
public class FillTable extends JFrame {
   JTable table;
   DefaultTableModel model;
   JButton closeButton, webButton;
   String source="";
 /**
  * Takes data from a CSV file and places it into a table for display.
  * @param source - a reference to the file where the CSV data is located.
 * @throws Exception 
  */
 public FillTable(String title, String source) throws Exception {
   super(title);
   this.source = source;
   table = new JTable();
   //JScrollPane scroll = new JScrollPane(table);
   String[] colNames = { "Transaction Time", "Source Channel","Target Number","Trans Country","Trans City","Trans Details","Trans Amount","Trans Curr","SIC Code","Settl Curr","Settl Amount","Posting Status","Outward Status","Return Code","Class","Recognition Result"};
   model = new DefaultTableModel(colNames, 0);
   InputStream is;
   try {
       if(source.indexOf("http")==0) {
           URL facultyURL = new URL(source);
           is = facultyURL.openStream();
       }
       else { //local file?
           File f = new File(source);
           is = new FileInputStream(f);
       }
       insertData(is);
       //table.getColumnModel().getColumn(0).setCellRenderer(new CustomCellRenderer());
   }
   catch(IOException ioe) {
       JOptionPane.showMessageDialog(this, ioe, "Error reading data", JOptionPane.ERROR_MESSAGE);
   }

   JPanel buttonPanel = new JPanel();
   closeButton = new JButton("Close");
   webButton = new JButton("Proctinator.com");
   buttonPanel.add(closeButton);
   buttonPanel.add(new JLabel("   You can download this file from our site: "));
   buttonPanel.add(webButton);

   JPanel notesPanel = new JPanel();
   //JLabel note1 = new JLabel(" Make sure that your list is formatted exactly as shown below, including the *markers between categories ");
   //JLabel note2 = new JLabel(" Be sure to place each faculty member into the correct category: *Teacher, *Subs, *TeacherAids, *TeacherAssistants ");
   //JLabel note3 = new JLabel(" Note that the your faculty list must be a plain text file:  Export to either CSV or tab delimited format.");
   BoxLayout layout = new BoxLayout(notesPanel, BoxLayout.Y_AXIS);
   notesPanel.setLayout(layout);
   //notesPanel.add(note1);
   //notesPanel.add(note2);
   //notesPanel.add(note3);       
 //  getContentPane().add(notesPanel, BorderLayout.NORTH);
   //getContentPane().add(scroll, BorderLayout.CENTER);
  // getContentPane().add(buttonPanel, BorderLayout.SOUTH);
   //pack();
}
public JTable getTable(){
	return this.table;
}
public DefaultTableModel getModel(){
	return model;
}
/**
* Places the data from the specified stream into this table for display.  The data from the file must be in CSV format
* @param is - an input stream which could be from a file or a network connection or URL.
 * @throws Exception 
*/
void insertData(InputStream is) throws Exception {

   Scanner scan = new Scanner(is);
   String[] array;
   DataSource source = new DataSource(this.source);
   Instances dataI = source.getDataSet();
   if (dataI.classIndex() == -1)
	   dataI.setClassIndex(dataI.numAttributes() - 1);
   
   SerializedClassifier cls = new SerializedClassifier();
   cls.setModelFile(new File(".//Models//BayesClassifier2.model"));
   double[] d;
   int it= -1;
   String result="";
   while (scan.hasNextLine()) {
	   String line = scan.nextLine();
	   if(line=="")
		   continue;
       if(it>-1){
	   int nin = dataI.numInstances();
       if(it>-1 && it<dataI.numInstances()){
       //dataI.instance(it);
       d= cls.distributionForInstance(dataI.instance(it));
       if(d[0]>0.7f && d[1]<0.5)
    	   result="Fraud";
       else
    	   result = "Legit";
       }
       }
       it++;
       if(line.indexOf(",")>-1)
           array = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
       else
           array = line.split("\t");
       Object[] data = new Object[array.length+1];
       for (int i = 0; i < array.length+1; i++)
           if(i<array.length)
    	   data[i] = array[i];
           else
        	   data[i]= result;
           model.addRow(data);
       
   }
   
   table.setModel(model);
} 

public static void main(String args[]) throws Exception {
   FillTable frame = new FillTable("Faculty List Example","http://proctinator.com/help/faculty.csv");
   frame.setVisible(true);
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
}