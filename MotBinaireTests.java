/**
 * Classe pour tester MotBinaire
 * @author  bruno LEGRIX
 * @version 2018-09-19
 */

public class MotBinaireTests
{
	public static void main(String[] arg)
	{
		// variables
		MotBinaire   m,m1,m2,m3,m4;
		String       test         ;
		int          n            ;
		MotBinaire[] ens          ;
	
		test="1ère fabrique et toString (10110100): ";
		int[] t = new int[]{1,0,1,1,0,1,0,0};
		m1 = MotBinaire.fabrique(t);
		System.out.println(test+m1.toString());

		test="intégrité de MotBinaire             ? ";
		t[0] = 5;
		System.out.println(test+m1.toString());

		test="recopie de MotBinaire               ? ";
		m2 = new MotBinaire(m1);
		System.out.println(test+m2.toString());
		
		test="le poids et le nombre de bits       : ";
		System.out.println(test+m1.poids()+" "+m1.nbBits());
		
		test="On vérifie bien l'indice dans get   : ";
		System.out.println(test+m1.get(0)+" "+m1.get(8)+" "+m1.get(-1));
		
		test="On vérifie bien l'indice dans set   : ";
		m1.set(0,0);
		m1.set(8,0);
		m1.set(-1,0);
		System.out.println(test+m1.toString());

		test="on met un vrai bit dans set         ? ";
		m1.set(0,2);
		m1.set(1,-1);
		m1.set(2,5);
		System.out.println(test+m1.toString());

		test="2nd fabrique et toString (10110100) : ";
		m3 = MotBinaire.fabrique("10110100");
		System.out.println(test+m3.toString());

		test="equals                  ";
		System.out.println(test+"(m1===null) : "+m1.equals(null));
		System.out.println(test+"(m1===m1)   : "+m1.equals(m1  ));
		System.out.println(test+"(m1===m2)   : "+m1.equals(m2  ));
		System.out.println(test+"(m1===m3)   : "+m1.equals(m3  ));
		System.out.println(test+"(m2===m3)   : "+m2.equals(m3  ));

		test="addition                ";
		m4 = MotBinaire.fabrique("101101");
		System.out.println(test+"(m1++m1)    : "+MotBinaire.addition(m1,m1));
		System.out.println(test+"(m1++m2)    : "+MotBinaire.addition(m1,m2));
		System.out.println(test+"(m1++null)  : "+MotBinaire.addition(m1,null));
		System.out.println(test+"(m1++m4)    : "+MotBinaire.addition(m1,m4));

		test="distance                ";
		System.out.println(test+"d(m1,m1)    : "+m1.distance(m1));
		System.out.println(test+"d(m1,m2)    : "+m1.distance(m2));
		System.out.println(test+"d(m1,null)  : "+m1.distance(null));
		System.out.println(test+"d(m1,m4)    : "+m1.distance(m4));

		test="sousMot                 ";
		System.out.println(test+"(01)        : "+m1.sousMot(1,3));
		System.out.println(test+"(00110100)  : "+m1.sousMot(0,8));
		System.out.println(test+"(null)      : "+m1.sousMot(-1,3));
		System.out.println(test+"(null)      : "+m1.sousMot(1,9));


		// genererMot
		n   = 3;
		ens = MotBinaire.genererMots(n); 
		System.out.println();
		System.out.println("L'ensemble des mots de 3 bits");
		for(int i=0; i<ens.length; i++)
			System.out.print(ens[i]+" ");
		System.out.println();

		System.out.println();
		System.out.println("addition binaire des mots binaires précédents");
		System.out.print(String.format("%"+n+"s"," + ")+" ");
		for(int i=0; i<ens.length; i++)
			System.out.print(ens[i]+" ");
		System.out.println();
		for(int i=0; i<ens.length; i++)
		{
			System.out.print(ens[i]+" ");
			for(int j=0; j<ens.length; j++)
			{
				m = MotBinaire.addition(ens[i],ens[j]);
				System.out.print(m+" ");
			}
			System.out.println();
		}

		System.out.println();
		System.out.println("poids des mots binaires précédents");
		for(int i=0; i<ens.length; i++)
			System.out.print(ens[i]+" ");
		System.out.println();
		for(int i=0; i<ens.length; i++)
			System.out.print(String.format("%"+n+"s",ens[i].poids())+" ");
		System.out.println();

		System.out.println();
		System.out.println("distances des mots binaires précédents");
		System.out.print(String.format("%"+n+"s"," d ")+" ");
		for(int i=0; i<ens.length; i++)
			System.out.print(ens[i]+" ");
		System.out.println();
		for(int i=0; i<ens.length; i++)
		{
			System.out.print(ens[i]+" ");
			for(int j=0; j<ens.length; j++)
				System.out.print(String.format("%"+n+"s",ens[i].distance(ens[j]))+" ");
			System.out.println();
		}
	}
}

