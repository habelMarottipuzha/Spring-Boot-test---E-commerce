package in.habel.resources;

import in.habel.annotations.CurrentStore;
import in.habel.exceptions.ResourceNotFoundException;
import in.habel.models.store.Product;
import in.habel.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RequestMapping(value = "product", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@RestController

public class ProductResource {
    @Autowired
    ProductService productService;


    @GetMapping
    public Optional<List<Product>> getAllStore(@CurrentStore String storeId) {
        return productService.getAllPaginated(null, storeId);
    }

    @PostMapping
    public Product addStore(@CurrentStore String storeId, @Valid @RequestBody Product product) {
        return productService.insert(product, storeId);
    }

    @PutMapping
    public Product updateStore(@CurrentStore String storeId, @Valid @RequestBody Product product) {
        return productService.updateProduct(product, storeId);
    }

    @GetMapping(value = "{id}")
    public Product getProduct(@CurrentStore String storeId, @NotNull @PathVariable Long id) {
        return productService.getProduct(id, storeId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

}
