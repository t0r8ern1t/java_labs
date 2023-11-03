import static java.lang.Math.*;

public class Room {
    // id - уникальный номер, используется для сопоставления зала и сеанса
    // type - тип зала, тк в одном зале может быть несколько сеансов
    private int id;
    private int type;
    private Seat[][] seats;

    //создаем зал размером n x m все места свободны и задаем цену в зависимости от удаленности места от края зала
    public Room(int id, int type, int rows, int cols){
        this.id = id;
        this.type = type;
        this.seats = new Seat[rows][cols];
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                int koef = min(rows-i+1, i+1) * min(cols-j+1, j+1);
                this.seats[i][j] = new Seat(koef*10);
            }
        }
    }
    //проверка того занято ли место и соответствующий ответ пользователю
    public boolean take_seat(int row, int col){
        if (seats[row][col].is_taken() == 1){
            System.out.println("Место занято, выберите другое");
            return false;
        }
        else {
            System.out.println("Цена билета " + seats[row][col].get_price());
            System.out.println("Подтверждение не нужно, ваши деньги уже на нашем счету, приятного просмотра");
            seats[row][col].take_seat();
            return true;
        }
    }
    // заполнение мест (для загрузки из файла)
    public void fill_seats(int row, int col){
        seats[row][col].take_seat();
    }

    public int get_id(){
        return id;
    }
    public int get_type() { return type; }
    public int get_rows() { return seats.length; }
    public int get_cols() { return seats[0].length; }

    public int[][] get_room_arr(){
        int[][] tmp = new int[seats.length][seats[0].length];
        for (int i = 0; i < seats.length; i++){
            for (int j = 0; j < seats[0].length; j++){
                tmp[i][j] = seats[i][j].is_taken();
            }
        }
        return tmp;
    }

    public void get_room(){
        System.out.println("Зал " + type);
        for (int i = 0; i < seats.length; i++){
            for (int j = 0; j < seats[0].length; j++){
                System.out.print(seats[i][j].is_taken());
            }
            System.out.println();
        }
    }

}
