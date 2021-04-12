/**
	*	Classe CodeDetecteur
	* 	@author : -
*/

// Au préalable, compiler avec l'encodage utf8 : javac -encoding utf8 CodeDetecteur.java

public abstract class CodeDetecteur extends Transmetteur
{
	/*---------------*/
	/* Les attributs */
	/*---------------*/
	
	/**
		* Constructeur CodeDetecteur
		* @param n : la longueur du mot de code.
		* @param k : le nombre de bits d'information.
	*/
	public CodeDetecteur(int n, int k)
	{
		super(n, k);
	}
	
	/**
		* @return Retourne le mot binaire sécurisé.
		* @param bits : le mot binaire à sécuriser.
	*/
	public abstract MotBinaire securiser(MotBinaire bits);
	
	/**
		* @return Retourne true si le code a un problème et false si le code n'as pas de problème. 
	*/
	public abstract boolean aProbleme(MotBinaire bits);
	
	/**
		* @param args
	*/
	public static void main(String[] args)
	{
		
	}
}
