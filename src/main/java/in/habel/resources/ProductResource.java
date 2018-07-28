package in.habel.resources;

import in.habel.annotations.CurrentStore;
import in.habel.exceptions.ResourceNotFoundException;
import in.habel.models.store.Product;
import in.habel.services.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


@Api(description = "Rest controller for products")
@RequestMapping(value = "product", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class ProductResource {
    @Autowired
    ProductService productService;

    @GetMapping
    @ApiOperation(value = "Get all products of current store.(Currently not paginated)",
            notes = "Requires Store authentication",
            response = Product.class,
            responseContainer = "List")
    public List<Product> getAllProducts(@ApiParam(hidden = true) @CurrentStore String storeId) {
        return productService.getAllPaginated(null, storeId);
    }

    @PostMapping
    public Product addProduct(@ApiParam(hidden = true) @CurrentStore String storeId, @Valid @RequestBody Product product) {
        return productService.insert(product, storeId);
    }

    @PutMapping
    public Product updateProduct(@ApiParam(hidden = true) @CurrentStore String storeId, @Valid @RequestBody Product product) {
        return productService.updateProduct(product, storeId);
    }

    @GetMapping(value = "{id}")
    public Product getProduct(@ApiParam(hidden = true) @CurrentStore String storeId, @NotNull @PathVariable Long id) {
        return productService.getProduct(id, storeId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

}
