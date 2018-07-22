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
    StoreAuth findByApiKey(String apiKey);

    StoreAuth findByApiId(String apiKey);

    @Modifying
    @Query("UPDATE StoreAuth s SET s.enabled = false WHERE s.apiId = :apiId")
    int disable(@Param("apiId") String apiId);


    @Query("SELECT apiKey,apiId FROM StoreAuth  s WHERE s.apiId = :apiId and s.apiKey = :apiKey and s.enabled = true")
    StoreAuth valid(@Param("apiId") String apiId, @Param("apiKey") String apiKey);

    Optional<StoreAuth> findByApiIdAndApiKeyAndEnabledTrue(String apiId, String apiKey);
}