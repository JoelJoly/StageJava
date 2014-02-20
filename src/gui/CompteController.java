package gui;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

import entity.Banque;
import exceptions.CompteInexistant;

public class CompteController implements ListModel {
	private Collection<ListDataListener> listeners;
	private Banque banque;
	public CompteController(Banque banque) {
		this.banque = banque;
		this.listeners = new ArrayList<ListDataListener>();
	}
	@Override
	public void addListDataListener(ListDataListener listener) {
		listeners.add(listener);
	}
	@Override
	public Object getElementAt(int index) {
		try {
			return banque.toString(banque.getComptes().get(index), true);
		} catch (CompteInexistant e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public int getSize() {
		return banque.getComptes().size();
	}
	@Override
	public void removeListDataListener(ListDataListener listener) {
		listeners.remove(listener);		
	}
	
}
