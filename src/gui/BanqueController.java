package gui;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import entity.Banque;

public class BanqueController {
	private Banque banque;
	private JList comptesList;
	private CompteBindable compteBindable;
	public interface CompteBindable {
		void bindOn(Banque banque, int compteNumero);
	}
	public BanqueController(Banque banque, JList comptesList, CompteBindable compteBindable) {
		this.banque = banque;
		this.comptesList = comptesList;
		this.compteBindable = compteBindable;

		comptesList.setModel(new CompteController(banque));
		for (ListSelectionListener l : comptesList.getListSelectionListeners()) {
			this.comptesList.removeListSelectionListener(l);
		}
		comptesList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				Banque banque = BanqueController.this.banque;
				int compteNumero = banque.getComptes().get(event.getFirstIndex());
				BanqueController.this.compteBindable.bindOn(banque, compteNumero);
			}
		});
	}
	public void close() {
		this.banque = null;
		this.comptesList.setModel(null);
		BanqueController.this.compteBindable.bindOn(null, -1);
	}
}
