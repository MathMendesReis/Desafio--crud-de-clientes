package com.matheusmendes.app.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends CustomError{
    public ValidationError(Instant timestamp, Integer status, String error, String trace) {
        super(timestamp, status, error, trace);
    }

    private List<FieldMessage> erros = new ArrayList<>();

    public List<FieldMessage> getErros() {
        return erros;
    }

    public void addErrors(String fieldName, String fieldMessage){
        erros.add(new FieldMessage(fieldName, fieldMessage));
    }
   
}
