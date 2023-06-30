package com.driver;

public class Order {

    private String Order_Id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
    }

    public String getOrder_Id() {
        return Order_Id;
    }

    public int getDeliveryTime() {

        return deliveryTime;
    }
}
