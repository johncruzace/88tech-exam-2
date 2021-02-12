package johncruz.tech.exam.minibank.model.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiError {

    private HttpStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;

    public ApiError(LocalDateTime timestamp){
        super();
        this.timestamp = timestamp;
    }

    public ApiError(HttpStatus status){
        super();
        this.status = status;
    }

    public ApiError(HttpStatus status, Throwable e){
        super();
        this.status = status;
        this.message = "UNEXPECTED ERROR";
        this.debugMessage = e.getLocalizedMessage();
    }

    public ApiError(HttpStatus status, String message, Throwable e){
        super();
        this.status = status;
        this.message = message;
        this.debugMessage = e.getLocalizedMessage();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }
}
