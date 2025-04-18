package ems.Utils;

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

    private static final String CONSUMER_KEY = "YOUR_CONSUMER_KEY";
    private static final String CONSUMER_SECRET = "YOUR_CONSUMER_SECRET";
    private static final String SHORTCODE = "174379";
    private static final String PASSKEY = "";
    private static final String CALLBACK_URL = "https://sandbox.safaricom.co.ke/mpesa/";


    public static String getAccessToken() throws Exception {
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

    public static void triggerSTKPush(String token, String phoneNumber, String amount) throws Exception {
        // Format the timestamp correctly
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String password = Base64.getEncoder().encodeToString((SHORTCODE + PASSKEY + timestamp).getBytes());



        Map<String, Object> payload = new HashMap<>();
        payload.put("BusinessShortCode", SHORTCODE);
        payload.put("Password", password);
        payload.put("Timestamp", timestamp);
        payload.put("TransactionType", "CustomerPayBillOnline");
        payload.put("Amount", amount); // Amount to charge
        payload.put("PartyA", "25471234567"); // Customer's phone number in international format
        payload.put("PartyB", SHORTCODE);
        payload.put("PhoneNumber", phoneNumber); // Customer's phone number in international format
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
