package com.alura.churnnsight.dto.creation;

import java.time.LocalDate;

public record DataCreateCustomer(
        String customerId,
        String geography,
        String gender,           // 👈 String
        LocalDate birthDate,
        LocalDate createdAt,
        String surname,
        Double estimatedSalary,
        String customerSegment
) {
}
