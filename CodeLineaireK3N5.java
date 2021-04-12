/**
	*	Classe CodeLineaireK3N5
	* 	@author : -
*/

// Au préalable, compiler avec l'encodage utf8 : javac -encoding utf8 Transmetteur.java MotBinaire.java

public class CodeLineaireK3N5 extends CodeDetecteur
{
	/*---------------*/
	/* Les attributs */
	/*---------------*/
	private static final int NB_BITS = 5; // longueur du mot de code
	private static final int K_BITS = 3; // nombre de bits d'information


	/**
		* Constructeur CodeLineaireK3N5
	*/
	public CodeLineaireK3N5()
	{
		super(NB_BITS, K_BITS);
	}
		
	/**
		* @return Retourne le mot binaire sécurisé grâce à l'application de codage ϕ.
		* @param bits : le mot binaire à sécuriser.
	*/
	public MotBinaire securiser(MotBinaire bits)
	{
		// Vérifie que le mot binaire bits n'est pas vide
		if ( bits == null || bits.toString().equals("") )
		{
			System.out.println("Aucun message à sécuriser");
			return null;
		}
		
		String sMot = new String();
		
		for(int cpt=0; cpt<bits.toString().length(); cpt += K_BITS )
		{
			sMot += this.applicationCodage( bits.sousMot(cpt, cpt+K_BITS) ).toString();
		}

		return MotBinaire.fabrique(sMot);
	}
	
	/**
		* @return Retourne true si le code a un problème et false si le code n'as pas de problème. 
	*/
	public boolean aProbleme(MotBinaire bits)
	{
		// Vérifie que le mot binaire bits n'est pas vide
		if ( bits == null || bits.toString().equals("") )
		{
			System.out.println("Aucun message à détecter si il y a un problème");
			return false;
		}

		for(int cpt=0; cpt<bits.toString().length(); cpt += CodeLineaireK3N5.NB_BITS )
		{
			MotBinaire mMotBinaire = MotBinaire.fabrique( bits.toString().substring(cpt, cpt+NB_BITS) );
			MotBinaire mTest = this.applicationCodage( bits.sousMot(cpt, cpt+K_BITS) );

			if ( ! (mMotBinaire.toString().equals(mTest.toString()) ) )
			{
				return true;
			}
		}

		return false;
	}

	/**
		* @return À un mot binaire de B3 retourne le mot binaire de B5 calculé à l’aide de la définition de ϕ.
		* @param bits : Le mot binaire de B3.
	*/
	public MotBinaire applicationCodage(MotBinaire bits)
	{
		// Vérifie que le mot binaire bits n'est pas vide
		if ( bits == null || bits.toString().equals("") )
		{
			System.out.println("Application de codage invalide");
			return null;
		}

		final char VAL = '1';

		// Les définitions de ϕ
		MotBinaire m1 = MotBinaire.fabrique("10001"); // ϕ(100) = 10001
		MotBinaire m2 = MotBinaire.fabrique("01010"); // ϕ(010) = 01010 
		MotBinaire m3 = MotBinaire.fabrique("00111"); // ϕ(001) = 00111

		// Le résultat à retourner
		MotBinaire mResultat = MotBinaire.fabrique("00000"); // ϕ(000) = 00000

		if ( bits.toString().charAt(0) == VAL )
		{
			mResultat = MotBinaire.addition(mResultat, m1);
		}
		
		if ( bits.toString().charAt(1) == VAL )
		{
			mResultat = MotBinaire.addition(mResultat, m2);
		}

		if ( bits.toString().charAt(2) == VAL )
		{
			mResultat = MotBinaire.addition(mResultat, m3);
		}


		return mResultat;
	}


		
	/**
		* @param args
	*/
	public static void main(String[] args)
	{
		
	}
}
