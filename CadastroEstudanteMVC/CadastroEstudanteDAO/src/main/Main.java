package main;
import controller.CadastroController;
import model.AnimalBD;
import view.JanelaCadastro;
/**
* Junta as tres camadas. Execute SEMPRE esta classe.
*
* Rodar a JanelaCadastro direto abre a janela sem Controller: os botoes
* nao reagem e nao aparece erro nenhum.
*/
public class Main {
public static void main(String[] args) {
AnimalBD bd = new AnimalBD();
JanelaCadastro view = new JanelaCadastro();
CadastroController controller = new CadastroController(bd, view);
controller.iniciarTela();
}
}