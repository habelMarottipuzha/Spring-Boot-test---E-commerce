package in.habel.models.store;

import com.fasterxml.jackson.annotation.JsonBackReference;
import in.habel.models.AuditModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "Product", description = "Product model")

public class Product extends AuditModel {

    @NotEmpty
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @ApiModelProperty(hidden = true)
    private Store store;


}
