package org.monarchinitiative.phenopacketlab.autoconfigure;

import org.junit.jupiter.api.Test;
import org.monarchinitiative.phenopacketlab.core.disease.DiseaseService;
import org.monarchinitiative.phenopacketlab.core.ontology.HpoService;
import org.springframework.beans.factory.BeanCreationException;

import java.nio.file.Path;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PhenopacketLabAutoConfigurationTest extends AbstractAutoConfigurationTest {

    @Test
    public void testAllPropertiesSupplied() {
        load(PhenopacketLabAutoConfiguration.class,
                "phenopacketlab.data-directory=" + TEST_DATA);

        Path datadirectory = context.getBean("phenopacketLabDataDirectory", Path.class);
        assertThat(datadirectory.getFileName(), equalTo(Path.of("data")));

        assertThat(context.getBean(PhenopacketLabDataResolver.class), is(notNullValue()));
        assertThat(context.getBean(HpoService.class), is(notNullValue()));
        assertThat(context.getBean(DiseaseService.class), is(notNullValue()));
    }

    @Test
    public void testMissingDataDirectory() {
        BeanCreationException exception = assertThrows(BeanCreationException.class, () -> load(PhenopacketLabAutoConfiguration.class));
        assertThat(exception.getMessage(), containsString("Path to PhenopacketLab data directory (`phenopacketlab.data-directory`) is not specified"));
    }

    @Test
    public void testNonExistingDataDirectory() {
        Path nonExistingDataDirectory = TEST_DATA.resolve("bla");
        BeanCreationException exception = assertThrows(BeanCreationException.class, () -> load(PhenopacketLabAutoConfiguration.class, "phenopacketlab.data-directory=" + nonExistingDataDirectory));
        assertThat(exception.getMessage(), containsString(String.format("Path to PhenopacketLab data directory '%s' does not point to an existing directory", nonExistingDataDirectory)));
    }

    @Test
    public void testMissingResourceFile() {
        BeanCreationException exception = assertThrows(BeanCreationException.class, () -> load(PhenopacketLabAutoConfiguration.class, "phenopacketlab.data-directory=" + TEST_DATA.getParent()));
        assertThat(exception.getMessage(), containsString("The file `hp.json` is missing in the data directory"));
    }
}