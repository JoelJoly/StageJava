import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ListSelectionModel;


public class IHM  extends JFrame{
	private JTextField txtMessage;
	private JTextField txtPiece;
	
	private Robot robot;
	private JList lObjPiece;
	private JList lObjTransportes;
	private JButton btnEst;
	private JButton btnSud;
	private JButton btnOuest;
	private JButton btnNord;
	
	public IHM(Robot rob) {
		
		robot = rob;
		
		getContentPane().setLayout(null);
		
		JLabel lblPiece = new JLabel("Pi\u00E8ce");
		lblPiece.setBounds(53, 21, 45, 28);
		getContentPane().add(lblPiece);
		
		btnNord = new JButton("Nord");
		btnNord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mouvement(PC.Nord);			}
		});
		btnNord.setBounds(147, 71, 91, 23);
		getContentPane().add(btnNord);
		
		btnOuest = new JButton("Ouest");
		btnOuest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mouvement(PC.Ouest);
			}
		});
		btnOuest.setBounds(53, 111, 91, 23);
		getContentPane().add(btnOuest);
		
		btnEst = new JButton("Est");
		btnEst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mouvement(PC.Est);
			}
			});
		btnEst.setBounds(248, 111, 91, 23);
		getContentPane().add(btnEst);
		
		btnSud = new JButton("Sud");
		btnSud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mouvement(PC.Sud);
			}
	});
		btnSud.setBounds(147, 141, 91, 23);
		getContentPane().add(btnSud);
		
		JLabel lblObjetsTransports = new JLabel("Objets Transport\u00E9s");
		lblObjetsTransports.setBounds(53, 175, 135, 14);
		getContentPane().add(lblObjetsTransports);
		
		JLabel lblObjetsDansLa = new JLabel("Objets Dans la pi\u00E8ce");
		lblObjetsDansLa.setBounds(228, 175, 146, 14);
		getContentPane().add(lblObjetsDansLa);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(53, 200, 137, 158);
		getContentPane().add(scrollPane);
		
		lObjTransportes = new JList();
		lObjTransportes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lObjTransportes.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				try {
					if (lObjTransportes.getSelectedValue()!= null)
						robot.deposer( (String) lObjTransportes.getSelectedValue());
				} catch (ExceptionGagne e) {
					txtMessage.setForeground(Color.GREEN);
					txtMessage.setText("Vous avez gagné !!!");
					btnEst.setEnabled(false);
					btnNord.setEnabled(false);
					btnOuest.setEnabled(false);
					btnSud.setEnabled(false);
				}
				catch (TresorNonExistant e) {
					e.printStackTrace();
				}
				finally {
					rafraichir();
				}
			}
		});
		scrollPane.setViewportView(lObjTransportes);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(228, 200, 146, 155);
		getContentPane().add(scrollPane_1);
		
		lObjPiece = new JList();
		lObjPiece.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				try {
					if (lObjPiece.getSelectedValue()!= null)
					robot.prendre((String)lObjPiece.getSelectedValue());

				} catch (ExceptionPerdu e) {
					txtMessage.setForeground(Color.RED);
					txtMessage.setText("Vous avez perdu Turlututu !!!");
					btnEst.setEnabled(false);
					btnNord.setEnabled(false);
					btnOuest.setEnabled(false);
					btnSud.setEnabled(false);
				}
				catch (TresorNonExistant e) {
					e.printStackTrace();
				}
				finally {
					rafraichir();
				}
				
			}
		});
		lObjPiece.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(lObjPiece);
		
		txtMessage = new JTextField();
		txtMessage.setEditable(false);
		txtMessage.setBounds(124, 389, 251, 23);
		getContentPane().add(txtMessage);
		txtMessage.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Message");
		lblNewLabel.setBounds(53, 393, 61, 14);
		getContentPane().add(lblNewLabel);
		
		txtPiece = new JTextField();
		txtPiece.setEditable(false);
		txtPiece.setBounds(123, 25, 251, 24);
		getContentPane().add(txtPiece);
		txtPiece.setColumns(10);
		
		rafraichir();
		
		this.dispose();
		
		setSize(400, 500);
		setVisible(true);
		
	}
	private void rafraichir() {
		txtPiece.setText(robot.tesou());
		lObjPiece.setListData(robot.yaquoi().toArray());
		lObjTransportes.setListData(robot.tasquoi().toArray());
		
	}

	private void mouvement(PC direction) {
		if (robot.go(direction) == true)
		{
			rafraichir();
			txtMessage.setText("");
		}
		else
			txtMessage.setText("Mouvement Impossible");
	}

}
