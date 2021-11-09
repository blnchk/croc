import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @author Evelina Tkachenko
 */
public class Min {
    /**
     * Пример входной строки.
     */
    public static final String INPUT_STRING = "Невежество есть мать промышленности, как и суеверий." +
            " Сила размышления и воображения подвержена ошибкам; но привычка двигать рукой или ногой" +
            " не зависит ни от того, ни от другого. Поэтому мануфактуры лучше всего процветают там, где" +
            " наиболее подавлена духовная жизнь, так что мастерская может рассматриваться как машина," +
            " части которой составляют люди.";
    /**
     * Шаблон символа, вхождения которого считаем.
     */
    public static final String TEMPLATE = " ";
    //Кол-во вхождений нужного символа в заданную строку с атрибутом volatile, что сохраняет актуальность
    public static volatile int sum = 0;
    //Переменная, при помощи которой беруться нужные символы из строки, с атрибутом volatile
    public static volatile int j = 0;

    public static void main(String args[]) throws InterruptedException, ExecutionException {
        long current = System.currentTimeMillis();
        //Создаем 5 потоков
        ExecutorService service = Executors.newFixedThreadPool(5);
        //Лист с объектами которые могут быть вызваны, они и будут выполнятся в потоках
        Set<Callable<String>> tasks = new HashSet<Callable<String>>();
        //Добавляем объект - выполняет Matcher.match(str,str), именно этот объект берет символ с индексом j
        tasks.add(new Callable<String>(){
            public String call() throws Exception{
                try {
                    if (Matcher.match(String.valueOf(INPUT_STRING.charAt(j)), TEMPLATE)) {
                        sum++;
                    }
                }
                catch (Exception e){
                    return e.getMessage();
                }
                return "Task 1";
            }
        });
        //Объект, берущий символ с индексом j+1
        tasks.add(new Callable<String>(){
            public String call() throws Exception{
                try {
                    if (Matcher.match(String.valueOf(INPUT_STRING.charAt(j + 1)), TEMPLATE)) {
                        sum++;
                    }
                }
                catch (Exception e){
                    e.getMessage();
                }
                return "Task 2";
            }
        });
        //Объект, берущий символ с индексом j+2
        tasks.add(new Callable<String>(){
            public String call() throws Exception{
                try {
                    if (Matcher.match(String.valueOf(INPUT_STRING.charAt(j + 2)), TEMPLATE)) {
                        sum++;
                    }
                }
                catch (Exception e){
                    return e.getMessage();
                }
                return "Task 3";
            }
        });
        //Объект, берущий символ с индексом j+3
        tasks.add(new Callable<String>(){
            public String call() throws Exception{
                try {
                    if (Matcher.match(String.valueOf(INPUT_STRING.charAt(j + 3)), TEMPLATE)) {
                        sum++;
                    }
                }
                catch (Exception e){
                    return e.getMessage();
                }
                return "Task 4";
            }
        });
        //Объект, берущий символ с индексом j+4
        tasks.add(new Callable<String>(){
            public String call() throws Exception{
                try {
                    if (Matcher.match(String.valueOf(INPUT_STRING.charAt(j + 4)), TEMPLATE)) {
                        sum++;
                    }
                }
                catch (Exception e){
                    return e.getMessage();
                }
                return "Task 5";
            }
        });
        //Лист, сохраняющий результаты работы invokeAll(tasks), который вовзращет объект Future
        List<Future<String>> futures = new ArrayList<Future<String>>();

        //Бежим по строке с шагом = кол-ву потоков, на каждом шаге выполняется 5 проверок символов при помощи Matcher.match(str,str)
        for (;j<INPUT_STRING.length();j=j+5) {
            //invokeAll реализован так, что ждет выполнения всех потоков, которые выполняются, поэтому доп прверок на завершение потоков не требуется
            futures = service.invokeAll(tasks);
        }
        //Закрываем потоки
        service.shutdown();
        //Результат работы: Count of space: 48
        System.out.println("Count of space: " + sum );

        //Результат работы: Time: 7 c. (Изначально было 37с)
        System.out.println("Time: " + (System.currentTimeMillis() - current) / 1000 + " c.");


    }
}

