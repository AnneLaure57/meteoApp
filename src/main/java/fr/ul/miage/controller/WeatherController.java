package fr.ul.miage.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

import org.json.*;
import org.json.JSONArray;
import org.json.JSONML;
import org.json.JSONObject;

import fr.ul.miage.meteo.json.Example;
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
    private ImageView imgIcon2;
    
    @FXML
    private ImageView imgIcon3;
    
    @FXML
    private ImageView imgIcon4;
    
    @FXML
    private ImageView imgIcon5;
    
    private static final Logger LOG = Logger.getLogger(MeteoClient.class.getName());
    
    private MeteoClient cl;
    
    private DateFormat dateFormat;
    
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
    	imgIcon2.setImage(new Image("images/not_available.png"));
    	imgIcon3.setImage(new Image("images/not_available.png"));
    	imgIcon4.setImage(new Image("images/not_available.png"));
    	imgIcon5.setImage(new Image("images/not_available.png"));
    	
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
    
    public String getTempDouble (Double number, Boolean temperature) {
    	Float temp = (float) (temperature ? number - 273.15f : number);
    	return String.valueOf(Math.round(temp * 100.0) / 100.0);
    }
    
    public void getDate() {
    	dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    	DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    	Date date = new Date();
    	LOG.fine(dateFormat.format(date));
    	lastUpdate.setText("le " + dateFormat.format(date) + " à " + timeFormat.format(date));
    }
    
    public String addDays(int nbDays) {
    	dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    	Date date = new Date();
    	Calendar c = Calendar.getInstance(); 
    	c.setTime(date); 
    	c.add(Calendar.DATE, nbDays);
    	date = c.getTime();
    	return dateFormat.format(date);
    }
    
    @SuppressWarnings("deprecation")
    public String getTimeSun(long time) {
    	Date date = new Date(time);
    	String convertDate = date.getHours() + ":" + date.getMinutes();
    	return convertDate;
    }
    
	public void displayGUI() {
      	cl = new MeteoClient(city.getText());
      	Result res = cl.getWeatherByCityName();
		LOG.fine(cl.getJsonWeatherByCityName());
		
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
		System.out.println("Ville saisie : " + res.getName() + ", " + res.getSys().getCountry());
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
		
		displayPrevGUI();
		
    }
	
	public String getIcon(int nbIcon, JSONObject obj) {
		JSONObject weatherList = obj.getJSONArray("list").getJSONObject(nbIcon);
		JSONArray weatherEl = weatherList.getJSONArray("weather");
		String icon = weatherEl.getJSONObject(0).getString("icon");
		return icon;
	}
	
	public String getDesciption(int nbDesc, JSONObject obj) {
		JSONObject weatherList = obj.getJSONArray("list").getJSONObject(nbDesc);
		JSONArray weatherEl = weatherList.getJSONArray("weather");
		String description = weatherEl.getJSONObject(0).getString("description");
		return description;
	}
	
	public Double getTempMaxMin(int nbDesc, JSONObject obj, String temp_string) {
		JSONObject mainList = obj.getJSONArray("list").getJSONObject(nbDesc);
		JSONObject mainEl = mainList.getJSONObject("main");
		Double temp = (Double) mainEl.get(temp_string);
		return temp;
	}
	
	
	public void displayPrevGUI() {
		//For the rest
		LOG.fine("Pour la ville" + city.getText());
      	LOG.fine(cl.getJsonWeatherByCityNameFor5());
      	
      	//JSONObject tomJsonObject = new JSONObject(cl.getJsonWeatherByCityNameFor5());
      	String jsonString = cl.getJsonWeatherByCityNameFor5() ; //assign your JSON String here
      	JSONObject obj = new JSONObject(jsonString);
      	//System.out.println("Request : " + obj);

      	//Get elements from list
      	/*JSONArray arr = obj.getJSONArray("list");
  		//System.out.println("list : " + arr);
  		for (int i = 0; i < arr.length(); i++)
  		{
  			String dt_txt = arr.getJSONObject(i).getString("dt_txt");
  			//System.out.println(arr.getJSONObject(i).getString("dt_txt"));
  			int dt = arr.getJSONObject(i).getInt("dt");
  			//System.out.println(arr.getJSONObject(i).getInt("dt"));
  		}
      	
      	//Get elements from main list
		JSONObject mainList = obj.getJSONArray("list").getJSONObject(5);
		JSONObject mainEl = mainList.getJSONObject("main");
		System.out.println("temp_max value: " + mainEl.get("temp_max"));
		System.out.println("temp_min value: " + mainEl.get("temp_min"));
		
		//Get elements from weather list
		JSONObject weatherList = obj.getJSONArray("list").getJSONObject(5);
		JSONArray weatherEl = weatherList.getJSONArray("weather");
		System.out.println(weatherEl);
		for (int i = 0; i < weatherEl.length(); i++)
  		{
			//description
			System.out.println("taille " + weatherEl.length());
  			String description = weatherEl.getJSONObject(i).getString("description");
  			System.out.println(description);
  			//icon
  			String icon = weatherEl.getJSONObject(i).getString("icon");
  			System.out.println(icon);
  		}*/
		
      	//date
      	//other possibility : use dt_txt in list
      	date2.setText("J+1 : " + addDays(1));
      	date3.setText("J+2 : " + addDays(2));
      	date4.setText("J+3 : " + addDays(3));
      	date5.setText("J+4 : " + addDays(4));
      	
      	//temperature
		temp2.setText(getTempDouble(getTempMaxMin(5,obj,"temp_max"), true) + "°C/" + getTempDouble(getTempMaxMin(5,obj,"temp_min"), true) + "°C");
		temp3.setText(getTempDouble(getTempMaxMin(13,obj,"temp_max"), true) + "°C/" + getTempDouble(getTempMaxMin(13,obj,"temp_min"), true) + "°C");
		temp4.setText(getTempDouble(getTempMaxMin(21,obj,"temp_max"), true) + "°C/" + getTempDouble(getTempMaxMin(21,obj,"temp_min"), true) + "°C");
		temp5.setText(getTempDouble(getTempMaxMin(29,obj,"temp_max"), true) + "°C/" + getTempDouble(getTempMaxMin(29,obj,"temp_min"), true) + "°C");
		
		//description
		descrip2.setText(getDesciption(5,obj));
		descrip3.setText(getDesciption(13,obj));
		descrip4.setText(getDesciption(21,obj));
		descrip5.setText(getDesciption(29,obj));
      	
      	//Imagesview
		imgIcon2.setImage(new Image("images/" + getIcon(5,obj) +".png"));
		imgIcon3.setImage(new Image("images/" + getIcon(13,obj) +".png"));
		imgIcon4.setImage(new Image("images/" + getIcon(21,obj) +".png"));
		imgIcon5.setImage(new Image("images/" + getIcon(29,obj)+".png"));
		
	}
    
	//TODO
    public void updateGUI() {
    	
    }

}
