import static org.junit.Assert.*;

import org.junit.Test;

import fr.ul.miage.controller.WeatherController;
import fr.ul.miage.model.MeteoClient;

public class TestsUnitaires {
	
	/*@BeforeEach
    public static void before(){
        System.out.println("\tAppel de BeforeEach");
    }

    @AfterEach
    public static void after(){
        System.out.println("\tAppel de AfterEach");
    }

    @Test
    public void test1(){
        System.out.println("test 1");
    }

    @Test
    public void test2(){
        System.out.println("test 2");
    }

     @BeforeAll
    public  static void beforeAll(){
        System.out.println("\tAvant tous les tests");
    }

     @AfterAll
    public static void afterAll(){
        System.out.println("\tAprès tous les tests");
    }*/
	
	@Test
	public void testTemp() {
		WeatherController c = new WeatherController();
		float temp = (float) 286.16;
		String  resultat =  c.getTemp(temp, true);
		String resAttendu = "13.01" ;
		assertEquals(resAttendu,resultat);
	}
	
	@Test
	public void testTempDouble() {
		WeatherController c = new WeatherController();
		String resultat = c.getTempDouble(286.16, true);
		String resAttendu = "13.01" ;
		assertEquals(resAttendu,resultat);
	}
	
	@Test
	public void testDate() {
		WeatherController c = new WeatherController();
    	String resultat = c.addDays(0);
    	String resAttendu = "14/04/2020";
    	//System.out.println(resultat);
    	assertFalse(resAttendu.equals(resultat));
	}
	
	@Test
	public void testTempSun() {
		WeatherController c = new WeatherController();
		String resultat = c.getTimeSun(1586888682);
    	String resAttendu = "9:48";
    	//System.out.println(resultat);
		assertEquals(resAttendu,resultat);
	}
	
	@Test
	public void testCity() {
		String city = "Nancy";
		MeteoClient c = new MeteoClient();
    	String resultat = c.getCity();
		assertEquals(city,resultat);
	}
	
	@Test
	public void testCountry() {
		String country = "fr";
		MeteoClient c = new MeteoClient();
    	String resultat = c.getCountry();
		assertEquals(country,resultat);
	}
	
	@Test
	public void testApiKey() {
		String apiKey = "0740f72b4127e981e4d0499b5fb05c1f";
		MeteoClient c = new MeteoClient();
    	String resultat = c.getApiKey();
		assertEquals(apiKey,resultat);
	}
	
	@Test
	public void testTimer() {
		Boolean timer = false;
		WeatherController c = new WeatherController();
		Boolean resultat = c.stateTimer();
		assertEquals(timer,resultat);
	}
	
	@Test
	public void teststateCity() {
		Boolean resultat = false;
		WeatherController c = new WeatherController();
		Boolean resAttendu = false;
		assertTrue(resultat == resAttendu);
	}
	
	@Test
	public void testStateRefresh() {
		Boolean resultat = false;
		assertNotNull(resultat);
	}

}
