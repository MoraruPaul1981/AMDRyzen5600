package com.dsy.dsu.BusinessLogicForApps.JsonSerializerAndDeserializer.modelJSON;

import com.fasterxml.jackson.annotation.JsonFormat;


import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the track database table.
 *
 */


public class Track implements Serializable {
    private static final long serialVersionUID = 1L;


    private Integer id;

    private String name;

    private String fullname;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss.SSS" )
    private Date dateUpdate;


    private Integer userUpdate;


    private Integer dir;


    private BigDecimal uuid;


    private BigDecimal currentTable;




    private Integer owner;

    private Integer vid_tc;

    public Track() {
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

    public int getDir() {
        return this.dir;
    }

    public void setDir(Integer dir) {
        this.dir = dir;
    }

    public String getFullname() {
        return this.fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public Integer getVid_tc() {
        return vid_tc;
    }

    public void setVid_tc(Integer vid_tc) {
        this.vid_tc = vid_tc;
    }
    public Integer getOwner() {
        return owner;
    }
}