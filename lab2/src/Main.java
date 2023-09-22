import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void problem1(){
        //наибольная подстрока без повторяющихся элементов

        System.out.print("№1\nВведите строку: ");
        Scanner in = new Scanner(System.in);
        String text = in.nextLine();
        String sub = "";
        String max_sub = "";
        int current = 0;
        int end = text.length() - 1;

        while (current <= end){
            String a = Character.toString(text.charAt(current));
            // если следующий элемент является уникальным для подстроки, добавляем
            if (!sub.contains(a)){
                sub = sub + a;
            }
            // если нет, начинаем проверять строку, начиная с символа, который
            // идет за первых вхождением повторного элемента
            else {
                if (max_sub.length() <= sub.length()){
                    max_sub = sub;
                }
                int index = sub.indexOf(a);
                sub = sub.substring(index + 1) + text.charAt(current);
            }
            current += 1;
        }
        System.out.print("Последняя строка с уникальными символами макисмальной длины: " + max_sub + "\n");
    }

    // метод для ввода с консоли одномерного массива для задач 2, 3, 5
    public static int[] getOneArr(){
        Scanner in = new Scanner(System.in);
        int len = in.nextInt();
        int[] arr = new int[len];
        for (int i = 0; i < len; ++i){
            arr[i] = in.nextInt();
        }
        return arr;
    }
    public static void problem2(){
        // объединить два отсортированных массива
        System.out.print("№2\n");
        System.out.print("Введите длину первого массива и элементы, разделяя пробелом: ");
        int[] arr1 = getOneArr();
        System.out.print("Введите длину второго массива и элементы, разделяя пробелом: ");
        int[] arr2 = getOneArr();
        int[] res = new int[arr1.length + arr2.length];
        int iter1 = 0;
        int iter2 = 0;
        for (int k = 0; k < res.length; ++k){
            if (iter1 < arr1.length){
                if (iter2 < arr2.length){
                    if (arr1[iter1] < arr2[iter2]) {
                        res[k] = arr1[iter1];
                        iter1++;
                    }
                    else {
                        res[k] = arr2[iter2];
                        iter2++;
                    }
                }
                else {
                    res[k] = arr1[iter1];
                    iter1++;
                }
            }
            else if (iter2 < arr2.length){
                res[k] = arr2[iter2];
                iter2++;
            }
        }
        System.out.println(Arrays.toString(res) + "\n");
    }

    //TODO
    public static void problem3(){
        // найти максимальную сумму подмассива
        // алгоритм кадане
        System.out.print("№3\nВведите массив, разделяя элементы пробелом: ");
        int[] arr = getOneArr();
        //int[] max_subarr = new;
        int maxsum = 0;

    }

    // ввод с консоли двумерного массива для задач 4, 6, 7
    public static int[][] getDoubleArr(){
        System.out.print("Введите количество строк и столбцов: ");
        Scanner in = new Scanner(System.in);
        int rows = in.nextInt();
        int cols = in.nextInt();
        int[][] arr = new int[rows][cols];
        for (int i = 0; i < rows; ++i){
            for (int j = 0; j < cols; ++j){
                arr[i][j] = in.nextInt();
            }
        }
        return arr;
    }

    // вывод двумерного массива
    public static void printDoubleArr(int[][] arr){
        for (int i = 0; i < arr.length; ++i){
            for (int j = 0; j < arr[0].length; ++j){
                System.out.print(Integer.toString(arr[i][j]) + " ");
            }
            System.out.print("\n");
        }
    }

    public static void problem4(){
        // повернуть двумерный массив на 90 градусов по часовой стрелке
        System.out.print("№4\n");
        int[][] arr = getDoubleArr();
        int rows = arr[0].length; // кол-во строк в перевернутом массиве
        int cols = arr.length; // кол-во столбцов в перевернутом массиве
        int[][] rotated = new int[rows][cols];
        for (int i = 0; i < rows; ++i){
            for (int j = 0; j < cols; ++j){
                rotated[i][j] = arr[cols - j - 1][i];
            }
        }
        System.out.print("Перевернутый массив: " + "\n");
        printDoubleArr(rotated);

    }

    public static void problem5(){
        // найти пару элементов в массивe, сумма которых равна заданному числу
        System.out.print("№5\nВведите длину массива и элементы, разделенные пробелом: ");
        int[] arr = getOneArr();
        System.out.print("Введите сумму: ");
        Scanner in = new Scanner(System.in);
        int sum = in.nextInt();
        int a = 0;
        int b = 0;
        boolean flag = false;
        Arrays.sort(arr);
        for (int i = arr.length - 1; i > 0; --i){
            int j = 0;
            while (arr[j] + arr[i] < sum && j < arr.length - 1){
                j += 1;
            }
            if (arr[i] + arr[j] == sum){
                a = arr[i];
                b = arr[j];
                flag = true;
                break;
            }
        }
        if (flag) {
            System.out.print("Искомые элементы: " + a + " и " + b);
        }
        else {
            System.out.print("Элементы не найдены");
        }
    }

    public static void problem6(){
        // найти сумму всех элементов в двумерном массиве
        System.out.print("№6\n");
        int[][] arr = getDoubleArr();
        int rows = arr.length;
        int cols = arr[0].length;
        int sum = 0;
        for (int i = 0; i < rows; ++i){
            for (int j = 0; j < cols; ++j){
                sum += arr[i][j];
            }
        }
        System.out.print("Сумма всех элементов равна " + sum);
    }

    public static void problem7(){
        // найти максимальный элемент в каждой строке двумерного массива
        System.out.print("№7\n");
        int[][] arr = getDoubleArr();
        int rows = arr.length;
        int cols = arr[0].length;
        int sum;
        for (int i = 0; i < rows; ++i){
            sum = 0;
            for (int j = 0; j < cols; ++j){
                sum += arr[i][j];
            }
            System.out.print("Длина " + (i+1) + "й строки равна " + sum + "\n");
        }
    }

    public static void problem8(){
        System.out.print("№8\nЗадача удивительным образом совпадает с №4, поэтому полюбуемся на нее еще раз\n\n");
        problem4();
    }

    public static void main(String[] args) {
        //problem1();
        //problem2();
        //problem4();
        //problem5();
        //problem6();
        //problem7();
        //problem8();
    }
}