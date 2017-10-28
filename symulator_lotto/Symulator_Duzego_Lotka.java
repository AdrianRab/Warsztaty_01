package symulator_lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/*
 * Jak wszystkim wiadomo, LOTTO to gra liczbowa polegająca na losowaniu 6 liczb z zakresu 1-49. Zadaniem gracza jest poprawne wytypowanie 
 * losowanych liczb. Nagradzane jest trafienie 3, 4, 5 lub 6 poprawnych liczb.

Napisz program, który:

   zapyta o typowane liczby, przy okazji sprawdzi następujące warunki:
   czy wprowadzony ciąg znaków jest poprawną liczbą,
   czy użytkownik nie wpisał tej liczby już poprzednio,
   czy liczba należy do zakresu 1-49,
   po wprowadzeniu 6 liczb, posortuje je rosnąco i wyświetli na ekranie,
   wylosuje 6 liczb z zakresu i wyświetli je na ekranie,
   poinformuje gracza, czy trafił przynajmniej "trójkę".

Aby wylosować 6 liczb z zakresu 1-49 bez powtórzeń możemy utworzyć tablicę z wartościami 1-49, wymieszać jej zawartość i pobrać 
pierwsze 6 elementów.

Poniższy kod powinien Ci pomóc:

Integer[] arr = new Integer[49];
for (int i = 0; i < arr.length; i++) {
	arr[i] = i;
}
System.out.println(Arrays.toString(arr));
Collections.shuffle(Arrays.asList(arr));
System.out.println(Arrays.toString(arr));

Możesz również losować liczby z określonego zakresu (sprawdź w snippetach jak to wykonać) - jeżeli wybierzesz takie rozwiązanie, 
pamiętaj o sprawdzaniu czy dana wartość nie została wcześniej wylosowana.
 */

public class Symulator_Duzego_Lotka {

	public static void main(String[] args) {
		System.out.println(wynik_Lotto());
	}
	static String wynik_Lotto() {
		Scanner scan = new Scanner(System.in);
		List<Integer>selectedNbrs = new ArrayList<>();
		List<Integer>drawNumbers = new ArrayList<>();
		int matchedNumbers = 0;
		String result = "";
		System.out.println("Select 6 numbers from 1 to 49. Numbers cannot duplicate.");
		int counter = 0;
		while(counter<6){
			try{
				int chosenNbr = scan.nextInt();
				if(chosenNbr>0 && chosenNbr<50 && !(selectedNbrs.contains(chosenNbr))) {
					selectedNbrs.add(chosenNbr);
				}else {
					counter--;
					System.out.println("Provide number from range 1-49. Remember to avoid duplicates.");
				}
			}catch(NumberFormatException e) {
				System.out.println("Insert integer");
				counter--;
			}
			counter++;
		}
		scan.close();
		Collections.sort(selectedNbrs);
		System.out.println("Selected numbers are: " + selectedNbrs.toString());
		Integer[] arr = new Integer[49];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i+1;
		}
		System.out.println(Arrays.toString(arr));
		Collections.shuffle(Arrays.asList(arr));
		System.out.println(Arrays.toString(arr));

		for(int i = 0; i<6; i++) {
			drawNumbers.add(arr[i]);
		}
		for(int i = 0; i<selectedNbrs.size(); i++){
			if(selectedNbrs.contains(drawNumbers.get(i))){
				counter++;
			}
		}
		if(counter>=3) {
			result = "Congratulations, you have selected 3 numbers.";
		}else if(counter>=4) {
			result = "Congratulations, you have selected 4 numbers.";
		}else if(counter>=5){
			result = "Congratulations, you have selected 5 numbers.";
		}else if(counter>=6) {
			result = "Congratulations, you have won the main prize!!";
		}else {
			result = "Unfortunately you have not won. Please try again.";
		}
		return result;
	}
}
