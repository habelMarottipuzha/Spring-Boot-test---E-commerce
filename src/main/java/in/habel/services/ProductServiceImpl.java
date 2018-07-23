package in.habel.services;

import in.habel.exceptions.BadRequestException;
import in.habel.exceptions.ResourceNotFoundException;
import in.habel.models.store.Product;
import in.habel.models.store.Store;
import in.habel.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private StoreService storeService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, StoreService storeService) {
        this.productRepository = productRepository;
        this.storeService = storeService;
    }


    @Override
    public Product insert(@Valid Product product, String storeId) {
        Store store = storeService.getStoreByApiId(storeId).orElseThrow(() -> new ResourceNotFoundException("Store not found"));
        product.setStore(store);
        productRepository.save(product);
        return product;
    }

    @Override
    public Product updateProduct(Product product, String storeId) {
        Store store = storeService.getStoreByApiId(storeId).orElseThrow(() -> new ResourceNotFoundException("Store not found"));
// Check whether product belongs to same store storeId
        Product existingProd;
        try {
            existingProd = productRepository.findById(product.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        } catch (Exception e) {
            throw new ResourceNotFoundException("Product not found");
        }
        if (existingProd.getStore().getId().equals(store.getId())) {
            existingProd.setName(product.getName());
            productRepository.save(existingProd);
            return existingProd;
        } else throw new BadRequestException("Product doesn't belong to your store");
    }

    @Override
    public Optional<Product> getProduct(Long productId, String storeId) {
        Store store = storeService.getStoreByApiId(storeId).orElseThrow(() -> new ResourceNotFoundException("Store not found"));
// Check product belongs to same store
        return productRepository.findByIdAndStore(productId, store);
    }

    public List<Product> getAllPaginated(Pageable pageable, String storeId) {
        Store store = storeService.getStoreByApiId(storeId).orElseThrow(() -> new ResourceNotFoundException("Store not found"));

        return productRepository.findAllByStore(store).orElse(new ArrayList<>());
    }
}