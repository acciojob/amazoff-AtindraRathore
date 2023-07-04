package com.driver;

public class Order {

    private String Order_Id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
          this.Order_Id = id;
        int hr = ((deliveryTime.charAt(0)-'0')*10)+(deliveryTime.charAt(1)-'0');
        int min = ((deliveryTime.charAt(3)-'0')*10)+(deliveryTime.charAt(4)-'0');

        this.deliveryTime = (hr*60)+min;
    }

    public String getOrder_Id() {
        return Order_Id;
    }

    public int getDeliveryTime() {

        return deliveryTime;
    }
}
