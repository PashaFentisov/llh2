package ngo.drc.address.batch.batchInitV1.district;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DistrictBatchConfig {
    @Bean("districtItemProcessor")
    public ItemProcessor<DistrictData, DistrictData> itemProcessor() {
        return (districtData) -> districtData;
    }

    @Bean("districtItemReader")
    public FlatFileItemReader<DistrictData> csvDistrictReader() {
        return new FlatFileItemReaderBuilder<DistrictData>().name("csv-district-reader")
                .resource(new ClassPathResource("batch/districts.csv"))
                .targetType(DistrictData.class)
                .delimited()
                .delimiter("|")
                .names("regionCode", "nameUa", "code", "nameEn").build();
    }


    @Bean
    public Step processDistrictStep(@Qualifier("districtItemReader") ItemReader<DistrictData> csvReader,
                                    @Qualifier("districtItemProcessor") ItemProcessor<DistrictData, DistrictData> itemProcessor,
                                    @Qualifier("districtItemWriter") ItemWriter<DistrictData> dbWriter,
                                    JobRepository jobRepository,
                                    PlatformTransactionManager transactionManager) {

        return new StepBuilder("district-step", jobRepository)
                .<DistrictData, DistrictData>chunk(2, transactionManager)
                .reader(csvReader)
                .processor(itemProcessor)
                .writer(dbWriter)
                .build();
    }
}
