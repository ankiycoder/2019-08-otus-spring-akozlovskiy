package ru.akozlovskiy.springdz14.batch;

import java.util.HashMap;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Sort.Direction;

import ru.akozlovskiy.springdz14.jpa.domain.Book;
import ru.akozlovskiy.springdz14.jpa.domain.repository.JpaBookRepository;

@EnableBatchProcessing
@Configuration
public class BatchConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	private JpaBookRepository bookRepository;

	@StepScope
	@Bean
	public RepositoryItemReader<Book> reader() {
		RepositoryItemReaderBuilder<Book> readerBuilder = new RepositoryItemReaderBuilder<>();

		HashMap<String, Direction> sorts = new HashMap<>();
		sorts.put("id", Direction.ASC);

		readerBuilder.name("personItemReader").repository(bookRepository).sorts(sorts).methodName("findAll");
		return readerBuilder.build();
	}

	@Bean
	public ItemProcessor<Book, ru.akozlovskiy.springdz14.mongo.domain.Book> processor() {
		return new BookItemProcessor();
	}

	@StepScope
	@Bean
	public FlatFileItemWriter writer() {
		return new FlatFileItemWriterBuilder<>().name("personItemWriter")
				.resource(new FileSystemResource("_MyTest.txt")).lineAggregator(new DelimitedLineAggregator<>())
				.build();
	}

	@Bean
	public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
		return jobBuilderFactory.get("importUserJob").incrementer(new RunIdIncrementer()).listener(listener).flow(step1)
				.end().build();
	}

	@Bean
	public Step step1(FlatFileItemWriter<ru.akozlovskiy.springdz14.mongo.domain.Book> writer,
			ItemProcessor<Book, ru.akozlovskiy.springdz14.mongo.domain.Book> itemProcessor) {

		return stepBuilderFactory.get("step1").<Book, ru.akozlovskiy.springdz14.mongo.domain.Book>chunk(10)
				.reader(reader()).processor(itemProcessor).writer(writer).build();
	}

}
