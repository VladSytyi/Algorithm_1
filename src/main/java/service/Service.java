package service;

import entity.Cons;
import org.apache.commons.lang3.StringUtils;

public class Service {

    public Cons convertToCons(String text) {
        if (StringUtils.isEmpty(text)) return null;

        final Cons firstCons = new Cons(text.charAt(text.length() - 1), null);
        final String textWithoutLastSymbol = text.substring(0, text.length() - 1);
        return convertText(firstCons, textWithoutLastSymbol);
    }

    public boolean checkIsFirst(Cons cons1, Cons cons2) {
        if (cons1 == null || cons2 == null) throw new RuntimeException("nulls");

        final boolean maybeTheSameLength = checkConsLength(cons1,cons2);

        if (maybeTheSameLength) {
            final int result = compareCells(cons1, cons2, cons1.findLengths());
            return convertToBoolean(result);

        } else {
            final Cons consIsLess = findSmallerCons(cons1 , cons2);
            final int result = compareCells(cons1,cons2, consIsLess.findLengths());
            return convertToBoolean(result);
        }

    }

    public Cons sort(Cons cons) {
        if (cons == null) return null;

        final Cons earliestInAlphabet = findTheEarliestInAlphabet(cons);



        return null;
    }

    public Cons removeCons(Cons cons, Cons removalCons) {
        if (cons.getNextCons() == null) return cons;

        if (cons.getNextCons().getValue().equals(removalCons.getValue())) {
           // final  Cons newCons = new Cons(cons.getValue() , cons.getNextCons().getNextCons());
            removeCons( new Cons(cons.getValue(), cons.getNextCons().getNextCons()) , removalCons);
        }
        return removeCons(cons.getNextCons(), removalCons);
    }

    public Cons findConsWhereNextIsRemoval(Cons cons, Cons removalCons) {
        if (cons.getNextCons() == null) return cons;

        if (cons.getNextCons().getValue().equals(removalCons.getValue())) {
            return cons;
        }

        return findConsWhereNextIsRemoval(cons.getNextCons(), removalCons);
    }


//    private void change(Cons comparable, Cons cons) {
//        final Cons comparableWithNext = new Cons(comparable.getValue(), cons.getNextCons());
//        final Cons replacedCons = new Cons( cons.getValue() , comparableWithNext);
//
//    }

//    private void sortInner(Cons cons) {
//    }

//    public Cons sort(Cons cons) {
//        if (cons == null) return null;
//        Cons acc = null;
//        return sortIner(cons, acc);
//    }
//
//    public Cons sortIner(Cons cons, Cons acc) {
//        if (cons.getNextCons() == null) return acc;
//
//        if (valueIsEarlierInAlphabet(cons)) {
//            acc = change(cons);
//            final Cons newC = new Cons( acc.getValue() ,  change(cons));
//
//            sortIner(cons.getNextCons(), newC);
//        } else  {
//            acc = new Cons(cons.getValue() , cons.getNextCons());
//            sortIner(cons.getNextCons(), acc);
//        }
//
//        return acc;
//    }
//
//
//
    public Cons findTheEarliestInAlphabet(Cons cons) {
        if (cons.getNextCons() == null) return cons;

        return valueIsEarlierInAlphabet(cons) ? findTheEarliestInAlphabet( change(cons) )
                                              : findTheEarliestInAlphabet( cons.getNextCons() );
    }

    private Cons change (Cons cons) {
        if (cons.getNextCons() == null) return cons;

        final Cons temp = cons.getNextCons();
        final Cons newCons = new Cons(cons.getValue(), temp.getNextCons());

        return new Cons(temp.getValue(), newCons);
    }

    private boolean valueIsEarlierInAlphabet(Cons cons) {
        if (cons.getNextCons() == null) return false;
        final int res = cons.compareTo(cons.getNextCons());

        if (res > 0) return false;
        if (res < 0) return true;

        return false;

    }

//    private Cons iterate(Cons cons, Character value) {
//
//        if (value.equals(cons.getValue())) return cons;
//        if (!value.equals(cons.getValue()) && cons.getNextCons() == null) throw new RuntimeException("No value found");
//        return iterate(cons.getNextCons(),value);
//    }



    //////
    // Utils
    private Cons convertText(Cons cons, String text) {
        if (StringUtils.isEmpty(text)) return cons;

        final Cons newCons = new Cons(text.charAt(text.length() - 1), cons);
        return convertText(newCons, text.substring(0, text.length() - 1));
    }

    private boolean convertToBoolean(int result) {
        switch (result) {
            case 1 : return true;
            case -1 : return false;
            default : return false;
        }
    }

    private Cons findSmallerCons(Cons cons1, Cons cons2) {
        return cons1.isLess(cons2) ? cons1 : cons2;
    }

    private int findSmaller(Cons cons, Cons cons2) {
        // this method is applied only if
        // 2 cons has the same values but one of them is longer
        // so the first will be ----> who's length is shorter
        if (cons == null) return 1;
        if (cons2 == null) return -1;
        return -1;
    }

    private int compareCells(Cons cons1, Cons cons2, Integer counter) {
        if (counter == 0) return findSmaller(cons1,cons2);
        // here wee need to find out cons who is smaller size

        final int  res = cons1.compareTo(cons2);

        if (res > 0) return -1; /// First symbol goes earlier
        if (res < 0) return 1; /// First symbol goes later

        return compareCells(cons1.getNextCons(),cons2.getNextCons(), --counter);
    }

    private boolean checkConsLength(Cons cons1, Cons cons2) {
        final Integer consOneLength = cons1.findLengths();
        final Integer consTwoLength = cons2.findLengths();
        return consOneLength.equals(consTwoLength);
    }

    /**
     * The compare(char x, char y) method of Character class returns the value 0 if x == y;
     * a value less than 0 if x < y;
     * and a value greater than 0 if x > y.
     */
}