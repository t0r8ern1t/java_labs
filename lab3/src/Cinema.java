import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// в этом классе релизованы все методы, на которые ссылается меню
// а также хранится вся информация о залах и фильмах
public class Cinema {

    private ArrayList<Room> rooms;
    private ArrayList<Movie> movies;
    private ArrayList<Seance> seances;
    private ArrayList<Room> seances_rooms;

    public Cinema(){
        rooms = new ArrayList<>();
        movies = new ArrayList<>();
        seances = new ArrayList<>();
        seances_rooms = new ArrayList<>();
    }

    public void add_room(){
        System.out.println("Введите номер зала и количество рядов и мест в каждом (предположим, зал прямоугольный)");
        Scanner in = new Scanner(System.in);
        int type = in.nextInt();
        int n = in.nextInt();
        int m = in.nextInt();
        rooms.add(new Room(0, type, n , m));
        System.out.println("Зал добавлен");
    }
    public void add_movie(){
        System.out.println("Введите название фильма");
        Scanner in = new Scanner(System.in);
        String title = in.nextLine();
        //double mult = in.nextDouble();
        int last_id = -1;
        if (!movies.isEmpty()) {
            last_id = movies.get(movies.size() - 1).get_id();
        }
        movies.add(new Movie(title, last_id+1, 1));
        System.out.println("Фильм добавлен");
    }

    public void add_seance() {
        System.out.println("Выберите зал");
        for (Room room : rooms){
            System.out.print(room.get_type() + "\n");
        }
        Scanner in = new Scanner(System.in);
        int room_type = in.nextInt();
        int last_id = -1;
        if (!seances_rooms.isEmpty()) {
            last_id = seances_rooms.get(seances_rooms.size() - 1).get_id();
        }
        seances_rooms.add(new Room(last_id+1, room_type, rooms.get(room_type).get_rows(), rooms.get(room_type).get_cols()));
        Room curr_room = seances_rooms.get(last_id+1);

        System.out.println("Выберите номер фильма");
        for (int i = 0; i < movies.size(); i++){
            System.out.print(i+1 + " ");
            System.out.print(movies.get(i).get_title() + "\n");
        }
        int movie_num = in.nextInt() - 1;
        Movie curr_movie = movies.get(movie_num);

        System.out.println("Введите время начала");
        String start_time = in.nextLine();
        System.out.println("Введите время окончания");
        String end_time = in.nextLine();

        last_id = -1;
        if (!seances.isEmpty()) {
            last_id = seances.get(seances.size() - 1).get_id();
        }
        seances.add(new Seance( last_id+1, curr_movie, curr_room, start_time, end_time));
        System.out.println("Сеанс добавлен");
    }
    public void get_all_movies(){
        if (movies.isEmpty()){
            System.out.println("Фильмов нет :(");
        }
        else{
            for (Movie movie : movies) {
                System.out.print(movie.get_title() + "\n");
            }
        }
    }
    public void get_all_rooms(){
        if (rooms.isEmpty()){
            System.out.println("Залов нет :(");
        }
        else{
            for (Room room : rooms) {
                room.get_room();
            }
        }
    }
    public void get_all_seances(){
        if (seances.isEmpty()){
            System.out.println("Сеансов нет :(");
        }
        else{
            for (Seance seance : seances){
                seance.get_info();
            }
        }
    }
    public void buy_ticket(){
        System.out.println("Выберите номер сеанса:");
        get_all_seances();
        Scanner in = new Scanner(System.in);
        int seance_num = in.nextInt();
        System.out.println("Выберите место, введите ряд и номер");
        seances.get(seance_num).get_room().get_room();
        int row = in.nextInt();
        int col = in.nextInt();
        boolean flag = seances.get(seance_num).get_room().take_seat(row, col);
        while (!flag) {
            row = in.nextInt();
            col = in.nextInt();
            flag = seances.get(seance_num).get_room().take_seat(row, col);
        }
    }
    public void save_to_file(){
        // сохраняем фильмы
        try(FileWriter writer = new FileWriter("movies.txt", false))
        {
            for (Movie movie : movies){
                writer.write(movie.get_id() + " " + movie.get_title() + "\n");
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        // сохраняем залы
        try(FileWriter writer = new FileWriter("rooms.txt", false))
        {
            for (Room room : rooms){
                int[][] room_seats = room.get_room_arr();
                writer.write(room.get_type() + " " + room_seats.length + " " + room_seats[0].length + "\n");
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }

        // сохраняем залы для всех сеансов
        try(FileWriter writer = new FileWriter("seances_rooms.txt", false))
        {
            for (Room seance_room : seances_rooms){
                int[][] room_seats = seance_room.get_room_arr();
                writer.write(seance_room.get_id() + " " + seance_room.get_type() + "\n");
                for (int[] row : room_seats){
                    for (int seat : row){
                        writer.write(seat + " ");
                    }
                    writer.write("\n");
                }
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        //сохраняем сеансы
        try(FileWriter writer = new FileWriter("seances.txt", false))
        {
            for (Seance seance : seances){
                writer.write(seance.get_id() + " " + seance.get_movie().get_id() + " " + seance.get_room().get_id() + " " + seance.get_start() + " " + seance.get_end() + "\n");
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        System.out.println("Сохранено");
    }
    public void read_from_file(){
        // читаем фильмы
        try(Scanner scanner = new Scanner(new File("movies.txt")))
        {
            while (scanner.hasNextLine()){
                int movie_id = scanner.nextInt();
                String movie_title = scanner.nextLine().substring(1);
                movies.add(new Movie(movie_title, movie_id, 1));
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        // читаем залы
        try(Scanner scanner = new Scanner(new File("rooms.txt")))
        {
            while (scanner.hasNextLine()){
                String[] info = scanner.nextLine().trim().split(" ");
                int room_id = 0;
                int room_type = Integer.parseInt(info[0]);
                int room_rows = Integer.parseInt(info[1]);
                int room_cols = Integer.parseInt(info[2]);
                rooms.add(new Room(room_id, room_type, room_rows, room_cols));
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        // читаем залы для всех сеансов
        try(Scanner scanner = new Scanner(new File("seances_rooms.txt")))
        {
            while (scanner.hasNextLine()){
                String[] info = scanner.nextLine().trim().split(" ");
                int seance_room_id = Integer.parseInt(info[0]);
                int seance_room_type = Integer.parseInt(info[1]);
                int seance_room_rows = rooms.get(seance_room_type).get_rows();
                int seance_room_cols = rooms.get(seance_room_type).get_cols();
                Room curr_room = new Room(seance_room_id, seance_room_type, seance_room_rows, seance_room_cols);
                for (int i = 0; i < seance_room_rows; i++){
                    String[] line = scanner.nextLine().trim().split(" ");
                    for (int j = 0; j < seance_room_cols; j++){
                        if (Integer.parseInt(line[j]) == 1){
                            curr_room.fill_seats(i, j);
                        }
                    }
                }
                seances_rooms.add(curr_room);
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        // читаем сеансы
        try(Scanner scanner = new Scanner(new File("seances.txt")))
        {
            while (scanner.hasNextLine()){
                String[] info = scanner.nextLine().trim().split(" ");
                int seance_id = Integer.parseInt(info[0]);
                int movie_id = Integer.parseInt(info[1]);
                int room_type = Integer.parseInt(info[2]);
                String start_time = info[3];
                String end_time = info[4];
                seances.add(new Seance(seance_id, movies.get(movie_id), seances_rooms.get(seance_id), start_time, end_time));
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }

    public void clear_files(){
        try {
            FileWriter pw = new FileWriter("movies.txt");
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter pw = new FileWriter("rooms.txt");
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter pw = new FileWriter("seances.txt");
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter pw = new FileWriter("seances_rooms.txt");
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
