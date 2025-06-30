package com.dsy.dsu.BusinessLogicForApps.JsonSerializerAndDeserializer.modelJSON;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * The persistent class for the data_chat database table.
 *
 */
/**
 * @author moraru_pi
 *
 */


public class DataChat extends   ParentJsonClass implements Serializable {
    private static final long serialVersionUID = 1L;


    private Integer id;

    private Integer alreadyshownnotifications;


    @JsonProperty("chat_uuid")

    private BigDecimal chatUuid;


    @JsonProperty("current_table")
    private BigDecimal currentTable;


    @JsonProperty("date_update")
    private Date dateUpdate;


    @JsonProperty("image_chat")
    private String imageChat;

    private String message;


    @JsonProperty("status_write")
    private boolean statusWrite;


    @JsonProperty("user_update")
    private int userUpdate;

    private BigDecimal uuid;

    public DataChat() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAlreadyshownnotifications() {
        return this.alreadyshownnotifications;
    }

    public void setAlreadyshownnotifications(Integer alreadyshownnotifications) {
        this.alreadyshownnotifications = alreadyshownnotifications;
    }

    public BigDecimal getChatUuid() {
        return this.chatUuid;
    }

    public void setChatUuid(BigDecimal chatUuid) {
        this.chatUuid = chatUuid;
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

    public String getImageChat() {
        return this.imageChat;
    }

    public void setImageChat(String imageChat) {
        this.imageChat = imageChat;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getStatusWrite() {
        return this.statusWrite;
    }

    public void setStatusWrite(boolean statusWrite) {
        this.statusWrite = statusWrite;
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
    // TODO mappimg

}
