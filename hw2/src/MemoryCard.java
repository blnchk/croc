//Класс для носителя информации: Карта паяти
public class MemoryCard extends Media {
    /**
     * @see CD#CD(Song[])
     */
    MemoryCard(Song[] songs){
        this.songs = songs;
        if(songs.length>0){
            this.playNow = songs[0];
        }
        this.i = 0;
    }
}
