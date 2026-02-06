package de.stoll.nicolas.bgraph.person;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;

@AnalyzeClasses(packages = "de.stoll.nicolas.bgraph.person", importOptions = {ImportOption.DoNotIncludeTests.class})
public class PersonModuleArchUnitTest {

    @ArchTest
    public static final ArchRule onionArchitecture = Architectures.onionArchitecture()
            .domainModels("..person.application.domain.model..")
            .domainServices("..person.application.domain.service..")
            .applicationServices("..person.application..")
            .adapter("in.web", "..person.adapter.in.web..")
            .adapter("out.db", "..person.adapter.out.db..")
            .adapter("out.event", "..person.adapter.out.event..")
            .adapter("out.web", "..person.adapter.out.web..");

}
