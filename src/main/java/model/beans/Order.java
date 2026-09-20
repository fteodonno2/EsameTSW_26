package model.beans;

import java.sql.Timestamp;

public class Order {
    private int id;
    private int userId;
    private double totale;
    private String stato;
    private Timestamp data_ordine;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public double getTotalPrice() { return totale; }
    public void setTotalPrice(double totale) { this.totale = totale; }

    public String getStatus() { return stato; }
    public void setStatus(String stato) { this.stato = stato; }

    public Timestamp getOrderDate() { return data_ordine; }
    public void setOrderDate(Timestamp data_ordine) { this.data_ordine = data_ordine; }
}
