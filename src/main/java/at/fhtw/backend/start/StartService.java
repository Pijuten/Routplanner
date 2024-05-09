package at.fhtw.backend.start;

import at.fhtw.backend.model.Log;
import at.fhtw.backend.repository.TourRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StartService {
    private final TourRepository tourRepository;

    public StartService(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    public List<Log> fetchTours() {
        return tourRepository.findAll();
    }

    public void addTour(Log log) {
        tourRepository.save(log);
    }
    public void removeTour(Long logId) {
        tourRepository.deleteById(logId);
    }

}
