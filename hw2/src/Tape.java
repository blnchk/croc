//Класс для носителя информации: Касета
public class Tape extends Media{
    /**
     * @see CD#CD(Song[])
     */
    Tape(Song[] songs){
        this.songs = songs;
        if(songs.length>0){
            this.playNow = songs[0];
        }
        this.i = 0;
    }
}
