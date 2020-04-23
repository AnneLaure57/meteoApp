package fr.ul.miage.controller;

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
	private MenuItem menuTest;
	
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
    private TextField refresh;

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
    
    private static final Logger LOG = Logger.getLogger(MeteoClient.class.getName());
    
    private MeteoClient cl;
    
    private String cityFound;
    
    private Result res;
    
    private DateFormat dateFormat;
    
    private Timer timer;
    
    private boolean timerStart = false;
    

	@FXML
   	public void initialize() {
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
    	imgIcon2.setImage(new Image("images/not_available.png"));
    	imgIcon3.setImage(new Image("images/not_available.png"));
    	imgIcon4.setImage(new Image("images/not_available.png"));
    	imgIcon5.setImage(new Image("images/not_available.png"));
    	refresh_icon.setImage(new Image("images/refresh_icon.png"));
    	/*other solution (Menu without MenuItem)
    	quit.setGraphic( ButtonBuilder.create().text("") .onAction(new EventHandler<ActionEvent>(){
    		@Override
    		public void handle(ActionEvent t) {
    	          System.exit(0);
    	           } }).build());*/
   	}
	
	@FXML
    void display(ActionEvent event) {
    	// To optimize : use observableList
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
			MenuItem newMenu = new MenuItem(nameCity);
		    select.getItems().add(newMenu);
		    addIntoDelete(nameCity);
		    newMenu.setOnAction(e -> {
		    	setCityResearch(e);
		    });
		}
	}
	
	public void addIntoDelete(String nameCity) {
		MenuItem newMenu = new MenuItem(nameCity);
		deleteMenu.getItems().add(newMenu);
		newMenu.setOnAction(e -> {
			//newMenu.setDisable(true);
			LOG.info("Nom du menu " + newMenu.getText());
			for (int i = 0; i < select.getItems().size(); i++) {
				//LOG.info("Je suis sur l'onglet " + i + " qui correspond "+ select.getItems().get(i).getText());
				if (newMenu.getText().equals(select.getItems().get(i).getText())) {
					//LOG.info("je supprime l'index " + i);
					select.getItems().remove(i);
				}
			}
			deleteMenu.getItems().remove(newMenu);
	    });
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
    		checkCityName(cityFound);
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
    
    @FXML
    void valider(ActionEvent event) {
    	try {
    		checkTextFieldCity();
    	} catch (Exception e) {
    		LOG.severe("Erreur de saisie : "+ e.getMessage());
    		e.printStackTrace();
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
     * 		contient le nombre non converti en Farhenheit
     * @param temperature 
     * 		Boolean : si c'est une température ou pas
     * @return convertTemp
     *     retourne la température en Celsius (initialement en Farhenheit)
     */ 
    
    public String getTempDouble (Double nb, Boolean temperature) {
    	Float temp = (float) (temperature ? nb - 273.15f : nb);
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
	
	public Double getTempMaxMin(int nbDesc, JSONObject obj, String temp_string) {
		JSONObject mainList = obj.getJSONArray("list").getJSONObject(nbDesc);
		JSONObject mainEl = mainList.getJSONObject("main");
		Double temp = (Double) mainEl.get(temp_string);
		return temp;
	}
	
	
	public void displayPrevGUI() {
		//For the rest
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
		temp2.setText(getTempDouble(getTempMaxMin(8,obj,"feels_like"), true) + "°C/" + getTempDouble(getTempMaxMin(8,obj,"temp_max"), true) + "°C");
		temp3.setText(getTempDouble(getTempMaxMin(16,obj,"feels_like"), true) + "°C/" + getTempDouble(getTempMaxMin(16,obj,"temp_max"), true) + "°C");
		temp4.setText(getTempDouble(getTempMaxMin(24,obj,"feels_like"), true) + "°C/" + getTempDouble(getTempMaxMin(23,obj,"temp_max"), true) + "°C");
		temp5.setText(getTempDouble(getTempMaxMin(32,obj,"feels_like"), true) + "°C/" + getTempDouble(getTempMaxMin(32,obj,"temp_max"), true) + "°C");
		
		//description
		descrip2.setText(getDesciption(8,obj));
		descrip3.setText(getDesciption(16,obj));
		descrip4.setText(getDesciption(24,obj));
		descrip5.setText(getDesciption(32,obj));
      	
      	//Imagesview
		imgIcon2.setImage(new Image("images/" + getIcon(8,obj) +".png"));
		imgIcon3.setImage(new Image("images/" + getIcon(16,obj) +".png"));
		imgIcon4.setImage(new Image("images/" + getIcon(24,obj) +".png"));
		imgIcon5.setImage(new Image("images/" + getIcon(32,obj)+".png"));
		
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
	void reload(ActionEvent event) throws Exception{
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
    		//e.printStackTrace();
        }
    }
}