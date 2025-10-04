package com.BankApplication.BankApplication.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailDetails {

    private String recipient;
    private String messageBody;
    private String Subject;
    private String attachemnt;
}
