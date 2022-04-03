package com.example.redis.domain;

import lombok.Data;

@Data
public class User {
    public User(){}
    public User(String id, String stripeId, Long balance, String defaultPaymentMethod) {
        this.id = id;
        this.stripeAccount = new StripeAccount(stripeId, balance, defaultPaymentMethod);
    }

    private String id;
    private StripeAccount stripeAccount;
}
