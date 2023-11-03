public class Movie {
    private String title;
    private int id;
    private double price_multiplier;

    public Movie(String title, int id, double price_multiplier){
        this.title = title;
        this.id = id;
        this.price_multiplier = price_multiplier;
    }

    public int get_id(){
        return id;
    }
    public String get_title(){
        return title;
    }
}
