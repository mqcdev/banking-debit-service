package com.nttdata.banking.debitcard.dto;

import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Class DebitCardDto.
 * DebiCard microservice class DebitCardDto.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class DebitCardDto {

    @Id
    private Integer id;
    private String cardNumber;
    private Boolean state;
}
