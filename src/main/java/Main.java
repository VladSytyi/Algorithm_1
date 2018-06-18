import entity.Cons;
import service.Service;

public class Main {
    public static void main(String[] args) {
        final Service service = new Service();
        final String inputStr = "hello za 1";
        final String inputStr2 = "hello za";

        final Cons cons1 = service.convertToCons(inputStr);
        final Cons cons2 = service.convertToCons(inputStr2);

        System.out.println(cons1.findLengths());
        System.out.println(cons2.findLengths());

        System.out.println("Cons1 is more than cons2 -- " + cons1.isMore(cons2));
        System.out.println("Cons2 is less than cons1 -- " + cons2.isLess(cons1));
        System.out.println("Cons1 is less than cons2 -- " + cons1.isLess(cons2));
        System.out.println("Cons2 is more than cons1 -- " + cons2.isMore(cons1));

        final boolean result = service.checkIsFirst(cons1, cons2);
        System.out.println("cons1 is first -- " +  result);

        final Cons bubbleSortedCons = service.sort(cons1);

        System.out.println(bubbleSortedCons);



    }
}
