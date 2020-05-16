package ru.akozlovskiy.springdz15.integration;

import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlowDefinition;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;

import ru.akozlovskiy.springdz15.domain.repository.DocumentRepository;
import ru.akozlovskiy.springdz15.domain.service.ServiceSample;


@Configuration
@IntegrationComponentScan
public class IntegrationConfig {
    private static final int DEFAULT_QUEUE_CAPACITY = 100;
    private static final int DEFAULT_POLLER_PERIOD = 1000;
    
    @Autowired 
    private DocumentRepository documentRepository;
    
    @Autowired 
    private ServiceSample serviceSample;


    @Bean
    public PollableChannel documentFlowInChanel() {
        return MessageChannels.queue("inChanel", DEFAULT_QUEUE_CAPACITY).get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(DEFAULT_POLLER_PERIOD).get();
    }
    
    @Bean
    public IntegrationFlow documentFlow() {
        return flow -> flow        
            .channel("outputChannel");
    }
    
    // Точка входа
    @Bean
    public IntegrationFlow activityStatFlow() {
        return f -> f.channel("documentFlow.input")
        		.handle(documentRepository, "save")
        		.handle(serviceSample, "doWork")
        		.channel(documentFlowInChanel())
                .log();

    }


   /* @Bean
    public IntegrationFlow appUserActivityFlow() {
        return f -> f.channel(appUserActivityInChanel())
                .handle(activityRepository, SAVE_METHOD_NAME)
                .route(Message.class, m -> m.getHeaders().get(IS_IMPORTANT_MESSAGE, Boolean.class)
                        , mapping -> mapping.subFlowMapping(true, sub -> sub
                                .transform(messageTransformer, TRANSFORM_METHOD_NAME)
                                .handle(m -> {
                                    val isImportant = m.getHeaders().get(IS_IMPORTANT_MESSAGE, Boolean.class);
                                    if (isImportant != null && isImportant) {
                                        System.out.println("Как будто посылаем письмо: " + m.getPayload());
                                        //mailSender.send((SimpleMailMessage) m.getPayload());
                                    }
                                })
                        )
                        .subFlowMapping(false, IntegrationFlowDefinition::nullChannel)
                );
    }*/


}
