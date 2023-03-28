package org.monarchinitiative.phenopacketlab.restapi.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.monarchinitiative.phenol.ontology.data.Term;
import org.monarchinitiative.phenol.ontology.data.TermId;
import org.monarchinitiative.phenopacketlab.core.ontology.HpoService;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PhenotypicFeatureControllerTest {

    private static final RestResponseEntityExceptionHandler HANDLER = new RestResponseEntityExceptionHandler();

    @Mock
    public HpoService phenotypicFeatureService;
    @InjectMocks
    public PhenotypicFeatureController controller;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .addPlaceholderValue("api.version", "/api/v1")
                .setControllerAdvice(HANDLER)
                .build();
    }

    @Test
    public void phenotypicFeatureById() throws Exception {
        TermId phenotypicFeatureId = TermId.of("OMIM:123456");
        when(phenotypicFeatureService.phenotypicFeatureById(phenotypicFeatureId))
                .thenReturn(Optional.of(createPhenotypicFeature(phenotypicFeatureId.getValue(), "First")));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/phenotypic-features/OMIM:123456"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertThat(response.getContentAsString(), equalTo("{\"id\":\"OMIM:123456\",\"label\":\"First\"}"));
    }

    @Test
    public void phenotypicFeatureById_missingPhenotypicFeature() throws Exception {
        TermId diseaseId = TermId.of("OMIM:123456");
        when(phenotypicFeatureService.phenotypicFeatureById(diseaseId))
                .thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/phenotypic-features/OMIM:123456"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void phenotypicFeatureById_InvalidId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/phenotypic-features/INVALID"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void getAllPhenotypicFeatures() throws Exception {
        when(phenotypicFeatureService.phenotypicFeatures())
                .thenReturn(Stream.of(
                        createPhenotypicFeature("OMIM:123456", "First"),
                        createPhenotypicFeature("OMIM:987654", "Second")
                ));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/phenotypic-features"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getContentAsString(), equalTo("[{\"id\":\"OMIM:123456\",\"label\":\"First\"},{\"id\":\"OMIM:987654\",\"label\":\"Second\"}]"));
    }

    private static Term createPhenotypicFeature(String phenotypicFeatureId, String phenotypicFeatureName) {
        return Term.of(TermId.of(phenotypicFeatureId),
                phenotypicFeatureName);
    }
}