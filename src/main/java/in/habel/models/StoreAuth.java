package in.habel.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Indexed;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Indexed
@Table(name = "auth", indexes = {
        @Index(columnList = "apiId", name = "idx_api_id"),
        @Index(columnList = "apiKey", name = "idx_api_key")
})
public class StoreAuth extends AuditModel {
    private String apiId;
    private String apiKey;
    private boolean enabled;
}
