package at.fhtw.routplanner.viewModel;

import at.fhtw.routplanner.ObserverSelectedTour;
import at.fhtw.routplanner.UpdateTourListener;
import at.fhtw.routplanner.enums.TransportType;
import at.fhtw.routplanner.model.Tour;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OverviewViewModelTest {
    @Mock
    private ObserverSelectedTour observerSelectedTour;

    @InjectMocks
    private OverviewViewModel overviewViewModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        doNothing().when(observerSelectedTour).register(any(UpdateTourListener.class));
    }

    @Test
    void testUpdateTour() {
        Tour tour = mock(Tour.class);
        when(tour.getTourName()).thenReturn("Tour Name");
        when(tour.getDescription()).thenReturn("Description");
        when(tour.getStartPoint()).thenReturn("Start Point");
        when(tour.getEndPoint()).thenReturn("End Point");
        when(tour.getTransportType()).thenReturn(TransportType.Bike);
        when(tour.getDistance()).thenReturn(1000.);
        when(tour.getTime()).thenReturn(3600.);

        overviewViewModel.updateTour(tour);

        assertEquals("Tour Name", overviewViewModel.getCurrentTourName().get());
        assertEquals("Description", overviewViewModel.getCurrentDescription().get());
        assertEquals("Start Point", overviewViewModel.getCurrentStartPoint().get());
        assertEquals("End Point", overviewViewModel.getCurrentEndpoint().get());
        assertEquals("Bike", overviewViewModel.getCurrentTransportType().get());
        assertEquals("1000.0 m", overviewViewModel.getCurrentDistance().get());
        assertEquals("60 min", overviewViewModel.getCurrentTime().get());
    }


}