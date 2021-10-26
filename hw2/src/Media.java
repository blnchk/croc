//Класс, инициализирующий носитель
public class Media  {
    //Список произведений
    private Song[] songList;
    //Формат(вид) носителя
    private String mediaFormat;
    //Количество произведений на данном носителе
    private int countSong;

    /**
     * конструктор класса
     * @param song - список произведений
     * @param mediaFormat - формат(вид) носителя
     */
    Media(Song[] song, String mediaFormat){
        this.songList = song;
        this.mediaFormat = mediaFormat;
        countSong = song.length;
    }

    /**
     * Доступ к занчению countSong
     * @return - вовзвращает количество произвдений
     */
    int getCount(){
        return countSong;
    }

    /**
     * Доступ к элементам songList по заданному индексу
     * @param i - номер песни
     * @return - возвращаем песню под заданным номером
     */
    public Song retSong(int i){
        return this.songList[i];
    }

    /**
     *
     * @return - возвращает формат носителя
     */
    public String getFormat(){
        return this.mediaFormat;
    }

}
