package bca.tugas.transaksi.batch.dataMapper;

import bca.tugas.transaksi.batch.model.TransactionTransferModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionTransferMapper implements RowMapper<TransactionTransferModel> {

    @Override
    public TransactionTransferModel mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        TransactionTransferModel transactionTransfer = new TransactionTransferModel();

        transactionTransfer.setTranId(resultSet.getLong("tran_id"));
        transactionTransfer.setFromAccount(resultSet.getLong("from_account"));
        transactionTransfer.setToAccount(resultSet.getLong("to_account"));
        transactionTransfer.setAmount(resultSet.getFloat("amount"));
        transactionTransfer.setTransType(resultSet.getString("trans_type"));
        transactionTransfer.setFromInstitution(resultSet.getString("from_institution"));
        transactionTransfer.setToInstitution(resultSet.getString("to_institution"));
        transactionTransfer.setSwitchingType(resultSet.getString("switching_type"));
        transactionTransfer.setDate(resultSet.getString("date"));
        transactionTransfer.setTime(resultSet.getString("time"));
        transactionTransfer.setStatus(resultSet.getString("status"));

        return transactionTransfer;
    }

}
