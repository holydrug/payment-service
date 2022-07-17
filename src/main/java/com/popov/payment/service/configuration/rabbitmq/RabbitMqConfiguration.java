package com.popov.payment.service.configuration.rabbitmq;

import com.popov.payment.service.properties.YAMLProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class RabbitMqConfiguration {
    private final YAMLProperties properties;

    @Bean
    public CachingConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(properties.getRabbitMq().getHost());
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(properties.getRabbitMq().getExchanges().getInternal());
    }

    @Bean
    public Queue defaultParsingQueue() {
        return new Queue(properties.getRabbitMq().getQueues().getInternal());
    }

    @Bean
    public Binding queueToExchangeBinding() {
        return BindingBuilder
                .bind(defaultParsingQueue())
                .to(topicExchange())
                .with(properties.getRabbitMq().getRoutingKeys().getInternal());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }
}
