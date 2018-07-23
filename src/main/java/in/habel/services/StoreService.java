package in.habel.services;

import in.habel.models.StoreAuth;
import in.habel.models.store.Store;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface StoreService {

    /**
     * Insert new store
     *
     * @param store Store to be inserted
     * @return newly created Store
     */
    Store insert(@Valid Store store);

    /**
     * Fetch a store using store id
     *
     * @param storeId id of store
     * @return store if exist. Optional.empty() otherwise
     */
    Optional<Store> getStore(@NotNull Long storeId);

    /**
     * Fetch a store using store ApiId
     *
     * @param apiId ApiId of store
     * @return store if exist. Optional.empty() otherwise
     */
    Optional<Store> getStoreByApiId(String apiId);

    /**
     * Fetch all stores
     *
     * @param pageable
     * @return all stores
     */
    List<Store> getAllStores(Pageable pageable);

    /**
     * Refresh store api key
     *
     * @param storeId ApiId of store
     * @return new auth credentials
     */
    StoreAuth refreshToken(String storeId);

    void update(Store store);
}