package com.between.challenge.common.exception;

import com.between.challenge.commons.exception.ErrorResponse;
import com.between.challenge.commons.exception.ErrorResponseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
public class ErrorResponseExceptionTest {

    @Test
    public void errorResponseExceptionTest(){
        ErrorResponse errorResponse = new ErrorResponse(500, "ERROR", HttpStatus.BAD_REQUEST);
        ErrorResponseException errorResponseException = new ErrorResponseException(errorResponse);

        Assertions.assertNotNull(errorResponseException);
    }
}

