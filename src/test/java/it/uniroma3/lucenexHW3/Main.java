package it.uniroma3.lucenexHW3;

import java.io.IOException;
import java.util.Scanner;

import com.google.gson.JsonSyntaxException;

public class Main {

	final static String JSON_PATH = "Resources/tables.json";

	public static void main(String[] args) throws IOException {

		System.out.println("----------------------------------------------------------------------");
		System.out.println("-------------------Benvenuto cosa vuoi fare?--------------------------");
		System.out.println("----------------------------------------------------------------------");

		System.out.println("1. Per salvare le statistiche del database digita 1 e invio          -");            
		System.out.println("                                                                     -");
		System.out.println("2. Per inizializzare Lucene per risolvere il problema del            -\n"
				+ "   Joinable Table Search tramite l'algoritmo \"MergeList\"             -"
				+ "\n   digita 2 e invio                                                  -");
		System.out.println("                                                                     -");
		System.out.println("3. Per uscire digita 0 e invio                                       -");

		System.out.println("----------------------------------------------------------------------");

		Scanner scan = new Scanner(System.in);
		String s = scan.nextLine();

		if(s.equals("1")) {
			Statistics statistic = new Statistics(JSON_PATH);
			statistic.run();

		}
		/*
		if(s.equals("2")) {
			DocCreator lucene = new DocCreator();
			lucene.run();
		}

		if(s.equals("0")) {
			System.exit(0);
		}
		 */










	}

}
