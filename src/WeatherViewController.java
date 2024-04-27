/****************************************************************************************************************
 * Program Title: JavaFX Weather API App
 * Author: Ben Stearns
 * Date: 4-11-2024
 * Description: Class for handling user interactions (e.g., button clicks).
 * 		        Communicates with the WeatherService to fetch weather data.
 * 		        Updates the WeatherView with new data.
 * Known Issues: none
 ***************************************************************************************************************/
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.fxml.FXML;
import org.json.JSONArray;
import org.json.JSONObject;
import java.net.URL;
import java.util.ResourceBundle;

public class WeatherViewController implements Initializable {

    /**** Implement initialize() method from interface class ***/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /*** Define class variables ***/
    int zipCode;
    static double latitude;
    static double longitude;
    @FXML
    private TextField tfZipCode;
    @FXML
    private Button btnGetWeather;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnExit;
    @FXML
    private Text txtTemperatureOutput;
    @FXML
    private Text txtHumidityOutput;
    @FXML
    private Text txtPrecipitationOutput;
    @FXML
    private Text txtWindSpeedOutput;
    @FXML
    private Text txtHighTempOutput;
    @FXML
    private Text txtLowTempOutput;
    @FXML
    private TextField tfLatitude;
    @FXML
    private TextField tfLongitude;
    @FXML
    private Text txtLatitudeErrorMessage;
    @FXML
    private Text txtLongitudeErrorMessage;
    private WebView webView;


    /*** Getter methods to return latitude and longitude so Weather Service can access them ***/
    public static double getLatitude() {
        return latitude;
    }

    public static double getLongitude() {
        return longitude;
    }

    /*** Exit button method ***/
    @FXML
    private void exitApplication() {
        Platform.exit();
    }

    @FXML
    /*** Method for Clear button to clear all text output and user input ***/
    private void clearText(){
        txtTemperatureOutput.setText("");
        txtHumidityOutput.setText("");
        txtPrecipitationOutput.setText("");
        txtWindSpeedOutput.setText("");
        txtHighTempOutput.setText("");
        txtLowTempOutput.setText("");
        tfLatitude.clear();
        tfLongitude.clear();
        txtLatitudeErrorMessage.setText("");
        txtLongitudeErrorMessage.setText("");
    }

    /*** Method for Get Weather button ***/
    @FXML
    private void GetWeather() {

        latitude = Double.parseDouble(tfLatitude.getText().trim());
        longitude = Double.parseDouble(tfLongitude.getText().trim());

        //Get current, daily, and hourly weather information from Open Meteo
        JSONObject currentWeather = WeatherService.getCurrentWeather();
        JSONObject dailyWeather = WeatherService.getDailyWeather();
        JSONObject hourlyWeather = WeatherService.getHourlyWeather();

        //Parse information into separate areas of memory
        double temperatureFahrenheit = currentWeather.getDouble("temperature_2m");
        double windSpeed = currentWeather.getDouble("wind_speed_10m");
        int relativeHumidity = currentWeather.getInt("relative_humidity_2m");
        JSONArray highTemp = dailyWeather.getJSONArray("temperature_2m_max");//API returns as floating point array
        JSONArray lowTemp = dailyWeather.getJSONArray("temperature_2m_min");//API returns as floating point array
        JSONArray precipitationProbability = hourlyWeather.getJSONArray("precipitation_probability");

        //output to text fields
        txtTemperatureOutput.setText(String.valueOf(temperatureFahrenheit) + "\u00B0");
        txtHighTempOutput.setText("" + highTemp.getDouble(lowTemp.length() - 1) + "\u00B0");//always info in last index for latest info
        txtLowTempOutput.setText("" + lowTemp.get(highTemp.length() - 1) + "\u00B0");
        txtPrecipitationOutput.setText("" + precipitationProbability.get(precipitationProbability.length() - 1) + "%");
        txtHumidityOutput.setText("" + relativeHumidity + "%");
        txtWindSpeedOutput.setText(String.valueOf(windSpeed + "mph"));

        //Clear latitude and longitude text fields and shift focus back to txtLatitude
        tfLatitude.requestFocus();
        tfLatitude.clear();
        tfLongitude.clear();

    }

    /*** Methods to process if user clicks the enter button ***/
    @FXML
    private void ProcessGetWeatherClick(KeyEvent enterEvent){
        if (enterEvent.getCode() == KeyCode.ENTER){
            GetWeather();
        }
    }
    @FXML
    private void ProcessClearButtonClick(KeyEvent enterEvent){
        if (enterEvent.getCode() == KeyCode.ENTER){
            clearText();
        }
    }
    @FXML
    private void ProcessExitClick(KeyEvent enterEvent){
        if (enterEvent.getCode() == KeyCode.ENTER){
            exitApplication();
        }
    }
    public void processEscapeKeyClick(KeyEvent keyEvent){
        if (keyEvent.getCode() == KeyCode.ESCAPE){
            exitApplication();
        }
    }
}