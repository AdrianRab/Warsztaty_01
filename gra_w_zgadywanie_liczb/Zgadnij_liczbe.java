package gra_w_zgadywanie_liczb;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/*
 * Napisz prostą grę w zgadywanie liczb. Komputer musi wylosować liczbę w zakresie od 1 do 100. Następnie:

    Zadać pytanie: "Zgadnij liczbę" i pobrać liczbę z klawiatury.
    Sprawdzić, czy wprowadzony napis, to rzeczywiście liczba i w razie błędu wyświetlić komunikat "To nie jest liczba", po czym wrócić do pkt. 1
    Jeśli liczba podana przez użytkownika jest mniejsza niż wylosowana, wyświetlić komunikat "Za mało!", po czym wrócić do pkt. 1.
    Jeśli liczba podana przez użytkownika jest większa niż wylosowana, wyświetlić komunikat "Za dużo!", po czym wrócić do pkt. 1.
    Jeśli liczba podana przez użytkownika jest równa wylosowanej, wyświetlić komunikat "Zgadłeś!", po czym zakończyć działanie programu.

Przykład:

Zgadnij liczbę: cześć
To nie jest liczba.
Zgadnij liczbę: 50
Za mało!
Zgadnij liczbę: 75
Za dużo!
Zgadnij liczbę: 63
Zgadłeś!

 */	

public class Zgadnij_liczbe {

	public static void main(String[] args) {

		System.out.println(guessNumber());

	} 
	public static String guessNumber() {
		System.out.println("I want to play a game. Guess drawn by lot number. Type integer value.");
		int generatedNumber = 0;
		int inputValue = 0;
		String result = "";
		Random rand = new Random();
		generatedNumber = rand.nextInt(100) +1; //generates numbers 1-100. +2 will be 2-101. 
		System.out.println(generatedNumber);
		Scanner scan = new Scanner(System.in);
		try{
			inputValue = scan.nextInt();
			while(!(inputValue == generatedNumber)) {
				if(inputValue< generatedNumber) {
					System.out.println("Number is too low. Try again.");
					inputValue = scan.nextInt();
				}else if(inputValue>generatedNumber) {
					System.out.println("Number is too high. Try again.");
					inputValue = scan.nextInt();
				}else {
					System.out.println("This is not an integet. Enter integer value.");
					inputValue = scan.nextInt();
				}
			}	
		}catch(InputMismatchException iME) {
			System.out.println("This is not an integet. Enter integer value.");
		}
		if(inputValue == generatedNumber) {
			result = "Congratz, you won!";
		}
		scan.close();
		return result;
	}

}

