package com.example.digitinary.config;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SpringFoxConfigTest {
    @Test
    public void testDocketBeanConfiguration() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringFoxConfig.class);

        Docket docket = context.getBean(Docket.class);

        assertThat(docket).isNotNull();

        assertThat(docket.getDocumentationType()).isEqualTo(DocumentationType.SWAGGER_2);

        context.close();
    }
}