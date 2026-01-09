package com.alura.churnnsight.dto;

import com.alura.churnnsight.dto.integration.ClienteIn;
import com.alura.churnnsight.dto.integration.SesionIn;
import com.alura.churnnsight.dto.integration.TransaccionIn;
import com.alura.churnnsight.model.Customer;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

public record DataMakePrediction(
        @JsonProperty("cliente") ClienteIn cliente,
        @JsonProperty("transacciones") List<TransaccionIn> transacciones,
        @JsonProperty("sesiones") List<SesionIn> sesiones
) {

        public DataMakePrediction(Customer customer, Float balance, Integer numOfProducts, Integer isActiveMember) {
                this(
                        new ClienteIn(
                                1,
                                customer.getCustomerId(),
                                customer.getSurname(),
                                600,
                                customer.getGeography(),
                                "Male",
                                customer.getAge(),
                                customer.getTenure(LocalDate.now()),
                                balance,
                                numOfProducts,
                                1,
                                isActiveMember,
                                customer.getEstimatedSalary() != null ?
                                        customer.getEstimatedSalary().floatValue() : 0.0f,
                                "GOLD"
                        ),
                        List.of(),
                        List.of()
                );
        }

}
