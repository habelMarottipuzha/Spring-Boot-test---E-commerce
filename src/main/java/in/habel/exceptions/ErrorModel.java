package in.habel.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.Calendar;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
class ErrorModel {
    private int status;
    private String message;
    private String error;
    private Long timestamp;

    ErrorModel(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = Calendar.getInstance().getTimeInMillis();
    }

    ErrorModel(int status, String message, String error) {
        this(status, message);
        this.error = error;
    }
}
