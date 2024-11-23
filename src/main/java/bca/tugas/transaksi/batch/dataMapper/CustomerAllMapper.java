package bca.tugas.transaksi.batch.dataMapper;

import bca.tugas.transaksi.batch.model.CustomerAllModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerAllMapper implements RowMapper<CustomerAllModel> {

    @Override
    public CustomerAllModel mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        CustomerAllModel customerAll = new CustomerAllModel();

        customerAll.setTranId(resultSet.getLong("tran_id"));
        customerAll.setCustomerId(resultSet.getLong("customer_id"));
        customerAll.setTransactionType(resultSet.getString("transaction_type"));
        customerAll.setAccountId(resultSet.getLong("account_id"));
        customerAll.setDescription(resultSet.getString("description"));
        customerAll.setAmountDebit(resultSet.getFloat("amount_debit"));
        customerAll.setAmountCredit(resultSet.getFloat("amount_credit"));
        customerAll.setTranDate(resultSet.getDate("tran_date"));

        return customerAll;
    }

}
