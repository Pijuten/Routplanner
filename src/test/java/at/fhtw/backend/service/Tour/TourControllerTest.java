package at.fhtw.backend.service.Tour;

import at.fhtw.backend.enums.TransportType;
import at.fhtw.backend.model.Tour;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class TourControllerTest {

    @Mock
    private TourService tourService;

    @InjectMocks
    private TourController tourController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(tourController).build();
    }

    @Test
    void fetchLogs_shouldReturnTourList() throws Exception {
        Tour tour1 = new Tour();
        Tour tour2 = new Tour();
        List<Tour> tourList = Arrays.asList(tour1, tour2);

        when(tourService.fetchTours()).thenReturn(tourList);

        mockMvc.perform(get("/tour/get")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(tourService,times(1)).fetchTours();
    }
    @Test
    void addLog_shouldAddTour() throws Exception {
        Tour tour = new Tour(1L, "Tour 1", "Description 1", "Start Point", 1.0, 1.0, "End Point", 1.0, 1.0, TransportType.Bike, 1.0, 1.0, "Information", null);

        when(tourService.addTour(any(Tour.class))).thenReturn(tour);

        mockMvc.perform(MockMvcRequestBuilders.post("/tour/add")
                .content("{\"tourid\":1, \"tourname\":\"Tour 1\", \"description\":\"Description 1\", \"startpoint\":\"Start Point\", \"latstartpoint\":1.0, \"longstartpoint\":1.0, \"endpoint\":\"End Point\", \"latendpoint\":1.0, \"longendpoint\":1.0, \"transporttype\":\"Bike\", \"distance\":1.0, \"time\":1.0, \"information\":\"Information\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(tourService, times(1)).addTour(any(Tour.class));
    }
    @Test
    void updateLog_shouldUpdateTour() throws Exception {
        Long tourId = 1L;

        doNothing().when(tourService).removeTour(tourId);

        mockMvc.perform(delete("/tour/remove")
                        .param("tourId", tourId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(tourService, times(1)).removeTour(tourId);
    }
    @Test
    void createReport_shouldCreateReport() throws Exception {
        doNothing().when(tourService).createReport(any(Tour.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/tour/report")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"tourid\":1, \"tourname\":\"Tour 1\", \"description\":\"Description 1\", \"startpoint\":\"Start Point\", \"latstartpoint\":1.0, \"longstartpoint\":1.0, \"endpoint\":\"End Point\", \"latendpoint\":1.0, \"longendpoint\":1.0, \"transporttype\":\"Bike\", \"distance\":1.0, \"time\":1.0, \"information\":\"Information\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(tourService, times(1)).createReport(any(Tour.class));
    }
}