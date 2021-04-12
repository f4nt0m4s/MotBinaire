/**
 * Classe pour faire des expériences sur BitTriple
 * @author  bruno LEGRIX
 * @version 2017-10-05
 */

public class BitTripleTest
{
	// ESSAIS
	private static int nbBit=50;
	public static void main(String[] arg)
	{
		if (arg.length>0) nbBit = new Integer(arg[0]);
		BitTriple codeur = new BitTriple();
		
		System.out.println();
		System.out.println("101 donnera : " + codeur.securiser(MotBinaire.fabrique("101")));
		System.out.println();
		System.out.println("101111000010 est corrigé par : " + codeur.corriger(MotBinaire.fabrique("101111000010")));
		System.out.println();
		String messEmis = "Construire peut être le fruit d'un travail long "
		                 + "et acharné. Détruire peut être l'oeuvre "
		                 + "d'une seule journée.";
		ecrire("émis"      ,messEmis  );
		MotBinaire messCode   = codeur.encoder    (messEmis     );
		ecrire("codé"      ,messCode  );
		MotBinaire signalEmis = codeur.securiser  (messCode     );
		ecrire("sécurisé"  ,signalEmis);
		int n=100;
		MotBinaire signalRecu;
		MotBinaire signalCorr;
		boolean    probleme  ;
		do{
			System.out.print("un bit sur "+n+" est transformé. problème =");
			signalRecu = codeur.transmettre(signalEmis,n);
			signalCorr = codeur.corriger(signalRecu);
			probleme   = !signalCorr.equals(signalEmis);
			System.out.println(probleme);
			n+=100;
		}while(probleme);
		MotBinaire messDesecu = codeur.desecuriser(signalCorr   );
		ecrire("désécurisé",messDesecu);
		String     messRecu   = codeur.decoder    (messDesecu   );
		ecrire("reçu"      ,messRecu  );
		
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

