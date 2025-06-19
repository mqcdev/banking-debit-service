package com.nttdata.banking.debitcard.application;

import com.nttdata.banking.debitcard.model.DebitCard;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Class DebitCardService.
 * DebiCard microservice class DebitCardService.
 */
public interface DebitCardService {
    public Flux<DebitCard> findAll();

    public Mono<DebitCard> findById(String idDebitCard);

    public Mono<DebitCard> save(DebitCard debitCard);

    public Mono<DebitCard> update(DebitCard debitCard, String idDebitCard);

    public Mono<Void> delete(String idDebitCard);

    public Mono<DebitCard> findByCardNumber(String cardNumber);

}
