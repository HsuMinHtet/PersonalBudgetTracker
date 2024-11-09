package edu.miu.cs489.hsumin.personalbudgettracker.dto.requestDTO;

public record AddressRequestDTO (
        String city,
        String street,
        String number,
        Integer postalCode,
        String country,
        String state
){ }
