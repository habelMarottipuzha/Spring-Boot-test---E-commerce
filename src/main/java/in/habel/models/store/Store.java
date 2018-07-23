package in.habel.models.store;

import in.habel.models.AuditModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Indexed;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Indexed
@Table(name = "store", indexes = {
        @Index(columnList = "storeId", name = "idx_store_id")
})
public class Store extends AuditModel {
    @NotEmpty
    private String name;
    private String description;

    private String storeId;

}
