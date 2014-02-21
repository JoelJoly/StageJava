import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class Robot {
	private Map<String, Tresor> lesTresors;
	private Piece maPiece;
	private int chargeMax;
	private int maCharge = 0;
	
	public Robot(Piece maPiece, int chargeMax) {
		super();
		this.maPiece = maPiece;
		this.chargeMax = chargeMax;
		lesTresors = new HashMap<String, Tresor>();
	}
	
	public boolean go(PC pc){		
		Piece p=maPiece.go(pc);
		if (p != null) {
			maPiece = p;
			return true;
		}
		return false;
	}
	
	public void prendre (String tresor) throws TresorNonExistant, Perdu {
		Tresor t = maPiece.prendre(tresor);
		lesTresors.put(t.getNom(), t);
		maCharge += t.getPoids();
		if (maCharge > chargeMax) {
			throw new Perdu();
		}
	}
	
	public void deposer (String tresor) throws TresorNonExistant, Gagne{
		Tresor t = lesTresors.get(tresor);
		if (t == null)
			throw new TresorNonExistant();
		maPiece.deposer(t);
		maCharge -= t.getPoids();
		lesTresors.remove(t.getNom());
	}
	
	public String tesou () {
		return maPiece.getNom();
	}
	
	public Collection<String> tasquoi(){
		return lesTresors.keySet();
	}
	
	public Collection<String> yaquoi () {
		return maPiece.yaquoi();		
	}
	
	

}
