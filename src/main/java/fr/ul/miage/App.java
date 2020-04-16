package fr.ul.miage;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * 
 * <p>Application Météo</p>
 * 
 * @author Anne-Laure CHARLES
 * @version 1.0
 * 
 */

public class App extends Application{

	/*public static void main(String[] args) {
		// TODO Auto-generated method stub

		//paramètres
		String ville = "Nancy";
		String pays = "fr";
		
		//options 
		Options options = new Options();
		Option city = new Option("v", "ville", true, "nom de la ville");
		Option country = new Option("p", "pays", true, "nom du pays");
		options.addOption(city);
		options.addOption(country);
		
		//parser la ligne de commande
		CommandLineParser parser = new DefaultParser();
		try {
			CommandLine line = parser.parse(options, args);
			if (line.hasOption("v")) {
				ville = line.getOptionValue("v");
			}
			if(line.hasOption("p")) {
				pays = line.getOptionValue("p");
			}
		} catch (ParseException exp) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("meteo", options);
			System.exit(1);
		}
		
		//traitement 
		MeteoClient cl = new MeteoClient(ville,pays);
		//This allow to get the json schema
		System.out.println(cl.getJsonWeatherByCityName());
		
		Result res = cl.getWeatherByCityName();
		if (res != null) {
			String v = res.getName();
			float t = res.getMain().getTemp();
			System.out.printf("Il fait %.1f °C à %s%n", t-273.15f, v);
		} else {
			System.out.println("Impossible de trouver la température.");
		}
	}*/
	
	/** 
     * <b>Start</b> 
     * 
     * @param primaryStage
     *     permet d'afficher, modifier le contenu de l'objet scene (qui contient le noeud parent)
     */ 
	
	public void start(Stage primaryStage) {
		Parent root =  null;
		try {
			root = FXMLLoader.load(getClass().getResource("view/Window.fxml"));
		} catch (IOException | NullPointerException e) {
			e.printStackTrace();
			Platform.exit();
            System.exit(0);
		}
		Scene scene = new Scene(root);
		primaryStage.setTitle("TP3 - Programmation Objet Avancée");
		primaryStage.getIcons().add(new Image("images/logo.jpg"));
		scene.getStylesheets().add(getClass().getResource("/app.css").toExternalForm());
		//not allow to modify the window 
		primaryStage.setResizable(false);
		//To change the visibility to false
		primaryStage.hide();
		//for the responsive window's
		primaryStage.sizeToScene();
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		//Démarrer le menu
		launch(args);
	}
}
