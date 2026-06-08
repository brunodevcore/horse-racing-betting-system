package malapata.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {

    public enum Evento {
        VALOR_ACTUALIZADO
    }

    private List<Observador> observadores;

    public Observable() {
        this.observadores = new ArrayList<>();
    }

    public void subscribir(Observador observador) {
        this.observadores.add(observador);
    }

    public void desubscribir(Observador observador) {
        this.observadores.remove(observador);
    }

    public void notificar(Object evento) {
        for (Observador observador : observadores) {
            observador.actualizar(this, evento);
        }
    }

}
