package gra_w_zgadywanie_liczb_2;

import java.util.Scanner;

public class Zgadnij_numer_2 {

	public static void main(String[] args) {
		selectedNumber();

	}
	static void selectedNumber() {
		System.out.println("Pomyśl liczbę od 0 do 1000 a ja ją zgadnę w max. 10 ruchach.");
		int min = 0;
		int max = 1000;
		String odpowiedz = "";
		String result = "";
		Scanner scan = new Scanner(System.in);
		while(true) {
			int guess = ((max-min)/2)+min;
			System.out.println("Zgaduję, że to: " + guess);
			System.out.println("Zgadłem?");
			odpowiedz = scan.next();

			if(odpowiedz.equals("tak") || odpowiedz.equals("yes") || odpowiedz.equals("y")){
				result = "Wygrałem!";
				System.out.println(result);
				scan.close();
				break;
			}else {
				System.out.println("Za dużo?");
				odpowiedz = scan.next();
				if(odpowiedz.equals("tak") || odpowiedz.equals("yes") || odpowiedz.equals("y")){
					max = guess;
				}else {
					System.out.println("Za mało?");
					odpowiedz = scan.next();
					if(odpowiedz.equals("tak") || odpowiedz.equals("yes") || odpowiedz.equals("y")){
						min = guess;
					}else {
						System.out.println("Nie oszukuj!");
					}
				}

			}
		}
	}	
}
