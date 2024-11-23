package bca.tugas.transaksi.batch.writer;

import bca.tugas.transaksi.batch.model.TransactionTransferModel;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
public class TransactionTransferWriter {

    private static String[] names = new String[] {"tranId", "fromAccount", "toAccount", "amount",
            "transType", "fromInstitution", "toInstitution", "switchingType", "date", "time", "status"
    };

    @Bean
    public ItemWriter<TransactionTransferModel> writeTransactionTransferCSV() {
        FlatFileItemWriter<TransactionTransferModel> flatFileItemWriter = new FlatFileItemWriter<>();
        flatFileItemWriter.setResource(new FileSystemResource("data/transaction_transfer.csv"));

        DelimitedLineAggregator<TransactionTransferModel> aggregator = new DelimitedLineAggregator<>();
        aggregator.setDelimiter(",");

        BeanWrapperFieldExtractor<TransactionTransferModel>fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(names);
        aggregator.setFieldExtractor(fieldExtractor);

        flatFileItemWriter.setLineAggregator(aggregator);
        return flatFileItemWriter;
    }

}
