package wyszukiwarka_najpopularniejszych_słow;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
 * 
    Zaimportuj do projektu bibliotekę jsoup, możesz ją pobrać z adresu: https://jsoup.org/download.
    Wyszukaj w popularnych serwisach internetowych nagłówków artykułów, a następnie zapisz pojedyncze słowa w nich występujące do pliku
    o nazwie popular_words.txt. Przykład pobrania tytułów z tagu html span z atrybutem class o wartości title

Connection connect = Jsoup.connect("http://www.onet.pl/");
try {
    Document document = connect.get();
    Elements links = document.select("span.title");
    for (Element elem : links) {
        System.out.println(elem.text());
    }
} catch (IOException e) {
    e.printStackTrace();
}

    Wywołaj pobieranie dla wybranych serwisów internetowych.
    Wczytaj utworzony plik popular_words.txt i na jego podstawie utwórz plik most_popular_words.txt, 
    który zawierać będzie 10 najbardziej popularnych słów.
    Utwórz tablicę elementów wykluczonych np. i, lub , ewentualnie pomiń wszystkie elementy 3-znakowe.
 *
 */

public class Most_popular_words {

	public static void main(String[] args) {
		popularWords();

	}

	public static void popularWords() {
		Path path = Paths.get("src/wyszukiwarka_najpopularniejszych_słow/popular_words.txt");
		Connection connect = Jsoup.connect("http://www.onet.pl/");
		List<String>popularWords = new ArrayList<>();
		List<String>exludedWords = new ArrayList<>();
		String[] excluded = {"i", "span", "class=", "lub", "ale", "nie", "tak", "tile", ">", "<", "class", "\"", ".", "/", "jak", "że", "albo", "w", "z", "pod", "nad", "na"}; 
		exludedWords.addAll(Arrays.asList(excluded));

		try {
			Document document = connect.get();
			Elements links = document.select("span.title");
			for (Element elem : links) {
				popularWords.add(elem.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Files.write(path, popularWords, StandardOpenOption.CREATE);
		} catch (IOException e) {
			System.out.println("No such file.");;
		}
		String allWords = "";
		try {
			BufferedReader reader  = Files.newBufferedReader(path);
			String line  = null;
			while((line = reader.readLine()) !=null){
				allWords += line;
			}
			System.out.println(allWords);
		} catch (IOException e) {
			System.out.println("File does not  exist. Check path");
		}
		StringTokenizer divider = new StringTokenizer(allWords);
		while(divider.hasMoreTokens()) {
			popularWords.add(divider.nextToken());
		}
		for(int i = 0; i<exludedWords.size(); i++) {
			for(int j = 0; j<popularWords.size();j++) {
				if(popularWords.get(j).equals(exludedWords.get(i))) {
					popularWords.remove(j);
					//j--;
				}

			}
		}
		for(int i = 0; i<popularWords.size();i++) {
			System.out.println(popularWords.get(i));
		}
	}

}
