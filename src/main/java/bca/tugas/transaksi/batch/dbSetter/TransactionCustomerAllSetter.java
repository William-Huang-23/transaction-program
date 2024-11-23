package bca.tugas.transaksi.batch.dbSetter;

import bca.tugas.transaksi.batch.model.TransactionCodeModel;
import bca.tugas.transaksi.batch.model.TransactionCustomerAllModel;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionCustomerAllSetter implements ItemPreparedStatementSetter<TransactionCustomerAllModel> {

    @Override
    public void setValues(TransactionCustomerAllModel item, PreparedStatement ps) throws SQLException {
        ps.setLong(1,item.getCustomerId());
        ps.setString(2,item.getTransactionType());
        ps.setLong(3,item.getAccountId());
        ps.setString(4,item.getDescription());
        ps.setDouble(5,item.getAmountDebit());
        ps.setDouble(6,item.getAmountCredit());
        ps.setDate(7,item.getTranDate());
    }
}
