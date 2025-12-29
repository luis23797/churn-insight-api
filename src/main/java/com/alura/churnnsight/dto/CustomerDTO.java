package com.alura.churnnsight.dto;

import jakarta.validation.constraints.*;

public record CustomerDTO(
        @NotNull Integer RowNumber,
        @NotBlank String CustomerId,
        @NotBlank String Surname,
        @Min(300) @Max(850) Integer CreditScore,
        @NotBlank String Geography,
        @NotNull Integer Gender,
        @Min(18) Integer Age,
        @NotNull Integer Tenure,
        @PositiveOrZero Double Balance,
        @Min(1) Integer NumOfProducts,
        @NotNull Integer HasCrCard,
        @NotNull Integer IsActiveMember,
        @PositiveOrZero Double EstimatedSalary
) {}