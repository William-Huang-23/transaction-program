package bca.tugas.transaksi.batch.reader;

import bca.tugas.transaksi.batch.dataMapper.CustomerAllMapper;
import bca.tugas.transaksi.batch.dataMapper.TransactionTransferMapper;
import bca.tugas.transaksi.batch.model.CustomerAllModel;
import bca.tugas.transaksi.batch.model.TransactionTransferModel;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class CustomerAllReader {

    private static String SQLQUERY =
            """
                select tran_id, customer_id, transaction_type, account_id, description, amount_debit, amount_credit, tran_date
                    from transaction_customer_all;
                    """;

    @Bean
    public ItemReader<CustomerAllModel> readCustomerAllSQL(DataSource dataSource) throws Exception {
        return new JdbcCursorItemReaderBuilder<CustomerAllModel>()
                .dataSource(dataSource)
                .name("jdbcCursorItemReader")
                .sql(SQLQUERY)
                .rowMapper(new CustomerAllMapper())
                .saveState(false)
                .build();
    }

}
