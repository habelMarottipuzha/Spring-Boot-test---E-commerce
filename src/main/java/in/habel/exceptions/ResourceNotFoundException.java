package in.habel.exceptions;


import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private String message;

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        message = String.format("%s not found with %s %s ", resourceName, fieldName, fieldValue);
    }

    public ResourceNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getLocalizedMessage() {
        return StringUtils.defaultIfBlank(message, super.getLocalizedMessage());
    }
}