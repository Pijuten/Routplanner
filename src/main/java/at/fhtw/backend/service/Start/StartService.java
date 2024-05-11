package at.fhtw.backend.service.Start;

import at.fhtw.backend.model.Tour;
import at.fhtw.backend.repository.TourRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StartService {
    private final TourRepository tourRepository;

    public StartService(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    public List<Tour> fetchTours() {
        return tourRepository.findAll();
    }

    public Long addTour(Tour tour) {
       Tour tour1 = tourRepository.save(tour);
       return tour1.getTour_id();
    }
    public void removeTour(Long logId) {
        tourRepository.deleteById(logId);
    }

}
