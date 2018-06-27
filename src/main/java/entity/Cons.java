package entity;

public class Cons implements Comparable<Cons> {

    private final Character value;
    private final Cons nextCons;

    public Cons(Character value, Cons nextCons) {
        this.value = value;
        this.nextCons = nextCons;
    }

    ////////////////////
    //
    // Accessors

    public Character getValue() {
        return value;
    }

    public Cons getNextCons() {
        return nextCons;
    }

    ////////////////////
    //
    // Utils

    public boolean isMore(Cons cons) {
        if (cons == null) return true;
        return findLengths() > cons.findLengths();
    }

    public boolean isLess(Cons cons) {
        if (cons == null) return true;
        return findLengths() < cons.findLengths();
    }

    public Integer findLengths() {
        if (this.getValue() == null) return 0;
        if (this.getValue() != null && this.getNextCons() == null) return 1;
        final int counter = 1;
        return countConsLength(this.getNextCons(), counter);
    }

    private Integer countConsLength(Cons cons, Integer counter) {
        if (cons.getNextCons() == null) return ++counter;
        return countConsLength(cons.getNextCons(), ++counter);
    }


    public int compareTo(Cons o) {
        /**
         * The compare(char x, char y) method of Character class returns the value 0 if x == y;
         * a value less than 0 if x < y;  ------    x - y < 0
         * and a value greater than 0 if x > y.  ------ x - y > 0
         */
        if (value == null) return 1;
        if (o.getValue() == null) return -1;
        if (value.equals(o.getValue())) return 0;

        return value - o.getValue();
    }


    @Override
    public String toString() {
        return "Cons{" +
                "value='" + value + '\'' +
                ", nextCons=" + nextCons +
                '}';
    }
}
