package bca.tugas.transaksi.batch.model;

public class TransactionCodeModel {

    private Integer transactionCode;
    private String description;
    private String debitCredit;
    private String coaCode;
    private String type;

    public Integer getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(Integer transactionCode) {
        this.transactionCode = transactionCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDebitCredit() {
        return debitCredit;
    }

    public void setDebitCredit(String debitCredit) {
        this.debitCredit = debitCredit;
    }

    public String getCoaCode() {
        return coaCode;
    }

    public void setCoaCode(String coaCode) {
        this.coaCode = coaCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TransactionCodeModel{" +
                "transactionCode=" + transactionCode +
                ", description='" + description + '\'' +
                ", debitCredit='" + debitCredit + '\'' +
                ", coaCode='" + coaCode + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
