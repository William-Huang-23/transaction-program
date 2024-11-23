package bca.tugas.transaksi.batch.model;

public class TransactionTransferModel {

    private long tranId;
    private long fromAccount;
    private long toAccount;
    private float amount;
    private String transType;
    private String fromInstitution;
    private String toInstitution;
    private String switchingType;
    private String date;
    private String time;
    private String status;

    public long getTranId() {
        return tranId;
    }

    public void setTranId(long tranId) {
        this.tranId = tranId;
    }

    public long getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(long fromAccount) {
        this.fromAccount = fromAccount;
    }

    public long getToAccount() {
        return toAccount;
    }

    public void setToAccount(long toAccount) {
        this.toAccount = toAccount;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getFromInstitution() {
        return fromInstitution;
    }

    public void setFromInstitution(String fromInstitution) {
        this.fromInstitution = fromInstitution;
    }

    public String getToInstitution() {
        return toInstitution;
    }

    public void setToInstitution(String toInstitution) {
        this.toInstitution = toInstitution;
    }

    public String getSwitchingType() {
        return switchingType;
    }

    public void setSwitchingType(String switchingType) {
        this.switchingType = switchingType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
