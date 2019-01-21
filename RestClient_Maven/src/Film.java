public class Film {
	
	int id;
	String original_title;
	String overview;
	String release_date;
	double vote_average;
	String name;
	
	
	public Film(int id, String original_title, String overview, String release_date, double vote_average, String name) {
		super();
		this.id = id;
		this.original_title = original_title;
		this.overview = overview;
		this.release_date = release_date;
		this.vote_average = vote_average;
		this.name = name;
	}

	
	
	
	public String toString() {
		return "Titel: " +  original_title + "\n" + "\n" + "ID: " + id + "\n" +  "Från: " + release_date + "\n" + "Betyg: " + vote_average
	+  "\n" + "Beskrivning: " + overview; }

	
}
