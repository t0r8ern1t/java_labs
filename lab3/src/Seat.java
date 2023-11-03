// TODO стоимость каждого билета считается по расположению в зале и ценовому коэффициенту фильма

public class Seat {
    private int price;
    private int taken;

    public Seat(){
        this.price = 10;
        this.taken = 0;
    }
    public Seat(int price){
        this.price = price;
        this.taken = 0;
    }
    public void take_seat(){
        taken = 1;
    }
    public int is_taken(){
        return taken;
    }
    public int get_price(){
        return price;
    }
}
