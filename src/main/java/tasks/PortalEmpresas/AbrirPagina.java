package tasks.PortalEmpresas;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Open;

public class AbrirPagina implements Task {

    private final String url;

    public AbrirPagina(String url) {
        this.url = url;
    }

    public static AbrirPagina en(String url) {
        return Tasks.instrumented(AbrirPagina.class, url);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.url(url));
        System.out.println("🌐 Página abierta correctamente en: " + url);
    }
}
