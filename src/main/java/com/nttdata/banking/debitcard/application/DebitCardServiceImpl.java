package com.nttdata.banking.debitcard.application;

import com.nttdata.banking.debitcard.exception.ResourceNotFoundException;
import com.nttdata.banking.debitcard.infrastructure.DebitCardRepository;
import com.nttdata.banking.debitcard.model.DebitCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Class DebitCardServiceImpl.
 * DebiCard microservice class DebitCardServiceImpl.
 */
@Service
public class DebitCardServiceImpl implements DebitCardService {

    @Autowired
    private DebitCardRepository debitCardRepository;

    @Override
    public Flux<DebitCard> findAll() {
        return debitCardRepository.findAll();
    }

    @Override
    public Mono<DebitCard> findById(String idDebitCard) {
        return Mono.just(idDebitCard)
                .flatMap(debitCardRepository::findById)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Tarjeta de Débito", "idDebitCard", idDebitCard)));
    }

    @Override
    public Mono<DebitCard> findByCardNumber(String cardNumber) {
        return Mono.just(cardNumber)
                .flatMap(debitCardRepository::findByCardNumber)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Tarjeta de Débito", "cardNumber", cardNumber)));
    }

    @Override
    public Mono<DebitCard> save(DebitCard debitCard) {
        return Mono.just(debitCard).flatMap(dc -> debitCardRepository.save(dc));
    }

    @Override
    public Mono<DebitCard> update(DebitCard debitCard, String idDebitCard) {
        return debitCardRepository.findById(idDebitCard)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Tarjeta de Débito", "idDebitCard", idDebitCard)))
                .flatMap(dcu -> {
                    dcu.setCardNumber(debitCard.getCardNumber());
                    dcu.setState(debitCard.getState());
                    return debitCardRepository.save(dcu);
                });
    }

    @Override
    public Mono<Void> delete(String idDebitCard) {
        return debitCardRepository.findById(idDebitCard)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Tarjeta de Débito", "idDebitCard", idDebitCard)))
                .flatMap(dcu -> debitCardRepository.delete(dcu));
    }
}
