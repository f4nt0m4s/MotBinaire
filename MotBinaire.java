import java.util.Arrays;
import java.lang.Math;

/**
	*	Classe MotBinaire
	* 	@author : -
*/

public class MotBinaire
{
	/*------------*/
	/* L'attribut */
	/*------------*/
	private int[]  tabMot; // le tableau de d'entier de mot -> chaque case prend la valeur de 0 ou 1.
	

	/*-----------------------------------------------------------------------*/
	/* METHODE POUR TESTER LES PARAMETRES DES CONSTRUCTEURS (DESIGN FACTORY) */
	/*-----------------------------------------------------------------------*/

	/**
		* @return Retourne l'objet mot binaire si le tableau contient que des 0 et 1. Sinon retourne null.
		* @param mot : le tableau d'entier de mot.
	*/
	public static MotBinaire fabrique(int[] mot)
	{
		if ( MotBinaire.verifMotBinaire(mot) )
		{
			System.out.println("La vérification du mot binaire signale que le tableau d'entier fournit n'est pas composé que de chiffres binaires ! ");
			return null;
		}
		return new MotBinaire(mot);
	}

	/**
		* @return Retourne le mot binaire.
		* @param mot : le mot sous forme la forme d'une chaîne de caractères.
	*/
	public static MotBinaire fabrique(String mot)
	{
		if ( mot.length() == 0 || mot == null || mot.equals("") || mot.equals(null) )
		{
			return null;
		}

		if ( MotBinaire.verifMotBinaire(mot) )
		{
			return null;
		}

		return new MotBinaire(mot);
	}
	
	/**
		* @return Retourne vrai si le mot contient au moins un chiffre/nombre différent de 0 ou de 1. Et faux si le mot contient que des 0 et 1.
		* @param mot : le tableau d'entier de mot.
	*/
	private static boolean verifMotBinaire(int[] mot)
	{
		for(int cptMot = 0; cptMot<mot.length; cptMot++)
		{
			if ( mot[cptMot] != 0 && mot[cptMot] != 1 )
			{
				return true;	
			}
		}
		return false;
	}

	/**
		* @return Retourne vrai si le mot contient au moins un chiffre/nombre différent de 0 ou de 1. Et faux si le mot contient que des 0 et 1.
		* @param mot : la chaîne de caractères du mot.
	*/
	private static boolean verifMotBinaire(String mot)
	{
		for(int cptCarac = 0; cptCarac < mot.length(); cptCarac++)
		{
			if ( mot.charAt(cptCarac) != '0' && mot.charAt(cptCarac) != '1' && mot.charAt(cptCarac) != ' ' )
			{
				return true;	
			}
		}
		return false;
	}

	/*-------------------------*/
	/* DEBUT DES CONSTRUCTEURS */
	/*-------------------------*/
	
	/**
		* Constructeur privée pour tableau de mot entier.
		* @param mot : le tableau de mot.
	*/
	private MotBinaire(int[] mot)
	{
		this.tabMot = new int[mot.length];

		for(int chiffre = 0; chiffre < mot.length; chiffre++)
		{
			this.tabMot[chiffre] = mot[chiffre];
		}
	}
	
	/**
		* Constructeur privée pour le mot sous la forme de chaîne de caractères.
		* @param mot : le mot sous la forme de chaîne de caractères.
	*/
	private MotBinaire(String mot)
	{
		this.tabMot = new int[mot.length()];

		for(int caractere = 0; caractere < mot.length(); caractere++)
		{
			this.tabMot[caractere] = Integer.parseInt( Character.toString( mot.charAt(caractere) ) ); // MotBinaire.getInt(mot, caractere);
		}
	}

	private MotBinaire()
	{
		
	}

	/**
		* @return Retourne la conversion d'un caractere(0 ou 1) en un int.
		* @param mot : le mot binaire sous forme de chaîne de caractères.
		* @param indiceCaractere : l'indice de l'entier à recupèrer dans la chaîne de caractères.

	*/
	private static int getInt(String mot, int indiceCaractere)
	{
		if ( mot.length() == 0 || mot == null || mot.equals("") || mot.equals(null) || indiceCaractere < 0 || indiceCaractere > mot.length() )
		{
			return -1;
		}

		return Integer.parseInt( Character.toString( mot.charAt(  indiceCaractere ) ) );
	}
	
	/**
		* Constructeur public par recopie pour l'objet mot binaire.
		* @param autre : l'objet mot binaire.
	*/
	public MotBinaire(MotBinaire autre)
	{
		this(autre.toString());
	}
	
	/*-----------------------*/
	/* FIN DES CONSTRUCTEURS */
	/*-----------------------*/
	
	
	/*--------------------*/
	/* DEBUT DES METHODES */
	/*--------------------*/
	
	/**
		@return Addition binaire sans la retenue.
		* @param a : MotBinaire a.
		* @param b : MotBinaire b. 
	*/
	public static MotBinaire addition(MotBinaire a, MotBinaire b)
	{
		if ( a == null || b == null )
		{
			return new MotBinaire( new int[] {-1} );
		}

		if ( ! ( a.toString().length() == b.toString().length() ) )
		{
			return new MotBinaire( new int[] {-1} );
		}
		
		String sResultatAddition = new String();
		
		for(int cpt=0; cpt<a.toString().length(); cpt++)
		{
			if ( a.toString().charAt(cpt) !=  b.toString().charAt(cpt) )
			{
				sResultatAddition += "1";
			}
			else
			{
				sResultatAddition += "0";
			}
		}

		return new MotBinaire(sResultatAddition.toString());
	}
	
	/**
		Pour cette méthode, j'ai recherché de l'aide pour l'algorithme de calcul en binaire à cette adresse : https://lewebpedagogique.com/langemelanie/files/2014/01/TP-11-ISN.pdf
		* @return Retourne le résultat sous forme d'un objet MotBinaire grâce l'addition de deux MotBinaire.
		* @param a : MotBinaire a.
		* @param b : MotBinaire b. 
	*/
	public static MotBinaire additionBinaire(MotBinaire a, MotBinaire b)
	{
		if ( a == null || b == null )
		{
			return new MotBinaire( new int[] {-1} );
		}

		if ( ! ( a.toString().length() == b.toString().length() ) )
		{
			return new MotBinaire( new int[] {-1} );
		}
		
		// Comme on recupère le toString de a, et b -> conversion en int pour le calcul
		final int taille = a.toString().length(); 
		int[] tabContenantMotA = new int[taille];
		int[] tabContenantMotB = new int[taille];
		
		for(int cptChiffre = 0; cptChiffre < a.toString().length(); cptChiffre++)
		{
		 	tabContenantMotA[cptChiffre] = MotBinaire.getInt(a.toString(), cptChiffre);
		 	tabContenantMotB[cptChiffre] = MotBinaire.getInt(b.toString(), cptChiffre);
		}


		int retenue = 0;
		StringBuilder sResultatAddition = new StringBuilder();
		for(int cptChiffre = tabContenantMotA.length-1; cptChiffre >= 0; cptChiffre--)
		{
			if ( tabContenantMotA[cptChiffre] + tabContenantMotB[cptChiffre] + retenue <= 1 )
			{
				sResultatAddition.append(Integer.toString(tabContenantMotA[cptChiffre] + tabContenantMotB[cptChiffre] + retenue));
				retenue = 0;
			}
			else if ( tabContenantMotA[cptChiffre] + tabContenantMotB[cptChiffre] + retenue == 2 )
			{
				sResultatAddition.append(Integer.toString(0));
				retenue = 1;
			}
			else
			{
				sResultatAddition.append(Integer.toString(1));;
				retenue = 1;
			}
		}
		
		if ( retenue == 1 )
		{
			sResultatAddition.append(Integer.toString(retenue));
		}
		sResultatAddition.reverse();

		return new MotBinaire(sResultatAddition.toString());
	}
	
	/**
		* @return Retourne le poids du mot binaire.
	*/
	public int poids()
	{
		int nbUn = 0;
		for(int cptChiffre = 0; cptChiffre < this.tabMot.length; cptChiffre++)
		{
			if ( this.tabMot[cptChiffre] == 1)
			{
				nbUn++;
			}
		}

		return nbUn;
	}

	/**
		* @return Retourne la distance avec le mot binaire comparée.
		* @param autre : le mot binaire qui est comparé.
	*/
	public int distance(MotBinaire autre)
	{
		if ( autre == null )
		{
			return -1;
		}

		// Vérification que les deux mots binaire ont la même taille
		if ( this.toString().length() != autre.toString().length() )
		{
			return -1;
		}

		// pour calculer la distance, il suffit de compter le nombre de bit qui sont différents.
		int distance = 0;
		for(int caractere = 0; caractere < this.toString().length(); caractere++ )
		{
			if ( this.get(caractere) != autre.get(caractere) )
			{
				distance++;
			}
		}

		return distance;
	}

	/**
		* @return Retourne le nombre de bits du mot binaire.
	*/
	public int nbBits()
	{
		return this.tabMot.length;
	}

	/**
		* @return Retourne le sous-mot composé des bits avec un indice de début et de fin.
		* @param debut : l'indice de debut.
		* @param fin : l'indice de fin.
	*/
	public MotBinaire sousMot(int debut, int fin)
	{
		if ( debut < 0 || debut > fin || debut > this.toString().length() )
		{
			return null;
		}

		if ( fin < 0 || fin < debut || fin > this.toString().length() )
		{
			return null;
		}

		String sousMot = new String();
		for(int caractere = debut; caractere < fin; caractere++)
		{
			sousMot += Integer.toString( this.get(caractere) );
		}

		return new MotBinaire(sousMot);
	}

	/**
		* @return Retourne le bit d'indice i.
		* @param i : l'indice i.
	*/
	public int get(int i)
	{
		if ( i < 0 || i > this.tabMot.length-1  )
		{
			return -1;
		}

		return this.tabMot[i];
	}

	/**
		* Affecte le bit d'indice i à la valeur 'bit'.
		* @param i : l'indice i.
		* @param bit : le bit qui reçoit la valeur du bit d'indice i.
	*/
	public void set(int i, int bit)
	{
		if ( i >= 0 && i < this.toString().length() )
		{
			if ( bit == 0 || bit == 1)
			{
				this.tabMot[i] = bit;
			}
		}
	}

	/**
		* @return Affichage le mot binaire.
	*/
	public String toString()
	{
		String sRet = new String(); 

		for(int cptChiffre = 0; cptChiffre < this.tabMot.length; cptChiffre++)
		{
			sRet += Integer.toString(this.tabMot[cptChiffre]);
		}

		return sRet;
	}


	/**
		* @return Affiche le mot binaire lorsqu'il est composé de bit de contrôle.
		* @param n : la longueur du mot de code.
	*/
	public String toString(int n)
	{
		String sRet = new String();

		for(int cpt=0; cpt < this.tabMot.length; cpt++)
		{
			sRet += Integer.toString( this.tabMot[cpt] );

			// Séparation des blocs de n bits
			if ( ( (cpt+1) % n )  == 0 )
			{
				sRet += " ";
			}
		}

		return sRet;
	}

	
	/**
		* @return Retourne vrai si les deux objets correspondent. Et false si les deux objets ne correspondent pas.
		* @param autre : l'objet qui est comparé
	*/
	public boolean equals(MotBinaire autre)
	{
		if ( autre == null )
		{
			return false;
		}

		return this.toString().equals( autre.toString() );
	}

	/**
		* Lors de mes recherches, j'ai trouvé que cela pouvait se faire avec de la récursivité mais j'ai opté pour un algorithme avec modulo et division pour convertir des entiers en binaires.
		* @return Retourne l'ensemble des mots de n bits.
		* @param n : le nombre de bits dans un mot binaire. (n = 2; 2^2 combinaisons => 00, 01, 10, 11)
	*/
	public static MotBinaire[] genererMots(int n)
	{
		if ( n < 0 )
		{
			return null;
		}

		if ( n == 0 )
		{
			return new MotBinaire[] { new MotBinaire( new int[] {n} ) };
		}

		MotBinaire[] listMotBinaire = new MotBinaire[ (int) Math.pow( 2, n ) ]; // 0 à 2^n 


		int puissanceN = 0; 

		for(int cptListe = 0; cptListe < listMotBinaire.length; cptListe++)
		{
			listMotBinaire[cptListe] = new MotBinaire( MotBinaire.calculerDecimalEnBinaire(puissanceN++, n) );
		}

		return listMotBinaire;
	}

	/**
		Pour cette méthode, j'ai recherché de l'aide sur l'algorithme pour convertir un entier en binaire à cette adresse : https://codes-sources.commentcamarche.net/source/26456-conversion-decimal-vers-binaire
		* @return Retourne un tableau d'entier d'une combinaison de la méthode genererMots.
		* @param puissanceN : L'exposant n à convertir en binaire (ex : n = 2 -> 10(binaire) ).
		* @param nbBits : le nombre de bits dans un mot binaire. (n = 2; 2^2 combinaisons => 00, 01, 10, 11)
	*/
	private static int[] calculerDecimalEnBinaire(int puissanceN, int nbBits)
	{
		int[] tabConversion 	= new int[nbBits];
		int indiceTab 			= nbBits-1;
		int reste 				= 0;
		int nbPuissanceDeux		= puissanceN;
		
		while(nbPuissanceDeux > 1)
		{
			reste 			= nbPuissanceDeux % 2;
			nbPuissanceDeux = nbPuissanceDeux / 2;

			if ( reste == 0 )
			{
				tabConversion[indiceTab--] = 0;
			}
			else
			{
				tabConversion[indiceTab--] = 1;
			}
		}

		if ( nbPuissanceDeux == 0 )
		{
			tabConversion[indiceTab--] = 0;
		}

		if( nbPuissanceDeux == 1 )
		{
			tabConversion[indiceTab--] = 1;
		}

		return tabConversion;
	}
	
	/*------------------*/
	/* FIN DES METHODES */
	/*------------------*/
}
