import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //problem1();
        //problem2();
        //problem3();
        //problem4();
        //problem5();
    }
    public static void problem1() {
        System.out.print("№1" + "\n" + "Введите число ");
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        int steps = 0;

        while (num > 1){
            if (num % 2 == 0){
                num = num / 2;
                ++steps;
            }
            else {
                num = 3 * num + 1;
                ++steps;
            }
        }

        if (num == 1) {
            System.out.print("Количество шагов " + steps);
        }
        else {
            System.out.print("Ошибка");
        }
    }

    public static void problem2() {
        System.out.print("№2" + "\n" + "Введите длину ряда ");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int current_num = 0;
        int res = 0;

        for (int i = 0; i < n; ++i) {
            current_num = in.nextInt();
            if (i % 2 == 0) {
                res += current_num;
            }
            else {
                res -= current_num;
            }
        }

        System.out.print("Результат: " + res);
    }

    public static void problem3(){
        System.out.print("№3" + "\n" + "Введите координаты сокровища " + "\n");
        Scanner in = new Scanner(System.in);
        int treasure_x = in.nextInt();
        int treasure_y = in.nextInt();
        int curr_x = 0;
        int curr_y = 0;
        int way = 0;
        int min_way = 1000;
        int steps = 0;
        String direction = null;
        System.out.print("Теперь вводите указания" + "\n");

        while (!Objects.equals(direction, "стоп")) {
            direction = in.nextLine();
            if (Objects.equals(direction, "север")) {
                steps = in.nextInt();
                curr_y += steps;
                way += 1;
            }
            else if (Objects.equals(direction, "юг")) {
                steps = in.nextInt();
                curr_y -= steps;
                way += 1;
            }
            else if (Objects.equals(direction, "восток")) {
                steps = in.nextInt();
                curr_x += steps;
                way += 1;
            }
            else if (Objects.equals(direction, "запад")) {
                steps = in.nextInt();
                curr_x -= steps;
                way += 1;
            }
            if (curr_x == treasure_x && curr_y == treasure_y && way < min_way) {
                min_way = way;
            }
        }
        System.out.print("Минимальное количество шагов " + min_way);
    }

    public static void problem4(){
        System.out.print("№4" + "\n" + "Введите количество дорог " + "\n");
        Scanner in = new Scanner(System.in);
        int roads = in.nextInt();
        int max_height = 0; // самый высокий туннель среди самых низких
        int best_road = 0;

        for (int i = 0; i < roads; ++i) {
            System.out.print("Количество туннелей на дороге " + (i+1) + "\n");
            int tunnels = in.nextInt();
            int curr_height = 0;
            int curr_min = 10000; // самый низкий туннель на i-й дороге
            for (int j = 0; j < tunnels; ++j) {
                curr_height = in.nextInt();
                if (curr_height < curr_min){
                    curr_min = curr_height;
                }
            }
            if (curr_min > max_height){
                max_height = curr_min;
                best_road = i + 1;
            }
        }

        System.out.print("Лучшая дорога " + best_road + ", максимальная доступная высота " + max_height);
    }

    public static void problem5(){
        class Number{
            int _x, _y, _z;
            Number(int x, int y, int z) {
                _x = x;
                _y = y;
                _z = z;
            };
            public boolean ifTwiceEven() {
                if ((_x + _y + _z) % 2 == 0 && (_x * _y * _z) % 2 == 0){
                    return true;
                }
                return false;
            }
        }

        System.out.print("№5" + "\n" + "Введите число " + "\n");
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        if (num / 100 > 9 || num / 10 < 10) {
            System.out.print("Неправильное число");
        }
        else {
            int x = num / 100;
            int y = num % 100 / 10;
            int z = num % 10;
            Number n = new Number(x, y, z);
            if (n.ifTwiceEven()) {
                System.out.print("Число дважды четное");
            }
            else {
                System.out.print("Число не дважды четное");
            }
        }
    }
}