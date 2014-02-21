import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner clavier = new Scanner(System.in);
		System.out.println("Entrez un chiffre , -1 pour terminer");
		final int nbMaximumEntries = 100;
		int integers[] = new int[nbMaximumEntries];
		int nextIndex = 0; 
		do
		{
			try
			{
				int newValue = clavier.nextInt();
				if (newValue == -1)
					break;
				integers[nextIndex++] = newValue;
			}
			catch(InputMismatchException e)
			{
				System.out.println("Entrée '" + clavier.next() + "' non valide");				
			}
		}
		while (nextIndex < nbMaximumEntries);
//		int integers[] = {4, 6, 8, 7, 4, 3};
//		int nextIndex = integers.length;
		int sum = 0;
		int nbElements = 0;
		for (int i = 0; i < nextIndex; ++i)
		{
			if (integers[i] % 2 == 0)
			{
				sum += integers[i];
				++nbElements;
			}
		}
		if (nbElements > 0)
		{
			System.out.println("Moyenne " + (float)sum / nbElements +
					"(" + sum + "/" + nbElements+ ")");
		}
		else
		{
			System.out.println("Pas de chiffres entrés");		
		}
		displayArray(integers, nextIndex);
		Arrays.sort(integers, 0, nextIndex);
//		for (int i = 0; i < nextIndex - 1; ++i)
//		{
//			for (int j = i; j < nextIndex; ++j)
//			{
//				if (integers[i] > integers[j])
//				{
//					int intermediary = integers[j];
//					integers[j] = integers[i];
//					integers[i] = intermediary;
//				}
//			}
//		}
		displayArray(integers, nextIndex);
		if (nextIndex > 1)
		{
			System.out.println("Plus petit " + integers[0] + " plus grand " + integers[nextIndex-1]);
		}
	}
	private static void displayArray(int array[], int size) {
		System.out.print("[");
		for (int i = 0; i < size; ++i) {
			if (i != 0)
				System.out.print(", ");
			System.out.print(array[i]);
		}
		System.out.println("]");
	}


}
