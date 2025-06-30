package com.dsy.dsu.BusinessLogicForApps.JsonSerializerAndDeserializer.modelJSON;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the data_tabels database table.
 *
 */


public class DataTabel  extends   ParentJsonClass implements Serializable {
    private static final long serialVersionUID = 1L;


    private Integer id;


    @JsonProperty("current_table")
    private BigDecimal currentTable;

    private String d1;

    private String d10;

    private String d11;

    private String d12;

    private String d13;

    private String d14;

    private String d15;

    private String d16;

    private String d17;

    private String d18;

    private String d19;

    private String d2;

    private String d20;

    private String d21;

    private String d22;

    private String d23;

    private String d24;

    private String d25;

    private String d26;

    private String d27;

    private String d28;

    private String d29;

    private String d3;

    private String d30;

    private String d31;

    private String d4;

    private String d5;

    private String d6;

    private String d7;

    private String d8;

    private String d9;


    @JsonProperty("date_update")
    private Date dateUpdate;

    private BigDecimal fio;


    @JsonProperty("status_carried_out")
    private boolean statusCarriedOut;


    @JsonProperty("status_send")
    private String statusSend;


    @JsonProperty("user_update")
    private int userUpdate;

    private BigDecimal uuid;


    @JsonProperty("uuid_tabel")
    private BigDecimal uuidTabel;

    private Integer prof;

    public DataTabel() {
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

    public String getD1() {
        return this.d1;
    }

    public void setD1(String d1) {
        this.d1 = d1;
    }

    public String getD10() {
        return this.d10;
    }

    public void setD10(String d10) {
        this.d10 = d10;
    }

    public String getD11() {
        return this.d11;
    }

    public void setD11(String d11) {
        this.d11 = d11;
    }

    public String getD12() {
        return this.d12;
    }

    public void setD12(String d12) {
        this.d12 = d12;
    }

    public String getD13() {
        return this.d13;
    }

    public void setD13(String d13) {
        this.d13 = d13;
    }

    public String getD14() {
        return this.d14;
    }

    public void setD14(String d14) {
        this.d14 = d14;
    }

    public String getD15() {
        return this.d15;
    }

    public void setD15(String d15) {
        this.d15 = d15;
    }

    public String getD16() {
        return this.d16;
    }

    public void setD16(String d16) {
        this.d16 = d16;
    }

    public String getD17() {
        return this.d17;
    }

    public void setD17(String d17) {
        this.d17 = d17;
    }

    public String getD18() {
        return this.d18;
    }

    public void setD18(String d18) {
        this.d18 = d18;
    }

    public String getD19() {
        return this.d19;
    }

    public void setD19(String d19) {
        this.d19 = d19;
    }

    public String getD2() {
        return this.d2;
    }

    public void setD2(String d2) {
        this.d2 = d2;
    }

    public String getD20() {
        return this.d20;
    }

    public void setD20(String d20) {
        this.d20 = d20;
    }

    public String getD21() {
        return this.d21;
    }

    public void setD21(String d21) {
        this.d21 = d21;
    }

    public String getD22() {
        return this.d22;
    }

    public void setD22(String d22) {
        this.d22 = d22;
    }

    public String getD23() {
        return this.d23;
    }

    public void setD23(String d23) {
        this.d23 = d23;
    }

    public String getD24() {
        return this.d24;
    }

    public void setD24(String d24) {
        this.d24 = d24;
    }

    public String getD25() {
        return this.d25;
    }

    public void setD25(String d25) {
        this.d25 = d25;
    }

    public String getD26() {
        return this.d26;
    }

    public void setD26(String d26) {
        this.d26 = d26;
    }

    public String getD27() {
        return this.d27;
    }

    public void setD27(String d27) {
        this.d27 = d27;
    }

    public String getD28() {
        return this.d28;
    }

    public void setD28(String d28) {
        this.d28 = d28;
    }

    public String getD29() {
        return this.d29;
    }

    public void setD29(String d29) {
        this.d29 = d29;
    }

    public String getD3() {
        return this.d3;
    }

    public void setD3(String d3) {
        this.d3 = d3;
    }

    public String getD30() {
        return this.d30;
    }

    public void setD30(String d30) {
        this.d30 = d30;
    }

    public String getD31() {
        return this.d31;
    }

    public void setD31(String d31) {
        this.d31 = d31;
    }

    public String getD4() {
        return this.d4;
    }

    public void setD4(String d4) {
        this.d4 = d4;
    }

    public String getD5() {
        return this.d5;
    }

    public void setD5(String d5) {
        this.d5 = d5;
    }

    public String getD6() {
        return this.d6;
    }

    public void setD6(String d6) {
        this.d6 = d6;
    }

    public String getD7() {
        return this.d7;
    }

    public void setD7(String d7) {
        this.d7 = d7;
    }

    public String getD8() {
        return this.d8;
    }

    public void setD8(String d8) {
        this.d8 = d8;
    }

    public String getD9() {
        return this.d9;
    }

    public void setD9(String d9) {
        this.d9 = d9;
    }

    public Date getDateUpdate() {
        return this.dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public BigDecimal getFio() {
        return this.fio;
    }

    public void setFio(BigDecimal fio) {
        this.fio = fio;
    }

    public boolean getStatusCarriedOut() {
        return this.statusCarriedOut;
    }

    public void setStatusCarriedOut(boolean statusCarriedOut) {
        this.statusCarriedOut = statusCarriedOut;
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

    public BigDecimal getUuidTabel() {
        return this.uuidTabel;
    }

    public void setUuidTabel(BigDecimal uuidTabel) {
        this.uuidTabel = uuidTabel;
    }

    public Integer getProf() {
        return this.prof;
    }

    public void setProf(Integer prof) {
        this.prof = prof;
    }

}