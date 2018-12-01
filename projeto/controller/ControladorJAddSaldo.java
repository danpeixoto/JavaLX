package projeto.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projeto.dao.UsuarioDAO;
import projeto.modelo.Usuario;
import projeto.servicos.DbConnection;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class ControladorJAddSaldo implements Initializable {

    @FXML
    private Label erroLabel;

    @FXML
    private TextField valorField;

    @FXML
    private Button addSaldoBnt;
    private Connection connection;
    private Usuario usuarioAux;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connection = DbConnection.getConexao();
    }

    @FXML
    public void gerenciarAddSaldo(){
        float valorPretendido = 0.0f;

        if(!valorField.getText().isEmpty()){
            if(verificarValor(valorField.getText())){

                valorPretendido =Float.parseFloat(valorField.getText());
                if( valorPretendido >= 0 ){
                    UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
                    usuarioDAO.creditarSaldoUsuario(usuarioAux.getCodigo() , valorPretendido);
                    usuarioAux.setSaldo(usuarioAux.getSaldo()+valorPretendido);//gambiarra que adiciona o saldo ao usuario
                    Stage stage = (Stage)addSaldoBnt.getScene().getWindow();
                    stage.close();


                }else {
                    erroLabel.setText("Valor negativo.");
                    erroLabel.setVisible(true);
                }

            }else{
                erroLabel.setText("Valor inválido.");
                erroLabel.setVisible(true);
            }
        }else{
            erroLabel.setText("Valor não preenchido.");
            erroLabel.setVisible(true);
        }

    }

    private boolean verificarValor(String valor){
        try {
            Float.parseFloat(valor);
            return true;
        }
        catch (NumberFormatException ex) {
            return false;
        }
    }

    public void setUsuario(Usuario usuario){
        usuarioAux = usuario;
    }
}
