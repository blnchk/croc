import static java.lang.Math.abs;
//Класс для хранения кординат клетки
public class ChessCage {
    //Координата по горизонтали(буквенное обозначение)
    private int x;
    //Кордината по вертикали(числовое обозначение)
    private int y;
    //Строки, хранящие позицию координат в строковом формате (буквы - для х, цифры - для у)
    private final String posX = "abcdefgh";
    private final String posY = "12345678";
    //На шахматной доске 8 клеток по вертикали
    //и 8 по диагонали

    /**
     * Функция для проверки корретктности передаваемых аргументов
     * @param a - значение координаты х/у
     * @param cord - имя проверяемой координаты для того, чтобы в исключении
     *             было указана какая именно координата некорректна
     * @throws IllegalArgumentException - если координата не попоадает
                                        в диапазон нужных значений, то генериуется исключение
     */
    private void check(int a, char cord) throws IllegalArgumentException{
        if(0 > a || a > 7){
            throw new IllegalArgumentException("Incorrect "+ cord + " coordinate!\nThe value should be from 0 to 7");
        }
    }

    /**
     * Конструктор класса для кординаты клетки
     * @param x - координата по горизонтали
     * @param y - координата по вертикали
     */
    ChessCage(int x, int y){
        this.check(x,'x');
        this.check(y,'y');
        this.x = x;
        this.y = y;
    }
    //Функция, возвращающая строковое значение клетки, как на шахматной доске
    public String toString(){
        String s = "";
        s+=posX.charAt(x);
        s+=posY.charAt(y);
        return s;
    }

    /**
     * Ход пешки
     * @param x - координата по горизонтали
     * @param y - координата по вертикали
     * @throws IllegalArgumentException - генерируем исключение, если введены кординаты,
     *                                   на которые пешка не сможет попасть за один свой шаг
     */
    public void stepPawn(int x, int y) throws IllegalArgumentException{
        //Проверка на попадание координата в диапазон значений
        this.check(x,'x');
        this.check(y,'y');
        //Пешка ходит только вперед по доске, значит не перемещается по горизонтали
        if(this.x == x) {
            //По правилам, если пешка стартует, то для нее есть доп возможность
            if (this.y == 1) {
                //Ход либо на 1 клетку, либо на 2 в начале партии
                if ( y - this.y == 1 || y - this.y == 2) {
                    this.y = y;
                }
                else throw new IllegalArgumentException("A pawn doesn't move like that");
            }
            //Если пешка уже двигалась, то ее можно передвинуть только на клетку вперед
            else if (y - this.y == 1){
                this.y = y;
            }
            else throw new IllegalArgumentException("A pawn doesn't move like that");
        }
        else throw new IllegalArgumentException("A pawn doesn't move like that");
    }

    /**
     * Ход слона
     * @see ChessCage#stepPawn(int, int)
     */
    public void stepBishop(int x, int y){
        check(x,'x');
        check(y,'y');
        //Слон передвигается на любое кол-во клеток по диагонали вперед и назад
        //При перемещении должно выполнятся условие:
        //катеты в прямугольном треугольнике (х0,у0),(х,у),(х,у0) равны
        if (abs(x-this.x)==abs(y-this.y)){
            this.x = x;
            this.y = y;
        }
        else {
            throw new IllegalArgumentException("A bishop doesn't move like that");
        }
    }
}
