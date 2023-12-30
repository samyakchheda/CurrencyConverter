import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
public class currencyConverter1
{
    public static void main(String args[])
    {
          try {

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter the amount to convert: ");
            double amountToConvert = scanner.nextDouble();

            System.out.print("Enter the symbol of the currency you want to convert from: ");
            String currencyFrom = scanner.next().toUpperCase();

            System.out.print("Enter the symbol of currency you want to convert to: ");
            String currencyTo = scanner.next().toUpperCase();

          
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://currency-converter18.p.rapidapi.com/api/v1/convert?from=" + currencyFrom + "&to=" + currencyTo + "&amount=" + amountToConvert))
                    .header("X-RapidAPI-Key", "5a49871c0cmsh9b15b2793087336p143bd4jsn63eda080b121")
                    .header("X-RapidAPI-Host", "currency-converter18.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            
            int statusCode = response.statusCode();
           
            if (statusCode == 200) {
                parseConvertedAmount(response.body());
                
            } else {
                System.out.println("HTTP request failed with status code: " + statusCode);
                System.out.println(response.body()); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void parseConvertedAmount(String responseBody) {
        
            int ind = responseBody.indexOf("convertedAmount");
            int endin = responseBody.length() - 2;
           System.out.println("Converted Amount: "+responseBody.substring(ind + 17, endin));
    }
    
}