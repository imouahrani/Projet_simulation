package simulation_cellule;

public class CelluleNormale extends Cellule{

	public CelluleNormale( int x, int y, boolean etat )
	{
		super("N", x, y, etat);
	}
	
	public CelluleNormale()
	{
		super("N", 0, 0, true);
	}
	
	
	
}
