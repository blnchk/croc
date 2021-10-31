//Класс для устройства: Магнитофон
//Поддерживаемые носители: Касета
public class Recorder extends Device {
    /**
     * @see CDPlayer#CDPlayer(Media)
     */
    Recorder(Media media){
        this.name = "Recorder";
        this.media = media;
        this.interoper = media instanceof Tape;
    }
}
