package com.nttdata.banking.debitcard.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Class DebitCard.
 * DebiCard microservice class DebitCard.
 */
@Document(collection = "DebitCard")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class DebitCard {

    @Id
    private String idDebitCard;
    private String cardNumber;
    private Boolean state;
    
}
