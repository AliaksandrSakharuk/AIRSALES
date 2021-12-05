package by.ita.je.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ValidationError {
    private String message;
    private String exception;
}
