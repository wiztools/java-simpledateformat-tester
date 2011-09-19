package org.wiztools.testing;

import java.util.Locale;

/**
 *
 * @author subwiz
 */
class MyLocale implements Comparable<MyLocale> {
    
    private final Locale locale;
    private final String name;

    MyLocale(Locale locale) {
        this.locale = locale;
        name = locale.toString();
    }
    
    Locale getLocale() {
        return locale;
    }

    public int compareTo(MyLocale o) {
        return this.name.compareTo(o.name);
    }
    
}
