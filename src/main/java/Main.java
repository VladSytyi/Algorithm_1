import entity.Cons;
import service.Service;

public class Main {
    public static void main(String[] args) {
        final Service service = new Service();
        final String inputStr = "hello za 1";
        final String inputStr2 = "hello za ";
        final String inputStr3 = "bafced";

        final Cons cons1 = service.convertToCons(inputStr);
        final Cons cons2 = service.convertToCons(inputStr2);
        final Cons cons3 = service.convertToCons(inputStr3);

        System.out.println("=========================================================");
        System.out.println(cons3);
        System.out.println("size = " + cons3.findLengths());

        System.out.println("=========================================================");
        System.out.println("Find cons by index");
        System.out.println(service.findConsByIndex(cons3 , 0) + " index = " + 0);
        System.out.println(service.findConsByIndex(cons3 , 1) + " index = " + 1);
        System.out.println(service.findConsByIndex(cons3 , 2) + " index = " + 2);
        System.out.println(service.findConsByIndex(cons3 , 3) + " index = " + 3);
        System.out.println(service.findConsByIndex(cons3 , 4) + " index = " + 4);
        System.out.println(service.findConsByIndex(cons3 , 5) + " index = " + 5);
        System.out.println(service.findConsByIndex(cons3 , 6) + " index = " + 6);

        System.out.println("=========================================================");
        System.out.println("Find cons head ");
        System.out.println("Returns all cons cell before cons with mentioned index");

        System.out.println(service.findConsHead(cons3, 0) + " index = " + 0);
        System.out.println(service.findConsHead(cons3, 1) + " index = " + 1);
        System.out.println(service.findConsHead(cons3, 2) + " index = " + 2);
        System.out.println(service.findConsHead(cons3, 3) + " index = " + 3);
        System.out.println(service.findConsHead(cons3, 4) + " index = " + 4);
        System.out.println(service.findConsHead(cons3, 5) + " index = " + 5);
        System.out.println(service.findConsHead(cons3, 6) + " index = " + 6);
        System.out.println(service.findConsHead(cons3, 7) + " index = " + 7);

        System.out.println("=========================================================");
        System.out.println("Find index of Cons by value");
        System.out.println(service.findIndexOfConsByValue(service.findConsByIndex(cons3,0).getValue(), cons3 ) + " is index of character " + service.findConsByIndex(cons3,0).getValue() );
        System.out.println(service.findIndexOfConsByValue(service.findConsByIndex(cons3,1).getValue(), cons3 ) + " is index of character " + service.findConsByIndex(cons3,1).getValue() );
        System.out.println(service.findIndexOfConsByValue(service.findConsByIndex(cons3,2).getValue(), cons3 ) + " is index of character " + service.findConsByIndex(cons3,2).getValue() );
        System.out.println(service.findIndexOfConsByValue(service.findConsByIndex(cons3,3).getValue(), cons3 ) + " is index of character " + service.findConsByIndex(cons3,3).getValue() );
        System.out.println(service.findIndexOfConsByValue(service.findConsByIndex(cons3,4).getValue(), cons3 ) + " is index of character " + service.findConsByIndex(cons3,4).getValue() );
        System.out.println(service.findIndexOfConsByValue(service.findConsByIndex(cons3,5).getValue(), cons3 ) + " is index of character " + service.findConsByIndex(cons3,5).getValue() );


        System.out.println("=========================================================");
        System.out.println("Sorted cons");
        final Cons sortedCons = service.sort(cons1);
        final Cons sortedCons2 = service.sort(cons2);
        final Cons sortedCons3 = service.sort(cons3);
        System.out.println(sortedCons);
        System.out.println(sortedCons2);
        System.out.println(sortedCons3);

    }


}
