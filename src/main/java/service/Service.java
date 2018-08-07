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

      /*
    Sorting algorithm from A - Z
    Thus we have Cons cells --> first we need to find out the latest in alphabet(will be as 'L') , to be the deepest element
    Then after that we need to find an index of this Cons cell
    Then we need to split cons cell in 2 parts : Head -- all prev cons of 'L'
                                                 Tail -- all after of 'L'
    Then we need to remove this Cons cell by   connecting Head + Tail
    After update we repeat the operation
    The process will be finished when the input Cons cell will be empty
     */

     public Cons sort (Cons cons) {
        if (cons == null) return null;
        final Cons sortedCons = new Cons(null, null);
        return sorting(sortedCons , cons);
     }

    private Cons sorting(Cons sortedCons, Cons cons) {
        if (cons == null) return finalCons(sortedCons, cons);
        final Cons theLatestInAlphabet = findTheLatestInAlphabet(cons);
        final Cons updatedSortedCons = addToSortedCons(sortedCons , theLatestInAlphabet);
        final Cons updatedCons = updateCons(theLatestInAlphabet , cons);

        return sorting(updatedSortedCons , updatedCons);
    }

    /**
     *
     * @param theLatestInAlphabet this cons with the value, which should be removed from input cons
     * @param cons input Cons cell
     * @return
     * The algorithm is
     * We search for an index of theLatestInAlphabet
     * recreate Cons sequence without theLatestInAlphabet
     * by connecting  head and tail without theLatestInAlphabet
     */
    private Cons updateCons(Cons theLatestInAlphabet, Cons cons) {
        if (cons.getValue() == null) return theLatestInAlphabet;

        final int indexOfTheLastOfAlphabet = findIndexOfConsByValue(theLatestInAlphabet.getValue(), cons);
        final Cons prevCons = findConsHead(cons , indexOfTheLastOfAlphabet );
        final Cons nextCons = findConsTail(cons , indexOfTheLastOfAlphabet );

        return connectCons(prevCons , nextCons);
    }

    private Cons findConsTail(Cons cons, int indexOfTheLastOfAlphabet) {
        final Cons c = findConsSeqByIndex(cons , indexOfTheLastOfAlphabet);
        return c.getNextCons();
    }

    private Cons connectCons(Cons head, Cons tail) {
        if (tail == null) return head;
        if (head == null) return tail;
        final int size = head.findLengths() - 1;
        return connection(head, tail, size);
    }


    private Cons connection(Cons head, Cons finalCons, int step) {
         if (step < 0) return finalCons;

         final Cons headsCons = findConsByIndex(head , step);
         final Cons connectedCons = new Cons(headsCons.getValue(), finalCons);

         return connection(head,connectedCons, --step);
    }

    public Cons findConsHead(Cons cons, int index) {
        int indexPrevCons = --index;
        final Cons found = findConsByIndex(cons, indexPrevCons);
        if (found == null) return null;
        final Cons finalCons = new Cons(found.getValue(), null);
        return findHead(finalCons, cons , --indexPrevCons);
    }

    private Cons findHead(Cons finalCons, Cons cons, int i) {
        if (i < 0) return finalCons;

        final Cons found = findConsByIndex(cons, i);
        final Cons upd = new Cons(found.getValue(), finalCons);

        return findHead(upd, cons, --i);
    }

    public int findIndexOfConsByValue(Character value, Cons cons) {
        /**
         * maybe here is needed to add additional check for value
         * if this value isn't in  Cons cell --> return null or exception
         */
        int counter = -1;
        return iterate(value, cons, counter);
    }


    public Cons findConsByIndex(Cons cons , Integer index) {
         if (index < 0) {
            // System.out.println("Index is above 0");
             //return goNextCons(cons , 0);
             return null;
         }
         if (index == 0) {
             return new Cons(cons.getValue(), null);
         }
         if (index > cons.findLengths()) {
             System.out.println("Index is more than size");
             return null;
         }

        /**
         * We need to wrap this depth, to not to lose any value of Cons cell
         */
        return goNextCons(cons , index);
    }

    public Cons findConsSeqByIndex(Cons cons , Integer index) {
        if (index < 0) {
            // System.out.println("Index is above 0");
            //return goNextCons(cons , 0);
            return null;
        }
        if (index == 0) {
            return cons;
        }
        if (index > cons.findLengths()) {
          //  System.out.println("Index is more than size");
            return null;
        }
        return nextCons(cons , index);
    }

    private Cons addToSortedCons(Cons finalCons, Cons newCons) {
        if (finalCons.getValue() == null && finalCons.getNextCons() == null)  {
            return new Cons(newCons.getValue() , null);
        }

        return new Cons(newCons.getValue(), finalCons);
    }

    private Cons finalCons(Cons sortedCons, Cons cons) {
        if (cons == null) return sortedCons;
        return new Cons(cons.getValue() , sortedCons);
    }

    private Cons goNextCons(Cons cons, Integer depth) {
        if (cons == null) return null;
        final Cons tmp = new Cons(cons.getValue(), null);
        if (depth == 0) return tmp;
        return goNextCons(cons.getNextCons() , --depth );
    }

    private Cons nextCons(Cons cons, Integer depth) {
        if (depth == 0) return cons;
        if (cons.getNextCons() == null) return cons;
        return nextCons(cons.getNextCons() , --depth );
    }

    private int iterate(Character value, Cons cons , int counter) {
        if (counter == cons.findLengths()) return calculateIndex(counter);
        if (cons.getValue().equals(value)) return calculateIndex(counter) ;

        return iterate(value, cons.getNextCons() , ++counter);
    }

    private int calculateIndex(int counter) {
        /*
        We started from -1
        so we need to transform to normal index
         */
        return  ++counter;
    }

    public Cons findTheLatestInAlphabet(Cons cons) {
        if (cons.getNextCons() == null) return cons;

        return valueIsLatestInAlphabet(cons) ? findTheLatestInAlphabet( change(cons) )
                                             : findTheLatestInAlphabet( cons.getNextCons() );
    }

    private Cons change (Cons cons) {
        if (cons.getNextCons() == null) return cons;

        final Cons temp = cons.getNextCons();
        final Cons newCons = new Cons(cons.getValue(), temp.getNextCons());

        return new Cons(temp.getValue(), newCons);
    }

    private boolean valueIsLatestInAlphabet(Cons cons) {
        if (cons.getNextCons() == null) return false;
        final int res = cons.compareTo(cons.getNextCons());

        if (res > 0) return true;
        if (res < 0) return false;

        return false;
    }


    ////////////////////////////////////////
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