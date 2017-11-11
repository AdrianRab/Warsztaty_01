package wyszukiwarka_najpopularniejszych_słow;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
		Path destPath = Paths.get("src/wyszukiwarka_najpopularniejszych_słow/most_popular_words.txt");
		Connection connect = Jsoup.connect("http://www.onet.pl/");
		List<String>popularWords = new ArrayList<>();
		List<String>exludedWords = new ArrayList<>();
		List<Integer>countOccurr = new ArrayList<>();
		List<String>finalList = new ArrayList<>();
		List<String>top10 = new ArrayList<>();
		String[] excluded = {"i", "span", "class=", "lub", "ale", "nie", "tak", "title", ">", "<", "class", "\"", ".", "/", "jak", "że", "albo", "w", "z",
				"pod", "nad", "na", "W", "a", "się", "w", "Z", "z", "Nad", "Pod", "dla", "Dla", "do", "Do", "O", "o", "class", "</span><span", "to", "Od", "od",
				"Jak", "jak", "<span>", "-", "</svg></span><span", "</span>", "<svg", "<use", "za",
				"xlink:href=\"#videoIconSmall\"></use>","po", "jest", "czy", "Czy", "co", "class=\"title\">","class=\"itemShortVideo\">",
				"class=\"itemMediaGallery\">", "xlink:href=\"#photoIconSmall\"></use>", "są", "class=\"title\"", "class=\"itemMediaGallery\"",
				"class=\"itemShortVideo\"", "/span", "/svg", "itemMediaGallery", "#photoIconSmall", "jej", "Jest", "ich"}; 
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
		} catch (IOException e) {
			System.out.println("File does not  exist. Check path");
		}
		popularWords.clear();
		String allWords2 = allWords.replace("<", " ").replace(">", " ").replace(".", " ").replace(",", " ").replace("\"", " ").
				replace(":", " ").replace("?", " ").replace("!", " ");
		//System.out.println(allWords2);

		StringTokenizer divider = new StringTokenizer(allWords2);
		while(divider.hasMoreTokens()) {
			popularWords.add(divider.nextToken());
		}
		for(int i = 0; i<exludedWords.size(); i++) {
			for(int j = 0; j<popularWords.size();j++) {
				if(popularWords.get(j).equals(exludedWords.get(i))) {
					popularWords.remove(j);
				}

			}
		}
		Collections.sort(popularWords);
		for(int i = 0; i<popularWords.size();i++) {
			int occurrences = Collections.frequency(popularWords, popularWords.get(i));
			countOccurr.add(occurrences);
		}
		/* adds values to the list only if they already not occurs in the list*/
		for(int i = 0; i<popularWords.size(); i++) {
			if(!finalList.contains("Number of occurences: "+countOccurr.get(i) + " word: "+ popularWords.get(i))) {
				finalList.add("Number of occurences: "+countOccurr.get(i) + " word: "+ popularWords.get(i));
			}
		}
		Collections.sort(finalList);
		Collections.reverse(finalList);
		//		for(int i = 0; i<popularWords.size();i++) {
		//			System.out.println(popularWords.get(i));
		//		}
		//		for(int i = 0; i<countOccurr.size();i++) {
		//			System.out.println(countOccurr.get(i));
		//		}
		//		for(int i = 0; i<finalList.size();i++) {
		//			System.out.println(finalList.get(i));
		//		}
		for(int i = 0;i<10; i++) {
			top10.add(finalList.get(i));
		}

		try {
			Files.write(destPath, top10, StandardOpenOption.CREATE);
		} catch (IOException e) {
			System.out.println("No such file.");;
		}
	}
}
