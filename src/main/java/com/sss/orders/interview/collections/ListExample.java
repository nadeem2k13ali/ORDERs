package com.sss.orders.interview.collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListExample {
    public static void main(String[] args) {

        ArrayList<String> names = new ArrayList<>();
        names.add("Gul Muhammad");
        names.add("Zulfiqar");
        names.add("Shahnawaz");
        names.add("Nadeem");
        names.add("Waseem");
        names.add("Waseem");
        System.out.println("ArrayList: "+names);
        List<String> name = new LinkedList<>();
        name.add("Gul Muhammad");
        name.add("Zulfiqar");
        name.add("Shahnawaz");
        name.add("Nadeem");
        name.add("Waseem");
        name.add("Waseem");
        System.out.println("Linkedlist: "+name);
    }

}
