package bca.tugas.transaksi.batch.model;

import java.util.Date;

public class CustomerAllModel {

    private long tranId;
    private long customerId;
    private String transactionType;
    private long accountId;
    private String description;
    private float amountDebit;
    private float amountCredit;
    private Date tranDate;

    public long getTranId() {
        return tranId;
    }

    public void setTranId(long tranId) {
        this.tranId = tranId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getAmountDebit() {
        return amountDebit;
    }

    public void setAmountDebit(float amountDebit) {
        this.amountDebit = amountDebit;
    }

    public float getAmountCredit() {
        return amountCredit;
    }

    public void setAmountCredit(float amountCredit) {
        this.amountCredit = amountCredit;
    }

    public Date getTranDate() {
        return tranDate;
    }

    public void setTranDate(Date tranDate) {
        this.tranDate = tranDate;
    }
}
