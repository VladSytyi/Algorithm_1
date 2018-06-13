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

    private Cons convertText(Cons cons, String text) {
        if (StringUtils.isEmpty(text)) return cons;

        final Cons newCons = new Cons(text.charAt(text.length() - 1), cons);
        return convertText(newCons, text.substring(0, text.length() - 1));
    }






}