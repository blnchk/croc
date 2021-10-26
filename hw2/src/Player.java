// Основной класс - наша музыкальная система
public class Player {
    //Список содержащихся в системе устройств
    private Device[] device;
    //Действующий носитель
    private Media usedMedia;
    /* Фиксация состояния в проигрывателе:
    1. Что играем
    2. Номер композиции
    */
    private Song playNow;
    int i;

    /**
     * Конструктор класса
     * @param dev - список устройств
     */
    Player(Device[] dev){
        device = dev;
        i = 0;
    }

    /**
     * Основной метод, имитация вставки носителя в устройство
     * @param media - носитель
     */
    public void play( Media media ) {
        usedMedia = media;
        this.i = 0;
        int flag = 0;
        String dev = "";
        for (int j=0; j < device.length; j++){
            if (device[j].canPlay(media.getFormat())){
                flag = 1;
                dev = device[j].getNameDevice();
                break;
            }
        }
        if ( flag == 0 ) {
            this.i = -1;
            System.out.println("This media can't be played!");
        }
        else {
            if (usedMedia.getCount() == 0) {
                playNow = new Song();
            } else {
                playNow = usedMedia.retSong(i);
            }
            System.out.println("Player: " + dev + "\nPlay now: " + playNow.retInfo());
        }
    }

    //Переключение композиции
    public void nextSong(){
        // i инициализиуется в play(), если нечем проигрывать формат, то i = -1
        if (i>-1) {
            // Проверка на пустоту списка произведений
            if (usedMedia.getCount() != 0) {
                this.i++;
                //Закольцовка списка произведений
                if (this.i == usedMedia.getCount()) {
                    this.i = 0;
                }
                playNow = usedMedia.retSong(i);
            }
            System.out.println("Next song: " + playNow.retInfo());
        }
    }

    /**
     * @see Player#nextSong()
     */
    public void prevSong(){
        if(i>-1) {
            if (usedMedia.getCount() != 0) {
                this.i--;
                if (this.i == -1) {
                    this.i = usedMedia.getCount() - 1;
                }
                playNow = usedMedia.retSong(i);
            }
            System.out.println("Previous song: " + playNow.retInfo());
        }
    }


}
