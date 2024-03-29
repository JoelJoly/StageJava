package gui;

import javax.swing.JFrame;

import javax.swing.ComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.event.ListDataListener;

import entity.Banque;
import exceptions.CompteInexistant;

import java.awt.CardLayout;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JTextPane;

public class MainWindow extends JFrame{
	private static final long serialVersionUID = 7995641138946568039L;
	private JComboBox banqueCombo;
	private Banque currentBanque;
	private JList comptesList;
	private JList operationsList;
	private JPanel operationPanel;
	private BanqueController banqueController;
	private JButton crediterButton;
	private JButton debiterButton;
	private JSpinner montantCredit;
	private JButton btnCreditOk;
	private JSpinner montantDebit;
	private JButton btnDebitOK;
	
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Gestion de banque");
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel banquePanel = new JPanel();
		getContentPane().add(banquePanel);
		banquePanel.setLayout(new BoxLayout(banquePanel, BoxLayout.X_AXIS));
		
		JLabel lblBanque = new JLabel("Banque");
		banquePanel.add(lblBanque);
		
		banqueCombo = new JComboBox();
		banquePanel.add(banqueCombo);
		
		JPanel comptePanel = new JPanel();
		getContentPane().add(comptePanel);
		comptePanel.setLayout(new BoxLayout(comptePanel, BoxLayout.Y_AXIS));
		
		JLabel compteLabel = new JLabel("Choisir un compte");
		compteLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		comptePanel.add(compteLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		comptePanel.add(scrollPane);
		
		comptesList = new JList();
		scrollPane.setViewportView(comptesList);
		comptesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JPanel operationsPanel = new JPanel();
		getContentPane().add(operationsPanel);
		operationsPanel.setLayout(new BoxLayout(operationsPanel, BoxLayout.Y_AXIS));
		
		JLabel operationsLabel = new JLabel("Op\u00E9rations");
		operationsLabel.setAlignmentX(0.5f);
		operationsPanel.add(operationsLabel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		operationsPanel.add(scrollPane_1);
		
		operationsList = new JList();
		operationsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		operationsList.setEnabled(false);
		scrollPane_1.setViewportView(operationsList);
		
		JPanel nouvelleOperationPanel = new JPanel();
		operationsPanel.add(nouvelleOperationPanel);
		
		crediterButton = new JButton("Cr\u00E9diter");
		crediterButton.setEnabled(false);
		nouvelleOperationPanel.add(crediterButton);
		
		debiterButton = new JButton("D\u00E9biter");
		debiterButton.setEnabled(false);
		nouvelleOperationPanel.add(debiterButton);
		
		operationPanel = new JPanel();
		operationsPanel.add(operationPanel);
		operationPanel.setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		operationPanel.add(panel, "name_182742241408614");
		
		JTextPane txtpnSlectionnezLopration = new JTextPane();
		txtpnSlectionnezLopration.setText("S\u00E9lectionnez l'op\u00E9ration \u00E0 effectuer");
		panel.add(txtpnSlectionnezLopration);
		
		JPanel crediterPanel = new JPanel();
		operationPanel.add(crediterPanel, "name_181546337259623");
		crediterPanel.setLayout(new BoxLayout(crediterPanel, BoxLayout.Y_AXIS));
		
		JPanel montantCreditPanel = new JPanel();
		crediterPanel.add(montantCreditPanel);
		
		JLabel lblNewLabel = new JLabel("Montant \u00E0 ajouter");
		montantCreditPanel.add(lblNewLabel);
		
		montantCredit = new JSpinner();
		montantCreditPanel.add(montantCredit);
		montantCredit.setPreferredSize(new Dimension(65, 20));
		montantCredit.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
		DecimalFormat format = ((JSpinner.NumberEditor)montantCredit.getEditor()).getFormat();
		format.setDecimalSeparatorAlwaysShown(true);
		format.setMaximumFractionDigits(2);
		format.setMinimumFractionDigits(2);
		
		JPanel montantCreditValidationPanel = new JPanel();
		crediterPanel.add(montantCreditValidationPanel);
		
		btnCreditOk = new JButton("OK");
		montantCreditValidationPanel.add(btnCreditOk);
		
		JButton btnCreditAnnuler = new JButton("Annuler");
		montantCreditValidationPanel.add(btnCreditAnnuler);
		btnCreditAnnuler.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				closeOperation(montantCredit);
			}
		});
		
		JPanel debiterPanel = new JPanel();
		operationPanel.add(debiterPanel, "name_182090482162677");
		
		JPanel montantDebitPanel = new JPanel();
		debiterPanel.add(montantDebitPanel);
		
		JLabel lblMontantRetirer = new JLabel("Montant \u00E0 retirer");
		montantDebitPanel.add(lblMontantRetirer);
		
		montantDebit = new JSpinner();
		montantDebit.setPreferredSize(new Dimension(65, 20));
		montantDebit.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
		format = ((JSpinner.NumberEditor)montantDebit.getEditor()).getFormat();
		format.setDecimalSeparatorAlwaysShown(true);
		format.setMaximumFractionDigits(2);
		format.setMinimumFractionDigits(2);
		montantDebitPanel.add(montantDebit);
		
		JPanel montantDebitValidationPanel = new JPanel();
		debiterPanel.add(montantDebitValidationPanel);
		
		btnDebitOK = new JButton("OK");
		montantDebitValidationPanel.add(btnDebitOK);
		
		JButton btnDebitAnnuler = new JButton("Annuler");
		montantDebitValidationPanel.add(btnDebitAnnuler);
		btnDebitAnnuler.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				closeOperation(montantDebit);				
			}
		});
		
		setVisible(true);
		setSize(500, 300);
		Banque.restaurerBanque("Banque.ser");
		installBanks();
	}
	private void closeOperation(JSpinner source) {
		CardLayout layout = ((CardLayout)operationPanel.getLayout());
		layout.show(operationPanel, "name_182742241408614");
		// reset value when the operation is closed
		source.setValue(0);
	}
	private void installBanks() {
		banqueCombo.setModel(new ComboBoxModel() {
			private Object selectedObject;
			@Override
			public void removeListDataListener(ListDataListener l) {}
			
			@Override
			public int getSize() {
				return 1;
			}
			
			@Override
			public Object getElementAt(int index) {
				return Banque.getInstance().getName();
			}
			
			@Override
			public void addListDataListener(ListDataListener l) {}
			
			@Override
			public void setSelectedItem(Object anItem) {
				selectedObject = anItem;
				currentBanque = Banque.getInstance();
				observeBank(currentBanque);
			}
			
			@Override
			public Object getSelectedItem() {
				return selectedObject;
			}
		});
	}
	private void observeBank(final Banque banque) {
		if (banqueController != null) banqueController.close();
		banqueController = new BanqueController(banque, comptesList, new BanqueController.CompteBindable() {			
			@Override
			public void bindOn(Banque banque, int compteNumero) {
				try {
					if (banque == null || compteNumero < 0) {
						if (operationsList.getModel() != null) {
							((OperationController)operationsList.getModel()).close();
							operationsList.setModel(null);
						}
					}
					else {
						operationsList.setModel(new OperationController(banque, compteNumero, crediterButton, debiterButton, new OperationController.OperationCreation() {							
							@Override
							public void beginOperation(JButton button, OperationController controller) {
								String panelName = null;
								if (button == crediterButton) {
									panelName = "name_181546337259623";
									controller.bindOperationGUI(montantCredit, btnCreditOk, true);
								}
								else if (button == debiterButton) {
									panelName = "name_182090482162677";
									controller.bindOperationGUI(montantDebit, btnDebitOK, false);
									
								}
				 				CardLayout layout = ((CardLayout)operationPanel.getLayout());
								layout.show(operationPanel, panelName);
							}
						}));						
					}
				} catch (CompteInexistant e) {
					e.printStackTrace();
				}				
			}
		});
		
		
	}
	public static void main(String[] args) {
		new MainWindow();
	}
	
}
