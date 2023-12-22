package ngo.drc.address.batch.batchInitV1.hromada;

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
public class HromadaBatchConfig {
    @Bean("hromadaItemProcessor")
    public ItemProcessor<HromadaData, HromadaData> itemProcessor() {
        return (hromadaData)->hromadaData;
    }

    @Bean("hromadaItemReader")
    public FlatFileItemReader<HromadaData> csvHromadaReader() {
        return new FlatFileItemReaderBuilder<HromadaData>().name("csv-hromada-reader")
                .resource(new ClassPathResource("batch/hromades.csv"))
                .targetType(HromadaData.class)
                .delimited()
                .delimiter("|")
                .names("districtCode", "nameUa", "code", "nameEn").build();
    }


    @Bean
    public Step processHromadaStep(@Qualifier("hromadaItemReader") ItemReader<HromadaData> csvReader,
                                   @Qualifier("hromadaItemProcessor") ItemProcessor<HromadaData, HromadaData> itemProcessor,
                                   @Qualifier("hromadaItemWriter") ItemWriter<HromadaData> dbWriter,
                                   JobRepository jobRepository,
                                   PlatformTransactionManager transactionManager) {

        return new StepBuilder("hromada-step", jobRepository)
                .<HromadaData, HromadaData>chunk(100, transactionManager)
                .reader(csvReader)
                .processor(itemProcessor)
                .writer(dbWriter)
                .build();
    }
}
