package com.example.shoppe_food.util;

public class Enums {
    public static enum RoleSecurity{
        CLIENT, OWNER, ADMIN
    }
    public static enum CategoryType {
       HOMEPAGE, FASHION, HOME_LIFE , TECHNOLOGICAL, HEALTH, SCHOOL_SUPPLIES, BEAUTY, FOOD, SPORT
    }
    public static enum CategoryStatus {
        active, hide , delete
    }
    public static enum ProductStatus {
        active, hide , delete
    }
    public static enum BillStatus{
        paid, unpaid
    }
    public static enum FeedbackStatus {
        active, hide , delete
    }
}
