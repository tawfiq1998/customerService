package com.example.digitinary.config;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("basicAuth")
class SpringFoxConfigAuthTest {
    @Test
    public void testDocketBeanConfiguration() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringFoxConfig.class);

        Docket docket = context.getBean(Docket.class);

        assertThat(docket).isNotNull();

        assertThat(docket.getDocumentationType()).isEqualTo(DocumentationType.SWAGGER_2);

        context.close();
    }
}