package ru.akozlovskiy.springdz15;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.integration.channel.DirectChannel;

import ru.akozlovskiy.springdz15.domain.Document;
import ru.akozlovskiy.springdz15.integration.DocumentProcessor;

@SpringBootApplication
public class SpringIntegrationApplication {

	public static void main(String[] args) {
		

		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(SpringIntegrationApplication.class);
		
		
        //DirectChannel outputChannel = ctx.getBean("outputChannel", DirectChannel.class);
        // обработчик внутри subscribe выполнится как только закончится выполнение flow
        //outputChannel.subscribe(x -> System.out.println(x));

		// here we works with cafe using interface
        
		DocumentProcessor docProcessor = ctx.getBean(DocumentProcessor.class);
        for (int i=0; i<1; i++) {
        	
    		Document activity = new Document();    		
    		activity.setComment("FirstDoc");
    		docProcessor.process(activity);	
        }
        		
	}
}
