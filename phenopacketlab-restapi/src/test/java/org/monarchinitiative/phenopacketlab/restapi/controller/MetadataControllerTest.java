package org.monarchinitiative.phenopacketlab.restapi.controller;

import org.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.monarchinitiative.phenopacketlab.core.ConceptResourceService;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MetadataControllerTest {

    private static final RestResponseEntityExceptionHandler HANDLER = new RestResponseEntityExceptionHandler();

    @Mock
    public ConceptResourceService conceptResourceService;

    @InjectMocks
    public MetadataController controller;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .addPlaceholderValue("api.version", "/api/v1")
                .setControllerAdvice(HANDLER)
                .build();
    }

    // TODO(ielis) - check
    @Test
    public void getResourcePrefixesForPhenopacket_InvalidId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/metadata"))
                        .andExpect(MockMvcResultMatchers.status().isMethodNotAllowed());
    }

    @Test
    public void getResourcePrefixesForPhenopacket() throws Exception {
        when(metadataService.resourcesPrefixesForPhenopacket(TestData.INCORRECT_PHENOPACKET))
                .thenReturn(Stream.of("MONDO","HGNC","NCIT"));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/metadata")
                        .contentType("text/plain")
                        .content(TestData.INCORRECT_PHENOPACKET))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        String actual = response.getContentAsString();
        JSONArray jsonActual = new JSONArray(actual);
        List<String> prefixes = Arrays.asList("MONDO","HGNC","NCIT");
        for (int i=0; i < jsonActual.length(); i++) {
            String resourcePrefix = jsonActual.getString(i);
            assertTrue(prefixes.contains(resourcePrefix));
        }
    }

    private IdentifiedConceptResource createResource(String prefix) {
        Resource resource = switch (prefix.toUpperCase()) {
            case "HP" -> Resource.of("hp",
                    "Human Phenotype Ontology",
                    "http://purl.obolibrary.org/obo/hp.json",
                    "2022-12-15",
                    "HP",
                    "http://purl.obolibrary.org/obo/HP_");
            case "EFO" -> Resource.of("efo",
                    "Experimental Factor Ontology",
                    "http://www.ebi.ac.uk/efo/efo.owl",
                    "3.49.0",
                    "EFO",
                    "http://www.ebi.ac.uk/efo/EFO_");
            case "GENO" -> Resource.of("geno",
                    "Genotype Ontology",
                    "http://purl.obolibrary.org/obo/geno.json",
                    "2022-08-10",
                    "GENO",
                    "http://purl.obolibrary.org/obo/GENO_");
            case "MONDO" -> Resource.of("mondo",
                    "MONDO Disease Ontology",
                    "http://purl.obolibrary.org/obo/mondo.json",
                    "2022-12-01",
                    "MONDO",
                    "http://purl.obolibrary.org/obo/MONDO_");
            case "SO" -> Resource.of("so",
                    "Sequence types and features ontology",
                    "http://purl.obolibrary.org/obo/so.json",
                    "2021-11-22",
                    "SO",
                    "http://purl.obolibrary.org/obo/SO_");
            case "UBERON" -> Resource.of("uberon",
                    "Uber-anatomy ontology",
                    "http://purl.obolibrary.org/obo/uberon.json",
                    "2022-12-13",
                    "UBERON",
                    "http://purl.obolibrary.org/obo/UBERON_");
            case "HGNC" -> Resource.of("hgnc",
                    "HUGO Gene Nomenclature Committee",
                    "http://ftp.ebi.ac.uk/pub/databases/genenames/hgnc/tsv/hgnc_complete_set.txt",
                    "HGNC_VERSION",
                    "HGNC",
                    "http://identifiers.org/hgnc/HGNC:");
            case "NCIT" -> Resource.of("ncit",
                    "NCI Thesaurus",
                    "http://purl.obolibrary.org/obo/ncit.owl",
                    "22.07d",
                    "NCIT",
                    "http://purl.obolibrary.org/obo/NCIT_");
            case "GSSO" -> Resource.of("gsso",
                    "GSSO - the Gender, Sex, and Sexual Orientation ontology",
                    "http://purl.obolibrary.org/obo/gsso.owl",
                    "UNKNOWN",
                    "GSSO",
                    "http://purl.obolibrary.org/obo/GSSO_");
            case "UO" -> Resource.of("uo",
                    "Units of measurement ontology",
                    "http://purl.obolibrary.org/obo/uo.owl",
                    "UNKNOWN",
                    "UO",
                    "http://purl.obolibrary.org/obo/UO_");
            default -> null;
        };
        return IdentifiedConceptResource.of(List.of(), resource);
    }

    private Map<String, Map<String, String>> expectedFullMetadata() {
        Map<String, Map<String, String>> map = new HashMap<>();
        Map<String, String> hpMap = new HashMap<>();
        hpMap.put("name", "Human Phenotype Ontology");
        hpMap.put("id", "hp");
        hpMap.put("url", "http://purl.obolibrary.org/obo/hp.json");
        hpMap.put("namespacePrefix", "HP");
        hpMap.put("iriPrefix", "http://purl.obolibrary.org/obo/HP_");
        hpMap.put("version", "2022-12-15");
        map.put("HP", hpMap);

        Map<String, String> efoMap = new HashMap<>();
        efoMap.put("name", "Experimental Factor Ontology");
        efoMap.put("id", "efo");
        efoMap.put("url", "http://www.ebi.ac.uk/efo/efo.owl");
        efoMap.put("namespacePrefix", "EFO");
        efoMap.put("iriPrefix", "http://www.ebi.ac.uk/efo/EFO_");
        efoMap.put("version", "3.49.0");
        map.put("EFO", efoMap);

        Map<String, String> genoMap = new HashMap<>();
        genoMap.put("name", "Genotype Ontology");
        genoMap.put("id", "geno");
        genoMap.put("url", "http://purl.obolibrary.org/obo/geno.json");
        genoMap.put("namespacePrefix", "GENO");
        genoMap.put("iriPrefix", "http://purl.obolibrary.org/obo/GENO_");
        genoMap.put("version", "2022-08-10");
        map.put("GENO", genoMap);

        Map<String, String> mondoMap = new HashMap<>();
        mondoMap.put("name", "MONDO Disease Ontology");
        mondoMap.put("id", "mondo");
        mondoMap.put("url", "http://purl.obolibrary.org/obo/mondo.json");
        mondoMap.put("namespacePrefix", "MONDO");
        mondoMap.put("iriPrefix", "http://purl.obolibrary.org/obo/MONDO_");
        mondoMap.put("version", "2022-12-01");
        map.put("MONDO", mondoMap);

        Map<String, String> soMap = new HashMap<>();
        soMap.put("name", "Sequence types and features ontology");
        soMap.put("id", "so");
        soMap.put("url", "http://purl.obolibrary.org/obo/so.json");
        soMap.put("namespacePrefix", "SO");
        soMap.put("iriPrefix", "http://purl.obolibrary.org/obo/SO_");
        soMap.put("version", "2021-11-22");
        map.put("SO", soMap);

        Map<String, String> uberonMap = new HashMap<>();
        uberonMap.put("name", "Uber-anatomy ontology");
        uberonMap.put("id", "uberon");
        uberonMap.put("url", "http://purl.obolibrary.org/obo/uberon.json");
        uberonMap.put("namespacePrefix", "UBERON");
        uberonMap.put("iriPrefix", "http://purl.obolibrary.org/obo/UBERON_");
        uberonMap.put("version", "2022-12-13");
        map.put("UBERON", uberonMap);

        Map<String, String> hgncMap = new HashMap<>();
        hgncMap.put("name", "HUGO Gene Nomenclature Committee");
        hgncMap.put("id", "hgnc");
        hgncMap.put("url", "http://ftp.ebi.ac.uk/pub/databases/genenames/hgnc/tsv/hgnc_complete_set.txt");
        hgncMap.put("namespacePrefix", "HGNC");
        hgncMap.put("iriPrefix", "http://identifiers.org/hgnc/HGNC:");
        hgncMap.put("version", "HGNC_VERSION");
        map.put("HGNC", hgncMap);

        Map<String, String> ncitMap = new HashMap<>();
        ncitMap.put("name", "NCI Thesaurus");
        ncitMap.put("id", "ncit");
        ncitMap.put("url", "http://purl.obolibrary.org/obo/ncit.owl");
        ncitMap.put("namespacePrefix", "NCIT");
        ncitMap.put("iriPrefix", "http://purl.obolibrary.org/obo/NCIT_");
        ncitMap.put("version", "22.07d");
        map.put("NCIT", ncitMap);

        Map<String, String> gssoMap = new HashMap<>();
        gssoMap.put("name", "GSSO - the Gender, Sex, and Sexual Orientation ontology");
        gssoMap.put("id", "gsso");
        gssoMap.put("url", "http://purl.obolibrary.org/obo/gsso.owl");
        gssoMap.put("namespacePrefix", "GSSO");
        gssoMap.put("iriPrefix", "http://purl.obolibrary.org/obo/GSSO_");
        gssoMap.put("version", "UNKNOWN");
        map.put("GSSO", gssoMap);

        Map<String, String> uoMap = new HashMap<>();
        uoMap.put("name", "Units of measurement ontology");
        uoMap.put("id", "uo");
        uoMap.put("url", "http://purl.obolibrary.org/obo/uo.owl");
        uoMap.put("namespacePrefix", "UO");
        uoMap.put("iriPrefix", "http://purl.obolibrary.org/obo/UO_");
        uoMap.put("version", "UNKNOWN");
        map.put("UO", uoMap);
        return map;
    }

    private Map<String, Map<String, String>> expectedMissingMetadata() {
        Map<String, Map<String, String>> map = new HashMap<>();

        Map<String, String> mondoMap = new HashMap<>();
        mondoMap.put("name", "MONDO Disease Ontology");
        mondoMap.put("id", "mondo");
        mondoMap.put("url", "http://purl.obolibrary.org/obo/mondo.json");
        mondoMap.put("namespacePrefix", "MONDO");
        mondoMap.put("iriPrefix", "http://purl.obolibrary.org/obo/MONDO_");
        mondoMap.put("version", "2022-12-01");
        map.put("MONDO", mondoMap);

        Map<String, String> hgncMap = new HashMap<>();
        hgncMap.put("name", "HUGO Gene Nomenclature Committee");
        hgncMap.put("id", "hgnc");
        hgncMap.put("url", "http://ftp.ebi.ac.uk/pub/databases/genenames/hgnc/tsv/hgnc_complete_set.txt");
        hgncMap.put("namespacePrefix", "HGNC");
        hgncMap.put("iriPrefix", "http://identifiers.org/hgnc/HGNC:");
        hgncMap.put("version", "HGNC_VERSION");
        map.put("HGNC", hgncMap);

        Map<String, String> ncitMap = new HashMap<>();
        ncitMap.put("name", "NCI Thesaurus");
        ncitMap.put("id", "ncit");
        ncitMap.put("url", "http://purl.obolibrary.org/obo/ncit.owl");
        ncitMap.put("namespacePrefix", "NCIT");
        ncitMap.put("iriPrefix", "http://purl.obolibrary.org/obo/NCIT_");
        ncitMap.put("version", "22.07d");
        map.put("NCIT", ncitMap);
        return map;
    }
}