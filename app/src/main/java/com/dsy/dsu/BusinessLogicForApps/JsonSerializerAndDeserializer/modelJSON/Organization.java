package com.dsy.dsu.BusinessLogicForApps.JsonSerializerAndDeserializer.modelJSON;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the organization database table.
 *
 */

public class Organization extends   ParentJsonClass implements Serializable {
    private static final long serialVersionUID = 1L;


    private int id;


    private Integer chosen_organization;


    private BigDecimal current_table;


    private Date date_update;

    private String fullname;

    private String inn;

    private String kpp;
    @JsonProperty("name")
    private String name;


    private int user_update;

    private BigDecimal uuid;


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getChosenOrganization() {
        if (this.chosen_organization==null) {
            return 1;
        }else {
            return this.chosen_organization;
        }
    }

    public void setChosenOrganization(Integer chosenOrganization) {
        this.chosen_organization = chosenOrganization;
    }

    public BigDecimal getCurrentTable() {
        return this.current_table;
    }

    public void setCurrentTable(BigDecimal currentTable) {
        this.current_table = currentTable;
    }

    public Date getDateUpdate() {
        return this.date_update;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.date_update = dateUpdate;
    }

    public String getFullname() {
        return this.fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getInn() {
        return this.inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public Object getKpp() {
        return this.kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserUpdate() {
        return this.user_update;
    }

    public void setUserUpdate(int userUpdate) {
        this.user_update = userUpdate;
    }

    public BigDecimal getUuid() {
        return this.uuid;
    }

    public void setUuid(BigDecimal uuid) {
        this.uuid = uuid;
    }





}