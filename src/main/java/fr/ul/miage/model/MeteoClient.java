package fr.ul.miage.model;

import java.util.logging.Logger;

//To correct Result, search on 

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import fr.ul.miage.meteo.json.Example;
import fr.ul.miage.meteo.json.Result;

public class MeteoClient {
	private static final Logger LOG = Logger.getLogger(MeteoClient.class.getName());
	private String WEBSERVICE="https://api.openweathermap.org/data/2.5/";
	private String lang="fr";
	
	private String apiKey;
	private String city;
	private String country;
	
	public MeteoClient() {
		this("0740f72b4127e981e4d0499b5fb05c1f","Nancy","fr");
	}
	
	public MeteoClient(String city) {
		this("0740f72b4127e981e4d0499b5fb05c1f",city,"fr");
	}

	public MeteoClient(String city, String country) {
		this("0740f72b4127e981e4d0499b5fb05c1f",city,country);
	}

	public MeteoClient(String apiKey, String city, String country) {
		setApiKey(apiKey);
		setCity(city);
		setCountry(country);
	}
	
	public String buildRequest() {
		// Change to Fr
		//https://stackoverflow.com/questions/49555188/how-to-change-description-language-in-openweathermap-api-in-android
		String request = WEBSERVICE + "weather?" + "q=" + getCity() + "," + getCountry() +
				"&lang=" + lang + "&APPID=" + getApiKey() ;
		/*String request = WEBSERVICE + "forecast?" + "q=" + getCity() + "," + getCountry() +
				"&lang=" + lang + "&APPID=" + getApiKey() + "&cnt=5" ;*/
		return request;
	}
	
	public String buildRequestFor5() {
		String request = WEBSERVICE + "forecast?" + "q=" + getCity() + "," + getCountry() +
				"&lang=" + lang + "&APPID=" + getApiKey() + "&cnt=30";
		return request;
	}
	
	public String getJsonWeatherByCityNameFor5() {
		String res = null;
		
		String request = buildRequestFor5();
		try {
			Client client = Client.create();
			WebResource r = client.resource(request);
			r.accept("application/json");
			ClientResponse response = r.get(ClientResponse.class);
			if (response.getStatus() != 200) {
				LOG.severe("Erreur de requ�te :"+ request +"(code:"+ response.getStatus() + ")");
				return null;
			}
			res= response.getEntity(String.class);
		} catch (Exception e) {
			LOG.severe("Erreur de webservice"+request);
			return null;
		}
		return res;
	}
	
	public Example getWeatherByCityNameFor5() {
		Example ex = null;
		String tmp = getJsonWeatherByCityName();
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		ex =  gson.fromJson(tmp, Example.class);
		return ex;
	}
	
	public String getJsonWeatherByCityName() {
		String res = null;
		
		String request = buildRequest();
		try {
			Client client = Client.create();
			WebResource r = client.resource(request);
			r.accept("application/json");
			ClientResponse response = r.get(ClientResponse.class);
			if (response.getStatus() != 200) {
				LOG.severe("Erreur de requ�te :"+ request +"(code:"+ response.getStatus() + ")");
				return null;
			}
			res= response.getEntity(String.class);
		} catch (Exception e) {
			LOG.severe("Erreur de webservice"+request);
			return null;
		}
		return res;
	}
	
	public Result getWeatherByCityName() {
		Result res = null;
		String tmp = getJsonWeatherByCityName();
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		res =  gson.fromJson(tmp, Result.class);
		return res;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
}
