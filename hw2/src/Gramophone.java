//Класс для устройства: Граммофон
//Поддерживаемые носители: Виниловая пластинка
public class Gramophone extends Device {
    /**
     * @see CDPlayer#CDPlayer(Media)
     */
    Gramophone(Media media){
        this.name = "Gramophone";
        this.media = media;
        this.interoper = media instanceof Vinyl;
    }
}
