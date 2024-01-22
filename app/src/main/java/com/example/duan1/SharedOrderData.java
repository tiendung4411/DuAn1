package com.example.duan1;

import java.util.HashMap;

public class SharedOrderData {
    private static final SharedOrderData ourInstance = new SharedOrderData();
    private final HashMap<Integer, Integer> quantities = new HashMap<>();

    public static SharedOrderData getInstance() {
        return ourInstance;
    }

    private SharedOrderData() {
    }

    public void setQuantity(Integer itemId, int quantity) {
        quantities.put(itemId, quantity);
    }

    public int getQuantity(Integer itemId) {
        Integer quantity = quantities.get(itemId);
        return quantity != null ? quantity : 0;
    }

    public HashMap<Integer, Integer> getAllQuantities() {
        return quantities;
    }
}
