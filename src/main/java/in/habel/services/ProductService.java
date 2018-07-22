package in.habel.services;

import in.habel.models.store.Product;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product insert(@Valid Product product, String storeId);

    <T> Optional<Product> getProduct(@NotNull Long productId, String storeId);

    <T> Product updateProduct(@NotNull Product product, String storeId);

    <T> Optional<List<Product>> getAllPaginated(Pageable pageable, String storeId);
}