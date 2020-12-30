package simulation_cellule;

public class GlobuleBlanc extends Cellule{

	public GlobuleBlanc( int x, int y, boolean etat )
	{
		super("W", x, y, etat);
	}
	
	public GlobuleBlanc()
	{
		super("W", 0, 0, true);
	}
	
	public int[] deplacer()
	{
		int[] prochaine_cas = new int[2];
		int r = ran.nextInt( 16 ); 
		int[] tab_voisins = this.affichevoisins();
		
		
		if( (r%2) == 0 )
		{
			prochaine_cas[0] = tab_voisins[r];
			prochaine_cas[1] = tab_voisins[r+1];
		}
		else
		{
			prochaine_cas[0] = tab_voisins[r-1];
			prochaine_cas[1] = tab_voisins[r];
		}
		
		return prochaine_cas;
	}
	
	public int[] division()
	{
		int[] prochaine_cas = new int[2];
		int r = ran.nextInt( 16 ); 
		int[] tab_voisins = this.affichevoisins();
		
		
		if( (r%2) == 0 )
		{
			prochaine_cas[0] = tab_voisins[r];
			prochaine_cas[1] = tab_voisins[r+1];
		}
		else
		{
			prochaine_cas[0] = tab_voisins[r-1];
			prochaine_cas[1] = tab_voisins[r];
		}
		
		return prochaine_cas;
	}
	
	
	
}
