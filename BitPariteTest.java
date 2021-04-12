/**
 * Classe pour tester BitParite
 * @author  bruno LEGRIX
 * @version 2017-09-26
 */

public class BitPariteTest
{

	private static int nbBit=50;                       // nb de bits affichés

	public static void main(String[] arg)
	{
		if (arg.length>0) nbBit = new Integer(arg[0]);  // nb de bits affichés
		System.out.println("On affiche que les "+nbBit+" premiers bits des mots binaires.");
		
		BitParite codeur = new BitParite();
		
		System.out.println();
		System.out.println("10110100 donnera " + codeur.securiser(MotBinaire.fabrique("10110100")));
		System.out.println("R = 101010110000 a-t-il un problème : " + codeur.aProbleme(MotBinaire.fabrique("101010110000")));
		System.out.println();
		
		String messEmis = "La démocratie est un mauvais système, " +"mais elle est le moins mauvais de tous les systèmes.";
		ecrire("émis" , messEmis);
		MotBinaire messCode = codeur.encoder (messEmis);
		ecrire("codé", messCode);
		MotBinaire signalEmis = codeur.securiser( messCode );
		ecrire("sécurisé", signalEmis);
		int n=100;
		MotBinaire signalRecu;
		boolean probleme;
		do{
			System.out.print("un bit sur "+n+" est transformé. problème =");
			signalRecu = codeur.transmettre(signalEmis,n);
			probleme  = codeur.aProbleme(signalRecu);
			System.out.println(probleme);
			// System.out.println("message reçu : " + signalEmis);
			n+=100;
		}while(probleme);
		MotBinaire messDesecu = codeur.desecuriser(signalRecu);
		ecrire("désécurisé", messDesecu);
		String 	messRecu 	= codeur.decoder(messDesecu);
		ecrire("reçu", messRecu);
	}

	public static void ecrire(String mess, MotBinaire mot)
	{
		ecrire(mess,mot.toString());
	}

	public static void ecrire(String mess, String mot)
	{
		int n = (nbBit<mot.length()?nbBit:mot.length());
		System.out.println( "message " + String.format("%-10s",mess)+" : " + mot.substring(0,n));
	}

}

