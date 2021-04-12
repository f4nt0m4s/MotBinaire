/**
	*	Classe CodeCorrecteur
	* 	@author : -
*/

// Au préalable, compiler avec l'encodage utf8 : javac -encoding utf8 CodeCorrecteur.java

public abstract class CodeCorrecteur extends Transmetteur
{
	/*---------------*/
	/* Les attributs */
	/*---------------*/
	
	/**
		* Constructeur CodeCorrecteur
		* @param n : la longueur du mot de code.
		* @param k : le nombre de bits d'information.
	*/
	public CodeCorrecteur(int n, int k)
	{
		super(n, k);
	}
	/**
		* @return Retourne un mot binaire corrigé.
		* @param bits : le mot binaire à corriger 
	*/
	public abstract MotBinaire corriger(MotBinaire bits);
	
	/**
		* @param args
	*/
	public static void main(String[] args)
	{
		
	}
}
