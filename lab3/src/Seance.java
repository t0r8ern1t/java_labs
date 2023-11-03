// в этом классе хранится информация о каждом отдельном сеансе:
// фильм, продолжительность и занятые места в зале
public class Seance {
    private Movie movie;
    private Room room;
    private String start;
    private String end;
    private int id;

    public Seance(){
        this.id = -1;
        this.start = "25.60";
        this.end = "25.60";
    }
    public Seance(int id, Movie movie, Room room, String start, String end){
        this.id = id;
        this.movie = movie;
        this.room = room;
        this.start = start;
        this.end = end;
    }
    public void get_info(){
        System.out.println(get_id() + " Фильм " + movie.get_title() + " в зале " + room.get_type());
        System.out.println("Начало сеанса: " + start);
        System.out.println("Конец сеанса: " + end + "\n");
    }

    public int get_id() { return id; }
    public Movie get_movie() {
        return movie;
    }
    public Room get_room() {
        return room;
    }
    public String get_start() {
        return start;
    }
    public String get_end() {
        return end;
    }
}
