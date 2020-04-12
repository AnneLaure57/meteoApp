package fr.ul.miage.controller;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import fr.ul.miage.meteo.json.Result;
import fr.ul.miage.model.MeteoClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class WeatherController {
	
	//List components
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
    private Button buttonVal;

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
    
    private static final Logger LOG = Logger.getLogger(MeteoClient.class.getName());
    
    
    @FXML
   	public void initialize() {
    	result.setText("Aucune recherche effectuée !");
   	}
    
    @FXML
    void valider(ActionEvent event) {
    	
    	try {
			if (city.getText() == null || city.getText().trim().isEmpty()) {
	    		result.setText("Veuillez saisir une ville !");
	    		result.setTextFill(Color.RED);
	    	} else {
	    		result.setText("Recherche terminée !");
	    		result.setTextFill(Color.GREEN);
	    		displayGUI();
	    	}
			getDate();
    	} catch (Exception e) {
    		LOG.severe("Erreur de saisie : "+ e.getMessage());
    		//e.printStackTrace();
    		System.exit(-1);
    	}
    }
    
    public String getTemp (Float number, Boolean temperature) {
    	Float temp = temperature ? number - 273.15f : number;
    	return String.valueOf(Math.round(temp * 100.0) / 100.0);
    }
    
    public void getDate() {
    	//https://stackoverflow.com/questions/9886751/how-to-split-date-and-time-from-a-datetime-string
    	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    	DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    	Date date = new Date();
    	//System.out.println(dateFormat.format(date));
    	lastUpdate.setText("le " + dateFormat.format(date) + " à " + timeFormat.format(date));
    }
    
    @SuppressWarnings("deprecation")
    public String getTimeSun(long time) {
    	Date date = new Date(time);
    	String convertDate = date.getHours() + ":" + date.getMinutes();
    	return convertDate;
    }
    
	public void displayGUI() {
      	MeteoClient cl = new MeteoClient(city.getText());
      	Result res = cl.getWeatherByCityName();
      	//System.out.println(cl.getJsonWeatherByCityName());
		
		//Setting the interface
		temperature.setText(getTemp(res.getMain().getTemp(), true) + "°C");
		alterTemp.setText(getTemp(res.getMain().getFeelsLike(), true) + "°C");
		tempMax.setText(getTemp(res.getMain().getTempMax(), true) + "°C");
		tempMin.setText(getTemp(res.getMain().getTempMin(), true) + "°C");
		pressure.setText(res.getMain().getPressure()+ " hPA");
		wind.setText(getTemp(res.getWind().getSpeed(), false) + " m/s");
		description.setText(res.getWeather().get(0).getDescription());
		cityCountry.setText(res.getName() + ", " + res.getSys().getCountry());
		humidity.setText(res.getMain().getHumidity() + "%");
		
		long timeSunr = (long) res.getSys().getSunrise() * 1000;
		sunrise.setText(getTimeSun(timeSunr));
		long timeSuns = (long) res.getSys().getSunset() * 1000;
		sunset.setText(getTimeSun(timeSuns));
		
		//Setting the icon
		String icon = res.getWeather().get(0).getIcon();
		imgTemp.setImage(new Image("images/" + icon +".png"));
		imgMinMax.setImage(new Image("images/temp_min_max.png"));
		imgAlter.setImage(new Image("images/thermostat.png"));
		imgSunrise.setImage(new Image("images/sunrise.png"));
		imgSunset.setImage(new Image("images/sunset.png"));
		imgWind.setImage(new Image("images/wind.png"));
		imgWeather.setImage(new Image("images/Humidity.png"));
		imgPres.setImage(new Image("images/pressure.png"));
    }
    
    public void updateUI() {
    	
    }

}
