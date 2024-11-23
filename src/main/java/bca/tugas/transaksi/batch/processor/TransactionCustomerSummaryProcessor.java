package bca.tugas.transaksi.batch.processor;

import bca.tugas.transaksi.batch.model.TransactionSummaryCSVModel;
import bca.tugas.transaksi.batch.model.TransactionSummaryCustModel;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionCustomerSummaryProcessor {

    @Bean
    public ItemProcessor<TransactionSummaryCustModel, TransactionSummaryCSVModel> tranSumCusProcessor(){
        return  new ItemProcessor<TransactionSummaryCustModel, TransactionSummaryCSVModel>() {
            @Override
            public TransactionSummaryCSVModel process(TransactionSummaryCustModel item) throws Exception {

                TransactionSummaryCSVModel tscm = new TransactionSummaryCSVModel();
                tscm.setCustomerId(item.getCustomerId());
                tscm.setTransactionType(item.getTransactionType());
                tscm.setTranDate(item.getTranDate());
                tscm.setTotalDebit(item.getTotalDebit());
                tscm.setTotalCredit(item.getTotalCredit());
                tscm.setTotalAmount(item.getTotalDebit() - item.getTotalCredit());

                return tscm;
            }
        };
    }

}
