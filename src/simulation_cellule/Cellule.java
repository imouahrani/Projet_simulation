package simulation_cellule;


public abstract class Cellule {

	protected  MTRandom ran;
	
	protected String nom ;
	protected String couleur;
	protected int[] position;
	protected boolean etat;
	
	public Cellule( String nom, int x, int y, boolean etat )
	{
		this.ran = new MTRandom();
		
		this.nom = nom;
		this.position = new int[2];
		this.position[0] = Math.abs(x);
		this.position[1] = Math.abs(y);
		this.etat = etat;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getCouleur() {
		return couleur;
	}
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	public int[] getPosition() {
		return position;
	}
	public int getx() {
	
		return this.position[0];
	}
	
	public int gety() {
		
		return this.position[1];
	}
	
	public void setx( int x ) {
		
		this.position[0] = x;
	}
	
	
	public void sety( int y ) {
		
		this.position[1] = y;
	}
	
	public void setPosition(int[] position) {
		this.position = position;
	}
	public boolean isEtat() {
		return etat;
	}
	public void setEtat(boolean etat) {
		this.etat = etat;
	}
	
	@Override
	public String toString()
	{
		return this.nom;
	}
	
	public int[]  affichevoisins()
	{
	
		int x = this.getx();
		int y = this.gety();
		int x1 , y1;
		int[] voisins = new int[16];
		int indice = 0;
		
		for( int i = -1; i < 2 ; i++)
		{
			for( int j = -1; j<2; j++)
			{
				x1 = x + i;
				y1 = y + j;
				
				if(  ( x != x1 ) || ( y != y1 ) )
				{
					voisins[ indice ] = x1;
					indice++;
					voisins[ indice ] = y1;
					indice++;
					
					//System.out.println( x1 + " " + y1);

				}
				
				
				
			}
	
		}
		
		return voisins;
	}
	
	
	
	
	
	
	
	
}
