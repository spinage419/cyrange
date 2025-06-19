package com.cyrange.dto;

import lombok.Data;

@Data
public class UserUpdateDTO {
    private int id;
    private String username;
    private String mail;
    private String phone;
}
