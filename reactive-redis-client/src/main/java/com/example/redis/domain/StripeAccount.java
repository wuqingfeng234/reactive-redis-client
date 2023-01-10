package com.example.redis.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StripeAccount {
    public StripeAccount(){}
    public StripeAccount(String id, Long accountBalance, String defaultPaymentMethod) {
        this.id = id;
        this.accountBalance = accountBalance;
        this.defaultPaymentMethod = defaultPaymentMethod;
    }

    private String id;
    @JsonProperty("account_balance")
    private Long accountBalance;
    private String defaultPaymentMethod;
}
