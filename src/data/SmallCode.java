package data;

public class SmallCode {
    private final String code;

    public SmallCode(String code) throws NotCorrectCodeException {
        if(verifyCode(code)){
            this.code = code;
        }else{
            throw new NotCorrectCodeException("El formato del SmallCode no es correcto");
        }
    }

    private boolean verifyCode(String code){
        if(code.length() != 3 || !isNumeric(code)){;
            return false;
        }
        return true;
    }

    private boolean isNumeric(String code){
        try{
            Integer.parseInt(code);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
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
