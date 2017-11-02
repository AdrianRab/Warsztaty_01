package kostka_do_gry;

import java.time.chrono.IsoEra;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 * W grach planszowych i papierowych RPG używa się wielu rodzajów kostek do gry, nie tylko tych dobrze znanych, sześciennych.
 *  Jedną z popularniejszych kości jest np. kostka dziesięciościenna, a nawet stuścienna! Jako że w grach kośćmi rzuca się często, 
 *  pisanie za każdym razem np. "rzuć dwiema kostkami dziesięciościennymi, a do wyniku dodaj 20" byłoby nudne, trudne i marnowałoby 
 *  ogromne ilości papieru. W takich sytuacjach używa się kodu "rzuć 2D10+20".

Kod takiej kostki wygląda następująco:
xDy+z

gdzie:

    y – rodzaj kostek, których należy użyć (np. D6, D10),
    x – liczba rzutów kośćmi; jeśli rzucamy raz, ten parametr jest pomijalny,
    z – liczba, którą należy dodać (lub odjąć) do wyniku rzutów (opcjonalnie).

Przykłady:

    2D10+10: 2 rzuty D10, do wyniku dodaj 10,
    D6: zwykły rzut kostką sześcienną,
    2D3: rzut dwiema kostkami trójściennymi,
    D12-1: rzut kością D12, od wyniku odejmij 1.

Napisz funkcję, która:

    przyjmie w parametrze taki kod w postaci String,
    rozpozna wszystkie dane wejściowe:
        rodzaj kostki,
        liczbę rzutów,
        modyfikator,
    wykona symulację rzutów i zwróci wynik.

Typy kostek występujące w grach: D3, D4, D6, D8, D10, D12, D20, D100.
 */

public class Dice {

	public static void main(String[] args) {
		System.out.println("Result of your throw is: "+throwDice());

	}

	public static int throwDice() {
		Scanner userInput  = new Scanner(System.in);
		Random rand = new Random();
		int result = 0;
		int nmbrOfThrows = 0;
		int diceType = 0;
		int addValue = 0;

		System.out.println("Throw the dice. Enter xDy+z where y is type of a dice (e.g. D6, D10)"
				+ ", x - number of throws, z - number of points to be added");

		String diceThrow = "";
		while(diceThrow.equals("")) {
			diceThrow+=userInput.next();
		}
		System.out.println(diceThrow);
		userInput.close();
		if(diceThrow.startsWith("D")) {
			System.out.println("Begins with D");
			StringTokenizer ridD = new StringTokenizer(diceThrow, "D+-");

			diceType = Integer.parseInt(ridD.nextToken());
			try{
				addValue = Integer.parseInt(ridD.nextToken());
			}catch(NoSuchElementException nSEE) {
				System.out.println("No additional value available");
			}
			char[] tab = diceThrow.toCharArray();
			List<Character> charList = new ArrayList<>();
			for(int i = 0; i<tab.length; i++) {
				charList.add(tab[i]);
			}
			if(charList.contains('+')) {
				result = (rand.nextInt(diceType-1)+1) + addValue;
			}else if(charList.contains('-')) {
				result = (rand.nextInt(diceType-1)+1) - addValue;
			}else if(charList.contains('/')) {
				result = (rand.nextInt(diceType-1)+1) / addValue;
			}else if (charList.contains('*')){
				result = (rand.nextInt(diceType-1)+1) * addValue;
			}else {
				result = rand.nextInt(diceType-1)+1;
			}

			System.out.println(diceType);
			System.out.println(addValue);

		}else {
			System.out.println("Does not begins with D");	
			StringTokenizer ridD = new StringTokenizer(diceThrow, "D+-");

			nmbrOfThrows = Integer.parseInt(ridD.nextToken());
			diceType = Integer.parseInt(ridD.nextToken());
			try{
				addValue = Integer.parseInt(ridD.nextToken());
			}catch(NoSuchElementException nSEE) {
				System.out.println("No additional value available");
			}
			char[] tab = diceThrow.toCharArray();
			List<Character> charList = new ArrayList<>();
			for(int i = 0; i<tab.length; i++) {
				charList.add(tab[i]);
			}
			System.out.println(nmbrOfThrows);
			System.out.println(diceType);
			System.out.println(addValue);
			int counter = 0;
			int tempresult = 0;
			while(counter < nmbrOfThrows) {
				tempresult +=rand.nextInt(diceType-1)+1;
				counter++;
			}
			if(charList.contains('+')) {
				result = tempresult + addValue;
			}else if(charList.contains('-')) {
				result = tempresult - addValue;
			}else if(charList.contains('/')) {
				result = tempresult / addValue;
			}else if(charList.contains('*')) {
				result = tempresult * addValue;
			}else {
				result = tempresult;
			}
		}
		return result;
	}

}
