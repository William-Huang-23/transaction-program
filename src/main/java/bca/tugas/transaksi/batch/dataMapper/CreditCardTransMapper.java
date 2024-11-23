package bca.tugas.transaksi.batch.dataMapper;


import bca.tugas.transaksi.batch.model.CreditCardTransModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CreditCardTransMapper implements RowMapper<CreditCardTransModel> {
    @Override
    public CreditCardTransModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        CreditCardTransModel cctm = new CreditCardTransModel();
        cctm.setCustId(rs.getLong("cust_id"));
        cctm.setCreditcardId(rs.getLong("cc_no"));
        cctm.setAmount(rs.getDouble("amount"));
        cctm.setTransactionCode(rs.getInt("transaction_code"));
        cctm.setTranId(rs.getLong("tran_id"));
        cctm.setDate(rs.getDate("date"));
        return cctm;
    }
}
