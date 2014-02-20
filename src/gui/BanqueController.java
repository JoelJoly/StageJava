package gui;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import entity.Banque;
import exceptions.CompteInexistant;

public class BanqueController {
	private Banque banque;
	private JList comptesList;
	private JList operationsList;
	public BanqueController(Banque banque, JList comptesList, JList operationsList) {
		this.banque = banque;
		this.comptesList = comptesList;
		this.operationsList = operationsList;

		comptesList.setModel(new CompteController(banque));
		for (ListSelectionListener l : comptesList.getListSelectionListeners()) {
			this.comptesList.removeListSelectionListener(l);
		}
		comptesList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				Banque banque = BanqueController.this.banque;
				int compteNumero = banque.getComptes().get(event.getFirstIndex());
				try {
					BanqueController.this.operationsList.setModel(new OperationController(banque, compteNumero));
				} catch (CompteInexistant e) {
					e.printStackTrace();
				}				
			}
		});
	}
	public void close() {
		this.banque = null;
		this.comptesList.setModel(null);
		this.operationsList.setModel(null);
	}
}
