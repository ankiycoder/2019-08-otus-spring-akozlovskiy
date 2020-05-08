package ru.akozlovskiy.springdz14.batch;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.SimpleStepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;

import ru.akozlovskiy.springdz14.jpa.domain.Book;
import ru.akozlovskiy.springdz14.jpa.domain.repository.JpaBookRepository;
import ru.akozlovskiy.springdz14.mongo.domain.repository.AuthorRepository;

@EnableBatchProcessing
@Configuration
public class BatchConfig {

	private static final Logger logger = LoggerFactory.getLogger(BatchConfig.class);

	@StepScope
	@Bean
	public RepositoryItemReader<Book> bookItemReader(JpaBookRepository bookRepository) {
		RepositoryItemReaderBuilder<Book> readerBuilder = new RepositoryItemReaderBuilder<>();

		HashMap<String, Direction> sorts = new HashMap<>();
		sorts.put("id", Direction.ASC);

		readerBuilder.name("BookItemReader").repository(bookRepository).sorts(sorts).methodName("findAll");
		return readerBuilder.build();
	}

	@Bean
	public ItemProcessor<Book, ru.akozlovskiy.springdz14.mongo.domain.Book> itemProcessor(
			AuthorRepository authorRepository) {
		return new BookItemProcessor(authorRepository);
	}

	@StepScope
	@Bean
	public MongoItemWriter<ru.akozlovskiy.springdz14.mongo.domain.Book> bookItemWriter(MongoTemplate mongoTemplate) {
		MongoItemWriter<ru.akozlovskiy.springdz14.mongo.domain.Book> writer = new MongoItemWriter<>();
		writer.setTemplate(mongoTemplate);
		writer.setCollection("book");
		return writer;
	}

	@Bean
	public Job importUserJob(JobBuilderFactory jobBuilderFactory, JobCompletionNotificationListener listener,
			@Qualifier("step1") Step step1) {
		return jobBuilderFactory.get("importUserJob").incrementer(new RunIdIncrementer()).listener(listener).flow(step1)
				.end().build();
	}

	@SuppressWarnings("unchecked")
	@Bean
	public Step step1(RepositoryItemReader<Book> bookItemReader,
			MongoItemWriter<ru.akozlovskiy.springdz14.mongo.domain.Book> mongoItemWriter,
			ItemProcessor<Book, ru.akozlovskiy.springdz14.mongo.domain.Book> itemProcessor,
			StepBuilderFactory stepBuilderFactory) {

		SimpleStepBuilder<Book, ru.akozlovskiy.springdz14.mongo.domain.Book> stepBuilder = (SimpleStepBuilder<Book, ru.akozlovskiy.springdz14.mongo.domain.Book>) stepBuilderFactory
				.get("step1").<Book, ru.akozlovskiy.springdz14.mongo.domain.Book>chunk(3).reader(bookItemReader)
				.processor(itemProcessor).writer(mongoItemWriter).listener(new ChunkListener() {

					@Override
					public void beforeChunk(ChunkContext context) {
						logger.info(" / Начало пачки");
					}

					@Override
					public void afterChunkError(ChunkContext context) {
						logger.info(" / Ошибка пачки");
					}

					@Override
					public void afterChunk(ChunkContext context) {
						logger.info(" / Конец пачки");

					}
				});

		return stepBuilder.build();
	}
}
