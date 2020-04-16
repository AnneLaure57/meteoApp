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
	
	/** 
     * <b>buildRequest</b> 
     * 
     * @return request
     *     contient la requête avec l'adresse de OpenWeather, les informations du jour, la ville, le pays , la langue et la clé de l'api
     */ 
	
	public String buildRequest() {
		String request = WEBSERVICE + "weather?" + "q=" + getCity() + "," + getCountry() +
				"&lang=" + lang + "&APPID=" + getApiKey() ;
		return request;
	}
	
	/** 
     * <b>buildRequestFor5</b> 
     * 
     * @return request
     *     contient la requête avec l'adresse de OpenWeather, les informations des 4 prochains jours, la ville, le pays , la langue et la clé de l'api
     */ 
	
	public String buildRequestFor5() {
		String request = WEBSERVICE + "forecast?" + "q=" + getCity() + "," + getCountry() +
				"&lang=" + lang + "&APPID=" + getApiKey() + "&cnt=40";
		return request;
	}
	
	/** 
     * <b>getJsonWeatherByCityNameFor5</b> 
     * 
     * @return res
     *     permet d'otbenir la requête json
     */ 
	
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
	
	/** 
     * <b>getWeatherByCityNameFor5</b> 
     * 
     * @return ex
     *     permet générer la requête json
     */ 
	
	public Example getWeatherByCityNameFor5() {
		Example ex = null;
		String tmp = getJsonWeatherByCityName();
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		ex =  gson.fromJson(tmp, Example.class);
		return ex;
	}
	
	/** 
     * <b>getJsonWeatherByCityName</b> 
     * 
     * @return res
     *     permet d'otbenir la requête json pour un jour
     */ 
	
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
	
	/** 
     * <b>getWeatherByCityName</b> 
     * 
     * @return ex
     *     permet générer la requête json pour le jour courant
     */ 
	
	public Result getWeatherByCityName() {
		Result res = null;
		String tmp = getJsonWeatherByCityName();
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		res =  gson.fromJson(tmp, Result.class);
		return res;
	}
	
	/** 
     * <b>getApiKey</b> 
     * 
     * @return apiKey
     *     permet d'obtenir la clé de l'api
     */ 

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	
	/** 
     * <b>getCity</b> 
     * 
     * @return city
     *     permet d'obtenir la ville
     */ 


	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	/** 
     * <b>getCountry</b> 
     * 
     * @return country
     *     permet d'obtenir le pays
     */ 
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
}
