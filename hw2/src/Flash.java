//Класс для носителя информации: Флешка
public class Flash extends Media {
    /**
     * @see CD#CD(Song[])
     */
    Flash(Song[] songs){
        this.songs = songs;
        if(songs.length>0){
            this.playNow = songs[0];
        }
        this.i = 0;
    }
}
