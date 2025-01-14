package org.monarchinitiative.phenopacketlab.restapi.controller;

import org.monarchinitiative.phenopacketlab.core.model.IdentifiedConceptResource;
import org.monarchinitiative.phenopacketlab.core.model.Resource;

import java.util.List;

public class TestData {

    public final static String CORRECT_PHENOPACKET =
            """
                    {
                      "id": "arbitrary.id",
                      "subject": {
                        "id": "proband A",
                        "timeAtLastEncounter": {
                          "age": {
                            "iso8601duration": "P38Y"
                          }
                        },
                        "sex": "MALE"
                      },
                      "biosamples": [{
                        "id": "biosample 1",
                        "individualId": "proband A",
                        "sampledTissue": {
                          "id": "NCIT:C12389",
                          "label": "Esophagus"
                        },
                        "timeOfCollection": {
                          "age": {
                            "iso8601duration": "P49Y2M"
                          }
                        },
                        "tumorProgression": {
                          "id": "NCIT:C4813",
                          "label": "Recurrent Malignant Neoplasm"
                        },
                        "procedure": {
                          "code": {
                            "id": "NCIT:C15189",
                            "label": "Biopsy"
                          }
                        }
                      }, {
                        "id": "biosample 2",
                        "individualId": "proband A",
                        "sampledTissue": {
                          "id": "NCIT:C139196",
                          "label": "Esophageal Lymph Node"
                        },
                        "timeOfCollection": {
                          "age": {
                            "iso8601duration": "P48Y3M"
                          }
                        },
                        "histologicalDiagnosis": {
                          "id": "NCIT:C4024",
                          "label": "Esophageal Squamous Cell Carcinoma"
                        },
                        "tumorProgression": {
                          "id": "NCIT:C84509",
                          "label": "Primary Malignant Neoplasm"
                        },
                        "diagnosticMarkers": [{
                          "id": "NCIT:C131711",
                          "label": "Human Papillomavirus-18 Positive"
                        }],
                        "procedure": {
                          "code": {
                            "id": "NCIT:C15189",
                            "label": "Biopsy"
                          }
                        }
                      }, {
                        "id": "biosample 3",
                        "individualId": "proband A",
                        "sampledTissue": {
                          "id": "NCIT:C12468",
                          "label": "Lung"
                        },
                        "timeOfCollection": {
                          "age": {
                            "iso8601duration": "P50Y7M"
                          }
                        },
                        "tumorProgression": {
                          "id": "NCIT:C3261",
                          "label": "Metastatic Neoplasm"
                        },
                        "procedure": {
                          "code": {
                            "id": "NCIT:C15189",
                            "label": "Biopsy"
                          }
                        }
                      }],
                      "diseases": [{
                        "term": {
                          "id": "NCIT:C4024",
                          "label": "Esophageal Squamous Cell Carcinoma"
                        },
                        "clinicalTnmFinding": [{
                          "id": "NCIT:C48724",
                          "label": "T2 Stage Finding"
                        }, {
                          "id": "NCIT:C48706",
                          "label": "N1 Stage Finding"
                        }, {
                          "id": "NCIT:C48699",
                          "label": "M0 Stage Finding"
                        }]
                      }],
                      "metaData": {
                        "created": "2021-05-14T10:35:00Z",
                        "createdBy": "anonymous biocurator",
                        "resources": [{
                          "id": "ncit",
                          "name": "NCI Thesaurus",
                          "url": "http://purl.obolibrary.org/obo/ncit.owl",
                          "version": "21.05d",
                          "namespacePrefix": "NCIT",
                          "iriPrefix": "http://purl.obolibrary.org/obo/NCIT_"
                        }, {
                          "id": "efo",
                          "name": "Experimental Factor Ontology",
                          "url": "http://www.ebi.ac.uk/efo/efo.owl",
                          "version": "3.34.0",
                          "namespacePrefix": "EFO",
                          "iriPrefix": "http://purl.obolibrary.org/obo/EFO_"
                        }, {
                          "id": "uberon",
                          "name": "Uber-anatomy ontology",
                          "url": "http://purl.obolibrary.org/obo/uberon.owl",
                          "version": "2021-07-27",
                          "namespacePrefix": "UBERON",
                          "iriPrefix": "http://purl.obolibrary.org/obo/UBERON_"
                        }, {
                          "id": "ncbitaxon",
                          "name": "NCBI organismal classification",
                          "url": "http://purl.obolibrary.org/obo/ncbitaxon.owl",
                          "version": "2021-06-10",
                          "namespacePrefix": "NCBITaxon",
                          "iriPrefix": "http://purl.obolibrary.org/obo/NCBITaxon_"
                        }],
                        "phenopacketSchemaVersion": "2.0"
                      }
                    }""";
    public final static String INCORRECT_PHENOPACKET =
            """
                    {
                      "id": "id-C",
                      "subject": {
                        "id": "proband C",
                        "timeAtLastEncounter": {
                          "age": {
                            "iso8601duration": "P27Y"
                          }
                        },
                        "sex": "FEMALE"
                      },
                      "diseases": [{
                        "term": {
                          "id": "OMIM:154700 ",
                          "label": "Marfan syndrome"
                        }
                      }],
                      "medicalActions": [{
                        "treatment": {
                          "agent": {
                            "id": "DrugCentral:1610",
                            "label": "losartan"
                          },
                          "routeOfAdministration": {
                            "id": "NCIT:C38288",
                            "label": "Oral Route of Administration"
                          },
                          "doseIntervals": [{
                            "quantity": {
                              "unit": {
                                "id": "UO:0000022",
                                "label": "milligram"
                              },
                              "value": 30.0
                            },
                            "scheduleFrequency": {
                              "id": "NCIT:C64496",
                              "label": "Twice Daily"
                            },
                            "interval": {
                              "start": "2019-03-20T00:00:00Z",
                              "end": "2021-03-20T00:00:00Z"
                            }
                          }]
                        }
                      }],
                      "metaData": {
                        "created": "2021-05-14T10:35:00Z",
                        "createdBy": "anonymous biocurator",
                        "resources": [{
                          "id": "hp",
                          "name": "human phenotype ontology",
                          "url": "http://purl.obolibrary.org/obo/hp.owl",
                          "version": "2021-08-02",
                          "namespacePrefix": "HP",
                          "iriPrefix": "http://purl.obolibrary.org/obo/HP_"
                        }],
                        "phenopacketSchemaVersion": "2.0"
                      }
                    }""";

    public static IdentifiedConceptResource createResource(String prefix) {
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
}
