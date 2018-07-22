package in.habel.services;

import in.habel.models.store.Product;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    /**
     * Insert a new product
     *
     * @param product product to be inserted
     * @param storeId ApiId of store
     * @return product
     */
    Product insert(@Valid Product product, String storeId);

    /**
     * Fetch a single product
     *
     * @param productId id of the product
     * @param storeId   apiId of store
     * @return Product
     */
    Optional<Product> getProduct(@NotNull Long productId, String storeId);

    /**
     * Updates a product. It also checks whether the product belongs to same store
     *
     * @param product product to be updated
     * @param storeId store ApiId
     * @return updated product
     */
    Product updateProduct(@NotNull Product product, String storeId);

    /**
     * Get all stores
     *
     * @param storeId ApiId of store
     * @return prodducts list
     */
    Optional<List<Product>> getAllPaginated(Pageable pageable, String storeId);
}