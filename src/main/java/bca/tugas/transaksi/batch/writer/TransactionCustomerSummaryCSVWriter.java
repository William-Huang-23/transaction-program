package bca.tugas.transaksi.batch.writer;

import bca.tugas.transaksi.batch.model.TransactionSummaryCSVModel;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class TransactionCustomerSummaryCSVWriter {

    public static String[] names = new String[] { "customerId", "transactionType", "tranDate", "totalDebit", "totalCredit", "totalAmount",
             };

    @Bean
    public ItemWriter<TransactionSummaryCSVModel> TransactionSummaryCSVItemWriter() {
        FlatFileItemWriter<TransactionSummaryCSVModel> itemWriter = new FlatFileItemWriter<TransactionSummaryCSVModel>();
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        itemWriter.setResource(new FileSystemResource("data/Transaction_Summary_Customer_"+ date +".csv"));

        itemWriter.setHeaderCallback(writer -> {writer.write("customerId,transactionType,tranDate,totalDebit,totalCredit,totalAmount");});

        DelimitedLineAggregator<TransactionSummaryCSVModel> aggregator = new DelimitedLineAggregator<TransactionSummaryCSVModel>();
        aggregator.setDelimiter(",");

        BeanWrapperFieldExtractor<TransactionSummaryCSVModel> fieldExtractor = new BeanWrapperFieldExtractor<TransactionSummaryCSVModel>();
        fieldExtractor.setNames(names);
        aggregator.setFieldExtractor(fieldExtractor);

        itemWriter.setLineAggregator(aggregator);
        return itemWriter;
    }

}
