package in.habel.exceptions;


import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    private String message;

    public BadRequestException(String resourceName, String fieldName, Object fieldValue) {
        message = String.format("%s not found with %s %s ", resourceName, fieldName, fieldValue);
    }

    public BadRequestException(String message) {
        this.message = message;
    }

    @Override
    public String getLocalizedMessage() {
        return StringUtils.defaultIfBlank(message, super.getLocalizedMessage());
    }
}