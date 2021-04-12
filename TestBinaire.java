
/**
	* Classe TestBinaire
	* @author 	: -
	* @version 	: 1.0
	* date 		! 16/10/2020
*/

// Au préalable, compiler avec l'encodage utf8 : javac -encoding utf8 TestBinaire.java


public class TestBinaire
{
	public static void main(String[] args)
	{
		/**
			source du sujet : https://www.developpez.net/forums/d1490590/c-cpp/c/type-unsigned-int/

			Quelle est la différence entre unsigned int et signed int ?
			(signed) int : est un entier signé, peut être positive, négatif ou null.
			unsigned int : est un entier non-signé, soit positive ou soit null.


			En mémoire il n'y en a aucune. C'est juste une indication permettant à ton compilo de comprendre comment interpréter un nombre dont le premier bit est à 1. 
			(est-ce que ce bit entre en jeu dans la valeur du nombre ou bien signifie-t-il que c'est un nombre négatif).
			Ca peut avoir un impact en termes de performances quand une variable est copiée dans une variable plus large. 

			Par exemple prenons une variable sur 8 bits (char ou unsigned char) et avec la valeur 0x80 (1000 0000 en binaire) stockée à l'intérieur. 
				Si cette variable est recopiée dans un short (16 bits) alors
					si c'est un unsigned char, le short contiendra alors 0x0080 (0000 0000 1000 0000 en binaire)
					si c'est un signed char, le short contiendra alors 0xFF80 (1111 1111 1000 0000 en binaire)

			Hé oui, pour garder le bit de signe, la recopie l'étend sur toute la plage disponible (extension du bit de signe) et cette recopie prend forcément plus de temps que si on n'a pas à gérer le signe.
		
		*/

		System.out.println("Quelle est la différence entre (signed) int et unsigned int ? ");
		System.out.println("(signed) int : est un entier signé, peut être positive, négatif ou null.");
		System.out.println("unsigned int : est un entier non-signé, soit positive ou soit null.");
		System.out.println();

		System.out.println("En mémoire, il y a aucune différence.");
		System.out.println("C'est l'indication permettant au compilateur de comprendre comment interpréter un nombre dont le premier bit est à 1");
		System.out.println("(le premier bit comte ou bien cela signifie que c'est un nombre négatif)");
		System.out.println();

		System.out.println("Il peut y avoir une conséquence quand une variable est copiée dans une variable plus large.");
		System.out.println("Exemple : prenons une variable sur 8 bits (char ou unsigned char) et avec la valeur 0x80 (1000 0000 en binaire) stockée à l'intérieur.");
		System.out.println("\tSi cette variable est recopiée dans un short (16 bits) alors");
		System.out.println("\t\tsi c'est un unsigned char, le short contiendra alors 0x0080 (0000 0000 1000 0000 en binaire)");
		System.out.println("\t\tsi c'est un signed char, le short contiendra alors 0xFF80 (1111 1111 1000 0000 en binaire)");
		System.out.println("source de l'explication : https://www.developpez.net/forums/d1490590/c-cpp/c/type-unsigned-int/");

		System.out.println();
		System.out.println();
	
		System.out.println();

		// Entier signé (signed int)
			// Décalage à droite >>
		int x 			= 4;
		int y 			= -4;
		int decalage 	= 0;
		int resultat 	= 0;

		/*--------------------------------------------------------------------------*/
		/* 					DECALAGE A DROITE >> : ENTIER SIGNÉ  					*/
		/*--------------------------------------------------------------------------*/
		System.out.println("===================================");
		System.out.println("DECALAGE A DROITE >> : ENTIER SIGNÉ");
		System.out.println("===================================");

		/*-----------------------------------*/
		/* Test avec un entier signé positif */
		/*-----------------------------------*/

		// remplace tous les espaces par des "0" pour que le nombre de bits soit égale à 8.
		String sBinaire = String.format("%8s", Integer.toBinaryString(x)).replace(" ", "0");

		System.out.println( x + " en base 2 : " + sBinaire );

		decalage = 1;

		resultat = x>>decalage;
		System.out.println( x + ">>" + decalage + " : " + resultat + " (" + String.format("%8s", Integer.toBinaryString(resultat)).replace(" ", "0") + ")");

		System.out.println();

		/*-----------------------------------*/
		/* Test avec un entier signé négatif */
		/*-----------------------------------*/

		sBinaire = Integer.toBinaryString(y);
		System.out.println( y + " en base 2 : " + sBinaire );

		resultat = y>>decalage;
		System.out.println( y + ">>" + decalage + " : " + resultat + " (" + Integer.toBinaryString(resultat) + ")" );

		System.out.println();
		System.out.println();

		/*------------------------------------------------------------------------------*/
		/* 					DECALAGE A DROITE >>> : ENTIER NON-SIGNÉ  					*/
		/*------------------------------------------------------------------------------*/
		System.out.println("======================================================");
		System.out.println("DECALAGE A DROITE >> : ENTIER NON-SIGNÉ (unsigned int)");
		System.out.println("======================================================");


		/*-----------------------------------*/
		/* Test avec un entier signé positif */
		/*-----------------------------------*/

		// remplace tous les espaces par des "0" pour que le nombre de bits soit égale à 8.
		sBinaire = String.format("%8s", Integer.toBinaryString(x)).replace(" ", "0");

		System.out.println( x + " en base 2 : " + sBinaire );

		resultat = x>>>decalage;
		System.out.println( x + ">>>" +  decalage + " : " + resultat + " (" + String.format("%8s", Integer.toBinaryString(resultat)).replace(" ", "0") + ")");

		System.out.println();

		/*-----------------------------------*/
		/* Test avec un entier signé négatif */
		/*-----------------------------------*/

		decalage = 32;

		sBinaire = Integer.toBinaryString(y);
		System.out.println( y + " en base 2 : " + sBinaire );

		resultat = y>>>decalage;
		System.out.println( y + ">>>" + decalage + " : " + resultat + " (" + Integer.toBinaryString(resultat) + ")");
	}	

}