import java.util.Arrays;
import java.util.List;
//Класс, инициализирующий устройство
public class Device {
    //Назание устройства
    private String nameDevice;
    //Поддерживаемые форматы этого устройства
    private List<String> availableFormat;

    /**
     * Конструктор класса
     * @param name - задает поле nameDevice
     * @param formats - задает поле availableFormat
     */
    Device (String name, String[] formats){
        nameDevice = name;
        availableFormat = Arrays.asList(formats);
    }
    /**
     * @return - Возвращает имя устройства
     */
    public String getNameDevice(){
        return nameDevice;
    }

    /**
     * Метод для проверки на возможность считывания с носителя
     * @param format - формат носителя
     * @return - результат проверки true - можно, false - нельзя
     */
    public boolean canPlay(String format){
        //toLowerCase - тк. мы предполагаем, что допустимые форматы
        //хранятся в нижнем регистре
        return availableFormat.contains(format.toLowerCase());
    }
}
