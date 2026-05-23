package org.migel.trsis.samples.db.dao;

import org.migel.trsis.samples.db.model.GamePE;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<GamePE, Long> {
    List<GamePE> findAll();
}
