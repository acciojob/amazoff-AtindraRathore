package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
@Repository
public class OrderRepository {
    Map<String,Order> orderdb=new HashMap<>();
    Map<String, DeliveryPartner> deliveryPartnerdb=new HashMap<>();
    Map<String,String> orderPartnerdb=new HashMap<>();
    Map<String, List<String>> partnerOrdersdb=new HashMap<>();
    public void addOrder(Order order){
        orderdb.put(order.getOrder_Id(),order);
    }
    public void addPartner(String partnerId){
        deliveryPartnerdb.put(partnerId,new DeliveryPartner(partnerId));
    }
    public void addOrderPartnerPair(String orderId,String partnerId){
        if(orderdb.containsKey(orderId) && deliveryPartnerdb.containsKey(partnerId)){
            orderPartnerdb.put(orderId,partnerId);
        }

       //Mapping orderId -> partnerId
        List<String> currentorders=new ArrayList<>();
        if(partnerOrdersdb.containsKey(partnerId)){
           currentorders=partnerOrdersdb.get(partnerId);
        }
        currentorders.add(orderId);
        partnerOrdersdb.put(partnerId,currentorders);
        if(deliveryPartnerdb.get(partnerId)!=null){
        DeliveryPartner deliveryPartner=deliveryPartnerdb.get(partnerId);
        deliveryPartner.setNumberOfOrders(currentorders.size());}
        else{
            DeliveryPartner deliveryPartner=new DeliveryPartner(partnerId);
            deliveryPartner.setNumberOfOrders(currentorders.size());
        }

    }
    public Order getOrderById(String Order_Id){
      return orderdb.get(Order_Id);
    }
    public DeliveryPartner getPartnerById(String partnerId){
        return deliveryPartnerdb.get(partnerId);
    }
    public int getOrderCountByPartnerId(String partnerId){
     DeliveryPartner deliveryPartner=new DeliveryPartner(partnerId);
     return deliveryPartner.getNumberOfOrders();
    }
    public List<String> getOrderByPartnerId(String partnerId){
        return partnerOrdersdb.get(partnerId);
    }
    public List<String> getAllOrders(){
        List<String> orderslist=new ArrayList<>();
        for(String order:orderdb.keySet()){
            orderslist.add(order);
        }
        return orderslist;
    }
     public int getCountOfUnassignedOrders(){
        return orderdb.size()-orderPartnerdb.size();
        //unassigned_orders = total_orders-assigned_orders
     }
     public int getOrdersLeftAfterGivenTimeByPartnerId(String deliveryTime,String partnerId){
        String[] time=deliveryTime.split(":");
        int newtime = Integer.parseInt(time[0])*60 + Integer.parseInt(time[1]);
         int count=0;
         List<String> orders= partnerOrdersdb.get(partnerId);
         for(String orderId:orders){
             int deliverytime=orderdb.get(orderId).getDeliveryTime();
             if(deliverytime>newtime){
                 count++;
             }
         }
         return count;
     }
     public String getLastDeliveryTimeByPartnerId(String partnerId){
        int maxtime=0;
        List<String> orders=partnerOrdersdb.get(partnerId);
         for(String orderid:orders){
             int deliverytime=orderdb.get(orderid).getDeliveryTime();
             maxtime=Math.max(maxtime,deliverytime);
         }
         String HH = String.valueOf(maxtime/60);
         String MM = String.valueOf(maxtime%60);
         if(HH.length()<2){
             HH= "0"+HH;
         }
         if(MM.length()<2){
             MM="0"+MM;
         }
         return HH+":"+MM;
     }
     public void deletePartnerById(String partnerId){

            deliveryPartnerdb.remove(partnerId);


        List<String> orders=partnerOrdersdb.get(partnerId);

        partnerOrdersdb.remove(partnerId);

        for(String orderId:orders){
            orderPartnerdb.remove(orderId);
        }

     }

     public void deleteOrderByID(String orderId){
        orderdb.remove(orderId);
        String partnerID=orderPartnerdb.get(orderId);
        orderPartnerdb.remove(orderId);
        partnerOrdersdb.get(partnerID).remove(orderId);
        deliveryPartnerdb.get(partnerID).setNumberOfOrders(partnerOrdersdb.get(partnerID).size());
     }

}

