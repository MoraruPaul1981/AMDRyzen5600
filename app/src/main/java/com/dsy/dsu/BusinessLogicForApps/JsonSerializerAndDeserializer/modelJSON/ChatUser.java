package com.dsy.dsu.BusinessLogicForApps.JsonSerializerAndDeserializer.modelJSON;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

;


/**
 * The persistent class for the chat_users database table.
 *
 */


public class ChatUser   extends   ParentJsonClass implements Serializable {
    private static final long serialVersionUID = 1L;


    private Integer id;


    @JsonProperty("current_table")
    private BigDecimal currentTable;


    @JsonProperty("date_update")
    private Date dateUpdate;



    private boolean locked;

    private String name;

    private int rights;

    private String telephone;

    private BigDecimal uuid;

    public ChatUser() {
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

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getLocked() {
        return this.locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRights() {
        return this.rights;
    }

    public void setRights(int rights) {
        this.rights = rights;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public BigDecimal getUuid() {
        return this.uuid;
    }

    public void setUuid(BigDecimal uuid) {
        this.uuid = uuid;
    }

}
