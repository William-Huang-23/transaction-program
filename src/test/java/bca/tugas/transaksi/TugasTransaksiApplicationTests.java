package bca.tugas.transaksi;

import bca.tugas.transaksi.batch.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.*;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBatchTest
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class TugasTransaksiApplicationTests {

	@Autowired
	private Job dailyBatchJob;

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Autowired
	private JobRepositoryTestUtils jobRepositoryTestUtils;

	@Test
	public void testDailyBatchJob() throws Exception {
		JobParameters jobParameters = new JobParametersBuilder()
				.addLong("time", System.currentTimeMillis())
				.toJobParameters();

		JobExecution jobExecution = jobLauncherTestUtils.getJobLauncher().run(dailyBatchJob, jobParameters);

		assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);
	}

	@Test
	public void testLoadTransactionCodeStep() {
		JobExecution stepExecution = jobLauncherTestUtils.launchStep("loadTransactionCodeStep");

		assertThat(stepExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);
	}

	@Test
	public void testSummerizeCCTranStep() throws Exception {
		JobParameters jobParameters = new JobParametersBuilder()
				.addLong("time", System.currentTimeMillis())
				.toJobParameters();

		JobExecution jobExecution = jobLauncherTestUtils.getJobLauncher().run(dailyBatchJob, jobParameters);

		StepExecution stepExecution = jobExecution.getStepExecutions()
				.stream()
				.filter(step -> step.getStepName().equals("summerizeCCTranStep"))
				.findFirst()
				.orElse(null);

		assertThat(stepExecution).isNotNull();
		assertThat(stepExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);
	}

	@Test
	public void testGenereteCSVSummCustTran() throws Exception {
		JobParameters jobParameters = new JobParametersBuilder()
				.addLong("time", System.currentTimeMillis())
				.toJobParameters();

		JobExecution jobExecution = jobLauncherTestUtils.getJobLauncher().run(dailyBatchJob, jobParameters);

		StepExecution stepExecution = jobExecution.getStepExecutions()
				.stream()
				.filter(step -> step.getStepName().equals("genereteCSVSummCustTran"))
				.findFirst()
				.orElse(null);

		assertThat(stepExecution).isNotNull();
		assertThat(stepExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);
	}
}
