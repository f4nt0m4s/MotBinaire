/**
	*	Classe BitParite
	* 	@author : -
*/

// Au préalable, compiler avec l'encodage utf8 : javac -encoding utf8 BitParite.java


// ⊕ : signifie addition binaire sans retenue 
	// 0+0=0, 0+1=1, 1+0=1, 1+1=0

// Ajout d’un bit de parité (r = 1) à un message de longueur k = 2, au mot de 2 bits ab, on associe le mot abc où c = a ⊕ b
// Exemples : 
	// 00 -> 000, 01 -> 001, 10 -> 101, 11 -> 110

public class BitParite extends CodeDetecteur
{
	/*---------------*/
	/* Les attributs */
	/*---------------*/
	private static final int NB_BITS = 3; // longueur du mot de code
	private static final int K_BITS = 2; // nombre de bits d'information

	/**
		* Constructeur BitParite définit par défaut
	*/
	public BitParite()
	{
		super(NB_BITS, K_BITS);
	}
		
	/**
		* Ajout du bit de parité c au mot binaire ab.
		* @return Retourne le mot binaire sécurisé.
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
			MotBinaire[] tabBit = new MotBinaire[K_BITS];

			int indBit = 0;
			for(int cptBit=cpt; cptBit<(cptBit+K_BITS) && indBit<K_BITS; cptBit++)
			{
				tabBit[indBit++] = MotBinaire.fabrique( Character.toString( bits.toString().charAt(cptBit) ) );
			}

			MotBinaire bitA = tabBit[0];
			MotBinaire bitB = tabBit[1];
			MotBinaire bitC = MotBinaire.addition(bitA, bitB);

			sMot += bitA.toString() + bitB.toString() + bitC.toString();
			
		}
		return MotBinaire.fabrique(sMot);
	}
	
	/**
		* @return Retourne true si le code a un problème et false si le code n'as pas de problème. 
	*/
	public boolean aProbleme(MotBinaire bits)
	{
		MotBinaire[] tabBit = new MotBinaire[BitParite.NB_BITS];
		int indBit;
		for(int cpt=0; cpt<bits.toString().length(); cpt += BitParite.NB_BITS )
		{
			indBit = 0;

			for(int cptBit=cpt; cptBit<(cptBit+BitParite.NB_BITS) && indBit<BitParite.NB_BITS; cptBit++)
			{
				tabBit[indBit++] = MotBinaire.fabrique( Character.toString( bits.toString().charAt(cptBit) ) );
			}

			if ( ! ( MotBinaire.addition( tabBit[0], tabBit[1] ).toString().equals( tabBit[2].toString() ) ) )
			{
				return true;
			}
		}

		return false;
	}
	
	/**
		* @param args
	*/
	public static void main(String[] args)
	{
		
	}
}
