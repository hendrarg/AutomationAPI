package selenium.API;

public class Config {
	
	// Constants for BASE_URL and API_TOKEN
	public static final String BASE_URL = "https://gorest.co.in";
	public static final String API_TOKEN = "Bearer 7ae181045e2c211e897a4cf823c0d1f8976a087f83a9af4e9e595454b296c29b";

	// Function to combine BASE_URL with endpoint
	public static String getApiUrl(String endpoint) {
        return BASE_URL + endpoint;
	}
	// Function to get API_TOKEN
    public static String getApiToken() {
        return API_TOKEN;
    }

}
