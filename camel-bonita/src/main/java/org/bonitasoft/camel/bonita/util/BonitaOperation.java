package org.bonitasoft.camel.bonita.util;

public enum BonitaOperation {
	 startCase("startCase");

    private final String text;

    BonitaOperation(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
