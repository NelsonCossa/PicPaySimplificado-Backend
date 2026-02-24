package com.devnelson.picpay_simplificado.dtos;

import java.math.BigDecimal;

import com.devnelson.picpay_simplificado.entities.user.UserType;

public record UserDTO(String firstName,String lastName, String document, BigDecimal balance, String password, String email, UserType userType) {



}
