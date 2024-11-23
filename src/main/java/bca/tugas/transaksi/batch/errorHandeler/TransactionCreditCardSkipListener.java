package bca.tugas.transaksi.batch.errorHandeler;

import bca.tugas.transaksi.batch.model.CreditCardTransModel;
import bca.tugas.transaksi.batch.model.TransactionCustomerAllModel;
import bca.tugas.transaksi.batch.model.TransactionSummaryCustModel;
import org.springframework.batch.core.SkipListener;

public class TransactionCreditCardSkipListener implements SkipListener<CreditCardTransModel, TransactionCustomerAllModel> {

    @Override
    public void onSkipInRead(Throwable t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onSkipInWrite(TransactionCustomerAllModel item, Throwable t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onSkipInProcess(CreditCardTransModel item, Throwable t) {
        System.out.println("Skipping processing of item with id: " + item.getTranId());

    }

}
