package bca.tugas.transaksi.batch.model;


import java.sql.Date;

public class TransactionCustomerAllModel {

    private Long tranId;
    private Long customerId;
    private String transactionType;
    private Long accountId;
    private String description;
    private Double amountDebit;
    private Double amountCredit;
    private Date tranDate;

    public Long getTranId() {
        return tranId;
    }

    public void setTranId(Long tranId) {
        this.tranId = tranId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmountDebit() {
        return amountDebit;
    }

    public void setAmountDebit(Double amountDebit) {
        this.amountDebit = amountDebit;
    }

    public Double getAmountCredit() {
        return amountCredit;
    }

    public void setAmountCredit(Double amountCredit) {
        this.amountCredit = amountCredit;
    }

    public Date getTranDate() {
        return tranDate;
    }

    public void setTranDate(Date tranDate) {
        this.tranDate = tranDate;
    }

    @Override
    public String toString() {
        return "TransactionCustomerAllModel{" +
                "tranId=" + tranId +
                ", customerId=" + customerId +
                ", transactionType='" + transactionType + '\'' +
                ", accountId=" + accountId +
                ", description='" + description + '\'' +
                ", amountDebit=" + amountDebit +
                ", amountCredit=" + amountCredit +
                ", tranDate=" + tranDate +
                '}';
    }
}
