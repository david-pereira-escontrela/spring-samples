package com.samples.cafe;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.messaging.MessageChannel;

@Configuration
public class AppChannels {

  @Bean
  public MessageChannel orders() {

    return new PublishSubscribeChannel();
  }

  @Bean
  public MessageChannel drinks() {

    return new PublishSubscribeChannel();
  }

  @Bean
  public MessageChannel hotDrinks() {

    return new QueueChannel(10);
  }

  @Bean
  public MessageChannel coldDrinks() {

    return new QueueChannel(10);
  }

  @Bean
  public MessageChannel preparedDrinks() {

    return new PublishSubscribeChannel();
  }

  @Bean
  public MessageChannel deliveries() {

    return new PublishSubscribeChannel();
  }
}
