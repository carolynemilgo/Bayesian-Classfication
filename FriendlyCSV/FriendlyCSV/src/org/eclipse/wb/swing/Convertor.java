package org.eclipse.wb.swing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import jxl.*;
import jxl.read.biff.BlankCell;
import jxl.write.Blank;
public class Convertor {

	public static void main(String[] args) {
		 try
		    {
		      //File to store data in form of CSV
		      File f = new File("c://D10CCMfinal.csv");
		 
		      OutputStream os = (OutputStream)new FileOutputStream(f);
		      String encoding = "UTF8";
		      OutputStreamWriter osw = new OutputStreamWriter(os, encoding);
		      BufferedWriter bw = new BufferedWriter(osw);
		 
		      //Excel document to be imported
		      String filename = "c://final data.xls";
		      WorkbookSettings ws = new WorkbookSettings();
		      ws.setLocale(new Locale("en", "EN"));
		      Workbook w = Workbook.getWorkbook(new File(filename),ws);
		 
		      // Gets the sheets from workbook
		      for (int sheet = 0; sheet < w.getNumberOfSheets(); sheet++)
		      {
		        Sheet s = w.getSheet(sheet);
		        if(sheet==0){
		       // bw.write(s.getName());
		       // bw.newLine();
		        }
		        Cell[] row = null;
		         
		        // Gets the cells from sheet
		        int i;
		        if(sheet==0)
		        	i=0;
		        else
		        	i=1;
		        for ( ; i < s.getRows() ; i++)
		        {
		          row = s.getRow(i);
		          		          
		          if (row.length > 0 && row[0].getContents().length()>0 )
		          {
		            bw.write(row[0].getContents());
		            for (int j = 1; j < row.length; j++)
		            {
		              bw.write(',');
		              if(i==0)
		            	  bw.write(row[j].getContents());
		              else
		             bw.write('"'+row[j].getContents()+'"');
		            }
		            bw.newLine();
		          }
		          
		        }
		      }
		      bw.flush();
		      bw.close();
		    }
		    catch (UnsupportedEncodingException e)
		    {
		      System.err.println(e.toString());
		    }
		    catch (IOException e)
		    {
		      System.err.println(e.toString());
		    }
		    catch (Exception e)
		    {
		      System.err.println(e.toString());
		    }

	}

}
