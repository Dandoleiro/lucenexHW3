package it.uniroma3.lucenexHW3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class Statistics {

	private String jsonPath;
	private int numberOfTables;
	private int numberOfRows;
	private int numberOfColumns;
	private int numberOfNullValues;
	private	HashMap<Integer,Integer> distribuzioneRighe = new HashMap<Integer, Integer>();
	private	HashMap<Integer,Integer> distribuzioneColonne = new HashMap<Integer, Integer>();
	private HashMap<Integer,Integer> distribuzioneValoriDistinti = new HashMap<Integer,Integer>();


	public Statistics(String jsonPath) {
		this.jsonPath = jsonPath;		
	}

	public void run() throws JsonSyntaxException, IOException {

		BufferedReader br = new BufferedReader(new FileReader(jsonPath));
		String line;


		while ((line=br.readLine()) != null) {
			numberOfTables++;

			JsonObject table = JsonParser.parseString(line).getAsJsonObject();
			JsonObject maxDimensions = table.get("maxDimensions").getAsJsonObject();
			numberOfRows += maxDimensions.get("row").getAsInt();
			numberOfColumns += maxDimensions.get("column").getAsInt();

			calcolaNumeroValoriNulli(table);
			calcolaDistribuzioneRigheColonne(table);
			calcolaDistribuzioneValoriDistinti(table);
			stampaStatistiche();

			
		}
		br.close();


	}

	public void stampaStatistiche() {
		System.out.println("Numero tot di tabelle: "+numberOfTables);
		System.out.println("Numero tot di colonnein tutte le tabelle: "+numberOfColumns);
		System.out.println("Numero tot di righe in tutte le tabelle: "+numberOfRows);
		System.out.println("Numero tot di valori nulli in tutte le tabelle: "+numberOfNullValues);
		
		
		System.out.println("size distribuzione Righe"+distribuzioneRighe.size());
	}

	public void calcolaDistribuzioneValoriDistinti(JsonObject table) {

		JsonArray celle = (JsonArray) table.get("cells");
		ArrayList<String> valoriDistintiPerTabella = new ArrayList<String>();

		for (Object c : celle) {
			JsonObject cella = (JsonObject) c;
			if(!(valoriDistintiPerTabella.contains(cella.get("cleanedText").getAsString()))) {  //se la lista non contiene quel valore
				valoriDistintiPerTabella.add(cella.get("cleanedText").getAsString());
			}
		}

		int dimensioneLista = valoriDistintiPerTabella.size();
		if(this.distribuzioneValoriDistinti.containsKey(dimensioneLista)) {
			this.distribuzioneValoriDistinti.put(dimensioneLista, this.distribuzioneValoriDistinti.get(dimensioneLista)+1);
		} else {
			this.distribuzioneValoriDistinti.put(dimensioneLista, 1);
		}
	}


	public void calcolaDistribuzioneRigheColonne( JsonObject table) {

		JsonObject maxDimensions = table.get("maxDimensions").getAsJsonObject();
		if(this.distribuzioneRighe.containsKey(maxDimensions.get("row").getAsInt())) {
			this.distribuzioneRighe.put(maxDimensions.get("row").getAsInt(), this.distribuzioneRighe.get(maxDimensions.get("row").getAsInt()) + 1);
		} else {
			this.distribuzioneRighe.put(maxDimensions.get("row").getAsInt(), 1);
		}
		if(this.distribuzioneColonne.containsKey(maxDimensions.get("column").getAsInt())) {
			this.distribuzioneColonne.put(maxDimensions.get("column").getAsInt(), this.distribuzioneColonne.get(maxDimensions.get("column").getAsInt()) + 1);
		} else {
			this.distribuzioneColonne.put(maxDimensions.get("column").getAsInt(), 1);
		}



	}

	public void calcolaNumeroValoriNulli(JsonObject table)  {

		JsonArray celle = (JsonArray) table.get("cells");

		for (Object c : celle) {
			JsonObject cella = (JsonObject) c;
			if(cella.get("cleanedText").getAsString().equals("")) {
				numberOfNullValues++;
			}
		}
	}

	public void closeBuffer() {

	}
}
