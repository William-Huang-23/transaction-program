package bca.tugas.transaksi.batch.reader;

import bca.tugas.transaksi.batch.dataMapper.CreditCardTransMapper;
import bca.tugas.transaksi.batch.model.CreditCardTransModel;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
public class CreditCardSummTranReader {

    /*
    SELECT cc.cust_id, tc.creditcard_id, tc.amount, tc.transaction_code
    FROM transaction_creditcard  as tc
    JOIN master_creditcard as cc on cc.creditcard_id = tc.creditcard_id
    WHERE tc.status = 'Approved'
    ORDER BY tc.creditcard_id asc*/

    @Autowired
    DataSource dataSource;

    @Value("${chunk.size}")
    private Integer CHUNK;

    @Bean
    public PagingQueryProvider queryProvider() throws Exception {
        SqlPagingQueryProviderFactoryBean factoryBean = new SqlPagingQueryProviderFactoryBean();


        factoryBean.setSelectClause("SELECT cc.cust_id, tc.cc_no ,tc.tran_id, tc.amount, tc.transaction_code, tc.date");
        factoryBean.setFromClause("""
                FROM transaction_creditcard  as tc
                JOIN master_creditcard as cc on cc.creditcard_id = tc.cc_no
                """);
        factoryBean.setWhereClause("WHERE tc.status = 'Approved'");

        factoryBean.setSortKeys(Map.of("cc_no", Order.ASCENDING, "tran_id", Order.ASCENDING));

        factoryBean.setDataSource(dataSource);

        return factoryBean.getObject();
    }

    @Bean
    public ItemReader<CreditCardTransModel> ccTranItemReader() throws Exception{
        return new JdbcPagingItemReaderBuilder<CreditCardTransModel>()
                .name("ccTranItemReader")
                .dataSource(dataSource)
                .queryProvider(queryProvider())
                .rowMapper(new CreditCardTransMapper())
                .pageSize(CHUNK)
                .build();

    }

}
