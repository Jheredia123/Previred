package com.previred.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseException {
    private String mensaje;

    public ResponseException(String detail) {
        this.mensaje = detail;
    }
}
