package bca.tugas.transaksi.batch.reader;

import bca.tugas.transaksi.batch.dataMapper.TransactionCodeMapper;
import bca.tugas.transaksi.batch.model.TransactionCodeModel;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class TransactionCodeParamReader {

    @Autowired
    DataSourceTransactionManager transactionManager;

    @Value("${chunk.size}")
    private Integer CHUNK;

    private static String tranCodeQuery =
            """
                SELECT transaction_code, description, debit_credit, coa_code, type
                    FROM public.transaction_code;
                    """;

    @Bean
    public ItemReader<TransactionCodeModel> transactionCodeJDBCReader() {
        return new JdbcCursorItemReaderBuilder<TransactionCodeModel>()
                .dataSource(transactionManager.getDataSource())
                .name("transactionCodeJDBCReader")
                .sql(tranCodeQuery)
                .rowMapper(new TransactionCodeMapper())
                .build();

    }



}
