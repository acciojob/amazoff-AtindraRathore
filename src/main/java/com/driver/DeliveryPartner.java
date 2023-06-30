package com.driver;

public class DeliveryPartner {

    private String DeliveryPartner_Id;
    private int numberOfOrders;

    public DeliveryPartner(String id) {
        this.DeliveryPartner_Id= id;
        this.numberOfOrders = 0;
    }

    public String getDeliveryPartner_Id() {
        return DeliveryPartner_Id;
    }



    public int getNumberOfOrders() {
        return numberOfOrders;
    }

    public void setNumberOfOrders(int numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }
}