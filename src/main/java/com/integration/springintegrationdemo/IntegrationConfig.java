package com.integration.springintegrationdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.handler.GenericHandler;

import java.io.File;
import java.util.Map;
import java.util.function.Function;

@Configuration
public class IntegrationConfig {

    @Autowired
    private Transformer transformer;

    @Bean
    public IntegrationFlow integrationFlow()
    {
        return IntegrationFlows.from(fileReader(), spec->spec.poller(Pollers.fixedDelay(500)))
                //.wireTap(statusChannel)
                .transform(transformer, "transform")
                .handle(fileWriter())
                .get();
    }

//    @Bean
//    public IntegrationFlow statusFlow(DirectChannel statusChannel, Function<Object, String> test){
//        return IntegrationFlows.from(statusChannel)
//                .transform(test2())
//                .handle(test)
//                .get();
//    }

    private FileWritingMessageHandler fileWriter() {
            FileWritingMessageHandler handler=new FileWritingMessageHandler(new File("destination"));
            handler.setExpectReply(false);
            return handler;
    }

    private FileReadingMessageSource fileReader() {
        FileReadingMessageSource source = new FileReadingMessageSource();
        source.setDirectory(new File("source"));
        return source;
    }


//    @Bean
//    Function<Object, String> test(){
//       return o -> {
//           System.out.println("hello world");
//           return "Hello";
//       };
//    }
//
//
//    Function<Map,Map> test2()
//    {
//        return map -> {
//         map.put("Name","Nilesh");
//         map.put("Name","Nilesh2");
//         map.put("Name","Nilesh3");
//         map.put("Name","Nilesh4");
//            return map;
//        };
//        new Function<Map, Map>() {
//            @Override
//            public Map apply(Map map) {
//                map.put("Name","Nilesh");
//                map.put("Name","Sachin");
//                map.put("Name","Mahesh");
//                map.put("Name","Ramesh");
//                return map;
//            }
//        };

    }

//}

