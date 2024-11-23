package bca.tugas.transaksi.batch.reader;

import bca.tugas.transaksi.batch.dataMapper.CreditCardTransMapper;
import bca.tugas.transaksi.batch.dataMapper.TransactionSummaryCustMapper;
import bca.tugas.transaksi.batch.model.CreditCardTransModel;
import bca.tugas.transaksi.batch.model.TransactionCustomerAllModel;
import bca.tugas.transaksi.batch.model.TransactionSummaryCustModel;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
public class TransactionCustomerAllReader {
    /*
    SELECT customer_id, transaction_type, tran_date, sum(amount_debit) as total_debit, sum(amount_credit) as total_credit
FROM public.transaction_customer_all
group by customer_id, transaction_type, tran_date
order by customer_id asc, transaction_type asc
     */

    @Autowired
    DataSource dataSource;

    @Value("${chunk.size}")
    private Integer CHUNK;
    @Bean
    public PagingQueryProvider transactionSummaryQueryProvider() throws Exception {
        SqlPagingQueryProviderFactoryBean factoryBean = new SqlPagingQueryProviderFactoryBean();


        factoryBean.setSelectClause("SELECT customer_id, transaction_type, tran_date, sum(amount_debit) as total_debit, sum(amount_credit) as total_credit");
        factoryBean.setFromClause("FROM public.transaction_customer_all");
        factoryBean.setGroupClause("group by customer_id, transaction_type, tran_date");
        factoryBean.setSortKeys(Map.of("customer_id", Order.ASCENDING, "transaction_type", Order.ASCENDING));
        factoryBean.setDataSource(dataSource);

        return factoryBean.getObject();
    }

    @Bean
    public ItemReader<TransactionSummaryCustModel> transactionSummaryAllReader() throws Exception{
        return new JdbcPagingItemReaderBuilder<TransactionSummaryCustModel>()
                .name("transactionSummaryAllReader")
                .dataSource(dataSource)
                .queryProvider(transactionSummaryQueryProvider())
                .rowMapper(new TransactionSummaryCustMapper())
                .pageSize(CHUNK)
                .build();

    }

}
