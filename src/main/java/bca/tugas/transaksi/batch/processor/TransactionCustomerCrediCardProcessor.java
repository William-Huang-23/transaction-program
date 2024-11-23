package bca.tugas.transaksi.batch.processor;

import bca.tugas.transaksi.batch.errorHandeler.TransactionCodeException;
import bca.tugas.transaksi.batch.model.CreditCardTransModel;
import bca.tugas.transaksi.batch.model.TransactionCodeModel;
import bca.tugas.transaksi.batch.model.TransactionCustomerAllModel;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration
public class TransactionCustomerCrediCardProcessor {

    @Bean
    public ItemProcessor<CreditCardTransModel, TransactionCustomerAllModel> tranCustCCprocessor(List<TransactionCodeModel> transactionCodeArray){
        return new ItemProcessor<CreditCardTransModel, TransactionCustomerAllModel>() {
            @Override
            public TransactionCustomerAllModel process(CreditCardTransModel item) throws Exception {
                TransactionCustomerAllModel tcam = new TransactionCustomerAllModel();
                tcam.setCustomerId(item.getCustId());
                tcam.setTransactionType("Credit Card");
                tcam.setAccountId(item.getCreditcardId());
                tcam.setTranDate(item.getDate());


                Optional<TransactionCodeModel> tcm = transactionCodeArray.stream()
                        .filter(entity -> entity.getTransactionCode().equals(item.getTransactionCode()))
                        .findFirst();

                TransactionCodeModel tranCode = tcm.orElse(null);
                if (tranCode == null) {
                    throw new TransactionCodeException();
                }

                if(tranCode.getDebitCredit().equalsIgnoreCase("C"))
                {
                    tcam.setAmountCredit(item.getAmount());
                    tcam.setAmountDebit(0.0);
                }
                else if(tranCode.getDebitCredit().equalsIgnoreCase("D")){
                    tcam.setAmountCredit(0.0);
                    tcam.setAmountDebit(item.getAmount());
                }

                String descDesigner = String.format("%s On Date %s posted on COA %s",
                        tranCode.getDescription(),
                        item.getDate().toString(),
                        tranCode.getCoaCode());

                tcam.setDescription(descDesigner);




                return tcam;
            }
        };
    }

}
