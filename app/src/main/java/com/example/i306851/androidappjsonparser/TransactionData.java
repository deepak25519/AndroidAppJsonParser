package com.example.i306851.androidappjsonparser;

import java.util.Date;

/**
 * Created by  on 5/24/2015.
 */
public class TransactionData {

    private String itmeName;

    private String itemCategory;

    private String itemDescription;

    private Long payee;

    private Long payer;

    private String paymentMode;

    private String paymnetStatus;

    private Date dueDate;

    public String getItmeName() {
        return itmeName;
    }

    public void setItmeName(String itmeName) {
        this.itmeName = itmeName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public Long getPayee() {
        return payee;
    }

    public void setPayee(Long payee) {
        this.payee = payee;
    }

    public Long getPayer() {
        return payer;
    }

    public void setPayer(Long payer) {
        this.payer = payer;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getPaymnetStatus() {
        return paymnetStatus;
    }

    public void setPaymnetStatus(String paymnetStatus) {
        this.paymnetStatus = paymnetStatus;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }
}
