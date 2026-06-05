package interactions;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.Step;

public class IngresarTexto implements Interaction {

    private final Target target;
    private final String valor;
    private final String descripcion;

    public IngresarTexto(Target target, String valor, String descripcion) {
        this.target = target;
        this.valor = valor;
        this.descripcion = descripcion;
    }

    public static IngresarTexto con(String valor, String descripcion, Target target) {
        return instrumented(IngresarTexto.class, target, valor, descripcion);
    }

    @Override
    @Step("Ingresa #descripcion")
    public <T extends Actor> void performAs(T actor) {
        WebElementFacade element = target.resolveFor(actor);
        element.clear();
        element.sendKeys(valor);
    }
}