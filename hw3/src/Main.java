/**
 * @author Evelina Tkachenko
 */
public class Main {
    public static void main(String[] args){
        System.out.println("Ставим на поле слона:");
        try {
            //Ставим на поле слона
            ChessCage bishop = new ChessCage(7, 7);
            System.out.println(bishop.toString());
            //Сходим на возможную клетку
            bishop.stepBishop(5,5);
            System.out.println(bishop.toString());
            //сдвинемся вправо, но на корректную клетку
            bishop.stepBishop(7,3);
            System.out.println(bishop.toString());
            //Попробуем вернуться в начало координат
            bishop.stepBishop(0,0);
            System.out.println(bishop.toString()+"\n");

        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Ставим на поле пешку в начальную позицию:");
        try{
            //Ставим на поле пешку, причем в начальную позицию
            ChessCage pawn = new ChessCage(1,1);
            System.out.println(pawn.toString());
            //Воспользуемся преимуществом и сходим на 2 клетки вперед
            pawn.stepPawn(1,3);
            System.out.println(pawn.toString());
            //Сходим на 1 клетку перед
            pawn.stepPawn(1,4);
            System.out.println(pawn.toString());
            //Сходим на 2 клетки не с начальной позиции:
            pawn.stepPawn(1,6);
            System.out.println(pawn.toString());
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}
