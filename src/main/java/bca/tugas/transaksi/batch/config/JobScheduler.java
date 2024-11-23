package bca.tugas.transaksi.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.text.SimpleDateFormat;

@Configuration
public class JobScheduler {

    private final JobLauncher jobLauncher;
    private final JobDailyConfig jobDailyConfig;

    public JobScheduler(JobLauncher jobLauncher, JobDailyConfig jobDailyConfig) {
        this.jobLauncher = jobLauncher;
        this.jobDailyConfig = jobDailyConfig;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    //@Scheduled(cron = "*/10 * * * * ?")
    public void runJob() throws Exception {
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        JobParameters jobParameters = new JobParametersBuilder()
                //.addString("date", date)
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(jobDailyConfig.dailyBatchJob(), jobParameters);
    }

}
