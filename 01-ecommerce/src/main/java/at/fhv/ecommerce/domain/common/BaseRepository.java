package at.fhv.ecommerce.domain.common;

import java.util.Optional;

public interface BaseRepository<T, ID> {
    void save(T model);

    Optional<T> findById(ID id);
}
