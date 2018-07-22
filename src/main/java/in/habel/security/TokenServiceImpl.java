package in.habel.security;

import in.habel.exceptions.ResourceNotFoundException;
import in.habel.models.StoreAuth;
import in.habel.models.store.Store;
import in.habel.repositories.StoreAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private StoreAuthRepository storeAuthRepository;


    public Optional<StoreAuth> contains(String apiId, String apiKey) {
        return storeAuthRepository.findByApiIdAndApiKeyAndEnabledTrue(apiId, apiKey);
    }

    @Override
    public StoreAuth create(Store store) {
        storeAuthRepository.disable(store.getApiId());
        StoreAuth storeAuth = new StoreAuth();
        storeAuth.setApiId(store.getApiId());
        storeAuth.setApiKey(UUID.randomUUID().toString());
        storeAuth.setEnabled(true);
        storeAuthRepository.save(storeAuth);
        return storeAuth;
    }

    @Override
    public StoreAuth refresh(Store store) {
        Optional<StoreAuth> storeAuthOptional = Optional.ofNullable(storeAuthRepository.findByApiId(store.getApiId()));
        if (storeAuthOptional.isPresent()) {
            StoreAuth storeAuth = storeAuthOptional.get();
            if (!storeAuth.isEnabled()) throw new RuntimeException("Resource disabled/Expired");
            storeAuth.setApiKey(UUID.randomUUID().toString());
            storeAuthRepository.save(storeAuth);
            return storeAuth;

        } else throw new ResourceNotFoundException("auth", "storeId", store.getApiId());

    }

    @Override
    public void disable(String apiKey) {
        storeAuthRepository.disable(apiKey);
    }
}