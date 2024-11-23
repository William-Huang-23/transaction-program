package bca.tugas.transaksi.batch.reader;

import bca.tugas.transaksi.batch.dataMapper.TransactionTransferMapper;
import bca.tugas.transaksi.batch.model.TransactionTransferModel;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class TransactionTransferReader {

    private static String SQLQUERY =
            """
                select tran_id, from_account, to_account, amount, trans_type, from_institution, to_institution, switching_type, date, time, status
                    from transaction_transfer;
                    """;

    @Bean
    public ItemReader<TransactionTransferModel> readTransactionTransferSQL(DataSource dataSource) throws Exception {
        return new JdbcCursorItemReaderBuilder<TransactionTransferModel>()
                .dataSource(dataSource)
                .name("jdbcCursorItemReader")
                .sql(SQLQUERY)
                .rowMapper(new TransactionTransferMapper())
                .saveState(false)
                .build();
    }

}
