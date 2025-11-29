import cr.ac.ucenfotec.tl.Controller;
import cr.ac.ucenfotec.ui.Menu;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        Menu menu = new Menu(controller);
        menu.iniciar();
    }
}