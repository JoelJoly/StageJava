package entity.impl;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;


public class Operation implements Serializable {
	private static final long serialVersionUID = 1265194495724811487L;
	private char type;
	private float montant;
	private Date date;
	public Operation(float montant) {
		this.type = montant > 0 ? 'C' : 'D';
		this.montant = Math.abs(montant);
		this.date = new Date();
	}
	@Override
	public String toString() {
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
		return "Operation [type=" + type + ", montant=" + montant + ", date="
				+ dateFormat.format(date) + "]";
	}
		
}
