package gui;

import java.util.Collection;

import javax.swing.DefaultListModel;

import entity.Banque;
import exceptions.CompteInexistant;

public class OperationController extends DefaultListModel  {
	private static final long serialVersionUID = 7649727719653350574L;
	Banque banque;
	int compte;
	public OperationController(Banque banque, int compte) throws CompteInexistant {
		this.banque = banque;
		this.compte = compte;
		updateOperations();
	}
	
	private void updateOperations() throws CompteInexistant {
		Collection<String> operations = this.banque.getOperations(this.compte);
		removeAllElements();
		for (String s : operations) {
			addElement(s);
		}
	}
	
}
