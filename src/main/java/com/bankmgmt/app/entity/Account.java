package com.bankmgmt.app.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    private Integer id;
    @NotNull
    private String accountHolderName;
    @NotNull
    private String accountType;
    @NotNull
    @Email
    private String email;
    @Positive
    private Double balance;
}
