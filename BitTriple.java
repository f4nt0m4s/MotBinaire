/**
	*	Classe BitTriple
	* 	@author : -
*/

// Au préalable, compiler avec l'encodage utf8 : javac -encoding utf8 BitTriple.java

public class BitTriple extends CodeCorrecteur
{
	/*---------------*/
	/* Les attributs */
	/*---------------*/
	private static final int NB_BITS = 3; // longueur du mot de code
	private static final int K_BITS = 1; // nombre de bits d'information


	/**
		* Constructeur BitTriple
	*/
	public BitTriple()
	{
		super(BitTriple.NB_BITS, BitTriple.K_BITS);
	}
		
	/**
		* @return Retourne le mot binaire sécurisé.
		* @param bits : le mot binaire à sécuriser.
	*/
	public MotBinaire securiser(MotBinaire bits)
	{
		String message = bits.toString().replaceAll("0","000").replaceAll("1","111");
		return MotBinaire.fabrique(message);
	}
	
	/**
		* @return Retourne le mot binaire corrigé.
		* somme des trois : bits 
			* si somme <= 1 c egal à 0
			* sinon égal à  
	*/
	public MotBinaire corriger(MotBinaire bits)
	{
		String sBinaire = new String();

		// Découpe le mot binaire en trois bits
		for(int cpt=0; cpt<bits.toString().length(); cpt+=BitTriple.NB_BITS)
		{
			String sDecoupe = bits.sousMot(cpt, cpt+BitTriple.NB_BITS).toString();
			int somme = 0;
			for(int cpt2=0; cpt2<sDecoupe.toString().length(); cpt2++)
			{
				somme += Integer.parseInt( Character.toString(sDecoupe.charAt(cpt2)) );
			} 
			
			int bitReccurenceSuperieur = 0;
			bitReccurenceSuperieur = BitTriple.getCptBitSuperieur(sDecoupe);
			
			int bitReccurenceInferieur = -1;
			if ( bitReccurenceSuperieur != -1 )
			{
				bitReccurenceInferieur = (bitReccurenceSuperieur == 0) ? 1 : 0;
				sBinaire += sDecoupe.replaceAll( Integer.toString(bitReccurenceInferieur), Integer.toString(bitReccurenceSuperieur) );
			}
		}
		
		return MotBinaire.fabrique(sBinaire);
	}
	
	/**
		* @return Compte le nombre d'occurence d'un bit et retourne le bit où l'occurence est le plus supérieur.
	*/
	private static int getCptBitSuperieur(String mot)
	{
		if ( mot.length() <= 0 || mot.equals("") )
		{
			return 0;
		}

		int cptBit = -1;

		int nbBitZero 	= mot.replaceAll("1", "").length();
		int nbBitUn 	= mot.replaceAll("0", "").length();

		if ( nbBitZero > nbBitUn )
		{
			cptBit = 0;
		}
		else
		{
			cptBit = 1;		
		}

		return cptBit;
	}
		
	/**
		* @param args
	*/
	public static void main(String[] args)
	{
		
	}
}
