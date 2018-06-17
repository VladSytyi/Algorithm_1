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
            default: return false;
        }
    }
}