package com.dsy.dsu.BusinessLogicForApps.JsonSerializerAndDeserializer.modelJSON;




import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the cfo database table.
 *
 */



public class Cfo extends   ParentJsonClass  implements  Serializable {
private static final long serialVersionUID = 1L;


private Integer id;

private int boss;

private boolean closed;


@JsonProperty("current_table")
private BigDecimal currentTable;


@JsonProperty("date_update")
private Date dateUpdate;

private String kod;

private String name;

private int organization;

private int region;


@JsonProperty("user_update")
private int userUpdate;

private BigDecimal uuid;

public Cfo() {
        }

public Integer getId() {
        return this.id;
        }

public void setId(Integer id) {
        this.id = id;
        }

public int getBoss() {
        return this.boss;
        }

public void setBoss(int boss) {
        this.boss = boss;
        }

public boolean getClosed() {
        return this.closed;
        }

public void setClosed(boolean closed) {
        this.closed = closed;
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

public String getKod() {
        return this.kod;
        }

public void setKod(String kod) {
        this.kod = kod;
        }

public String getName() {
        return this.name;
        }

public void setName(String name) {
        this.name = name;
        }

public int getOrganization() {
        return this.organization;
        }

public void setOrganization(int organization) {
        this.organization = organization;
        }

public int getRegion() {
        return this.region;
        }

public void setRegion(int region) {
        this.region = region;
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
