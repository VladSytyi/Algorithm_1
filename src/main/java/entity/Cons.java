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
        if (value == null) return 1;
        if (o.getValue() == null) return -1;
        if (value.equals(o.getValue())) return 0;

        return value.compareTo(o.getValue());
    }

    @Override
    public String toString() {
        return "Cons{" +
                "value='" + value + '\'' +
                ", nextCons=" + nextCons +
                '}';
    }
}
