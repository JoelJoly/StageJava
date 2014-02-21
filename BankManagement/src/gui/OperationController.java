package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import entity.Banque;
import exceptions.CompteInexistant;
import exceptions.DebitNonAutorise;

public class OperationController extends DefaultListModel  {
	private static final long serialVersionUID = 7649727719653350574L;
	Banque banque;
	int compte;
	JButton crediterButton;
	JButton debiterButton;
	OperationCreation operationCreation;
	public interface OperationCreation {
		void beginOperation(JButton button, OperationController controller);
	}
	public OperationController(Banque banque, int compte, JButton crediterButton, JButton debiterButton, OperationCreation operationCreation) throws CompteInexistant {
		this.banque = banque;
		this.compte = compte;
		this.crediterButton = crediterButton;
		this.debiterButton = debiterButton;
		this.operationCreation = operationCreation;
		updateOperations();
		this.crediterButton.setEnabled(true);
		this.debiterButton.setEnabled(true);
		this.crediterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				OperationController.this.operationCreation.beginOperation(OperationController.this.crediterButton, OperationController.this);
			}
		});		
		this.debiterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				OperationController.this.operationCreation.beginOperation(OperationController.this.debiterButton, OperationController.this);
			}
		});		
	}
	
	public void bindOperationGUI(final JSpinner spinner, JButton validation, final boolean isCredit) {
		for (ActionListener l : validation.getActionListeners()) {
			validation.removeActionListener(l);
		}
		validation.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
 				float montant = (Float)spinner.getValue();
				createOperation(isCredit ? montant : -montant);
			}
		});
	}
	private void createOperation(float montant) {
		try {
			if (montant >= 0) {
				this.banque.crediter(this.compte, montant);
				updateOperations();
			}
			else {
				try {
					this.banque.debiter(this.compte, -montant);
					updateOperations();
				} catch (DebitNonAutorise e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (CompteInexistant e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void updateOperations() throws CompteInexistant {
		Collection<String> operations = this.banque.getOperations(this.compte);
		int previousSize = size();
		removeAllElements();
		for (String s : operations) {
			addElement(s);
		}
		fireContentsChanged(OperationController.this, 0, operations.size() - 1);
		fireIntervalAdded(OperationController.this, previousSize, operations.size() - 1);
//		int newSize = operations.size();
//		ListDataEvent event = null;
//		if (newSize == previousSize) {
//			event = new ListDataEvent(OperationController.this, ListDataEvent.CONTENTS_CHANGED, 0, newSize - 1);
//		}
//		else if (newSize > previousSize) {
//			event = new ListDataEvent(OperationController.this, ListDataEvent.INTERVAL_ADDED, previousSize, newSize - 1);
//		}
//		else {
//			event = new ListDataEvent(OperationController.this, ListDataEvent.INTERVAL_REMOVED, newSize, previousSize - 1);
//		}
//		for (ListDataListener l : getListDataListeners()) {
//			l.contentsChanged(event);
//		}
	}

	public void close() {
		this.crediterButton.setEnabled(false);
		this.debiterButton.setEnabled(false);
	}
	
}
