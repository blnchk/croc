//Класс для носителя информации: CD-диск
public class CD extends Media {
    //Конструктор класса, инициализирующие необходимые поля
    CD(Song[] songs){
        this.songs = songs;
        //Проверка на "чистоту списка" (пустого массива песен)
        if(songs.length > 0){
            this.playNow = songs[0];
        }
        this.i = 0;
    }
}
