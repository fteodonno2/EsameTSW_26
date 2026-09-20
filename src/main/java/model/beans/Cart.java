package model.beans;

import java.util.*;
import dao.ProductDAO;

public class Cart {
    private Map<Integer, Integer> items = new HashMap<>();

    public void addProduct(int productId) {
        items.merge(productId, 1, Integer::sum); // Se esiste, incrementa di 1
    }

    public void removeItem(int productId) {
        items.remove(productId);
    }

    public double getTotalPrice() {
        return items.entrySet().stream()
                .mapToDouble(entry -> {
                    Product product = ProductDAO.getById(entry.getKey());
                    int quantity = entry.getValue();
                    return product != null ? product.getPrezzo().doubleValue() * quantity : 0;
                }).sum();
    }

    public Map<Integer, Integer> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items == null || items.isEmpty();
    }

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        for (Integer productId : items.keySet()) {
            Product product = ProductDAO.getById(productId);
            if (product != null) {
                products.add(product);
            }
        }
        return products;
    }


}
