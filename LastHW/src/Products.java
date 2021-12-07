import java.util.HashMap;
import java.util.Map;

public class Products {
    //Храним тут пару [ id продукта : количество продукта ] для каждого продукта
    private Map<String, Integer> prodInfo = new HashMap<String, Integer>();

    /**
     * Метод, который обрабатывает одинаковые ключи корректно нашей задаче
     * @param p - id продукта
     * @param count - количествно продукта у какого-то продавца
     */
    void push(String p, int count ){
        //Если наш продукт встречался у других продавцов, то:
        if(prodInfo.containsKey(p)){
            //Перепишем пару ключ:значение с учетом прибавки общего количества товара
            int val = prodInfo.get(p);
            prodInfo.put(p,val+count);
        }
        else{
            //Иначе просто добавляем новую пару
            prodInfo.put(p,count);
        }
    }

    /**
     * Метод, который помогает не учитывать тот товар, что мы продали
     * @param p - id товара
     * @param count - количество проданного товара
     */
    void pop(String p, Integer count ){
        //Я считаю, что тут не обязательна проверка, так как продать можно только тот товар,
        // который имеется в наличии, если у продавца его нет, он его не может продать,
        // а значит у нас гарантировано есть нужная пара [ключ:значение]
        int val = prodInfo.get(p);
        //Перепишем уже без учета проданных товаров
        prodInfo.put(p,val-count);

    }

    /**
     * Метод, возвращающий значение по заданному ключу
     * @param key
     * @return
     */
    public int getValue(String key){
        return prodInfo.get(key);
    }

    /**
     * Предполагается вызов этого метода, после парсинга, для дальнейшей работы с собранной информацией
     * @return - пары [id товара : оставшееся количесво у всех продавцов]
     */
    public Map<String, Integer> retProducts(){
        return prodInfo;
    }

}
