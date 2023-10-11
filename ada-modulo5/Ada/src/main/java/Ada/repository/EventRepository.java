package Ada.repository;

import Ada.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {
    @Override
    Optional<Event> findById(Long id);

    @Override
    void deleteById(Long id);
}
