package bca.tugas.transaksi.controller;

import bca.tugas.transaksi.batch.config.JobDailyConfig;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {

    private final JobLauncher jobLauncher;
    private final JobDailyConfig jobDailyConfig;


    public JobController(JobLauncher jobLauncher, JobDailyConfig jobDailyConfig) {
        this.jobLauncher = jobLauncher;
        this.jobDailyConfig = jobDailyConfig;
    }

    @GetMapping("/start-job")
    public String startJob() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(jobDailyConfig.dailyBatchJob(), jobParameters);
            return "Job started successfully";
        } catch (Exception e) {
            return "Job failed to start: " + e.getMessage();
        }
    }
    @GetMapping("/start-job/{orderDate}")
    public String startJob(@PathVariable String orderDate) {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("orderDate", orderDate)
                    .toJobParameters();
            jobLauncher.run(jobDailyConfig.dailyBatchJob(), jobParameters);
            return "Job started successfully";
        } catch (Exception e) {
            return "Job failed to start: " + e.getMessage();
        }
    }
}
