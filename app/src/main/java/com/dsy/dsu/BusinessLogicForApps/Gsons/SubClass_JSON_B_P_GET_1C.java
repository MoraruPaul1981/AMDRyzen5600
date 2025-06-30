package com.dsy.dsu.BusinessLogicForApps.Gsons;

public class SubClass_JSON_B_P_GET_1C<LinkedBlockingDeque>  {


    // Public fields are included in the JSON data by default@SerializedName("mystring")
    public String  Ndoc ;
    public String CFO;
    public String organization;
    public String counterparty;
    public Double
            sum;
    public String articleDDS;
    public String nomenclature;

    public SubClass_JSON_B_P_GET_1C(String ndoc, String CFO, String organization, String counterparty, Double sum, String articleDDS, String nomenclature) {
        Ndoc = ndoc;
        this.CFO = CFO;
        this.organization = organization;
        this.counterparty = counterparty;
        this.sum = sum;
        this.articleDDS = articleDDS;
        this.nomenclature = nomenclature;
    }
    }



