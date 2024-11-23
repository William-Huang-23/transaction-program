package bca.tugas.transaksi.batch.config;

import bca.tugas.transaksi.batch.errorHandeler.TransactionCodeException;
import bca.tugas.transaksi.batch.errorHandeler.TransactionCreditCardSkipListener;
import bca.tugas.transaksi.batch.model.*;
import bca.tugas.transaksi.batch.processor.TransactionCustomerCrediCardProcessor;
import bca.tugas.transaksi.batch.processor.TransactionCustomerSummaryProcessor;
import bca.tugas.transaksi.batch.reader.*;
import bca.tugas.transaksi.batch.writer.CustomerAllWriter;
import bca.tugas.transaksi.batch.writer.TransactionCustomerAllWriter;
import bca.tugas.transaksi.batch.writer.TransactionCustomerSummaryCSVWriter;
import bca.tugas.transaksi.batch.writer.TransactionTransferWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class JobDailyConfig {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    DataSourceTransactionManager transactionManager;

    @Value("${chunk.size}")
    private Integer CHUNK;

    @Autowired
    CreditCardSummTranReader creditCardSummTranReader;

    @Autowired
    TransactionCustomerCrediCardProcessor transactionCustomerCrediCardProcessor;

    @Autowired
    TransactionCustomerSummaryProcessor transactionCustomerSummaryProcessor;

    @Autowired
    TransactionCodeParamReader transactionCodeParamReader;

    @Autowired
    TransactionCustomerAllReader transactionCustomerAllReader;

    @Autowired
    TransactionCustomerAllWriter transactionCustomerAllWriter;

    @Autowired
    TransactionCustomerSummaryCSVWriter transactionCustomerSummaryCSVWriter;

    @Autowired
    TransactionTransferReader transactionTransferReader;

    @Autowired
    TransactionTransferWriter transactionTransferWriter;

    @Autowired
    CustomerAllReader customerAllReader;

    @Autowired
    CustomerAllWriter customerAllWriter;

    private List<TransactionCodeModel> transactionCodeArray = new ArrayList<>();

    @Bean
    public TaskExecutor simpleExecutor(){
        SimpleAsyncTaskExecutor simple = new SimpleAsyncTaskExecutor();
        simple.setConcurrencyLimit(2);
        return simple;
    }

    @Bean
    public Step summerizeCCTranStep() throws Exception {
        return new StepBuilder("summerizeCCTranStep", jobRepository)
                .<CreditCardTransModel, TransactionCustomerAllModel>chunk(CHUNK,transactionManager)
                .faultTolerant()
                .skip(TransactionCodeException.class)
                .skipLimit(1)
                .listener(new TransactionCreditCardSkipListener())
                .reader(creditCardSummTranReader.ccTranItemReader())
                .processor(transactionCustomerCrediCardProcessor.tranCustCCprocessor(transactionCodeArray))
                .writer(transactionCustomerAllWriter.tranCustAllitemWriter())
                .taskExecutor(simpleExecutor())
                /*.writer(new ItemWriter<CreditCardTransModel>() {
                    @Override
                    public void write(Chunk<? extends CreditCardTransModel> chunk) throws Exception {
                        System.out.println(String.format("Received list of size: %s", chunk.size()));
                        chunk.forEach(System.out::println);
                    }
                })*/
                .build();

    }

    @Bean
    public Step loadTransactionCodeStep() {
        return new StepBuilder("loadTransactionCodeStep", jobRepository)
                .<TransactionCodeModel,TransactionCodeModel>chunk(3, transactionManager)
                .reader(transactionCodeParamReader.transactionCodeJDBCReader())
                .writer(new ItemWriter<TransactionCodeModel>() {
                    @Override
                    public void write(Chunk<? extends TransactionCodeModel> chunk) throws Exception {
                        System.out.println(String.format("Received list of size: %s", chunk.size()));
                        chunk.forEach(transactionCodeArray::add);

                    }
                })
                .build();
    }

    @Bean
    public Step genereteCSVSummCustTran() throws Exception {
        return new StepBuilder("genereteCSVSummCustTran",jobRepository)
                .<TransactionSummaryCustModel, TransactionSummaryCSVModel>chunk(CHUNK,transactionManager)
                .reader(transactionCustomerAllReader.transactionSummaryAllReader())
                .processor(transactionCustomerSummaryProcessor.tranSumCusProcessor())
                .writer(transactionCustomerSummaryCSVWriter.TransactionSummaryCSVItemWriter())
                .build();
    }

    @Bean
    public Step transactionTransferCSV() throws Exception {
        return new StepBuilder("transactionTransferCSV", jobRepository)
                .<TransactionTransferModel, TransactionTransferModel>chunk(100, transactionManager)
                .reader(transactionTransferReader.readTransactionTransferSQL(transactionManager.getDataSource()))
                .writer(transactionTransferWriter.writeTransactionTransferCSV())
                .build();
    }

    @Bean
    public Step customerAllCSV() throws Exception {
        return new StepBuilder("customerAllCSV", jobRepository)
                .<CustomerAllModel,CustomerAllModel>chunk(100, transactionManager)
                .reader(customerAllReader.readCustomerAllSQL(transactionManager.getDataSource()))
                .writer(customerAllWriter.writeCustomerAllCSV())
                .build();
    }

    @Bean
    public Job dailyBatchJob() throws Exception {
        return new JobBuilder("dailyBatchJob" + new RunIdIncrementer().toString(), jobRepository)
//                .start(loadTransactionCodeStep())
//                .next(summerizeCCTranStep())
//                .next(genereteCSVSummCustTran())
                .start(transactionTransferCSV())
                .next(customerAllCSV())
                .build();
    }
}
