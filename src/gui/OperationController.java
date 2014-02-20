package gui;

import java.util.Collection;

import javax.swing.DefaultListModel;
import javax.swing.JButton;

import entity.Banque;
import exceptions.CompteInexistant;

public class OperationController extends DefaultListModel  {
	private static final long serialVersionUID = 7649727719653350574L;
	Banque banque;
	int compte;
	JButton crediterButton;
	JButton debiterButton;
	public OperationController(Banque banque, int compte, JButton crediterButton, JButton debiterButton) throws CompteInexistant {
		this.banque = banque;
		this.compte = compte;
		this.crediterButton = crediterButton;
		this.debiterButton = debiterButton;
		updateOperations();
		this.crediterButton.setEnabled(true);
		this.debiterButton.setEnabled(true);
	}
	
	private void updateOperations() throws CompteInexistant {
		Collection<String> operations = this.banque.getOperations(this.compte);
		removeAllElements();
		for (String s : operations) {
			addElement(s);
		}
	}

	public void close() {
		this.crediterButton.setEnabled(false);
		this.debiterButton.setEnabled(false);
	}
	
}
