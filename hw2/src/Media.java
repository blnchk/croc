//Абстрактный класс-родитель для всех носителей
public abstract class Media {
    //Список песен
    protected Song[] songs;
    //Информация о играющей песне:
    //1.Сама песня, по умолчанию - "пустая песня"
    protected Song playNow = new Song();
    //2.Номер песни в списке
    protected int i;

    /**
     * Метод, возвращающий информации о играющей сейчас песне
     * @return - строка вида "Исполнитель - Назавние песни"
     */
    protected String getSongInfo(){
        return playNow.retInfo();
    }

    //Метод, переключающий песни вперед
    protected void nxt(){
        //Проверяем, есть ли что обходить
        if(songs.length != 0) {
            //Для обхода списка песен как "по кольцу", необходима проверка,
            //достигнут ли конец списка
            if (i == songs.length - 1) {
                this.i = 0;
            } else {
                this.i ++;
            }
            this.playNow = songs[i];
            System.out.println("Next song: "+this.getSongInfo());
        }
    }
    /**
     * Метод, переключающий песни назад
     * @see Media#nxt()
     */
    protected void prev(){
        if(songs.length != 0) {
            if (i == 0) {
                this.i = songs.length - 1;
            } else {
                this.i--;
            }
            this.playNow = songs[i];
            System.out.println("Previous song: " + this.getSongInfo());
        }
    }

}
