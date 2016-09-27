package com.gcfwpt.designonline.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/8/2.
 */
public class HurryRushOrders {
    private String order_salenumber;
    private String status;
    private String payment_type;
    private String order_amount;
    private String goods_amount;
    private String shipping_fee;
    private String shippingtime;
    private String shippingtimetostr;
    private String timer;
    private String address;
    private String contacter;
    private String longtitude;
    private String latitude;
    private String distance;
    private String income;
    private String subsidy;
    private int addtime;
    private int accepttime;
    private int arrivedtime;
    private List<HurryRushOrdersGoods> hurryRushOrdersGoods;

    public String getOrder_salenumber() {
        return order_salenumber;
    }

    public void setOrder_salenumber(String order_salenumber) {
        this.order_salenumber = order_salenumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(String order_amount) {
        this.order_amount = order_amount;
    }

    public String getGoods_amount() {
        return goods_amount;
    }

    public void setGoods_amount(String goods_amount) {
        this.goods_amount = goods_amount;
    }

    public String getShipping_fee() {
        return shipping_fee;
    }

    public void setShipping_fee(String shipping_fee) {
        this.shipping_fee = shipping_fee;
    }

    public String getShippingtime() {
        return shippingtime;
    }

    public void setShippingtime(String shippingtime) {
        this.shippingtime = shippingtime;
    }

    public String getShippingtimetostr() {
        return shippingtimetostr;
    }

    public void setShippingtimetostr(String shippingtimetostr) {
        this.shippingtimetostr = shippingtimetostr;
    }

    public String getTimer() {
        return timer;
    }

    public void setTimer(String timer) {
        this.timer = timer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContacter() {
        return contacter;
    }

    public void setContacter(String contacter) {
        this.contacter = contacter;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getSubsidy() {
        return subsidy;
    }

    public void setSubsidy(String subsidy) {
        this.subsidy = subsidy;
    }

    public int getAddtime() {
        return addtime;
    }

    public void setAddtime(int addtime) {
        this.addtime = addtime;
    }

    public int getAccepttime() {
        return accepttime;
    }

    public void setAccepttime(int accepttime) {
        this.accepttime = accepttime;
    }

    public int getArrivedtime() {
        return arrivedtime;
    }

    public void setArrivedtime(int arrivedtime) {
        this.arrivedtime = arrivedtime;
    }

    public List<HurryRushOrdersGoods> getHurryRushOrdersGoods() {
        return hurryRushOrdersGoods;
    }

    public void setHurryRushOrdersGoods(List<HurryRushOrdersGoods> hurryRushOrdersGoods) {
        this.hurryRushOrdersGoods = hurryRushOrdersGoods;
    }
}
