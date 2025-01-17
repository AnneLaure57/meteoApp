package fr.ul.miage.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.ul.miage.meteo.json.Result;
import fr.ul.miage.model.MeteoClient;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class WeatherController {
	
	@FXML
	private Parent root ;
	
	@FXML
	private MenuBar mainItems;
	
	@FXML
	private Menu params;
	
	@FXML
	private Menu disp;
	
	@FXML
	private Menu listCity;
	
	@FXML
	private Menu select;
	
	@FXML
	private Menu deleteMenu;
	
	@FXML
	private MenuItem newMenu;
	
	@FXML
	private MenuItem menuTest;
	
	@FXML
	private MenuItem menuTest1;
	
	@FXML
	private MenuItem addItem;
	
	@FXML
	private MenuItem quit;
	
	@FXML
	private MenuItem stoppedI;
	
	@FXML
	private CheckMenuItem menuH;
	
	@FXML
	private CheckMenuItem menuR;
	
	@FXML
	private CheckMenuItem menuW;
	
	@FXML
	private CheckMenuItem menuSunR;
	
	@FXML
	private CheckMenuItem menuSunS;
	
	@FXML
	private CheckMenuItem menuP;
	
	@FXML
	private CheckMenuItem menuTMa;
	
	@FXML
	private CheckMenuItem menuTMi;
	
	@FXML
    private TextField city;
	
	@FXML
    private TextField country;
	
	@FXML
    private TextField refresh;
	
	@FXML
	private Label textCountry;

    @FXML
    private Label lastUpdate;

    @FXML
    private Label temperature;
    
    @FXML
    private Label alterTemp;

    @FXML
    private Label wind;

    @FXML
    private Label tempMax;

    @FXML
    private Label tempMin;
    
    @FXML
    private Label humidity;
    
    @FXML
    private Label sunrise;
    
    @FXML
    private Label sunset;
    
    @FXML
    private Label pressure;
    
    @FXML
    private Label result;
    
    @FXML
    private Label description;
    
    @FXML
    private Label cityCountry;
    
    @FXML
    private Label date2;
    
    @FXML
    private Label date3;
    
    @FXML
    private Label date4;
    
    @FXML
    private Label date5;
    
    @FXML
    private Label temp2;
    
    @FXML
    private Label temp3;
    
    @FXML
    private Label temp4;
    
    @FXML
    private Label temp5;
    
    @FXML
    private Label descrip2;
    
    @FXML
    private Label descrip3;
    
    @FXML
    private Label descrip4;
    
    @FXML
    private Label descrip5;

    @FXML
    private Button buttonVal;
    
    @FXML
    private Button buttonRef;

    @FXML
    private ImageView imgWeather;
    
    @FXML
    private ImageView imgTemp;
    
    @FXML
    private ImageView imgAlter;

    @FXML
    private ImageView imgWind;
    
    @FXML
    private ImageView imgPres;
    
    @FXML
    private ImageView imgSunset;
    
    @FXML
    private ImageView imgSunrise;
    
    @FXML
    private ImageView imgMinMax;
    
    @FXML
    private ImageView refresh_icon;
    
    @FXML
    private ImageView imgIcon2;
    
    @FXML
    private ImageView imgIcon3;
    
    @FXML
    private ImageView imgIcon4;
    
    @FXML
    private ImageView imgIcon5;
    
    @FXML
    private ImageView addIcon;
    
    @FXML
    private ImageView searchIcon;
    
    @FXML
    private ImageView cleanIcon;
    
    @FXML
    private ImageView deleteIcon;
    
    @FXML
    private ImageView stopIcon;
    
    @FXML
    private ImageView exitIcon;
    
    @FXML
    private ImageView loadIcon;
    
    private static final Logger LOG = Logger.getLogger(MeteoClient.class.getName());
    
    private MeteoClient cl;
    
    private String cityFound;
    
    private String countryFound;
    
    private String nameResearch;
    
    private Result res;
    
    private DateFormat dateFormat;
    
    private Timer timer;
    
    private boolean timerStart = false;
    
    private boolean reload = false;
    
    private String path = "backup.txt";
    
    private File file = new File(path);

	@FXML
   	public void initialize() {
		textCountry.setText("Si ville hors France,\n renseignez le pays :");
    	result.setText("Aucune recherche effectuée !");
    	lastUpdate.setText("N/A");
    	cityCountry.setText("N/A");
    	temperature.setText("N/A");
    	date2.setText("N/A");
    	date3.setText("N/A");
    	date4.setText("N/A");
    	date5.setText("N/A");
    	temp2.setText("N/A");
    	temp3.setText("N/A");
    	temp4.setText("N/A");
    	temp5.setText("N/A");
    	descrip2.setText("N/A");
    	descrip3.setText("N/A");
    	descrip4.setText("N/A");
    	descrip5.setText("N/A");
    	addIcon.setImage(new Image("images/add.png"));
    	deleteIcon.setImage(new Image("images/delete.jpg"));
    	searchIcon.setImage(new Image("images/search.png"));
    	cleanIcon.setImage(new Image("images/clean.png"));
    	stopIcon.setImage(new Image("images/stop.png"));
    	exitIcon.setImage(new Image("images/exit.png"));
    	loadIcon.setImage(new Image("images/load.png"));
    	imgIcon2.setImage(new Image("images/not_available.png"));
    	imgIcon3.setImage(new Image("images/not_available.png"));
    	imgIcon4.setImage(new Image("images/not_available.png"));
    	imgIcon5.setImage(new Image("images/not_available.png"));
    	refresh_icon.setImage(new Image("images/refresh_icon.png"));
    	loadDataA();
 
   	}
	
	@FXML
    void display(ActionEvent event) {
    	menuH.setOnAction(e -> 
    	{
	    	if (menuH.isSelected())
	    	{
	    		if (humidity.isVisible()) {
	    			LOG.info("cacher");
	    			humidity.setVisible(false);
	    			imgWeather.setVisible(false);
	    		} else {
	    			LOG.info("visible");
	    			humidity.setVisible(true);
	    			imgWeather.setVisible(true);
	    		}
	    		
	    	}
    	});
    	
    	menuR.setOnAction(e -> 
    	{
	    	if (menuR.isSelected())
	    	{
	    		if (alterTemp.isVisible()) {
	    			alterTemp.setVisible(false);
	    			imgAlter.setVisible(false);
	    			
	    		} else {
	    			alterTemp.setVisible(true);
	    			imgAlter.setVisible(true);
	    		}
	    		
	    	}
    	});
    	
    	menuW.setOnAction(e -> 
    	{
	    	if (menuW.isSelected())
	    	{
	    		if (wind.isVisible()) {
	    			wind.setVisible(false);
	    			imgWind.setVisible(false);
	    			
	    		} else {
	    			wind.setVisible(true);
	    			imgWind.setVisible(true);
	    		}
	    		
	    	}
    	});
    	
    	menuSunR.setOnAction(e -> 
    	{
	    	if (menuSunR.isSelected())
	    	{
	    		if (sunrise.isVisible()) {
	    			sunrise.setVisible(false);
	    			imgSunrise.setVisible(false);
	    			
	    		} else {
	    			sunrise.setVisible(true);
	    			imgSunrise.setVisible(true);
	    		}
	    		
	    	}
    	});
    	
    	menuSunS.setOnAction(e -> 
    	{
	    	if (menuSunS.isSelected())
	    	{
	    		if (sunset.isVisible()) {
	    			sunset.setVisible(false);
	    			imgSunset.setVisible(false);
	    			
	    		} else {
	    			sunset.setVisible(true);
	    			imgSunset.setVisible(true);
	    		}
	    		
	    	}
    	});
    	
    	menuP.setOnAction(e -> 
    	{
	    	if (menuP.isSelected())
	    	{
	    		if (pressure.isVisible()) {
	    			pressure.setVisible(false);
	    			imgPres.setVisible(false);
	    			
	    		} else {
	    			pressure.setVisible(true);
	    			imgPres.setVisible(true);
	    		}
	    		
	    	}
    	});
    	
    	menuTMa.setOnAction(e -> 
    	{
	    	if (menuTMa.isSelected())
	    	{
	    		if (tempMax.isVisible()) {
	    			tempMax.setVisible(false);
	    			
	    		} else {
	    			tempMax.setVisible(true);
	    		}
	    		
	    		hideMinMax();
	    	}
	    	
    	});
    	
    	menuTMi.setOnAction(e -> 
    	{
	    	if (menuTMi.isSelected())
	    	{
	    		if (tempMin.isVisible()) {
	    			tempMin.setVisible(false);
	    			
	    		} else {
	    			tempMin.setVisible(true);
	    		}
	    		
	    		hideMinMax();
	    	}
    	});
    }
	
	@FXML 
	void setCityResearch(ActionEvent event) {
	    MenuItem currentMenu = (MenuItem) event.getSource();
	    String currentCity = currentMenu.getText();
	    checkCityName(currentCity);
	}
	
	@FXML 
	void addCityList(ActionEvent event) {
		if (stateCity()) {
			String nameCity = city.getText();
			if (country.getText() == null || country.getText().trim().isEmpty()) {
				nameResearch = nameCity;
				newMenu = new MenuItem(nameResearch);
				addIntoDelete(nameResearch);
			} else {
				String nameCountry = country.getText();
				nameResearch = nameCity + ',' + nameCountry;
				newMenu = new MenuItem(nameResearch);
				addIntoDelete(nameResearch);
			}
	    	try {
	    		for (int i = 0; i <= select.getItems().size() ; ++i) {
	    			//If the last element isn't not the same the new insert
					if (stateMenu(select,i,nameResearch) == true) {
						//LOG.info(newMenu.getText() + " on ajoute dans select");
						select.getItems().add(newMenu);
						saveData(newMenu);
					}
				}
	    		System.out.println("Menu sélectionné : " +newMenu.getText());
	    		newMenu.setOnAction(e -> {
			    	setCityResearch(e);
			    });
			} catch (Exception e) {
				LOG.severe(newMenu.getText() + " a déjà été ajouté au menu 'Sélectionner'");
	    	}
		}
	}
	
	/** 
     * <b>stateMenu</b> 
     * 
     * permet de vérifier si le dernier élément de la liste correspond ou non au dernier ajout (permet d'éviter des doublons et d'avoir par exemple n fois Paris, FR)
     * 
     * @param menuCheck
     * 		correspond au menu, s'il s'agit du menu de sélection ou de deletion
     * @param i
     * 		correspond à l'élément i (MenuItem i) du Menu, ex : si i=0 il s'agit du menuItem 0 qui correspond à Paris, FR
     * @param newAddResearch
     * 		correspond à la ville / ville, pays que l'on souhaite ajouter au menu
     * @return statut
     * 		retourne l'état, si true, alors le dernier élément ajouté du menu est différent du nouveau à ajouter
     */ 
	
	public boolean stateMenu(Menu menuCheck,int i, String newAddResearch) {
		Boolean statut = false;
		Integer LastElm = (menuCheck.getItems().size() -1);
		if (i == LastElm) {
			//If the last element of the menu not contains the new string (new MenuItem), return true
			if(!menuCheck.getItems().get(LastElm).getText().contentEquals(newAddResearch)) {
				//LOG.info(menuCheck.getItems().get(LastElm).getText()+ " : dernier élément de la liste");
				return statut = true;
			}
		}
		return statut;
    }
	
	public void addIntoDelete(String nameCity) {
		MenuItem newMenu = new MenuItem(nameCity);
		try {
			for (int i = 0; i <= deleteMenu.getItems().size() ; ++i) {
				if (stateMenu(deleteMenu,i,nameCity) == true) {
					deleteMenu.getItems().add(newMenu);
				}
			}
			newMenu.setOnAction(e -> {
				//LOG.info("On retire " + newMenu.getText());
				for (int i = 0; i < select.getItems().size(); i++) {
					//LOG.info("Je suis sur l'onglet " + i + " qui correspond "+ select.getItems().get(i).getText());
					if(newMenu.getText().equals(select.getItems().get(i).getText())) {
						//LOG.info("je supprime l'index " + i);
						select.getItems().remove(i);
					}
				}
				deleteMenu.getItems().remove(newMenu);
				saveDataD(deleteMenu);
		    });
		} catch (Exception e) {
			LOG.severe(newMenu.getText() + " a déjà été ajouté au menu 'Supprimer'");
    	}
	}
	
	public void saveDataD(Menu menuToSave) {
		try {
            FileWriter writer = new FileWriter(file, false);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            for (int i = 0; i < menuToSave.getItems().size(); i++) {
            	bufferedWriter.write(menuToSave.getItems().get(i).getText());
            	bufferedWriter.newLine();
			}
            result.setText("La liste a été mise à jour !");
    		result.setTextFill(Color.BLUE);
            bufferedWriter.close();
        } catch (IOException e) {
        	result.setText("Impossible de sauvegarder !");
    		result.setTextFill(Color.RED);
        	LOG.severe("Impossible de faire une sauvegarde des données");
        }
	}
	
	public void saveData(MenuItem menuItemToSave) {
		try {
            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(menuItemToSave.getText());
            bufferedWriter.newLine();
            result.setText("Le nouvel élément a bien été sauvegardé !");
    		result.setTextFill(Color.GREEN);
            bufferedWriter.close();
        } catch (IOException e) {
        	result.setText("Impossible de sauvegarder !");
    		result.setTextFill(Color.RED);
        	LOG.severe("Impossible de faire une sauvegarde de l'application");
        }
	}
	
	@FXML 
	public void loadData(ActionEvent event) {
		try {
			if (file.exists() && select.getItems().size() <= 1 ) {
				FileReader reader = new FileReader(path);
				BufferedReader bufferedReader = new BufferedReader(reader);
				String line;
				if (!reload) {
					while ((line = bufferedReader.readLine()) != null) {
		            	reload = true;
		            	MenuItem newM = new MenuItem(line);
		            	if (!line.equals("Paris,FR")) {
		            		select.getItems().add(newM);
			            	addIntoDelete(line);
			            	newM.setOnAction(e -> {
						    	setCityResearch(e);
						    });
		            	}
		            }
				}
				result.setText("Chargement des données terminé !");
	    		result.setTextFill(Color.GREEN);
				bufferedReader.close();
			} else {
				result.setText("Application déjà à jour !");
	    		result.setTextFill(Color.BLUE);
				file = new File(path);
			}
        } catch (IOException e) {
        	LOG.severe("Impossible de faire une lecture des données contenu dans le fichier de sauvegarde");
        	//e.printStackTrace();
        }
	}
	
	public void loadDataA() {
		try {
			if (file.exists()) {
				FileReader reader = new FileReader(path);
				BufferedReader bufferedReader = new BufferedReader(reader);
				//Skip first line
				//String line = bufferedReader.readLine();
				String line;
	            while ((line = bufferedReader.readLine()) != null) {
	            	MenuItem m = new MenuItem(line);
	            	if (!line.equals("Paris,FR")) {
		            	select.getItems().add(m);
		            	addIntoDelete(line);
		            	m.setOnAction(e -> {
					    	setCityResearch(e);
					    });
	            	}
	            }
	            result.setText("Chargement des données terminé !");
	    		result.setTextFill(Color.GREEN);
	            reader.close();
			} else {
				result.setText("Impossible de sauvegarder !");
	    		result.setTextFill(Color.RED);
				file = new File(path);
			}
        } catch (IOException e) {
        	LOG.severe("Impossible de faire une lecture des données contenu dans le fichier de sauvegarde");
        	//e.printStackTrace();
        }
	}
	
    @FXML 
    void exitScene(ActionEvent event) {
    	try {
    		if (stateTimer()) {
    			timer.cancel();
    		}
    		System.exit(0);
    	} catch (Exception e) {
    		LOG.severe("Erreur de saisie : "+ e.getMessage());
    		e.printStackTrace();
    	}
    }
    
    @FXML 
    void stopAct(ActionEvent event) {
    	try {
    		if (stateTimer() == true) {
    			result.setText("Actualisation stoppée !");
    			result.setTextFill(Color.BLUE);
    			timer.cancel();
    		}
    	} catch (Exception e) {
    		LOG.severe("Erreur de saisie : "+ e.getMessage());
    		e.printStackTrace();
    	}
    }
    
    @FXML 
    void clean(ActionEvent event) {
    	initialize();
    	temperature.setText("N/A");
    	alterTemp.setText("N/A");
    	wind.setText("N/A");
    	humidity.setText("N/A");
    	sunrise.setText("N/A");
    	sunset.setText("N/A");
    	tempMax.setText("N/A");
    	tempMin.setText("N/A");
    	pressure.setText("N/A");
    	description.setText("N/A");
    	imgTemp.setImage(new Image("images/not_available.png"));
    }
    
    public void hideMinMax() {
    	if (!tempMax.isVisible() && !tempMin.isVisible()) {
    		imgMinMax.setVisible(false);
    	} else {
    		imgMinMax.setVisible(true);
    	}
    }
    
    public void checkTextFieldCity() {
    	if (!stateCity()) {
    		result.setText("Veuillez saisir une ville !");
    		result.setTextFill(Color.RED);
    	} else {
    		cityFound = city.getText();
    		if (country.getText() == null || country.getText().trim().isEmpty()) {
        		checkCityName(cityFound);
    		} else {
    			countryFound = country.getText();
    			checkCityCountryName(cityFound,countryFound);
    		}
    	}
    }
    
    public void checkCityName(String city) {
    	cl = new MeteoClient(city);
      	res = cl.getWeatherByCityName();
		//LOG.info(cl.getJsonWeatherByCityName());
        if (res == null) {
        	LOG.severe("La ville que vous avez saisie n'existe pas");
            result.setText("Veuillez saisir un nom de ville valide !");
    		result.setTextFill(Color.RED);
        } else {
    		result.setText("Recherche terminée !");
    		result.setTextFill(Color.GREEN);
    		displayGUI(res);
    	}
		getDate();
    }
    
    public void checkCityCountryName(String city, String country) {
    	cl = new MeteoClient(city, country);
      	res = cl.getWeatherByCityName();
		//LOG.info(cl.getJsonWeatherByCityName());
        if (res == null) {
        	LOG.severe("La ville que vous avez saisie n'existe pas");
            result.setText("Veuillez saisir un nom de ville valide !");
    		result.setTextFill(Color.RED);
        } else {
    		result.setText("Recherche terminée !");
    		result.setTextFill(Color.GREEN);
    		displayGUI(res);
    	}
		getDate();
    }
    
    @FXML
    void valider(ActionEvent event) {
    	try {
    		checkTextFieldCity();
    	} catch (Exception e) {
    		LOG.severe("Erreur de saisie : "+ e.getMessage());
    		//e.printStackTrace();
    		//System.exit(-1);
    	}
    }
    
    /** 
     * <b>getTemp</b> 
     * @param nb
     * 		contient le nombre non converti en Farhenheit
     * @param temperature 
     * 		Boolean : si c'est une température ou pas
     * @return convertTemp
     *     retourne la température en Celsius (initialement en Farhenheit)
     */ 
    
    public String getTemp (Float nb, Boolean temperature) {
    	Float temp = temperature ? nb - 273.15f : nb;
    	String convertTemp = String.valueOf(Math.round(temp * 100.0) / 100.0);
    	return convertTemp;
    }
    
    /** 
     * <b>getTempDouble</b> 
     * @param nb
     * 		correspond à la string contenant la valeur non convertie en Farhenheit
     * @param temperature 
     * 		Boolean : si c'est une température ou pas
     * @return convertTemp
     *     retourne la température en Celsius (initialement en Farhenheit)
     */ 
    
    public String getTempDouble (String nb, Boolean temperature) {
		double nbC =Double.parseDouble(nb);
    	Float temp = (float) (temperature ? nbC - 273.15f : nbC);
    	String convertTemp = String.valueOf(Math.round(temp * 100.0) / 100.0);
    	return convertTemp;
    }
    
    /** 
     * <b>getDate</b> 
     *     permet d'obtenir la date et l'heure de actuelle au moment de la validation
     */ 
    
    public void getDate() {
    	dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    	DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    	Date date = new Date();
    	LOG.fine(dateFormat.format(date));
    	lastUpdate.setText("le " + dateFormat.format(date) + " à " + timeFormat.format(date));
    }
    
    /** 
     * <b>addDays</b> 
     * 
     * @param nbDays
     * 		permet d'obtenir le jour suivant la date actuelle
     * @return dateFormat.format(date)
     * 		retourne une string contenant une la nouvelle date
     */ 
    
    public String addDays(int nbDays) {
    	dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    	Date date = new Date();
    	Calendar c = Calendar.getInstance(); 
    	c.setTime(date); 
    	c.add(Calendar.DATE, nbDays);
    	date = c.getTime();
    	return dateFormat.format(date);
    }
    
    /** 
     * <b>getTimeSun</b> 
     * 
     * permet d'avoir l'heure du coucher de soleil et du lever du soleil
     * 
     * @param time
     * 		contient un nombre en secondes, qui sera ensuite convertie en heures et minutes
     * @return convertDate
     * 		retourne l'heure 
     */ 
    
    @SuppressWarnings("deprecation")
    public String getTimeSun(long time) {
    	Date date = new Date(time);
    	String convertDate = date.getHours() + ":" + date.getMinutes();
    	return convertDate;
    }
    
	public void displayGUI(Result res) {
      	
      	//For the current Days
		//Settings for the interface
		temperature.setText(getTemp(res.getMain().getTemp(), true) + "°C");
		alterTemp.setText(getTemp(res.getMain().getFeelsLike(), true) + "°C");
		tempMax.setText(getTemp(res.getMain().getTempMax(), true) + "°C");
		tempMin.setText(getTemp(res.getMain().getTempMin(), true) + "°C");
		pressure.setText(res.getMain().getPressure()+ " hPA");
		wind.setText(getTemp(res.getWind().getSpeed(), false) + " m/s");
		description.setText(res.getWeather().get(0).getDescription());
		cityCountry.setText(res.getName() + ", " + res.getSys().getCountry());
		LOG.info("Ville saisie : " + res.getName() + ", " + res.getSys().getCountry());
		humidity.setText(res.getMain().getHumidity() + "%");
		
		long timeSunr = (long) res.getSys().getSunrise() * 1000;
		sunrise.setText(getTimeSun(timeSunr));
		long timeSuns = (long) res.getSys().getSunset() * 1000;
		sunset.setText(getTimeSun(timeSuns));
		
		//Settings for the icons
		String icon = res.getWeather().get(0).getIcon();
		imgTemp.setImage(new Image("images/" + icon +".png"));
		imgMinMax.setImage(new Image("images/temp_min_max.png"));
		imgAlter.setImage(new Image("images/thermostat.png"));
		imgSunrise.setImage(new Image("images/sunrise.png"));
		imgSunset.setImage(new Image("images/sunset.png"));
		imgWind.setImage(new Image("images/wind.png"));
		imgWeather.setImage(new Image("images/humidity.png"));
		imgPres.setImage(new Image("images/pressure.png"));
		
		//LOG.info(cl.getCity());
		displayPrevGUI();
		
    }
	
	/** 
     * <b>getIcon</b> 
     * 
     * permet d'obtenir l'icône associée à l'évènement météorologique
     * 
     * @param nbIcon
     * 		correspond à l'élément de la requête, ex : 8 donnera le 8 élément de l'élement liste de la requête forecast (pour obtenir les prévisions)
     * @param obj
     * 		objet json contenant les informations de la requête
     * @return icon
     * 		retourne la string de l'icône
     */ 
	
	public String getIcon(int nbIcon, JSONObject obj) {
		JSONObject weatherList = obj.getJSONArray("list").getJSONObject(nbIcon);
		JSONArray weatherEl = weatherList.getJSONArray("weather");
		String icon = weatherEl.getJSONObject(0).getString("icon");
		return icon;
	}
	
	/** 
     * <b>getDesciption</b> 
     * 
     * permet d'obtenir la description associée à l'évènement météorologique
     * 
     * @param nbDesc
     * 		correspond à l'élément de la requête, ex : 8 donnera le 8 élément de l'élement liste de la requête forecast (pour obtenir les prévisions)
     * @param obj
     * 		objet json contenant les informations de la requête
     * @return description
     * 		retourne la string de la description, ex : couvert
     */ 
	
	public String getDesciption(int nbDesc, JSONObject obj) {
		JSONObject weatherList = obj.getJSONArray("list").getJSONObject(nbDesc);
		JSONArray weatherEl = weatherList.getJSONArray("weather");
		String description = weatherEl.getJSONObject(0).getString("description");
		return description;
	}
	
	/** 
     * <b>getTempMaxMin</b> 
     * 
     * permet d'obtenir les températures associées à l'évènement météorologique
     * 
     * @param nbDesc
     * 		correspond à l'élément de la requête, ex : 8 donnera le 8 élément de l'élement liste de la requête forecast (pour obtenir les prévisions)
     * @param obj
     * 		objet json contenant les informations de la requête
     * @param temp_string
     * 		correspond au nom de la température dans la requête : temp_max, temp_min...
     * @return temp
     * 		retourne le nombre au format Double temp
     */ 
	
	public String getTempMaxMin(int nbDesc, JSONObject obj, String temp_string) {
		JSONObject mainList = obj.getJSONArray("list").getJSONObject(nbDesc);
		JSONObject mainEl = mainList.getJSONObject("main");
		String temp =  String.valueOf(mainEl.get(temp_string));
		return temp;
	}
	
	
	public void displayPrevGUI() {
		//LOG.info(cl.getCity());
		//LOG.info(cl.getJsonWeatherByCityNameFor5());
      	
      	//JSONObject tomJsonObject = new JSONObject(cl.getJsonWeatherByCityNameFor5());
      	String jsonString = cl.getJsonWeatherByCityNameFor5() ; //assign your JSON String here
      	JSONObject obj = new JSONObject(jsonString);
		
      	//date
      	//other possibility : use dt_txt in list
      	date2.setText("J+1 : " + addDays(1));
      	date3.setText("J+2 : " + addDays(2));
      	date4.setText("J+3 : " + addDays(3));
      	date5.setText("J+4 : " + addDays(4));
      	
      	//temperature
		temp2.setText(getTempDouble(getTempMaxMin(7,obj,"feels_like"), true) + "°C/" + getTempDouble(getTempMaxMin(7,obj,"temp_max"), true) + "°C");
		temp3.setText(getTempDouble(getTempMaxMin(15,obj,"feels_like"), true) + "°C/" + getTempDouble(getTempMaxMin(15,obj,"temp_max"), true) + "°C");
		temp4.setText(getTempDouble(getTempMaxMin(23,obj,"feels_like"), true) + "°C/" + getTempDouble(getTempMaxMin(23,obj,"temp_max"), true) + "°C");
		temp5.setText(getTempDouble(getTempMaxMin(31,obj,"feels_like"), true) + "°C/" + getTempDouble(getTempMaxMin(31,obj,"temp_max"), true) + "°C");
		
		//description
		descrip2.setText(getDesciption(7,obj));
		descrip3.setText(getDesciption(15,obj));
		descrip4.setText(getDesciption(23,obj));
		descrip5.setText(getDesciption(31,obj));
      	
      	//Imagesview
		imgIcon2.setImage(new Image("images/" + getIcon(7,obj) +".png"));
		imgIcon3.setImage(new Image("images/" + getIcon(15,obj) +".png"));
		imgIcon4.setImage(new Image("images/" + getIcon(23,obj) +".png"));
		imgIcon5.setImage(new Image("images/" + getIcon(31,obj)+".png"));
		
	}
	
	/** 
     * <b>stateTimer</b> 
     * 
     * permet d'obtenir les températures associées à l'évènement météorologique
     * 
     * @return timerStart
     * 		retourne un booléen, false = le timer n'a pas démarré ou true = le timer est en route
     */
	
	public boolean stateTimer() {
        return this.timerStart;
    }
	
	/** 
     * <b>stateCity</b> 
     * 
     * @return
     * 
     *retourne un booléen, false = le TextField est vide ou null sinon true
     */
	
	public boolean stateCity(){
		if (city.getText() == null || city.getText().trim().isEmpty()) {
			return false;
		}
        return true;
    }
	
	/** 
     * <b>stateRefresh</b> 
     * 
     * @return
     * 
     *retourne un booléen, false = le TextField récupérant le nombres de minutes est vide ou null sinon true
     */
	
	public boolean stateRefresh() {
		if (refresh.getText() == null || refresh.getText().trim().isEmpty()) {
			return false;
		}
        return true;
    }
	
	@FXML
	void actualise(ActionEvent event) {
		try {
			if (!stateRefresh() || !stateCity() || res == null) {
				result.setText("Veuillez remplir tous les champs ! ");
	    		result.setTextFill(Color.RED);
			} else {
				long period = Integer.parseInt(refresh.getText());
				long millisecondes = TimeUnit.MINUTES.toMillis(period);
				timer = new Timer();
				timer.scheduleAtFixedRate(new TimerTask() {
			        @Override
			        public void run() {
			        	timerStart = true;
			        	//allow to make a pause and avoid the IllegalStateException Thread Timer-0
			        	Platform.runLater(() -> {
			                displayGUI(res);
			                getDate();
			                result.setText("Nouvelle actualisation en cours ...");
				    		result.setTextFill(Color.BLUE);
			            });
			        }
				}, period, millisecondes);
			}
        } catch (NullPointerException  | NumberFormatException  | IllegalStateException  e) {
        	LOG.severe("Erreur de saisie : "+ e.getMessage());
        	result.setText("Veuillez saisir un nombre de minutes !");
        	result.setTextFill(Color.RED);
        }
    }
}