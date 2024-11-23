package bca.tugas.transaksi.batch.model;

import java.math.BigInteger;
import java.sql.Date;

public class CreditCardTransModel {

    private Long custId;
    private Long creditcardId;
    private Double amount;
    private Integer transactionCode;
    private Long tranId;
    private Date date;

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public Long getCreditcardId() {
        return creditcardId;
    }

    public void setCreditcardId(Long creditcardId) {
        this.creditcardId = creditcardId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(Integer transactionCode) {
        this.transactionCode = transactionCode;
    }

    public Long getTranId() {
        return tranId;
    }

    public void setTranId(Long tranId) {
        this.tranId = tranId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CreditCardTransModel{" +
                "custId=" + custId +
                ", creditcardId=" + creditcardId +
                ", amount=" + amount +
                ", transactionCode=" + transactionCode +
                ", tranId=" + tranId +
                ", date=" + date +
                '}';
    }
}
