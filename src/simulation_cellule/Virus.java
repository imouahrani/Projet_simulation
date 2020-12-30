package simulation_cellule;

public class Virus extends Cellule{
	
	private int dureeVie; 
	private int tempsExp ;
	
	public Virus( int x, int y, boolean etat )
	{
		super("V", x, y, etat);
		this.dureeVie = 0;
		this.tempsExp = ran.nextInt(15);
	}
	
	public Virus()
	{
		super("V", 0, 0, true);
		this.dureeVie = 0;
		this.tempsExp = ran.nextInt(15);
	}
	

	
	public int[] explosion ()
	{
		int [] tab_voisins = this.affichevoisins();
		int [] tab = new int[6];
		int r, count= 0;
		
		for( int i = 0; i < 3; i++)
		{
			
			r = ran.nextInt( 16 );
			//System.out.println( "ran = " + r);
			
			if( ((r%2) == 0 ))
			{
				tab[count] = tab_voisins[r];
				count++;
				tab[count] = tab_voisins[r+1];
				count++;				
			}
			else
			{
				tab[count] = tab_voisins[r-1];
				count++;
				tab[count] = tab_voisins[r];
				count++;
				
			}
		}
		
	
		return tab;
	}
	
	public int getTempsExp()
	{
		return this.tempsExp;
	}
	
	public int getdureeVie()
	{
		return this.dureeVie;
	}
	
	public void incrementdureeVie()
	{
		this.dureeVie = this.dureeVie + 1;
	}
	
	
	
	
	
	
	
}
