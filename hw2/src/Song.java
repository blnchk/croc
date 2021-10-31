//Класс, инициализирующий произведение
public class Song {
    //Название произведения
    private String songName = "";
    //Автор произведения
    private String songAuthor = "";

    //Конструктор по умолчанию
    Song(){
    }
    /**
     * Конструктор класса
     * @param author
     * @param name
     */
    Song(String author, String name){
        if (name == "") {
            songName = "unknown";
        }
        else {
            songName = name;
        }
        if (author == "") {
            songAuthor = "unknown";
        }
        else {
            songAuthor = author;
        }
    }
    /**
     * Метод, вовзвращающий информацию о экзмепляре класса
     * @return - спрока вида: Автор - Композиция
     */
    public String retInfo(){
        return this.songAuthor + " - " + this.songName;
    }

}
