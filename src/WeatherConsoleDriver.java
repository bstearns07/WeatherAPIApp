/****************************************************************************************************************
 * Program Title: JavaFX Weather API App
 * Author: Ben Stearns
 * Date: 4-11-2024
 * Description: class to test application for console output
 * Known Issues: none
 ***************************************************************************************************************/
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Scanner;

public class WeatherConsoleDriver {

    /*** Declare class variables ***/
    static double latitude;
    static double longitude;
    static int zipCode;

    /*** Define methods to return latitude and longitude to WeatherService class ***/
    public static double getLatitude() {
        return latitude;
    }
    public static double getLongitude() {
        return longitude;
    }

    public static int getZipCode() {
        return zipCode;
    }

    /*** Begin main method ***/
    public static void main(String[] args) {

        /*** Ask user for latitude and longitude coordinates and store into class variables ***/

        /*Norfolk's coordinates:
         * latitude: 42.0327
         * longitude:-97.4168
         */
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter latitude: ");
        latitude = scan.nextDouble();
        System.out.print("Enter longitude: ");
        longitude = scan.nextDouble();

//        /*** Ask for zipcode ***/
//        System.out.print("Enter zip code: ");
//        zipCode = scan.nextInt();

        /*** Get current weather object and display its results ***/
        JSONObject driverCurrentWeatherInfo = WeatherService.getCurrentWeather();

        /*** Get daily weather object and display its results ***/
        JSONObject driverDailyWeatherInfo = WeatherService.getDailyWeather();

        /*** Parse current weather data from JSONObject ***/
        double temperatureFahrenheit = driverCurrentWeatherInfo.getDouble("temperature_2m");
        double windSpeed = driverCurrentWeatherInfo.getDouble("wind_speed_10m");
        int relativeHumidity = driverCurrentWeatherInfo.getInt("relative_humidity_2m");

        /*** Parse daily weather data from JSONObject ***/
        JSONArray highTemp = driverDailyWeatherInfo.getJSONArray("temperature_2m_max");//daily weather returns an array a numbers, so must be stored that way
        JSONArray lowTemp = driverDailyWeatherInfo.getJSONArray("temperature_2m_min");

        /*** Print weather data to console ***/
        System.out.println("Temperature in Fahrenheit: " + temperatureFahrenheit);
        System.out.println("Wind speed: " + windSpeed + "mph");
        System.out.println("Relative humidity: " + relativeHumidity + "%");
        System.out.println("High of: " + highTemp.getDouble(highTemp.length() - 1) + " degrees");//choose the last number in the array to ensure 42.the latest information
        System.out.println("Low of: " + lowTemp.getDouble(lowTemp.length() - 1) + " degrees");
    }
}