// javac -encoding ISO-8859-1 file_name.java
import java.io.*;
import java.util.Scanner;

/**
 * @author EvelinaTkachenko
 */
public class Main {
    /**
     * Основной класс
     * @param args - параметры с консоли, в частности путь к файлу
     * @throws IllegalArgumentException - если путь не задан
     */
    public static void main(String[] args) throws IllegalArgumentException {
        //"Пытаемся" создать сканнер, который считает наши символы в файле в сиде строки, если:
        //1.Если не задать путь в консоли, получим IndexOutOfBoundsException, отловим его и выведем сообщение
        //2.Путь задан, но файл не удается открыть, ловим IOException и генерируем IllegalArgumentException
        try (Scanner in = new Scanner(new FileReader(args[0]))) {
            int count = 0;
            //Если удалось создать сканнер и ему есть, что читать, то:
            while(in.hasNextLine()){
                /*
                Используем метод split(" "), чтобы разбить полученную строку на слова,
                если между словами несколько пробелов, этот метод вернет n-1 пустое слово, между значимыми, n-кол-во пробелов между словами
                */
                String[] words = in.nextLine().split(" ");
                //Пробегаемся по полученному массиву
                for(String word : words){
                    //Не учитываем "пустышки"
                    if(w!=""){
                        count++;
                    }
                }
            }
            System.out.println(count);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Path is not specified.");
        } catch (IOException ex) {
            System.out.println("This file can't be opened!");
        }
    }
}

