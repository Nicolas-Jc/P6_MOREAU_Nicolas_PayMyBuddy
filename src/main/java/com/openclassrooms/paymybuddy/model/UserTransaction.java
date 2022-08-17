package com.openclassrooms.paymybuddy.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@DynamicUpdate
@Table(name ="user_transaction")
public class UserTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_transaction_id")
    private Integer userTransactionId;

    @Column(name="amount")
    private Float amount;

    @Column(name="fee")
    private Float fee;

    @Column(name="transaction_date")
    private LocalDate transactionDate;

    @Column(name="description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "user_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "user_id")
    private User receiver;


    public UserTransaction()
    {}
    public UserTransaction(Integer userTransactionId, Float amount, Float fee, LocalDate transactionDate, String description, User sender, User receiver) {
        this.userTransactionId = userTransactionId;
        this.amount = amount;
        this.fee = fee;
        this.transactionDate = transactionDate;
        this.description = description;
        this.sender = sender;
        this.receiver = receiver;
    }

    public Integer getUserTransactionId() {
        return userTransactionId;
    }

    public void setUserTransactionId(Integer userTransactionId) {
        this.userTransactionId = userTransactionId;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Float getFee() {
        return fee;
    }

    public void setFee(Float fee) {
        this.fee = fee;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }
}
