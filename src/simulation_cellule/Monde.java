package simulation_cellule;

import java.util.ArrayList;
import java.util.Random;

public class Monde {
	
	private Random ran;
	private int ligne ;
	private int colonne ;
	private Cellule[][]  cellules; 
	
	public Monde( int lig, int col)
	{
		this.ran = new Random();
		this.ligne = lig;
		this.colonne = col;
		
		this.cellules = new Cellule[this.ligne][this.colonne];
				
		for( int i = 0; i < this.ligne; i++)
		{
			for( int j = 0; j < this.colonne ; j++)
			{
				cellules[i][j] = new Vide( i , j);
			}
		}
		
	}
	
	

	public void afficheMonde()
	{
		System.out.print("\t");
		for( int i = 0; i < this.ligne ; i++ )
		{
			System.out.print(i + "\t");
		}
		System.out.println();

		
		
		for( int i = 0; i < this.ligne ; i++)
		{
			System.out.print("" + i + "\t");
			for( int j = 0 ; j < this.colonne ; j++)
			{
				System.out.print( cellules[i][j].getNom() + "\t");
			}

			System.out.println();
			System.out.println();
		}
		
	}
	
	
	public boolean ajouterCellule( Cellule c)
	{
		if( c.getx() >= this.ligne || c.gety() >= this.colonne )
		{
			return false;
		}
		
		if( this.cellules[c.getx()][c.gety()].toString().equals(".") == true || this.cellules[c.getx()][c.gety()].toString().equals("N") )
		{
			this.cellules[ c.getx()][c.gety()] = c;
			
			return true;
		}
		return false;
	}
	
	public void ajouterCellule( Class<? extends Cellule> c, ArrayList<? super Cellule> cells, int n)
	{
		Cellule cell = null;
		int x;
		int y;
		
		for( int i = 0; i < n ; i++)
		{
			do
			{
			    x = ran.nextInt( this.ligne );
				y = ran.nextInt( this.colonne);
				
				if( !cellules[x][y].getClass().equals(c.getClass()) && cellules[x][y].toString().equals(".") )
				{
					
					break;
				}
								
			}
			while( true );
			
			
			try {
				cell = (Cellule)c.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			
			if( cell != null )
			{
				cell.setx(x);
				cell.sety(y);
				
				cellules[x][y] = cell; 
				cells.add( cell );
				
			}
		}
	}
	
	public boolean ecraser_cellule( Cellule c)
	{
		if( c.getx() >= this.ligne || c.gety() >= this.colonne )
		{
			return false;
		}
		
		this.cellules[ c.getx()][c.gety()] = c;
			
		return true;
	}
	
	
	
	public String retournerCase( int x, int y)
	{
		return this.cellules[x][y].toString();
	}
	
	
}
