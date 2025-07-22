import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

public class WeatherApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String apiKey = "2867ba65fd135f03af823539369e2600"; // Replace with your actual key

        System.out.print("Enter city name: ");
        String city = scanner.nextLine();

        try {
            String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" +
                    city + "&appid=" + apiKey + "&units=metric";

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
            );
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();

            JSONObject obj = new JSONObject(response.toString());

            System.out.println("\n📍 City: " + obj.getString("name") + ", " + obj.getJSONObject("sys").getString("country"));
            System.out.println("🌡️ Temperature: " + obj.getJSONObject("main").getDouble("temp") + "°C");
            System.out.println("🌤️ Weather: " + obj.getJSONArray("weather").getJSONObject(0).getString("description"));
            System.out.println("💧 Humidity: " + obj.getJSONObject("main").getInt("humidity") + "%");
            System.out.println("💨 Wind Speed: " + obj.getJSONObject("wind").getDouble("speed") + " m/s");

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }

        scanner.close();
    }
}
