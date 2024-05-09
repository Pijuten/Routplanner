package at.fhtw.backend.repository;

import at.fhtw.backend.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  TourRepository extends JpaRepository<Log,Long> {

}
