/**
 * @author Evelina Tkachenko
 */
public class Main {
    // Основной метод, здесь проведем проверку на работоспособность.
    public static void main(String[] args) {
        //Создание нескольких экземпляров муз. композиций
        Song s1 = new Song("Бетховен", "Симфония 5, Часть I");
        Song s2 = new Song("Орф, Carmina Burana","Фортуна");
        Song s3 = new Song("Верди,Реквием", "Dies Irae");
        Song[] music = new Song[]{s1,s2,s3};
        //Содание носителя
        Flash fl = new Flash(music);
        System.out.println("Попытка проиграть музыку с флешки на граммофоне:");
        //Попробуем проиграть это на граммофоне
        Gramophone gram = new Gramophone(fl);
        gram.play();
        //Создадим виниловую пластинку
        Vinyl vin = new Vinyl(music);
        //Сменим носитель
        System.out.println("\nПроиграем на граммофоне пластинку с классикой:");
        gram = new Gramophone(vin);
        System.out.println("Метод play():");
        gram.play();
        System.out.println("nextSong():");
        gram.nextSong();
        gram.nextSong();
        gram.nextSong();
        System.out.println("prevSong:");
        gram.prevSong();
        System.out.println("\nПоведение при пустом совместимом носителе:");

        //Создание носителя и проверка на работу, когда ностель пустой
        Vinyl clearVin = new Vinyl(new Song[]{});
        //Сменим носитель
        gram = new Gramophone(clearVin);
        gram.play();
        gram.nextSong();
        gram.prevSong();




    }
}
