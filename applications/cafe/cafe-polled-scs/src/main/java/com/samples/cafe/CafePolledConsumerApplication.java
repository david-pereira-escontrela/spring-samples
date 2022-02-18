/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.samples.cafe;

import com.samples.cafe.model.Delivery;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.binder.PollableMessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.messaging.MessageChannel;

/**
 * Sample app demonstrating a polled consumer. See more info in: https://spring.io/blog/2018/02/27/spring-cloud-stream-2-0-polled-consumers
 */
@SpringBootApplication
@EnableBinding(CafePolledConsumerApplication.PolledProcessor.class)
public class CafePolledConsumerApplication {

  private static final Logger LOGGER = LoggerFactory.getLogger(CafePolledConsumerApplication.class);

  public static final ExecutorService exec = Executors.newSingleThreadExecutor();

  public static void main(String[] args) {
    SpringApplication.run(CafePolledConsumerApplication.class, args);
  }

  @Bean
  public ApplicationRunner runner(PollableMessageSource input, MessageChannel output) {

    return args -> {

      LOGGER.info("Messages will be processed every 3 seconds...");

      exec.execute(() -> {

        boolean result = false;
        while (true) {

          // this is where we poll for a message, process it, and send a new one
          result = input.poll(m -> {

                      Delivery payload = (Delivery) m.getPayload();
                      LOGGER.info("Received: {}", payload);

                    }, new ParameterizedTypeReference<Delivery>() {
          });

          if (sleep()) {
            break;
          }

          if (result) {

            LOGGER.info("Success");
          }
        }
      });

    };
  }

  private boolean sleep() {
    try {

      Thread.sleep(3_000);

    } catch (InterruptedException e) {

      Thread.currentThread().interrupt();
      return true;
    }

    return false;
  }

  public interface PolledProcessor {

    @Input
    PollableMessageSource input();

    @Output
    MessageChannel output();

  }

}
