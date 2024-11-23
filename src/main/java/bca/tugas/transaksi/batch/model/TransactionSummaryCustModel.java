package bca.tugas.transaksi.batch.model;

import java.sql.Date;

public class TransactionSummaryCustModel {

    private Long customerId;
    private String transactionType;
    private Date tranDate;
    private Double totalDebit;
    private Double totalCredit;

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

    public Date getTranDate() {
        return tranDate;
    }

    public void setTranDate(Date tranDate) {
        this.tranDate = tranDate;
    }

    public Double getTotalDebit() {
        return totalDebit;
    }

    public void setTotalDebit(Double totalDebit) {
        this.totalDebit = totalDebit;
    }

    public Double getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(Double totalCredit) {
        this.totalCredit = totalCredit;
    }

    @Override
    public String toString() {
        return "TransactionSummaryCustModel{" +
                "customerId=" + customerId +
                ", transactionType='" + transactionType + '\'' +
                ", tranDate=" + tranDate +
                ", totalDebit=" + totalDebit +
                ", totalCredit=" + totalCredit +
                '}';
    }
}
