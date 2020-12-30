package simulation_cellule;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class Application {
	

	
	public static void main( String[] args ) throws InterruptedException
	{
		int nb_ligne = 10;
		int nb_colonne = 10;
		
		ArrayList<? super Cellule> globuleblanc ;
		ArrayList<? super Cellule> virus ;
		ArrayList<? super Cellule> cellulenormale ;
		 
		
		Monde matrice = new Monde(nb_ligne , nb_colonne);
		globuleblanc = new ArrayList<>();
		virus = new ArrayList<>();
		cellulenormale = new ArrayList<>();

		matrice.ajouterCellule(GlobuleBlanc.class, globuleblanc , 5);
		matrice.ajouterCellule(Virus.class, virus, 10);
		matrice.ajouterCellule(CelluleNormale.class, cellulenormale, 50);
		
		MTRandom r = new MTRandom(15);
		
		System.out.println("kllllk"+ r.next(4));
		
		
				
		int t = 0;
		int [] voisins_exploser = new int[6]; 
	
		
		while( true )
		{
			
			if( virus.size() == 0)
			{
				System.out.println("HEALED");
				System.exit(0);
			}
			
		

			if( virus.size() >= ( nb_ligne*nb_colonne)*2/3 )
			{
				//****************Moment de division des globule blanc *****************		
				
				System.out.println("les globules blancs ont explosé");
				System.out.println("SO SEAK");

				Thread.sleep(5000);
				
				ListIterator<? super Cellule> iter_gb = globuleblanc.listIterator();
				int[] next_etape = new int[2];
				
				while( iter_gb.hasNext() ) 
				{
					GlobuleBlanc gb = (GlobuleBlanc) iter_gb.next();

					next_etape = gb.division();

					
					if(next_etape[0] >= 0 && next_etape[1] >=0 && next_etape[0] < nb_ligne && next_etape[1] < nb_colonne )
					{
						if( matrice.retournerCase( next_etape[0], next_etape[1]).toString().equals(".") == true  )
						{
							GlobuleBlanc globule_b = new GlobuleBlanc( next_etape[0] ,  next_etape[1], true );// création d'un Globule Blanc avec les coordonnes de la prochaine cas 
						    matrice.ecraser_cellule( globule_b ); // mettre cet nouveaux globule blanc dans la matrice
						    iter_gb.add(globule_b);
						   
						   
						}
						else if( matrice.retournerCase( next_etape[0], next_etape[1]).toString().equals("V") == true )
						{

							ListIterator<? super Cellule> iter_v = virus.listIterator();
							//*J'enleve ce virus dans la liste des virus*
							while( iter_v.hasNext() )
							{
								Virus virus1 = (Virus) iter_v.next();
								if( virus1.getx() == next_etape[0] && virus1.gety() == next_etape[1] )
								{
									iter_v.remove(); 
									//System.out.println("Il enleve");

								}
							}
						
						   //*je remplis l'ancienne cas par une cas vide  *
							GlobuleBlanc globule_b = new GlobuleBlanc( next_etape[0] ,  next_etape[1], true );// création d'une cellule vide remplaçant le globule blanc
						    matrice.ecraser_cellule( globule_b );
						    iter_gb.add(globule_b);
						  
						}
						
					}	
				}

			}
			
			System.out.println("Nombre de virus est :" + virus.size());
			System.out.println("Nombre de Globul Blanc est :" + globuleblanc.size());
			System.out.println("Nombre de Cellule Normale est :" + cellulenormale.size());


			
			System.out.println("t = " + t);
			System.out.println("***************");

			matrice.afficheMonde();
			
			/****************Commencement de traitement pour les virus **************/
			
			ListIterator<? super Cellule> iter = virus.listIterator();
			
			while( iter.hasNext() )
			{
				Virus v1 = (Virus) iter.next();
				((Virus)v1).incrementdureeVie();/*Incrementer son tour de vie */
				
				int v_tour = v1.getdureeVie();
				int v_exp = v1.getTempsExp();

								
				if( v_tour == v_exp )
				{
					voisins_exploser  = ((Virus)v1).explosion();

					//int x_vide = v1.getx();
					//int y_vide = v1.gety();
				
					
					for( int i = 0; i < voisins_exploser.length; ++i )
					{
													
						int x = -1, y = -1;
						// infection begins
						if( ( i % 2 ) == 0 )
						{
							x = ( ( voisins_exploser[i] < 0 || voisins_exploser[i] > nb_ligne ) ? -1 : voisins_exploser[i] );
							y = ( ( voisins_exploser[i + 1] < 0 || voisins_exploser[i + 1] > nb_ligne ) ? -1 : voisins_exploser[i + 1] );
							
							//System.out.println("GGG : ");								
							//System.out.println("x = " + x);
							//System.out.println("y = " + y);
							
							if( x >= 0 && y >= 0 )
							{
								Virus new_virus = new Virus(x, y, true);
								ListIterator<? super Cellule> iter_cellule_normale = cellulenormale.listIterator();
								
								/*J'enleve ce virus dans la liste des virus*/
								while( iter_cellule_normale.hasNext() )
								{
									CelluleNormale cellule_noramle = (CelluleNormale) iter_cellule_normale.next();
									
									if( cellule_noramle.getx() == x && cellule_noramle.gety() == y )
									{
										iter_cellule_normale.remove(); 
										//System.out.println("Il enleve");

									}
								}
								
								//System.out.println("Main : ");								
								//System.out.println("x = " + x);
								//System.out.println("y = " + y);
								if( matrice.ajouterCellule(new_virus) == true )
								{
									iter.add(new_virus);
								}
								
							}
						}
						
					}
					
					//System.out.println();
				}
			}
			
			
				/****************Pour chaque Globule Blanc *****************/		
		
			
			ListIterator<? super Cellule> iter_gb = globuleblanc.listIterator();
			int[] next_etape = new int[2];
			
			while( iter_gb.hasNext() ) 
			{
				GlobuleBlanc gb = (GlobuleBlanc) iter_gb.next();
				next_etape = gb.deplacer();
				
				if(next_etape[0] >= 0 && next_etape[1] >=0 && next_etape[0] < nb_ligne && next_etape[1] < nb_colonne )
				{
					if( matrice.retournerCase( next_etape[0], next_etape[1]).toString().equals(".") == true  )
					{
					   Vide vide = new Vide( gb.getx(), gb.gety());// création d'une cellule vide remplaçant le globule blanc
					   matrice.ecraser_cellule( vide );
					   
					   gb.setx(next_etape[0] );
					   gb.sety( next_etape[1] );
					   matrice.ecraser_cellule( gb );
					   
					}/*****************Si dans la prochaine cas de deplacement est un Virus*******************/
					else if( matrice.retournerCase( next_etape[0], next_etape[1]).toString().equals("V") == true )
					{
						  // System.out.println("On est la ");

						ListIterator<? super Cellule> iter_v = virus.listIterator();
						/*J'enleve ce virus dans la liste des virus*/
						while( iter_v.hasNext() )
						{
							Virus virus1 = (Virus) iter_v.next();
							if( virus1.getx() == next_etape[0] && virus1.gety() == next_etape[1] )
							{
								iter_v.remove(); 
								//System.out.println("Il enleve");

							}
						}
					
					   /*je remplis l'ancienne cas par une cas vide  */
					   Vide vide = new Vide( gb.getx(), gb.gety());
					   matrice.ecraser_cellule( vide );
					   
					   /* je change les coordonnées de mon Globule Blanc*/
					   gb.setx(next_etape[0] );
					   gb.sety( next_etape[1] );
					   matrice.ecraser_cellule( gb );
					   
					   
					   
					   
						
					}
					
				}
					
			}
			
			
			
	

			
			
			t++;
			Thread.sleep(500);
		
		}
	
	}
	
	
	
}
