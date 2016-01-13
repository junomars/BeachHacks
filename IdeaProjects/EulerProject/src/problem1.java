/**
 * Question 1 Prompt:
 * If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
 * Find the sum of all the multiples of 3 or 5 below 1000.
 *
 */
public class problem1 {

    public static void main(String args[]){

        int sum = 0;

        for(int ii = 0; ii < 1000; ii++){
            if(ii % 3 == 0 && ii % 5 == 0)
            {
                sum += ii;
            }
            else if (ii % 5 == 0)
            {
                sum += ii;
            }
            else if(ii % 3 == 0)
            {
                sum += ii;
            }
        }

        System.out.println("The answer is" + sum);
    }

}
