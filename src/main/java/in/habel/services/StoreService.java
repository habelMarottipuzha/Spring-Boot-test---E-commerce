package in.habel.services;

import in.habel.models.StoreAuth;
import in.habel.models.store.Store;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface StoreService {


    Store insert(@Valid Store store);

    StoreAuth refreshToken(String storeId);

    <T> Optional<Store> findById(@NotNull Long storeId);

    <T> Optional<Store> findByApiId(String apiId);

    <T> List<Store> findAllPaginated(Pageable pageable);
}