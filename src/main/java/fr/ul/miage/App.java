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
		//WeatherController wc = new WeatherController();
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
		/*primaryStage.setScene(scene);
		wc.loadDataT();
		primaryStage = (Stage) root.getScene().getWindow();
		primaryStage.setScene(scene);
		loadDataT(root, primaryStage);*/
		//wc.loadDataT();
		//primaryStage = (Stage) root.getScene().getWindow();
		scene.setRoot((Parent) root);
		primaryStage.setScene(scene);
		/*wc.loadDataT();
		scene.setRoot((Parent) root);
		scene.getWindow().sizeToScene();*/
		primaryStage.show();
		
	}
	
	//TODO load
	//Try to work
	/*public Stage loadDataT(Parent root, Stage primaryStage) {
		String path = "./src/main/resources/backup.txt"; 
		MenuBar menuBar = new MenuBar();
		try {
			FileReader reader = new FileReader(path);
			BufferedReader bufferedReader = new BufferedReader(reader);
			//Skip first line
			String line = bufferedReader.readLine();
			Menu deleteMenu = new Menu();
			//MenuItem t = new MenuItem("Bienvenue");
            while ((line = bufferedReader.readLine()) != null) {
            	MenuItem m = new MenuItem(line);
            	System.out.println("ligne : " +line);
            	deleteMenu.getItems().add(m);
            	System.out.println("ligne : " + m.getText());
            	//deleteMenu.getItems().add(t);
            	for (int i = 0; i < deleteMenu.getItems().size(); i++) {
                	System.out.println(deleteMenu.getItems().get(i).getText());
                	System.out.println("taille du menu: " +deleteMenu.getItems().size());
    			}
            }
            menuBar.getMenus().add(deleteMenu);
            reader.close();
        } catch (IOException e) {
        	e.printStackTrace();
        	System.out.println("Gros PB");
        }
		return primaryStage = (Stage) root.getScene().getWindow();
	}*/

	public static void main(String[] args) {
		//Démarrer le menu
		launch(args);
	}
}
