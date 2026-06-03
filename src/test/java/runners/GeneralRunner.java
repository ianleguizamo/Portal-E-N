package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.runner.RunWith;
import utils.BeforeSuite;

import java.io.IOException;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDefinitions", "utils", "hooks"},
        snippets = SnippetType.CAMELCASE,
            tags = "@EyN_PORT_InicicioSesion"
)

@RunWith(CustomRunner.class)
public class GeneralRunner {
    @BeforeSuite
    public static void setUp() throws InvalidFormatException, IOException {
        System.out.println(" Iniciando configuración para pruebas Portal E&N...");
        System.out.println(" Configuration Portal E&N completada");
    }
}