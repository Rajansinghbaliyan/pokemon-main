package io.cherrytechnologies.pokemonmain.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.UUID;

@Component
@Slf4j
public class WebCall {
    private final WebClient client;

    public WebCall(WebClient client) {
        this.client = client;
    }

    public <T> Mono<T> getById(Class<T> tClass, UUID uuid, String url) {
        log.debug("Getting ability for pokemon id: "+ uuid);
        return client
                .get()
                .uri(url + "/{uuid}", uuid)
                .retrieve()
                .bodyToMono(tClass)
                .onErrorResume(Mono::error)
                .retryWhen(Retry.backoff(3, Duration.ofSeconds(2))
                        .doAfterRetry(retrySignal -> log.debug(retrySignal.toString()))
                );
    }

    public <T> Mono<T> saveEntity(T entity, String url, Class<T> tClass) {
        log.debug("Saving abilities for pokemons");
        return client
                .post()
                .uri(url)
                .bodyValue(entity)
                .exchangeToMono(clientResponse ->
                        clientResponse.bodyToMono(tClass)
                                .onErrorResume(Mono::error)
                                .retryWhen(Retry.backoff(3, Duration.ofSeconds(2))
                                        .doAfterRetry(retrySignal -> log.debug(retrySignal.toString()))
                                )
                );
    }
}
