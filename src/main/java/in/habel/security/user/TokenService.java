package in.habel.security.user;

import in.habel.models.StoreAuth;
import in.habel.models.store.Store;

import java.util.Optional;

public interface TokenService {

    StoreAuth create(Store store);

    StoreAuth refresh(Store store);

    Optional<StoreAuth> contains(String apiId, String apiKey);

    void disable(String token);
}
