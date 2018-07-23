package in.habel.repositories;

import in.habel.models.StoreAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
public interface StoreAuthRepository extends JpaRepository<StoreAuth, Long>, CrudRepository<StoreAuth, Long> {
    StoreAuth findByStoreKey(String storeKey);

    StoreAuth findByStoreId(String storeId);

    @Modifying
    @Query("UPDATE StoreAuth s SET s.enabled = false WHERE s.storeId = :storeId")
    int disable(@Param("storeId") String storeId);


    @Query("SELECT storeKey,storeId FROM StoreAuth  s WHERE s.storeId = :storeId and s.storeKey = :storeKey and s.enabled = true")
    StoreAuth valid(@Param("storeId") String storeId, @Param("storeKey") String storeKey);

    Optional<StoreAuth> findByStoreIdAndStoreKeyAndEnabledTrue(String storeId, String storeKey);
}