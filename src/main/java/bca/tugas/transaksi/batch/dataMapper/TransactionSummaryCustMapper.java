package bca.tugas.transaksi.batch.dataMapper;

import bca.tugas.transaksi.batch.model.TransactionCustomerAllModel;
import bca.tugas.transaksi.batch.model.TransactionSummaryCustModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionSummaryCustMapper implements RowMapper<TransactionSummaryCustModel> {

    @Override
    public TransactionSummaryCustModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        TransactionSummaryCustModel tscm = new TransactionSummaryCustModel();

        tscm.setCustomerId(rs.getLong("customer_id"));
        tscm.setTransactionType(rs.getString("transaction_type"));
        tscm.setTranDate(rs.getDate("tran_date"));
        tscm.setTotalDebit(rs.getDouble("total_debit"));
        tscm.setTotalCredit(rs.getDouble("total_credit"));
        return tscm;
    }
}
