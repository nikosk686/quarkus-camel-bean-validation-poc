package com.example;

import org.apache.camel.Converter;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import software.amazon.awssdk.utils.StringInputStream;

import javax.enterprise.context.ApplicationScoped;
import java.io.InputStream;

@ApplicationScoped
public class ExampleRoute extends RouteBuilder {

    @Converter
    public static class CustomConverter {
        @Converter
        public static InputStream toInputStream(ExampleDto exampleDto) {
            return new StringInputStream(exampleDto.toString());
        }
    }

    @Override
    public void configure() {

        onException(org.apache.camel.ValidationException.class)
                .log(LoggingLevel.ERROR,"CAMEL VALIDATION ERROR, ${exception.class} : ${exception.message} : ${exception.cause}")
                .handled(true);

        onException(javax.validation.ValidationException.class)
                .log(LoggingLevel.ERROR,"JAVAX VALIDATION ERROR, ${exception.class} : ${exception.message} : ${exception.cause}")
                .handled(true);

        onException(Exception.class)
                .log(LoggingLevel.ERROR,"ERROR : ${exception}")
                .logStackTrace(true)
                .handled(true);

        from("platform-http:/example?httpMethodRestrict=POST")
                .unmarshal().json(JsonLibrary.Jackson, ExampleDto.class)
                .log("${body}")
                .to("bean-validator:example-validator");
    }
}
