//Класс для носителя информации: CD-диск
public class CD extends Media {
    /**
     * Конструктор класса, инициализирующий необходимые поля
     * @param songs - список песен
     */
    CD(Song[] songs){
        this.songs = songs;
        //Проверка на "чистоту списка" (пустого массива песен)
        if(songs.length > 0){
            this.playNow = songs[0];
        }
        this.i = 0;
    }
}
