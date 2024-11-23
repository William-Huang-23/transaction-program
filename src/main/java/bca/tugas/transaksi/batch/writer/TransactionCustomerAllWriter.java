package bca.tugas.transaksi.batch.writer;

import bca.tugas.transaksi.batch.dbSetter.TransactionCustomerAllSetter;
import bca.tugas.transaksi.batch.model.TransactionCustomerAllModel;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class TransactionCustomerAllWriter {

    @Autowired
    DataSource dataSource;

    private String tcaInsertQuery =
            """
                    INSERT INTO public.transaction_customer_all(
                    	customer_id, transaction_type, account_id, description, amount_debit, amount_credit, tran_date)
                    	VALUES (?, ?, ?, ?, ?, ?, ?);
                    """;
    @Bean
    public ItemWriter<TransactionCustomerAllModel> tranCustAllitemWriter() {
        return new JdbcBatchItemWriterBuilder<TransactionCustomerAllModel>()
                .dataSource(dataSource)
                .sql(tcaInsertQuery)
                .itemPreparedStatementSetter(new TransactionCustomerAllSetter())
                .build();
    }
}
