package com.dsy.dsu.BusinessLogicForApps.JsonSerializerAndDeserializer.modelJSON;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * The persistent class for the fio database table.
 *
 */

public class Fio  extends  ParentJsonClass  implements  Serializable  {
    private static final long serialVersionUID = 1L;
    private int id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonProperty("BirthDate")

    private Date BirthDate;


    private Integer current_organization;

    private BigDecimal current_table;


    private Date date_update;

    private String f;

    private String n;

    private String name;

    private String o;

    private String snils;


    private int user_update;

    private BigDecimal uuid;

    private Integer prof;

    public Fio() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBirthDate() throws ParseException {
        DateFormat	dateFormat =   new SimpleDateFormat("yyyy-MM-dd",new Locale("ru"));
        if ( this.BirthDate==null) {
            return this.BirthDate;

        }else {
            String Дата = dateFormat.format(this.BirthDate);
            this. BirthDate = dateFormat.parse(Дата);
            return this.BirthDate;
        }


    }

    public void setBirthDate(Date BirthDate) {
        this.BirthDate = BirthDate;
    }

    public int getCurrentOrganization() {
        if (this.current_organization==null) {
            return 1;
        }else {
            return this.current_organization;
        }
    }

    public void setCurrentOrganization(int current_organization) {
        this.current_organization = current_organization;
    }

    public BigDecimal getCurrentTable() {
        return this.current_table;
    }

    public void setCurrentTable(BigDecimal current_table) {
        this.current_table = current_table;
    }

    public Date getDateUpdate() throws ParseException{
        DateFormat	dateFormat =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS",new Locale("ru"));
        String Дата = dateFormat.format(this.date_update);
        this.date_update = dateFormat.parse(Дата);
            return this.date_update;
    }

    public void setDateUpdate(Date date_update) {
        this.date_update = date_update;
    }

    public String getF() {
        return this.f;
    }

    public void setF(String f) {
        this.f = f;
    }

    public String getN() {
        return this.n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getO() {
        return this.o;
    }

    public void setO(String o) {
        this.o = o;
    }

    public String getSnils() {
        if (this.snils==null) {
            return " ";
        }else {
            return this.snils;
        }
    }

    public void setSnils(String snils) {
        this.snils = snils;
    }

    public int getUserUpdate() {
        return this.user_update;
    }

    public void setUserUpdate(int user_update) {
        this.user_update = user_update;
    }

    public BigDecimal getUuid() {
        return this.uuid;
    }

    public void setUuid(BigDecimal uuid) {
        this.uuid = uuid;
    }
    public Integer getProf() {
        return this.prof;
    }

    public void setProf(Integer prof) {

        this.prof = prof;
    }

}