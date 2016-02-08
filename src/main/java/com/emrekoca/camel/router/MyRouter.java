package com.emrekoca.camel.router;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.BindyType;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.emrekoca.camel.handler.MyCsvBeanHandler;;


/**
 * Created by ekoca on 1/25/16.
 */
@Component
public class MyRouter extends RouteBuilder{

    private static final org.slf4j.Logger logger = LoggerFactory
            .getLogger(MyRouter.class);

    @Override
    public void configure() throws Exception {
    	try{
        from("file:///Users/ekoca/Documents/uploads/?noop=true")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        String payload = exchange.getIn().getBody(String.class);
                        logger.info(payload);
                    }
                })
                .unmarshal()
                .bindy(BindyType.Csv, com.emrekoca.camel.domain.BackUser_back.class)
                .bean(new MyCsvBeanHandler(), "doHandleCsv(*)");
    	}catch(Exception ex){
    		logger.error(ex.getMessage());
    	}
    }
}