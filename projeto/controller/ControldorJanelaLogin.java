package projeto.controller;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import projeto.dao.LoginDAO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControldorJanelaLogin implements Initializable {
    @FXML
    private Button loginBnt;
    @FXML
    private Button cadastroBnt;
    @FXML
    private TextField entradaUsuario;
    @FXML
    private PasswordField entradaSenha;
    @FXML
    private Label conexao;
    @FXML
    private Label statusLogin;


    private LoginDAO loginDAO = new LoginDAO();


    public void verificarLogin() {

        try{
            if((entradaUsuario.getText().equals("admin")) && (entradaSenha.getText().equals("admin"))){
                Stage stage = (Stage) loginBnt.getScene().getWindow();
                stage.close();
                mudarJanelaAdmin();
            }else if(loginDAO.loginAceito(entradaUsuario.getText() , entradaSenha.getText())){
                Stage stage = (Stage) loginBnt.getScene().getWindow();
                stage.close();
                mudarJanelaPrincipal();
            }else{
                statusLogin.setText("Credenciais incorretas.");
                statusLogin.setVisible(true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //verifica se o login esta correto e muda para a JanelaPrincipal
    }

    private void mudarJanelaAdmin() throws IOException{
        Parent janelaAdmin = FXMLLoader.load(getClass().getResource("../view/JanelaAdmin.fxml"));
        Scene adminScene = new Scene(janelaAdmin,665,400);
        Stage adminStage = new Stage();
        adminStage.setScene(adminScene);
        adminStage.setResizable(false);
        adminStage.setTitle("Administrador");
        adminStage.show();
    }

    private void mudarJanelaPrincipal() throws IOException{
        Parent janelaPrincipal = FXMLLoader.load(getClass().getResource("../view/JanelaPrincipal.fxml"));
        Scene principalScene = new Scene(janelaPrincipal, 600, 400);
        Stage principalStage = new Stage();

        principalStage.setTitle("JavaLX");
        principalStage.setResizable(false);
        principalStage.setScene(principalScene);
        principalStage.show();
    }

    public void gerarCadastro() throws IOException {
        Parent janelaCadastro = FXMLLoader.load(getClass().getResource("../view/JanelaRegistro.fxml"));
        Scene cadastroScene = new Scene(janelaCadastro, 432, 389);
        Stage cadastroStage = new Stage();
        cadastroStage.setScene(cadastroScene);
        cadastroStage.setTitle("Cadastro");
        cadastroStage.initModality(Modality.APPLICATION_MODAL);//desabilita o stage de login enquanto esse estage estive aberto
        cadastroStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(this.loginDAO.isConectado()){
            this.conexao.setText("Conectado.");
        }else {
            this.conexao.setText("Não conectado com o DB.");
        }
    }
}
