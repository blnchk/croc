import org.w3c.dom.Document;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

//Клас индивидуального под задачи обработчика, наследник DefaultHandler
public class SaxHandler extends DefaultHandler {
    //список нужных хмл узлов
    static final String PRODUCTS = "products";
    static final String PRODUCT = "product";
    static final String SALES = "sales";
    static final String SALE = "sale";
    static final String PRODUCT_ID = "prodID";
    static final String PODUCT_NAME = "prodName";
    static final String COUNT_PRODUCT = "count";
    static final String COUNT_SALE = "countSale";
    static final String DATE = "date";

    //Конструкторы, нужны для того, чтобы сохранять информацию, полученную перед предыдущим парсингом

    /**
     * Понадобился для того, чтобы была возможность свзяать информацию с двух файлов и больше
     * В моем случае products.hml и sales.hml для коректного учета остатков продукта
     * @param products - Значения, полученные при парсинге одного из документов, когда собираемся парсить второй и нужен доступ
     */
    SaxHandler(Products products){
        ans1 = products;
    }

    /**
     * @see SaxHandler(Products)
     * @param dateInfo
     * @param products
     */
    SaxHandler(DateInfo dateInfo, Products products){
        ans1 = products;
        ans2 = dateInfo;
    }

    /**
     * Если есть дополнительные конструкторы, нужно создать и по-умолчанию
     */
    SaxHandler(){}

    //Поля нужного типа для получения ответа на вопросы задач
    private Products ans1;
    private DateInfo ans2;
    //Для хранения текущего узла
    String elem;

    //Для хранения информации с целью последующей записи при окончании элемента
    String prodID; // id продукта
    String count; // количество продукта
    String date; // дата продажи
    String name; // имя продукта
    Vector<String> names = null; //массив имен продуктов, индекс - это id-1

    /**
     * Для получения результата
     * @return
     */
    public Products getAnswer1() {
        return ans1;
    }

    /**
     * @see SaxHandler#getAnswer1()
     * @return
     */
    public DateInfo getAnswer2() {
        return ans2;
    }


    //Перегруженные методы DefaultHandler:

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        this.elem = qName;
        switch (elem) {
            //Если мы находимся в "шаапке" документов, то создаем новый класс, для хранения данных из этого документа
            case PRODUCTS:
                names = new Vector<String>();
                ans1 = new Products();
                break;
            case SALES:
                ans2 = new DateInfo();
                break;
            // В начале каждого узла name, создаем в векторе место, куда вставим имя в методе endElement;
            case PODUCT_NAME:
                names.addElement("sd");
            default: {
            }
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        //Собираем из полученных символов строку
        String text = new String(ch, start, length);
        //Так как для любого узла из дерева работает эта функция, то нужно как-то определять нужный ли это текст
        //Для этого используем проверку на содержание открывающей скобки для узла, потому что нам нужна информация только из
        // "листьев", а там чистый текст, ну или либо это конец нашего элемента (там мы ставим elem = null, так обозначила
        // конец элемента, поэтому тоже обратаываем)
        if(text.contains("<") || elem == null){
            return;
        }
        //Если обрабатываемый элемент содержит нужную нам информацию, то сработает один из кейсов, тут же проверяется, что мы в
        //"листе".
        switch (elem) {
            case PRODUCT_ID:
                prodID=text;
                break;
            case COUNT_PRODUCT:
                count=text;
                break;
            case COUNT_SALE:
                count=text;
                ans1.pop(prodID,Integer.valueOf(count));
            case DATE:
                date = text;
                break;
            case PODUCT_NAME:
                name = text;
                break;
            default: {
            }
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        //Если закрывающий тег для нужных нам элементов, то работает один из кейсов
        switch (qName) {
            case PRODUCT:
                //Если собираем информацию об имени
                if (names != null && names.size()!=0){
                    names.set(Integer.valueOf(prodID)-1,name);
                }
                else {
                    //Если писали в продукты, то пушим туда
                    ans1.push(prodID, Integer.valueOf(count));
                }
                break;
            case SALE:
                //Если писали в даты, то пушим туда
                ans2.push(date,Integer.valueOf(count));
            default: {
                // nothing to do
            }
        }
        //Конец элемента - зануляем наше поле
        elem = null;

    }


}
