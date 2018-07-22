package in.habel.services;

import in.habel.exceptions.ResourceNotFoundException;
import in.habel.models.StoreAuth;
import in.habel.models.store.Store;
import in.habel.repositories.StoreRepository;
import in.habel.security.TokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StoreServiceImpl implements StoreService {
    private final TokenServiceImpl tokenService;
    private StoreRepository storeRepository;

    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository, TokenServiceImpl tokenService) {
        this.storeRepository = storeRepository;
        this.tokenService = tokenService;
    }

    @Override
    public Store insert(@Valid Store store) {
        store.setApiId(UUID.randomUUID().toString());
        storeRepository.save(store);
        tokenService.create(store);
        return store;
    }

    @Override
    public StoreAuth refreshToken(String storeId) {
        Store store = storeRepository.findByApiId(storeId).orElseThrow(() -> new ResourceNotFoundException("store", "id", storeId));

        return tokenService.refresh(store);
    }

    @Override
    public Optional<Store> getStore(Long storeId) {
        return storeRepository.findById(storeId);
    }

    @Override
    public Optional<Store> getStoreByApiId(String apiId) {
        return storeRepository.findByApiId(apiId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Store> getAllStores(Pageable pageable) {

        return storeRepository.findAll();
    }
}