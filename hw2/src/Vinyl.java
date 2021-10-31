//Класс для носителя информации: Виниловая пластинка
public class Vinyl extends Media {
    /**
     * @see CD#CD(Song[])
     */
    Vinyl(Song[] songs){
        this.songs = songs;
        if(songs.length>0){
            this.playNow = songs[0];
        }
        this.i = 0;
    }
}
