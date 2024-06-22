package aed;

public class ExceptionUFPS extends Exception
{

    public ExceptionUFPS(String mensaje)
    {
        super(mensaje);
    }

    public String getMensaje(){
        return (super.getMessage());
    }
}
