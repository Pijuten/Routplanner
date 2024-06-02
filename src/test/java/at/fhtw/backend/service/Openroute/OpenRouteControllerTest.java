package at.fhtw.backend.service.Openroute;

import at.fhtw.backend.model.OpenRoute.Geocode.Geocoding;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class OpenRouteControllerTest {


    @Mock
    private OpenRouteService openRouteService;

    @InjectMocks
    private OpenRouteController openRouteController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(openRouteController).build();
    }
    @Test
    void getGeocode_shouldReturnGeocode() throws Exception {
        OpenRouteService service = new OpenRouteService();
        Geocoding result= service.GetGeocode("Spaungasse");
        assertNotNull(result);
        assertEquals(result.getFeatures().get(0).getProperties().getName(), "Spaungasse");
        assertEquals(result.getFeatures().get(0).getGeometry().getCoordinates().get(0), 16.365736);
        assertEquals(result.getFeatures().get(0).getGeometry().getCoordinates().get(1), 48.234628);
    }
    @Test
    void getGeocode_shouldCallOpenRouteService() throws Exception {
        mockMvc.perform(get("/route/geocode")
                        .param("locationname","Spaungasse")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(openRouteService,times(1)).GetGeocode("Spaungasse");
    }

}