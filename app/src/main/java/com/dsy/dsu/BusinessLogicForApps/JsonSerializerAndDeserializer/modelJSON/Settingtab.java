package com.dsy.dsu.BusinessLogicForApps.JsonSerializerAndDeserializer.modelJSON;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the settings_tabels database table.
 *
 */


public class Settingtab extends   ParentJsonClass implements Serializable{
    private static final long serialVersionUID = 1L;


    private int id;


    @JsonProperty("current_table")
    private BigDecimal currentTable;


    @JsonProperty("date_update")
    private Date dateUpdate;

    private String onesignal;

    private Integer organizations;


    @JsonProperty("user_update")
    private Integer userUpdate;

    private BigDecimal uuid;


    @JsonProperty("version_dsu1")
    private Integer versionDsu1;

    public Settingtab() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getCurrentTable() {
        return this.currentTable;
    }

    public void setCurrentTable(BigDecimal currentTable) {
        this.currentTable = currentTable;
    }

    public Date getDateUpdate() {
        return this.dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public String getOnesignal() {
        return this.onesignal;
    }

    public void setOnesignal(String onesignal) {
        this.onesignal = onesignal;
    }

    public Integer getOrganizations() {
        return this.organizations;
    }

    public void setOrganizations(Integer organizations) {
        this.organizations = organizations;
    }

    public Integer getUserUpdate() {
        return this.userUpdate;
    }

    public void setUserUpdate(Integer userUpdate) {
        this.userUpdate = userUpdate;
    }

    public BigDecimal getUuid() {
        return this.uuid;
    }

    public void setUuid(BigDecimal uuid) {
        this.uuid = uuid;
    }

    public Integer getVersionDsu1() {
        return this.versionDsu1;
    }

    public void setVersionDsu1(Integer versionDsu1) {
        this.versionDsu1 = versionDsu1;
    }

}