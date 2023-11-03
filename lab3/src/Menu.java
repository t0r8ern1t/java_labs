import java.util.Scanner;

// общая структура меню, все используемые методы описаны в классе Cinema
public class Menu {
    private Cinema cinema;
    public Menu(int access){
        cinema = new Cinema();
        cinema.read_from_file();
        switch (access){
            //админ
            case 1:
                System.out.println("Добавить зал - 1");
                System.out.println("Добавить фильм - 2");
                System.out.println("Добавить сеанс - 3");
                System.out.println("Доступные фильмы - 4");
                System.out.println("Все сеансы - 5");
                System.out.println("Доступные залы - 6");
                System.out.println("Очистить сохранение - 7");
                System.out.println("Смена режима доступа - 0");
                int command1 = 1000;
                while (command1 != 0) {
                    Scanner in1 = new Scanner(System.in);
                    command1 = in1.nextInt();
                    switch (command1){
                        case 1:
                            cinema.add_room();
                            break;
                        case 2:
                            cinema.add_movie();
                            break;
                        case 3:
                            cinema.add_seance();
                            break;
                        case 4:
                            cinema.get_all_movies();
                            break;
                        case 5:
                            cinema.get_all_seances();
                            break;
                        case 6:
                            cinema.get_all_rooms();
                            break;
                        case 7:
                            cinema.clear_files();
                            break;

                    }
                }
                break;
            //посетитель
            case 2:
                System.out.println("Доступные фильмы - 1");
                System.out.println("Ближайшие сеансы - 2");
                System.out.println("Все сеансы - 3");
                System.out.println("Купить билет - 4;");
                System.out.println("Смена режима доступа - 0");
                int command0 = 1000;
                while (command0 != 0) {
                    Scanner in0 = new Scanner(System.in);
                    command0 = in0.nextInt();
                    switch (command0){
                        case 1:
                            cinema.get_all_movies();
                            break;
                        case 2:
                            cinema.get_closest_seance();
                            break;
                        case 3:
                            cinema.get_all_seances();
                            break;
                        case 4:
                            cinema.buy_ticket();
                            break;
                    }
                }

                break;
        }
        cinema.save_to_file();
    }

}
