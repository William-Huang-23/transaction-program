package bca.tugas.transaksi.batch.dataMapper;

import bca.tugas.transaksi.batch.model.TransactionCodeModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionCodeMapper implements RowMapper<TransactionCodeModel> {

    @Override
    public TransactionCodeModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        TransactionCodeModel tcm = new TransactionCodeModel();
            tcm.setTransactionCode(rs.getInt("transaction_code"));
            tcm.setDescription(rs.getString("description"));
            tcm.setDebitCredit(rs.getString("debit_credit"));
            tcm.setCoaCode(rs.getString("coa_code"));
            tcm.setType(rs.getString("type"));

        return tcm;
    }
}
