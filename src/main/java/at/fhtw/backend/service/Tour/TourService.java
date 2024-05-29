package at.fhtw.backend.service.Tour;

import at.fhtw.backend.model.Tour;
import at.fhtw.backend.repository.TourRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourService {
    private final TourRepository tourRepository;

    public TourService(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    public List<Tour> fetchTours() {
        return tourRepository.findAll();
    }

    public Tour addTour(Tour tour) {
       return tourRepository.save(tour);
    }
    public void removeTour(Long logId) {
        tourRepository.deleteById(logId);
    }

}
