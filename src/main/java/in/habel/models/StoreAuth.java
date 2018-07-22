package in.habel.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Indexed;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Indexed
@Table(name = "auth")

public class StoreAuth extends AuditModel {
    private String apiId;
    private String apiKey;
    private boolean enabled;
}
