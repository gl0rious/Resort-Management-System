package edu.miu.cs.cs544.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDTO {

    private String errorMessage;
    private HttpStatusCode statusCode;

}
