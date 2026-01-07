package com.alura.churnnsight.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "customer_session")
public class CustomerSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "session_id", nullable = false)
    private String sessionId;

    @Column(name = "session_date", nullable = false)
    private LocalDateTime sessionDate;

    @Column(name = "duration_min", nullable = false)
    private double durationMin;

    @Column(name = "used_transfer", nullable = false)
    private int usedTransfer;

    @Column(name = "used_payment", nullable = false)
    private int usedPayment;

    @Column(name = "used_invest", nullable = false)
    private int usedInvest;

    @Column(name = "opened_push", nullable = false)
    private int openedPush;

    @Column(name = "failed_login", nullable = false)
    private int failedLogin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public LocalDateTime getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(LocalDateTime sessionDate) {
        this.sessionDate = sessionDate;
    }

    public double getDurationMin() {
        return durationMin;
    }

    public void setDurationMin(double durationMin) {
        this.durationMin = durationMin;
    }

    public int getUsedTransfer() {
        return usedTransfer;
    }

    public void setUsedTransfer(int usedTransfer) {
        this.usedTransfer = usedTransfer;
    }

    public int getUsedPayment() {
        return usedPayment;
    }

    public void setUsedPayment(int usedPayment) {
        this.usedPayment = usedPayment;
    }

    public int getUsedInvest() {
        return usedInvest;
    }

    public void setUsedInvest(int usedInvest) {
        this.usedInvest = usedInvest;
    }

    public int getOpenedPush() {
        return openedPush;
    }

    public void setOpenedPush(int openedPush) {
        this.openedPush = openedPush;
    }

    public int getFailedLogin() {
        return failedLogin;
    }

    public void setFailedLogin(int failedLogin) {
        this.failedLogin = failedLogin;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}

