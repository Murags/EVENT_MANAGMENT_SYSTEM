import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MpesaSTKPush {

    private static final String CONSUMER_KEY = "RPjjywZDygUeB6iUVqy6OOOPJKiL68z";
    private static final String CONSUMER_SECRET = "3hTj9xeV6brORo";
    private static final String SHORTCODE = "174379";
    private static final String PASSKEY = "bfb279f9aa9bdbcf158e97dd71a4672e0c893059b10f78e6b72ada1ed2c919";
    private static final String CALLBACK_URL = "https://sandbox.safaricom.co.ke/mpesa/";

    public static void main(String[] args) throws Exception {
        String token = getAccessToken();
        if (token != null) {
            triggerSTKPush(token);
        }
    }

    private static String getAccessToken() throws Exception {
        String auth = CONSUMER_KEY + ":" + CONSUMER_SECRET;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://sandbox.safaricom.co.ke/oauth/v1/generate?grant_type=client_credentials"))
                .header("Authorization", "Basic " + encodedAuth)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Log the status code and response body for debugging
        System.out.println("Status code: " + response.statusCode());
        System.out.println("Response body: " + response.body());

        if (response.statusCode() == 200) {
            Map<String, Object> result = new ObjectMapper().readValue(response.body(), HashMap.class);
            return (String) result.get("access_token");
        } else {
            System.err.println("Failed to get access token: " + response.body());
            return null;
        }
    }

    private static void triggerSTKPush(String token) throws Exception {
        // Format the timestamp correctly
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String password = Base64.getEncoder().encodeToString((SHORTCODE + PASSKEY + timestamp).getBytes());

        Map<String, Object> payload = new HashMap<>();
        payload.put("BusinessShortCode", SHORTCODE);
        payload.put("Password", password);
        payload.put("Timestamp", timestamp);
        payload.put("TransactionType", "CustomerPayBillOnline");
        payload.put("Amount", "1"); // Amount to charge
        payload.put("PartyA", "254708626805"); // Customer's phone number in international format
        payload.put("PartyB", SHORTCODE);
        payload.put("PhoneNumber", "254708626805"); // Customer's phone number in international format
        payload.put("CallBackURL", CALLBACK_URL);
        payload.put("AccountReference", "AccountReference");
        payload.put("TransactionDesc", "Payment description");

        String payloadJson = new ObjectMapper().writeValueAsString(payload);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://sandbox.safaricom.co.ke/mpesa/stkpush/v1/processrequest"))
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(payloadJson))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
}
