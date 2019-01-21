
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;


public class RestClient 
{
	
	
	static String rootURL = "https://api.themoviedb.org/3/"; 
	static String key = "api_key=" + ApiConfig.getApiKey();
	private Client client;
	public RestClient() { client = ClientBuilder.newClient(); }
	
	
	public static void SearchMovie(String movieTitle)
	{
		String _searchInput = movieTitle.replaceAll("\\s", "+");
		String searchURL = rootURL + "search/movie?"+ key + "&query=" + _searchInput;
		RestClient restClient = new RestClient();
		GenericType<String> string = new GenericType<String>() {};
		String s = restClient.client
	         .target (searchURL)
	         .request(MediaType.APPLICATION_JSON)
	         .get(string); 
		
		JSONUtility.getByTitle(s);

	}
	
	public static void SearchActor(String actorName)
	
	
	{
		
		String searchInput = actorName.replaceAll("\\s", "+");
		
		RestClient restClient = new RestClient();
		GenericType<String> string = new GenericType<String>() {};
		String s = restClient.client
	         .target (rootURL + "search/person?" + key + "&query=" +  searchInput)
	         .request(MediaType.APPLICATION_JSON)
	         .get(string); 
		
		
		JSONUtility.getByActor(s);

	}
	
	
	public static String searchID(String ID) 
	{
		
		String URL = rootURL + "movie/" + ID + "?" + key;
		RestClient restClient = new RestClient();
		GenericType<String> string = new GenericType<String>() {};
		String s = restClient.client
	         .target (URL)
	         .request(MediaType.APPLICATION_JSON)
	         .get(string); 
		return JSONUtility.getByID(s);
		
	}
	
	
}
