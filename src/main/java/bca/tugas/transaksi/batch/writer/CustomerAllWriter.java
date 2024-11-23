package bca.tugas.transaksi.batch.writer;

import bca.tugas.transaksi.batch.model.CustomerAllModel;
import bca.tugas.transaksi.batch.model.TransactionTransferModel;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
public class CustomerAllWriter {

    private static String[] names = new String[] {"tranId", "customerId", "transactionType", "accountId",
            "description", "amountDebit", "amountCredit", "tranDate"
    };

    @Bean
    public ItemWriter<CustomerAllModel> writeCustomerAllCSV() {
        FlatFileItemWriter<CustomerAllModel> flatFileItemWriter = new FlatFileItemWriter<>();
        flatFileItemWriter.setResource(new FileSystemResource("data/customer_all.csv"));

        DelimitedLineAggregator<CustomerAllModel> aggregator = new DelimitedLineAggregator<>();
        aggregator.setDelimiter(",");

        BeanWrapperFieldExtractor<CustomerAllModel> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(names);
        aggregator.setFieldExtractor(fieldExtractor);

        flatFileItemWriter.setLineAggregator(aggregator);
        return flatFileItemWriter;
    }
    
}
