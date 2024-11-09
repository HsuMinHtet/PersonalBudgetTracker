package edu.miu.cs489.hsumin.personalbudgettracker.dto.responseDTO;

import edu.miu.cs489.hsumin.personalbudgettracker.model.Role;

public record AccountHolderResponseDTO(
        String name,
        Role role,
        AddressResponseDTO addressResponseDTO)
{ }