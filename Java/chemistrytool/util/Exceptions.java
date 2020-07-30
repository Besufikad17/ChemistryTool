package chemistrytool.util;

public class Exceptions extends Exception {
    public static class UnitlessInputException extends Exception{
        public UnitlessInputException(String string){
            super(string);
        }
    }

    public static class InvalidInputException extends Exception{
        public InvalidInputException(String string){
            super(string);
        }
    }

    public static class ElementNotFoundException extends Exception{
        public ElementNotFoundException(String string){
            super(string);
        }
    }

    public static class MissingInputException extends Exception{
        public MissingInputException(String string){
            super(string);
        }
    }

    public static class ElementNotFoundInCompoundException extends Exception{
        public ElementNotFoundInCompoundException(String string){
            super(string);
        }
    }
}
