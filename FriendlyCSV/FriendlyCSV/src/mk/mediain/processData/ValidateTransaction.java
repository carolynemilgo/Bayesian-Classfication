package mk.mediain.processData;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Random;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ValidateTransaction extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	String[] answers = {"Milgo","TestAddress","000000000","carolMilgo@gmail.com"};;
	int random;
	int random2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ValidateTransaction dialog = new ValidateTransaction(new Random());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ValidateTransaction(Random rnd) {
		setTitle("Validate Transaction");
		setBounds(100, 100, 417, 363);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		String[] labelText = new String[4];
		labelText[0]="Please provide the surname of the next of kin listed in your account?";
		labelText[1]="Please provide your postal address and code for verification";
		labelText[2]="Please provide your phone number ";
		labelText[3]="Please provide your email address?";
		 random = rnd.nextInt(3);
		
		JLabel lblIPleaseProvide = new JLabel(labelText[random]);
		
		textField = new JTextField();
		textField.setColumns(10);
		 random2 = new Random().nextInt(3);
		if(random==random2 && random<3){
			random2+=1;
		}
		else 
		{
			if(random == 0) random=random+2;
			else
				random-=1;
		}
		
		JLabel lblIiPleaseProvide = new JLabel(labelText[random2]);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		
		
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblIPleaseProvide)
						.addComponent(lblIiPleaseProvide, GroupLayout.PREFERRED_SIZE, 329, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(52, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblIPleaseProvide)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblIiPleaseProvide)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(180, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(!(textField.getText().equals(answers[random]) && textField_1.getText().equals(answers[random2])) ){
							JOptionPane.showMessageDialog(new frameMessage(),
								    "The transaction is not valid.Rollback intiated.");
						}
						else
						{
							JOptionPane.showMessageDialog(new frameMessage(),
								    "The transaction is valid.Sorry for the incovinience. ");
						}
						dispose();
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
