package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    public void addOrder(Order order){
      orderRepository.addOrder(order);
    }
    public void addPartner(String partnerId){
      orderRepository.addPartner(partnerId);
    }
    public void addOrderPartnerPair(String orderId,String partnerId){
      orderRepository.addOrderPartnerPair(orderId, partnerId);
    }
    public Order getOrderById(String Order_Id){
         return orderRepository.getOrderById(Order_Id);
    }
    public DeliveryPartner getPartnerById(String partnerId){
        return orderRepository.getPartnerById(partnerId);
    }
    public int getOrderCountByPartnerId(String partnerId){
     return orderRepository.getOrderCountByPartnerId(partnerId);
    }
    public List<String> getOrderByPartnerId(String partnerId){
        return orderRepository.getOrderByPartnerId(partnerId);
    }
    public List<String> getAllOrders(){

        return orderRepository.getAllOrders();
    }
    public int getCountOfUnassignedOrders(){
        return orderRepository.getCountOfUnassignedOrders();
        //unassigned_orders = total_orders-assigned_orders
    }
    public int getOrdersLeftAfterGivenTimeByPartnerId(String deliveryTime,String partnerId){

        return orderRepository.getOrdersLeftAfterGivenTimeByPartnerId(deliveryTime, partnerId);
    }
    public String getLastDeliveryTimeByPartnerId(String partnerId){
      return orderRepository.getLastDeliveryTimeByPartnerId(getLastDeliveryTimeByPartnerId(partnerId));
    }
    public void deletePartnerById(String partnerId){

       orderRepository.deletePartnerById(partnerId);
    }

    public void deleteOrderByID(String orderId){
      orderRepository.deleteOrderByID(orderId);
    }
}
