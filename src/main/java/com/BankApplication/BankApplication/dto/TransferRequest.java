package com.BankApplication.BankApplication.dto;

import lombok.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequest {

    private String senderAccountNumber;
    private String receiverAccountNumber;
    private BigDecimal amount;
}
