/**
 * @author Evelina Tkachenko
 */
public class Main {
    // Основной метод, здесь проведем проверку на работоспособность.
    public static void main(String[] args) {
        //Создание нескольких звуковоспр. устройств.
        Device d1 = new Device("MP3-плеер",new String[] {"карта памяти"});
        Device d2 = new Device("Виниловая вертушка",new String[] {"пластинка"});
        Device d3 = new Device("Универсальный плеер",new String[] {"диск","флешка"});

        //Инициализация музыкальной системы, содержащую наши устройства.
        Player ourPlayer = new Player(new Device[] {d1,d2,d3});

        //Созздание нескольких экземпляров муз. композиций
        Song s1 = new Song("Бетховен", "Симфония 5, Часть I");
        Song s2 = new Song("Орф, Carmina Burana","Фортуна");
        Song s3 = new Song("Верди,Реквием", "Dies Irae");

        //Создание носителя и проверка работы, когда всё корректно
        Media classic = new Media(new Song[] {s1,s2,s3}, "Карта памяти");
        ourPlayer.play(classic);
        ourPlayer.nextSong();
        ourPlayer.prevSong();

        System.out.println();
        //Создание носителя и проверка на работу, когда ностель пустой
        Media empt = new Media(new Song[]{}, "Пластинка");
        ourPlayer.play(empt);
        ourPlayer.prevSong();
        ourPlayer.nextSong();

        System.out.println();
        //Создание носителя и проверка, когда формат не поддерживается
        Media wrongformat = new Media(new Song[] {s1}, "Дискета");
        ourPlayer.play(wrongformat);
        ourPlayer.prevSong();
        ourPlayer.nextSong();



    }
}
