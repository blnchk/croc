//Класс для устройства: Плеер
//Поддерживаемые носители: Флешка, Карта памяти
public class MediaPlayer extends Device{
    /**
     * @see CDPlayer#CDPlayer(Media)
     */
    MediaPlayer(Media media){
        this.name = "Media player";
        this.media = media;
        this.interoper = media instanceof Flash || media instanceof MemoryCard;
    }
}
