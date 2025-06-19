package com.nttdata.banking.debitcard.controller;

import com.nttdata.banking.debitcard.application.DebitCardService;
import com.nttdata.banking.debitcard.model.DebitCard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/debitcard")
@Slf4j
public class DebitCardController {

    @Autowired
    private DebitCardService debitCardService;

    @GetMapping
    public Mono<ResponseEntity<Flux<DebitCard>>> listDebitCards() {
        return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(debitCardService.findAll()));
    }

    @GetMapping("/{idDebitCard}")
    public Mono<ResponseEntity<DebitCard>> getDebitCardDetails(@PathVariable("idDebitCard") String idClient) {
        return debitCardService.findById(idClient).map(c -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(c))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/cardNumber/{cardNumber}")
    public Mono<ResponseEntity<DebitCard>> getDebitCardByCardNumber(@PathVariable("cardNumber") String cardNumber) {
        log.info("--getDebitCardByCardNumber-------cardNumber" + cardNumber);
        return debitCardService.findByCardNumber(cardNumber)
                .map(c -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(c))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<Map<String, Object>>> saveDebitCard(@Valid @RequestBody Mono<DebitCard> debitCard) {
        Map<String, Object> request = new HashMap<>();
        return debitCard.flatMap(bnkAcc -> debitCardService.save(bnkAcc).map(baSv -> {
                    request.put("DebitCard", baSv);
                    request.put("message", "Tarjeta de débito guardado con exito");
                    request.put("timestamp", new Date());
                    return ResponseEntity.created(URI.create("/api/debitcard/".concat(baSv.getIdDebitCard())))
                            .contentType(MediaType.APPLICATION_JSON).body(request);
                })
        );
    }

    @PutMapping("/{idDebitCard}")
    public Mono<ResponseEntity<DebitCard>> editDebitCard(@Valid @RequestBody DebitCard debitCard, @PathVariable("idDebitCard") String idDebitCard) {
        return debitCardService.update(debitCard, idDebitCard)
                .map(c -> ResponseEntity.created(URI.create("/api/debitcard/".concat(idDebitCard)))
                        .contentType(MediaType.APPLICATION_JSON).body(c));
    }

    @DeleteMapping("/{idDebitCard}")
    public Mono<ResponseEntity<Void>> deleteDebitCard(@PathVariable("idDebitCard") String idDebitCard) {
        return debitCardService.delete(idDebitCard).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
    }
}
