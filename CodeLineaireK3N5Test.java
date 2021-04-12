/**
 * Classe pour faire des expériences sur les codes correcteurs
 * @author  bruno LEGRIX
 * @version 2019-09-05
 */

public class CodeLineaireK3N5Test
{
	// ESSAIS
	private static int nbBit=50;

	public static void main(String[] arg)
	{
		if (arg.length>0) nbBit = new Integer(arg[0]);
		CodeLineaireK3N5 codeur = new CodeLineaireK3N5();
		
		System.out.println();
		System.out.println("101 donnera "
		                   +codeur.securiser(MotBinaire.fabrique("101")));
		System.out.println("110 donnera "
		                   +codeur.securiser(MotBinaire.fabrique("110")));
		System.out.println();
		
		String messEmis = "La critique peut être désagréable, "
		                 +"mais elle est nécessaire. "
		                 +"Elle est comme la douleur pour le corps humain : "
		                 +"elle attire l'attention sur ce qui ne va pas. ";
		ecrire("émis"      ,messEmis  );
		MotBinaire messCode   = codeur.encoder    (messEmis     );
		ecrire("codé"      ,messCode  );
		MotBinaire signalEmis = codeur.securiser  (messCode     );
		ecrire("sécurisé"  ,signalEmis);
		int n=100;
		MotBinaire signalRecu;
		boolean    probleme  ;
		do{
			System.out.print("un bit sur "+n+" est transformé. problème =");
			signalRecu = codeur.transmettre(signalEmis,n);
			probleme   = codeur.aProbleme(signalRecu);
			System.out.println(probleme);
			n+=100;
		}while(probleme);
		MotBinaire messDesecu = codeur.desecuriser(signalRecu   );
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
		System.out.println( "message "
		                   +String.format("%-10s",mess)+" : "
		                   +mot.substring(0,n));
	}

}

