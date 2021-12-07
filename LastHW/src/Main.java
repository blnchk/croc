import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Vector;

/*

Варианты заданий:
20%6 = 2
Для каждого товара вывести в
файл общее количество товара этого типа в наличии

20%3 = 2
Вывести в файл среднее количество проданных товаров в день

Форматы файлов:
20%4 = 0
XML -> XML

 */

/**
 * Итоговое домашнее задание
 * @author Evelina Tkachenko
 */

public class Main {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, XMLStreamException {
        //инициализируем фабрику
        SAXParserFactory spf = SAXParserFactory.newInstance();
        //Инициализируем парсер
        SAXParser saxParser = spf.newSAXParser();
        //Инициализируем ридер файла
        XMLReader xmlReader = saxParser.getXMLReader();

        //Инициализируем наш обработчик событий
        SaxHandler handler = new SaxHandler();
        xmlReader.setContentHandler(handler);
        //Закидываем нужный файл
        xmlReader.parse("data\\availability.xml");

        //Записываем промежутчноый результат, так как для 1 задания нужно обработать 3 файла,
        // при обработке 3 файла, буду выводить ответ уже в виде файла, промежуточные этапы в консоль
        Products answ1 = handler.getAnswer1();

        /*
        Вывод значений для проверки
        System.out.println("After parsing availability.xml:");
        for (Map.Entry<String, Integer> product : answ1.retProducts().entrySet()) {
            String key = product.getKey();
            int value = product.getValue();
            System.out.println("[ " + key + " : " + value + " ]");
        }*/

        //Парсим 2 файл со списком продаж, с сохранением промежуточного результата
        SaxHandler handlerPt2 = new SaxHandler(answ1);
        xmlReader.setContentHandler(handlerPt2);
        xmlReader.parse("data\\sales.xml");

        //Зафиксируем результаты после парсинга
        answ1 = handlerPt2.getAnswer1();
        //Появился новый класс, так как он писался под документ sales.xml
        DateInfo answ2 = handlerPt2.getAnswer2();

        /*
        Вывод уже двух списков пар по после 2 этапа парсинга
        System.out.println("\nAfter parse sales.xml");
        System.out.println("Map - id: count product");
        for (Map.Entry<String, Integer> product : answ1.retProducts().entrySet()) {
            String key = product.getKey();
            int value = product.getValue();
            System.out.println("[ " + key + " : " + value + " ]");
        }
        System.out.println();
        System.out.println("Map - date : count sales");
        for (Map.Entry<String, Integer> dates : answ2.retDates().entrySet()) {
            String key = dates.getKey();
            int value = dates.getValue();
            System.out.println("[ " + key + " : " + value + " ]");
        }*/

        //Парсим 3 файл с продуктами, чтобы вывести не только id продукта в ответ, но и его имя
        SaxHandler handlerPt3 = new SaxHandler(answ2, answ1);
        xmlReader.setContentHandler(handlerPt3);
        //Инициализируем вектор с именами в обработчике
        xmlReader.parse("data\\products.xml");
        //Возъмем полученный вектор
        Vector<String> names = handlerPt3.names;
        /*
        Вывод полученных имен
        System.out.println("Size names: " + names.size());
        for (String name : names) {
            System.out.println(name);
        }
         */


        //Строим XML файл с ответом на задание 1:
        //Фабрика выводов
        XMLOutputFactory factory = XMLOutputFactory.newFactory();
        XMLStreamWriter writer = factory.createXMLStreamWriter(new FileOutputStream("src\\answer1.xml"));
        /*
        Начинаем писать документ
        Результат:
        <products>
        <product>
        <prodID>ID</prodID>
        <prodName>NAME</prodName>
        <count>COUNT</count>
        </product>

        ...
        </products>
         */
        writer.writeStartDocument();
        writer.writeCharacters("\n");
        writer.writeStartElement("products");
        //Забиваем полученные данные
        for (int i=0; i<names.size(); i++) {
            //Ключ - это наше id = i+1
            // Так как элемент парситься последовательно и имена заносились последовательно,
            // можно гарантировать, что id продукта с именем name[i] = i+1
            String key = Integer.toString(i+1);

            writer.writeCharacters("\n");

            writer.writeStartElement("product"); // <product>
            writer.writeCharacters("\n");

            writer.writeStartElement("prodID"); // <prodID>
            writer.writeCharacters(key); // id
            writer.writeEndElement();// </prodID>
            writer.writeCharacters("\n");

            writer.writeStartElement("prodName"); // <prodName>
            writer.writeCharacters(names.get(i)); // name
            writer.writeEndElement(); // </prodName>
            writer.writeCharacters("\n");

            writer.writeStartElement("count"); // <count>
            //Если в нашем Map'e есть id родукта, то мы берем остатки из Map
            if(answ1.retProducts().containsKey(key)){
                writer.writeCharacters(Integer.toString(answ1.getValue(key))); //countProd
            }
            //Иначе считаем, что продукта нет, в другом случае он бы попал в наш Map
            else {
                writer.writeCharacters("0");
            }
            writer.writeEndElement();// </count>
            writer.writeCharacters("\n");

            writer.writeEndElement();// </product>

        }
        writer.writeEndElement(); //  </products>
        writer.writeEndDocument();


        //Выполнение 2 задания

        //Нужно посчитать среднюю продажу в день, значит нужен интервал дат. которые у нас есть
        String minDate = "31.12.2100";
        String maxDate = "01.01.1900";
        //Общее колличество продаж
        int count = 0;
        //Количество дней
        int n;

        //Работая с датой, а не со строкой намного проще посчитать интервал
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date max = null;
        Date min = null;
        try {
            min = format.parse(minDate);
            max = format.parse(maxDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Пройдемся по информации
        for (Map.Entry<String, Integer> dates : answ2.retDates().entrySet()) {
            Date d = null;
            try {
                d = format.parse(dates.getKey());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //поиск минимальной и максимальной даты мэпе
            if (max.getTime() < d.getTime()) {
                max= d;
            } else if (d.getTime() < min.getTime()) {
                min = d;
            }
            //Общее кол-во продаж
            count += dates.getValue();
        }
        //Разница в милисекундах
        long diff = max.getTime() - min.getTime();
        //Разница в днях
        n = (int)(diff/  (24 * 60 * 60 * 1000));

        /*
        Записываем ответ на задание 2

        Результат:
        <info>
        <interval>minDate - maxDate</interval>
        <intervInDays>n</intervInDays>
        <countOfSalesPerInterval>count</countOfSalesPerInterval>
        <averageSalesVal>count/n</averageSalesVal>
        </info>
         */


        writer = factory.createXMLStreamWriter(new FileOutputStream("src\\answer2.xml"));
        writer.writeStartDocument();
        writer.writeCharacters("\n");
        writer.writeStartElement("info"); // <info>

        writer.writeCharacters("\n");
        writer.writeStartElement("interval"); // <interval>
        writer.writeCharacters(format.format(min).toString() + " - " + format.format(max).toString());
        writer.writeEndElement(); // </interval>

        writer.writeCharacters("\n");
        writer.writeStartElement("intervInDays"); // <intervInDays>
        writer.writeCharacters(Integer.toString(n));
        writer.writeEndElement();// </intervInDays>

        writer.writeCharacters("\n");
        writer.writeStartElement("countOfSalesPerInterval"); //<countOfSalesPerInterval>
        writer.writeCharacters(Integer.toString(count));
        writer.writeEndElement();// </countOfSalesPerInterval>

        writer.writeCharacters("\n");
        writer.writeStartElement("averageSalesVal"); // <averageSalesVal>
        writer.writeCharacters(Integer.toString(count/n));
        writer.writeEndElement(); //<averageSalesVal>

        writer.writeCharacters("\n");
        writer.writeEndElement(); // </info>
        writer.writeEndDocument();

    }

}

