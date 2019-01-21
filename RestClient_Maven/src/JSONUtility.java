import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONUtility 
{
	public static ArrayList<Film> list = new ArrayList<Film>();

	public static void getByTitle(String s)
	{
		
		JSONObject obj_JSON = new JSONObject(s);
		JSONArray results = obj_JSON.getJSONArray("results"); // Ger längden på Arrrayen
		String name ="";
		
		getData(results, name);	
	}
	
	public static String getByID(String s) {
		
		
			JSONObject obj_JSON = new JSONObject(s);
			int id = obj_JSON.getInt("id");
			String original_title = obj_JSON.getString("original_title");
			String overview = obj_JSON.getString("overview");
			String release_date = obj_JSON.getString("release_date");
			Double vote_average = obj_JSON.getDouble("vote_average");
			String poster_path = obj_JSON.getString("poster_path");
			String backdrop_path = obj_JSON.getString("backdrop_path");
			String imdb_id = obj_JSON.getString("imdb_id");
			
			return "Titel: " +  original_title + "\n" + "\n" + "ID: " + id + "\n" +  "Från " + release_date + "\n" + "betyg " + vote_average
					+  "\n" + "Beskrivning " + overview +  "\n" +  "______________________________________________________________________________" + "\n"+  "\n"+  "Poster:  " + poster_path +  "\n" + "Backdrop: " + backdrop_path +  "\n" + "IMDB ID: " + imdb_id; 
			
		
	}
	
	public static void getByActor(String s)
	{
	
		JSONObject obj_JSON = new JSONObject(s);
	
		
		if (!s.substring(26, 27).equalsIgnoreCase("0"))
		{
			JSONArray results = obj_JSON.getJSONArray("results");
			JSONObject innerObj = results.getJSONObject(0);
			JSONArray known_for = innerObj.getJSONArray("known_for");
			String name = innerObj.getString("name");
			
			getData(known_for, name);
		}
	}
	
	public static void getData(JSONArray arrayName, String name){
		
		for (int i = 0; i < arrayName.length(); i++) 
    	{
			JSONObject filmInstans = arrayName.getJSONObject(i);
			
			int id = filmInstans.getInt("id");
			String original_title = filmInstans.getString("original_title");
			String overview = filmInstans.getString("overview");
			String release_date = filmInstans.getString("release_date");
			double vote_average = filmInstans.getDouble("vote_average");
			Film film = new Film(id, original_title, overview, release_date, vote_average, name);
			list.add(film);
			
    	}
	}

}
