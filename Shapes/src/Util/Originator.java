package Util;

import Bars.Bars;

public class Originator{
	

  private Bars bar;

    public void set(Bars bar) {
        this.bar = bar;

    }

    public BarMemento storeInMemento() {

        return new BarMemento(bar);

    }

    public Bars restoreFromMemento(BarMemento memento) {

    	bar = memento.getMemento();

        return bar;


}
}
