package com.nttd.mscustomer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nttd.mscustomer.entity.Customer;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class ResponseDto {
    
    private int code;
    private String message;
    private String errorMessage;
    private String description;
    private Customer customer;


    
    public ResponseDto() {
    }



    public ResponseDto(int code,String message,Customer customer) {
        this.code = code;
        this.message = message;
        this.customer = customer;
    }



    public ResponseDto(int code, String errorMessage, String description) {
        this.code = code;
        this.errorMessage = errorMessage;
        this.description = description;
    }


    

}
