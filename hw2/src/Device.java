//Абстрактный класс-родитель для всех классов звуковоспр. устройств
public abstract class Device {
    //Подключенный носитель, определяется в конструкторе
    public Media media;
    //Просто имя носителя, для красивого вывода
    public String name;
    //Переменная, обозначающая совместимость носителя и устройства,
    //определяется в конструкторе наследников
    public boolean interoper;

    //Метод для воспроизведения музыки
    public void play(){
        //Если при создании экземпляра, был "подключен" поддерживаемый нситель
        if (interoper){
            System.out.println("Player: " + name + "\nPlay now: "+media.getSongInfo());
        }
        else{
            System.out.println("This media can't be played on this device!");
        }
    }

    //Переключение композиции, при совместимости работает как нужно,
    //иначе ничего не происходит, само переключение идет в классе носителя
    public void nextSong(){
        if(interoper){
            media.nxt();
        }
    }
    /**
     * @see Device#nextSong()
     */
    public void prevSong(){
        if(interoper){
            media.prev();
        }
    }

}
