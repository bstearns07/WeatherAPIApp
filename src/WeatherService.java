/***************************************************************************************************************
 * Program Title: JavaFX Weather API App
 * Author: Ben Stearns
 * Date: 4-11-2024
 * Description: class that handles the API to Open-Meteo and returns a weather object
 * Known Issues: none
 ***************************************************************************************************************/
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class WeatherService {

    /*** Declare class variables ***/
    static JSONObject currentWeather;
    static JSONObject dailyWeather;
    static JSONObject hourlyWeather;
    static double latitude = WeatherViewController.getLatitude();
    static double longitude = WeatherViewController.getLongitude();

    /*** Methods to validate latitude and longitude ***/
    private static boolean isValidLatitude(double latitude){
        // Get latitude and longitude from controller class. Validate valid entries were given
        if (latitude >= -90 && latitude <= 90)
            return true;
        else
            return false;
    }
    private static boolean isValidLongitude(double longitude){
        if (longitude > -180 && longitude > 180)
            return true;
        else
            return false;
    }

    /*** Define method to return weather object ***/
    public static JSONObject getCurrentWeather(){

        /*** String to store API request ***/
        String urlString = String.format("https://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f&current=temperature_2m,relative_humidity_2m,is_day,precipitation,wind_speed_10m,wind_direction_10m&hourly=precipitation_probability&daily=temperature_2m_max,temperature_2m_min,sunrise,sunset&temperature_unit=fahrenheit&wind_speed_unit=mph&precipitation_unit=inch",latitude,longitude);

        /*** Try and connect to API and get a response ***/
        try {
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) !=null){
                response.append(inputLine);
            }
            in.close();//close buffer reader

            /*** Parse response into JSONObject using the appropriate key ***/
            JSONObject jsonResponse = new JSONObject(response.toString());
            System.out.println("JSON response: " + response);//print response for referencing purposes
            currentWeather = jsonResponse.getJSONObject("current");

        } catch(Exception ex){
            ex.printStackTrace();
        }
        return currentWeather;
    }

    /*** Create method that does the same, but returns daily weather information ***/
    public static JSONObject getDailyWeather(){
        /*** String to store API request ***/
        String urlString = String.format("https://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f&current=temperature_2m,relative_humidity_2m,is_day,precipitation,wind_speed_10m,wind_direction_10m&hourly=precipitation_probability&daily=temperature_2m_max,temperature_2m_min,sunrise,sunset&temperature_unit=fahrenheit&wind_speed_unit=mph&precipitation_unit=inch",latitude,longitude);

        /*** Try and connect to API and get a response ***/
        try {
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) !=null){
                response.append(inputLine);
            }
            in.close();//close buffer reader

            /*** Parse response into JSONObject using the appropriate key ***/
            JSONObject jsonResponse = new JSONObject(response.toString());
            System.out.println("JSON response: " + response);//print response for referencing purposes
            dailyWeather = jsonResponse.getJSONObject("daily");

        } catch(Exception ex){
            ex.printStackTrace();
        }
        return dailyWeather;
    }

    /*** Method to return Hourly weather information ***/
    public static JSONObject getHourlyWeather(){

        /*** String to store API request ***/
        String urlString = String.format("https://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f&current=temperature_2m,relative_humidity_2m,is_day,precipitation,wind_speed_10m,wind_direction_10m&hourly=precipitation_probability&daily=temperature_2m_max,temperature_2m_min,sunrise,sunset&temperature_unit=fahrenheit&wind_speed_unit=mph&precipitation_unit=inch",latitude,longitude);

        /*** Try and connect to API and get a response ***/
        try {
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) !=null){
                response.append(inputLine);
            }
            in.close();//close buffer reader

            /*** Parse response into JSONObject using the appropriate key ***/
            JSONObject jsonResponse = new JSONObject(response.toString());
            System.out.println("JSON response: " + response);//print response for referencing purposes
            hourlyWeather = jsonResponse.getJSONObject("hourly");

        } catch(Exception ex){
            ex.printStackTrace();
        }
        return hourlyWeather;
    }
}