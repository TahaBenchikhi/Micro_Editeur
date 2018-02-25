package Util;

import java.io.Serializable;

import Bars.Bars;

public class BarMemento implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Bars p;

	public BarMemento(Bars r) {
		p = (Bars) r.clone();
	}

	public Bars getMemento() {

		return p;
	}
}
