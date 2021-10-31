//Класс для устройства: Проигрыватель дисков
//Поддерживаемые носители: CD-диски
public class CDPlayer extends Device {
    //Конструктор класса, инициализирующий необходимые переменные
    CDPlayer(Media media){
        //Имя проигрывателя
        this.name = "CD-Player";
        //Подключенный носитель
        this.media = media;
        //Проверка на совместимость "подключенного" носителя
        this.interoper = media instanceof CD;
    }

}
