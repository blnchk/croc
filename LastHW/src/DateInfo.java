import java.util.HashMap;
import java.util.Map;

//Класс хранящий информацию для 2 задания
public class DateInfo {
    // Пары date : count sales
    private Map<String, Integer> dateInfo = new HashMap<String, Integer>();

    /**
     * @see Products#push(String, int)
     * @param d - дата
     * @param count - кол-во продаж
     */
    void push(String d, int count ){
        if(dateInfo.containsKey(d)){
            int val = dateInfo.get(d);
            dateInfo.put(d,val+count);
        }
        else{
            dateInfo.put(d,count);
        }
    }

    /**
     * @see Products#retProducts()
     * @return Все считанные пары - [date: count sales]
     */
    public Map<String,Integer> retDates(){
        return dateInfo;
    }

}
