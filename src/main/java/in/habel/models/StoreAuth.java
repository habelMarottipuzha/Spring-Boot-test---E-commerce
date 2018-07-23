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
        @Index(columnList = "storeId", name = "idx_store_id"),
        @Index(columnList = "storeKey", name = "idx_store_key")
})
public class StoreAuth extends AuditModel {
    private String storeId;
    private String storeKey;
    private boolean enabled;
}
