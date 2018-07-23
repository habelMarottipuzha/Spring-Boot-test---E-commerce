package in.habel.models.store;

import com.fasterxml.jackson.annotation.JsonBackReference;
import in.habel.models.AuditModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Indexed;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Indexed
@Table(name = "product")
public class Product extends AuditModel {

    @NotEmpty
    private String name;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Store store;
}
