package com.dsy.dsu.BusinessLogicForApps.JsonSerializerAndDeserializer.modelJSON;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;




public class Prof extends   ParentJsonClass implements Serializable  {
    private static final long serialVersionUID = 1L;

    private int id;


    private String name;


    @JsonProperty("user_update")
    private int userUpdate;


    @JsonProperty("date_update")
    private Date dateUpdate;


    @JsonProperty("current_table")
    private BigDecimal currentTable;


    private BigDecimal uuid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(int userUpdate) {
        this.userUpdate = userUpdate;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public BigDecimal getCurrentTable() {
        return currentTable;
    }

    public void setCurrentTable(BigDecimal currentTable) {

        this.currentTable = currentTable;
    }

    public BigDecimal getUuid() {
        return uuid;
    }

    public void setUuid(BigDecimal uuid) {

        this.uuid = uuid;
    }


}
