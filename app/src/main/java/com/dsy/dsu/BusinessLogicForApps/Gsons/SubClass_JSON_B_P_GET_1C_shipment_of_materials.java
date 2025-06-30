package com.dsy.dsu.BusinessLogicForApps.Gsons;

public class SubClass_JSON_B_P_GET_1C_shipment_of_materials<LinkedBlockingDeque>  {


    // Public fields are included in the JSON data by default@SerializedName("mystring")
    private String cfo;
    private String type_material;
    private Integer  amount ;
    private String nomenclatura;
    private Integer PurchasedPermits;
    private Integer BroughtPermits;
    private Integer Weighted;
    private Integer Remainder;


    public SubClass_JSON_B_P_GET_1C_shipment_of_materials(String cfo, String type_material, Integer amount, String nomenclatura, Integer purchasedPermits, Integer broughtPermits, Integer weighted, Integer remainder) {
        this.cfo = cfo;
        this.type_material = type_material;
        this.amount = amount;
        this.nomenclatura = nomenclatura;
        this.PurchasedPermits = purchasedPermits;
        this.BroughtPermits = broughtPermits;
        this.Weighted = weighted;
        this.Remainder = remainder;
    }




}
