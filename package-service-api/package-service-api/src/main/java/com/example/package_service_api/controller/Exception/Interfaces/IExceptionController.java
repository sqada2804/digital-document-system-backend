package com.example.package_service_api.controller.Exception.Interfaces;

import com.example.package_service_api.common.exceptions.NotFoundException;
import com.example.package_service_api.common.exceptions.UnauthorizedException;
import org.springframework.http.ResponseEntity;

public interface IExceptionController {
    public ResponseEntity<Object> handleNotFoundException(NotFoundException e);
    public ResponseEntity<Object> handleGenericException(Exception e);
    public ResponseEntity<Object> handleUnauthorizedException(UnauthorizedException e);
}
