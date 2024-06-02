package at.fhtw.backend.service.Tour;

import at.fhtw.backend.enums.TransportType;
import at.fhtw.backend.model.Tour;
import at.fhtw.backend.repository.TourRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TourServiceTest {

    @Mock
    private TourRepository tourRepository;

    private TourService tourService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        tourService = new TourService(tourRepository);
    }

    @Test
    public void testFetchTours() {
        // Arrange
        List<Tour> tours = new ArrayList<>();
        tours.add(new Tour(1L, "Tour 1", "Description 1", "Start Point", 1.0, 1.0, "End Point", 1.0, 1.0, TransportType.Bike, 1.0, 1.0, "Information", null));
        tours.add(new Tour(1L, "Tour 2", "Description 1", "Start Point", 1.0, 1.0, "End Point", 1.0, 1.0, TransportType.Bike, 1.0, 1.0, "Information", null));
        when(tourRepository.findAll()).thenReturn(tours);

        // Act
        List<Tour> fetchedTours = tourService.fetchTours();

        // Assert
        assertEquals(2, fetchedTours.size());
        assertEquals("Tour 1", fetchedTours.get(0).getTourName());
        assertEquals("Tour 2", fetchedTours.get(1).getTourName());
    }

    @Test
    public void testAddTour() {
        // Arrange
        Tour tour = new Tour(1L, "New Tour", "Description 1", "Start Point", 1.0, 1.0, "End Point", 1.0, 1.0, TransportType.Bike, 1.0, 1.0, "Information", null);
        when(tourRepository.save(tour)).thenReturn(tour);

        // Act
        Tour addedTour = tourService.addTour(tour);

        // Assert
        assertEquals("New Tour", addedTour.getTourName());
    }

    @Test
    public void testRemoveTour() {
        // Arrange
        Long tourId = 1L;

        // Act
        tourService.removeTour(tourId);

        // Assert
        verify(tourRepository, times(1)).deleteById(tourId);
    }
}