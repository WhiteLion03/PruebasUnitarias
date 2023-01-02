package data;

import Exceptions.NotCorrectFormatException;

public class Password {
    private final String password;

    public Password(String passw) {
        if (passw == null) throw new NullPointerException("La contraseña no puede ser null");
        this.password = passw;
    }
}
