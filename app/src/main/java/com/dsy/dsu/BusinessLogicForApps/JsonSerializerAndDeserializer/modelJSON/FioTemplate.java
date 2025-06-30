package com.dsy.dsu.BusinessLogicForApps.JsonSerializerAndDeserializer.modelJSON;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the fio_template database table.
 *
 */


public class FioTemplate  extends   ParentJsonClass implements Serializable {
    private static final long serialVersionUID = 1L;


    private Integer id;


    @JsonProperty("current_table")
    private BigDecimal currentTable;


    @JsonProperty("date_update")
    private Date dateUpdate;


    @JsonProperty("fio_template")
    private BigDecimal fioTemplate;


    @JsonProperty("fio_uuid")
    private BigDecimal fioUuid;


    @JsonProperty("status_send")
    private String statusSend;


    @JsonProperty("user_update")
    private int userUpdate;

    private BigDecimal uuid;

    public FioTemplate() {
    }




    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
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

    public BigDecimal getFioTemplate() {
        return this.fioTemplate;
    }

    public void setFioTemplate(BigDecimal fioTemplate) {
        this.fioTemplate = fioTemplate;
    }

    public BigDecimal getFioUuid() {
        return this.fioUuid;
    }

    public void setFioUuid(BigDecimal fioUuid) {
        this.fioUuid = fioUuid;
    }

    public String getStatusSend() {
        return this.statusSend;
    }

    public void setStatusSend(String statusSend) {
        this.statusSend = statusSend;
    }

    public int getUserUpdate() {
        return this.userUpdate;
    }

    public void setUserUpdate(int userUpdate) {
        this.userUpdate = userUpdate;
    }

    public BigDecimal getUuid() {
        return this.uuid;
    }

    public void setUuid(BigDecimal uuid) {
        this.uuid = uuid;
    }

}