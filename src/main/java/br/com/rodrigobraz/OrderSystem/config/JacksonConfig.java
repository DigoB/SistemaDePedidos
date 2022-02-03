package br.com.rodrigobraz.OrderSystem.config;

import br.com.rodrigobraz.OrderSystem.domain.CardPayment;
import br.com.rodrigobraz.OrderSystem.domain.TicketPayment;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {

        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
            public void configure(ObjectMapper mapper) {
                mapper.registerSubtypes(CardPayment.class);
                mapper.registerSubtypes(TicketPayment.class);
                super.configure(mapper);
            }
        };
        return builder;
    }
}
