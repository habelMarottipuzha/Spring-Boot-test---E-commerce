package in.habel.models.store;

import in.habel.models.AuditModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Indexed;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Indexed
@Table(name = "store")
public class Store extends AuditModel {
    @NotEmpty
    private String name;
    private String description;

    private String apiId;
/*
    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    private Set<Product> products;*/
}
