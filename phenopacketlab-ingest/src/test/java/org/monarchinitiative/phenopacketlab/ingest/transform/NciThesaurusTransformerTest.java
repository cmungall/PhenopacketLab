package org.monarchinitiative.phenopacketlab.ingest.transform;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.monarchinitiative.phenopacketlab.ingest.TestBase;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class NciThesaurusTransformerTest {


    @TempDir
    public Path tempDir;

    @Test
    public void transform() throws Exception  {
        /*
        Test parsing using small local NCIT flat file. Note that we must supply `url` and `version` as the fields
        are not present in the file.
         */
        Path zip = TestBase.TEST_BASE.resolve("transform").resolve("Thesaurus.50lines.zip");
        Path destination = tempDir.resolve("NCIT.tsv.gz");
        String url = "https://evs.nci.nih.gov/Thesaurus.FLAT.zip";
        String version = "04.D";
        assertThat(Files.isRegularFile(destination), equalTo(false));

        NciThesaurusTransformer.transform(zip, destination, url, version);

        assertThat(Files.isRegularFile(destination), equalTo(true));
        List<String> lines = TestBase.readLines(destination);

        assertThat(lines, hasSize(52));
        assertThat(lines.get(0), equalTo("# url=https://evs.nci.nih.gov/Thesaurus.FLAT.zip;version=04.D"));
        assertThat(lines.get(1), equalTo("code\tname\tdefinition\tsynonyms\tsemantic_types")); // header
        assertThat(lines.get(2), equalTo("C100000\tPercutaneous Coronary Intervention for ST Elevation Myocardial Infarction-Stable-Over 12 Hours From Symptom Onset\tA percutaneous coronary intervention is necessary for a myocardial infarction that presents with ST segment elevation and the subject does not have recurrent or persistent symptoms, symptoms of heart failure or ventricular arrhythmia. The presentation is past twelve hours since onset of symptoms. (ACC)\tPERCUTANEOUS CORONARY INTERVENTION (PCI) FOR ST ELEVATION MYOCARDIAL INFARCTION (STEMI) (STABLE, >12 HRS FROM SYMPTOM ONSET)\tTherapeutic or Preventive Procedure"));
        assertThat(lines.get(45), equalTo("C10003\tMethotrexate/Teniposide\t\tMTX/VM-26\tTherapeutic or Preventive Procedure"));
    }

}