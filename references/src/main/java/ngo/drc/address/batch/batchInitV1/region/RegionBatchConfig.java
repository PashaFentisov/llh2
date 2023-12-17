package ngo.drc.address.batch.batchInitV1.region;

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
public class RegionBatchConfig {
    @Bean("regionItemProcessor")
    public ItemProcessor<RegionData, RegionData> itemProcessor() {
        return (regionData) -> regionData;
    }

    @Bean("regionItemReader")
    public FlatFileItemReader<RegionData> csvRegionReader() {
        return new FlatFileItemReaderBuilder<RegionData>().name("csv-region-reader")
                .resource(new ClassPathResource("batch/regions.csv"))
                .targetType(RegionData.class)
                .delimited()
                .delimiter("|")
                .names("code", "nameEn", "nameUa").build();
    }


    @Bean
    public Step processRegionStep(@Qualifier("regionItemReader") ItemReader<RegionData> csvReader,
                                  @Qualifier("regionItemProcessor") ItemProcessor<RegionData, RegionData> itemProcessor,
                                  @Qualifier("regionItemWriter") ItemWriter<RegionData> dbWriter,
                                   JobRepository jobRepository,
                                   PlatformTransactionManager transactionManager) {

        return new StepBuilder("region-step", jobRepository)
                .<RegionData, RegionData>chunk(2, transactionManager)
                .reader(csvReader)
                .processor(itemProcessor)
                .writer(dbWriter)
                .build();
    }
}
