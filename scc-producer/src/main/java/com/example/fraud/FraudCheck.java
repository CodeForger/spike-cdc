package com.example.fraud;

import java.math.BigDecimal;

public class FraudCheck {
    private String clientId;
    private BigDecimal loanAmount;

    public FraudCheck() {
    }

    public FraudCheck(String clientId, double loanAmount) {
        this.clientId = clientId;
        this.loanAmount = BigDecimal.valueOf(loanAmount);
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }
}
