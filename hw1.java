public class hw1 {
    public static void main(String[] args){
        for  (int i=1; i<101; i++){
            if ( i % 3 == 0 ) System.out.print(i % 5 == 0 ? "FizzBuzz " : "Fizz ");
            else System.out.print(i+" ");
        }
    }
}
