package simulation_cellule;

public class Vide extends Cellule {

	public Vide(int x, int y)
	{
		super(".", x, y, false);
	}
	
	public Vide(String nom, int x, int y)
	{
		super(nom, x, y, false);
	}
}
