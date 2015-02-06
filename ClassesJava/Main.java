package projecto;

/**
 * Esta classe é apenas responsável por iniciar to do o programa da biblioteca
 * Primeiramente cria uma nova biblioteca e, seguidamente, inicia uma nova janela de Login, a partir de onde <br/>
 * se desenrolará o seguimento do programa
 */
public class Main
{
    public static void main(String[] args)
    {
        Biblioteca biblioteca = new Biblioteca();

        new Login(biblioteca).setVisible(true);
    }
}
