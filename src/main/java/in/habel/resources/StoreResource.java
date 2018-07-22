package in.habel.resources;

import in.habel.exceptions.ResourceNotFoundException;
import in.habel.models.StoreAuth;
import in.habel.models.store.Store;
import in.habel.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@RestController
@PermitAll
public class StoreResource {
    @Autowired
    StoreService storeService;


    @GetMapping("store")
    public List<Store> getAllStore() {
        return storeService.findAllPaginated(null);
    }

    @PostMapping("store")
    public Store addStore(@Valid @RequestBody Store store) {
        return storeService.insert(store);
    }

    @GetMapping(value = "store/{id}")
    public Store getStoreById(@NotNull @PathVariable Long id) {
        return storeService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("store", "id", id));
    }

    @GetMapping(value = "store/token/refresh")
    public StoreAuth generateStoreToken() {
        return storeService.refreshToken(1L);
    }
}
