import java.util.Scanner;

// список фильмов, залов и занятые места можно менять как через консоль, так и вручную
// через .txt файлы. во втором случае нужно обращать внимание на айдишники сеансов и соответствующих залов в seances_rooms.txt
// автосохранение включено см. Menu.java:77
public class Main {
    public static void main(String[] args) {
        int access = 2;
        while (access != 0) {
            System.out.println("Чтобы получить доступ админа, введите 1");
            System.out.println("Чтобы получить доступ посетителя, введите 2");
            System.out.println("Чтобы выйти, введите 0");

            Scanner in = new Scanner(System.in);
            access = in.nextInt();
            Menu menu = new Menu(access);
        }
    }
}