/**
	*	Classe Transmetteur
	* 	@author : -
*/

// Au préalable, compiler avec l'encodage utf8 : javac -encoding utf8 Transmetteur.java MotBinaire.java

// Pour ce programme, je suppose que pour un message "AAA" il y est des espace entre chaque caractère en binaire
	// exemple : AAA => new MotBinaire( new int[] {1000001, 1000001, 1000001} ) -> toString() : "1000001 1000001 1000001";

public class Transmetteur
{
	/*---------------*/
	/* Les attributs */
	/*---------------*/
	private static final int TAILLE_BIT_CHAR = 8;

	protected int n; 		// L'attribut renseigne sur la longueur du mot de code.
	protected int k;		// L'attribut renseigne sur le nombre de bits d'information.
	
	/**
		* @return Retourne l'objet mot binaire si le tableau contient que des 0 et 1. Sinon retourne null.
		* @param n : la longueur du mot de code.
		* @param k : le nombre de bits d'information.
	*/
	public static Transmetteur fabrique(int n, int k)
	{
		if ( n == 0 || k == 0 || n < 0 || k < 0 )
		{
			System.out.println("Aucun message à transmettre ! ");
			return null;
		}

		if ( n < k )
		{
			System.out.println("La longueur du mot (n) ne peut pas être inférieur aux nombre de bits d'informations (k) ! ");
			return null;
		}

		/*if ( n == k )
		{
			System.out.println("La transmetteur a besoin de bits de contrôle pour garantir l'intégrité des données ! ");
			return null;
		}*/

		return new Transmetteur(n, k);
	}


	/**
		* Constructeur Transmetteur
		* @param n : la longueur du mot de code.
		* @param k : le nombre de bits d'information.
	*/
	public Transmetteur(int n, int k)
	{
		this.n = n;
		this.k = k;	
	}
	
	/**
		* @return Transforme une chaîne de caractères en un mot binaire.
		* Chaque caractère possède huit bits selon la table ascii allant de 0 à 255.
		* @param message : le message à encoder.
	*/
	public MotBinaire encoder(String message)
	{
		// Vérification que le message n'est pas vide
		if ( message.length() == 0 || message == null || message.equals("") || message.equals(null) )
		{
			return null;
		}

		// Vérification que le nombre de bits d'information k (attribut) ne soit pas supérieur au nombre de bits d'information du message soit 8 bits par caractère
			// Tout caractère de l'intervalle 0 à 2^k-1 est correcte car il est dans la table ascii.
			// exemple : k = 10 / 'C' = 01000011 => 'C' possède 8 bits et k = 10 bits donc k > 'C'(base 2) donc c'est incohérent
		if ( this.k > 8 )
		{
			System.out.println("Le nombre de bits d'information ne doit pas dépasser 8 car chaque caractère de la table ASCII est codé sur 8 bits !");
			System.out.println("Changer la valeur de k afin d'obtenir de nouveaux résultats ! (changer paramètre constructeur ou méthode setK(int k) disponible ) ");
			return null;
		}
		
		// Chaque lettre est mise dans un tableau de char
		char[] tabMessage 	= message.toCharArray();
		String sBinaire 	= new String();

		// Convertit chaque caractères qui sont dans le tableau de char en nombre binaire
		for(int cptLettre=0; cptLettre < tabMessage.length; cptLettre++)
		{
			// Première solution : Convertir directement un entier en binaire
			String sRecup 		= Integer.toString( Transmetteur.entierEnBinaire( (int) tabMessage[cptLettre] ) );
			String sEntierBin 	= new String();

			// Si il manque des zéros qui ne s'affiche pas pour un int, ça les rajoute
			if ( sRecup.length() < 8 )
			{
				int nbZero = Transmetteur.TAILLE_BIT_CHAR - sRecup.length();
				// Nombre de zéro à rajouter
				for(int cpt=0; cpt < nbZero; cpt++ )
				{
					sEntierBin += "0";
				} 
			}
			sBinaire += sEntierBin + sRecup;

			// Deuxième solution : toBinaryString
				// String sBinaire = Integer.toString( Integer.toBinaryString( (int) tabMessage[cptLettre] ) );
		}

		int[] tabBinaire = new int[sBinaire.length()];

		for(int cpt=0; cpt < tabBinaire.length; cpt++)
		{
			tabBinaire[cpt] = Integer.parseInt( Character.toString( sBinaire.charAt(cpt) ) );
		}

		return MotBinaire.fabrique(tabBinaire);
	}
	
	/**
		* @return Retourne le nombre de bits à 1.
		* @param sBinaire : la chaîne de caractères binaire.
	*/
	private int nbBitsUn(String sBinaire)
	{
		int cptUn = 0;
		for(int cpt=0; cpt < sBinaire.length(); cpt++)
		{
			if ( sBinaire.charAt(cpt) == '1' )
			{
				cptUn++;
			}
		}
		return cptUn;
	}

	/**
		* @return Transforme un mot binaire en chaîne de caractères et retourne la chaîne de caractères.
		* @param bits : le mot binaire.
	*/
	public String decoder(MotBinaire bits)
	{
		if ( bits.toString().length() == 0 || bits == null || bits.toString().equals("") || bits.toString().equals(null) )
		{
			return null;
		}

		String sRet = new String();

		String sDecode 	= bits.toString();
		String sDecoupe = new String();
		
		// Découpe de n en n bits
		for(int cpt=0; cpt < sDecode.length(); cpt+=Transmetteur.TAILLE_BIT_CHAR )
		{
			// Enlève les bits de controles => 8 premiers bits sont le codage ascii binaire du caractère
			sDecoupe = sDecode.substring( cpt, (cpt+Transmetteur.TAILLE_BIT_CHAR) );

			// Traduit le message binaire en un caractère
			sRet += Character.toString( (char) Transmetteur.binaireEnEntier( Integer.parseInt(sDecoupe) ) );
		}
		
		return sRet;
	}

	/**
		* @return Retourne le nombre binaire d'un entier.
		* @param nombre : le nombre entier à convertir en binaire.
	*/
	private static int entierEnBinaire(int nombre)
	{
		String sBinaire = "";

		while( nombre > 0 )
		{
			if ( (nombre % 2) == 0 )
			{
				sBinaire += "0";
			}
			else
			{
				sBinaire += "1";
			}

			nombre = nombre / 2;
		}

		// Renverse la chaîne
		String sRet = "";
		for(int cpt=(sBinaire.length())-1; cpt >= 0; cpt--)
		{
			sRet += sBinaire.charAt(cpt);
		}

		return Integer.parseInt( sRet );
	}

	/**
		* Convertir un nombre binaire en entier.
		* @param nombre : le nombre binaire à convertir en entier.
	*/
	public static int binaireEnEntier(int nombre)
	{
		// Convertit le nombre binaire de type entier en String pour compter les puissances de 2
		String sEntierBinaire = Integer.toString(nombre);
		// Compte le nombre de puissance de 2 maximum
		final int cptPuissanceMax = sEntierBinaire.length()-1;

		int diviseur 		= 1;
		int dividende 		= 0;
		int cptPuissance 	= 0;
		int resultatBinaire = 0;
		int sommeEntier 	= 0;

		while ( diviseur <= nombre && cptPuissance <= cptPuissanceMax )
		{
			dividende 	= nombre / diviseur; // 1 puis 10 puis 100 puis 1000
			resultatBinaire = dividende % 10;

			if ( resultatBinaire == 1 )
			{
				sommeEntier += Math.pow(2, cptPuissance);
			}

			diviseur *= 10;
			cptPuissance++;
		}

		return sommeEntier;
	}

	/**
		* @return Remplace un bit par un bit erreur dans la transmission d'un signal émis, en moyenne, un bit sur n et retourne le mot binaire avec les erreurs rajoutées.
		* @param bits : Le mot binaire où il faut rajouter des erreurs.
		* @param nErreur : le nombre bits d'erreur sur la longueur du mot binaire.
	*/
	public MotBinaire transmettre(MotBinaire bits, int nErreur)
	{
		// Vérifie que le mot binaire bits n'est pas vide
		if ( bits == null || bits.toString().equals("") )
		{
			System.out.println("Aucun message à transmettre");
			return null;
		}
		
		// Vérifie que le nombre d'erreur n'est pas supérieur au nombre de bit
		if ( bits.toString().length() < nErreur )
		{
			return MotBinaire.fabrique(bits.toString());
		}

		String sRet = new String();

		int numBitModifie = ( (int) (Math.random() * this.n ) );
		
		sRet += bits.toString().substring(0, numBitModifie) + Character.toString( this.inverseBit(bits.toString().charAt(numBitModifie)) ) + bits.toString().substring(numBitModifie+1, bits.toString().length());
		
		return MotBinaire.fabrique(sRet);
	}

	/**
		* @return Retourne l'inverse d'un bit. (Si bit = 0 retourne 1 et inversement si bit = 1 retourne 0)
		* @param bit : le bit à inverser.
	*/
	private char inverseBit(char bit)
	{
		// Vérification que le bit est soit 0 ou 1
		if ( bit != '0' && bit != '1' )
		{
			return '\u0000'; // caractère null (valeur par défaut d'un char)
		}

		return bit == '0' ? '1' : '0';
	}
	
	/**
		* @return Permet de désécuriser un mot binaire reçu sans problème, on suppose qu’à chaque blocs de n bits, les k bits d’information sont au début du bloc.
		* @param bits : Le mot binaire à désécuriser.
	*/
	public MotBinaire desecuriser(MotBinaire bits)
	{
		// Vérification que le mot binaire bits n'est pas vide
		if ( bits == null || bits.toString().equals("") )
		{
			return null;
		}

		String sRet 	= new String();
		String sDecode 	= new String();
		sDecode = bits.toString();
		String sDecoupe = new String();


		// Découpe de n en n bits
		int debDecoup = 0;

		for(int cpt=0; cpt < sDecode.length(); cpt++ )
		{
			if ( ( (cpt+1) % n)  == 0 )
			{
				// Découpe le bloc de n bits en k bits ( les k bits sont gardées et les bits de contrôle enlevés )
				sDecoupe = sDecode.substring( debDecoup, (debDecoup + this.k) );

				debDecoup = cpt+1;		

				// Le message modifié avec les erreurs   
				sRet += sDecoupe;
			}
		}

		// System.out.println("sRet est : " + sRet + " taille : " + sRet.length() );

		return MotBinaire.fabrique( sRet );
	}

	/**
		* Changer le nombre de bits d'information.
		* @param k : le nombre de bits d'information.
	*/
	public void setK(int k)
	{
		if ( k > 0  && k <= 8 )
		{
			this.k = k;
		}
	}

	/**
		* @return Affichage du transmetteur.
	*/
	public String toString()
	{
		String sRet = new String();

		return sRet;
	}

	/**
		* Affichage du tableau
		* @param tab : le tableau à afficher.
	*/
	private static void afficherTableau(char[] tab)
	{
		for(int cpt=0; cpt<tab.length; cpt++)
		{
			System.out.print(tab[cpt] + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args)
	{
		int n = 8; // la longueur du mot pour chaque bloc
		int k = 8; // 8 bits sont des bits d'informations
		
		char c = 'A';
	
		System.out.println("===============================");
		System.out.println("TEST DE LA CLASSE TRANSMETTEUR ");
		System.out.println("===============================");	
		System.out.println("n : la longueur du mot de code");
		System.out.println("k : le nombre de bits d'information");
		System.out.println("r : le nombre de bits de contrôle(parité = n - k )");
		System.out.println("Les bits de parités sont pair(soit 0) quand le nombre de bits à 1 est pair");
		System.out.println("Les bits de parités sont impair(soit 1) quand le nombre de bits à 1 est impair");

		System.out.println("========================");
		System.out.println("LES CONDITIONS DE TESTS ");
		System.out.println("========================");
		System.out.println("Pour ce test voici les conditions que je suppose : "); 
		System.out.println("- je suppose que n est égal à " + n + ", que k est égal à " + k + " et que r est égal à " + (n-k) );
		System.out.println("- je suppose que les k premiers bits sont les bits d'information et que les suivants sont les bits de contrôle");

		Transmetteur t1 = Transmetteur.fabrique(n, k);

		System.out.println("=============");
		System.out.println("TEST ENCODER");
		System.out.println("=============");

		System.out.println("J'ai choisi un k égal à " + k + " car " + c + " ( " + 	Transmetteur.entierEnBinaire( (int) c ) +  " ) " + "est codé sur 8 bits");
		System.out.println("Message à encoder : " + Character.toString(c) + Character.toString(c) );
		MotBinaire mEncoder = t1.encoder( Character.toString(c) + Character.toString(c) );
		System.out.println("Résultat encoder : " + mEncoder.toString(k) );

		System.out.println("=============");
		System.out.println("TEST DECODER");
		System.out.println("=============");
		
		// 1000001 1000001 1000001 => "A A"
			// 1000001000 1000001000 1000001000 
				
		System.out.println("Résultat du décodage de mEncoder : " + t1.decoder( mEncoder ) );
		System.out.println("================");
		System.out.println("TEST TRANSMETTRE : MÉTHODE INDÉPENDANTE A NE PAS LIER A LA SUITE DE DECODER");
		System.out.println("================");

		int nbErreur = 2;
		System.out.println("Lors de la transmission, " );
		System.out.println("Nombre d'erreurs : " +  nbErreur );
		MotBinaire mTransmis = MotBinaire.fabrique( t1.transmettre(mEncoder, nbErreur).toString() );
		System.out.println( "Message transmis : " +  mTransmis.toString(n) );

		System.out.println("================");
		System.out.println("TEST DESECURISER : MÉTHODE INDÉPENDANTE A NE PAS LIER A LA SUITE DE TRANSMETTRE ");
		System.out.println("================");
		System.out.println("Lors de la désécurisation, les bits de controle sont enlevés et il n'y a que les bits d'informations qui sont gardés");
		System.out.print("Message transmis : ");
		MotBinaire mDesecuriser = MotBinaire.fabrique( mTransmis.toString() );
		System.out.println( t1.decoder(mDesecuriser) );
		
	}
}
