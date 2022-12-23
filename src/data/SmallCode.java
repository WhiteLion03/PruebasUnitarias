package data;

public class SmallCode {
    private final String code;

    public SmallCode(String code) throws NotCorrectFormatException {
        if (code == null) throw new NullPointerException();
        if(verifyCode(code)){
            this.code = code;
        }else{
            throw new NotCorrectFormatException("El formato del SmallCode no es correcto");
        }
    }

    private boolean verifyCode(String code){
        return code.length() == 3 && isNumeric(code);
    }

    private boolean isNumeric(String code){
        for (int i = 0; i < 3; i++) {
            if (!Character.isDigit(code.charAt(i))) return false;
        }
        return true;
    }

    public String getCode(){
        return code;
    }

    @Override
    public boolean equals (Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmallCode smallCode = (SmallCode) o;
        return code.equals(smallCode.code);
    }

    @Override
    public int hashCode () { return code.hashCode(); }
}
