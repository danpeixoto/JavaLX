package projeto.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import projeto.dao.CidadeDAO;
import projeto.dao.EstadoDAO;
import projeto.dao.UsuarioDAO;
import projeto.exception.EmailRegistradoException;
import projeto.modelo.Cidade;
import projeto.modelo.Estado;
import projeto.modelo.Usuario;
import projeto.servicos.DbConnection;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;

public class ControladorJanelaCadastro implements Initializable {

    @FXML
    private TextField nomeUsuario;

    @FXML
    private PasswordField senhaUsuario;

    @FXML
    private Button cadastrarBnt;

    @FXML
    private TextField emailUsuario;

    @FXML
    private TextField celUsuario;

    @FXML
    private ComboBox<Estado> estados;

    @FXML
    private ComboBox<Cidade> cidades;

    @FXML
    private Label erroLabel;

    private Connection connection;
    private List<Estado> estadoList;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connection = DbConnection.getConexao();
        EstadoDAO estadoDAO = new EstadoDAO(connection);
        ObservableList<Estado> observableList = FXCollections.observableArrayList();
        estadoList = estadoDAO.getAll();
        for(Estado e : estadoList){
            observableList.add(e);
        }
        estados.setItems(observableList);//popula esta combobox com as uf dos estados que estao presentes no bd


    }


    public void gerarCidades(){
        CidadeDAO cidadeDAO = new CidadeDAO(connection);
        List<Cidade> cidadeList = cidadeDAO.getByEstado(estados.getSelectionModel().getSelectedItem());
        ObservableList<Cidade> observableList = FXCollections.observableArrayList();

        for (Cidade c : cidadeList){
            observableList.add(c);
        }
        cidades.setItems(observableList);
    }

    public void gerarCadastro(){
        String usr = nomeUsuario.getText().trim().toUpperCase();
        String pass = senhaUsuario.getText().trim();
        String email = emailUsuario.getText().trim();
        String cel = celUsuario.getText().trim();
        Cidade cidade = cidades.getSelectionModel().getSelectedItem();

        if( ((usr != null) && !usr.isEmpty()) && ((pass != null) && !pass.isEmpty()) && (email != null && !email.isEmpty() ) &&
                ((cel != null) &&  !cel.isEmpty() ) &&  cidade != null ){
            UsuarioDAO usuarioDAO = new UsuarioDAO(connection);


            try {
                /*Antes de cadastras verifica se o email ja foi usado em outra conta
                 * se sim , o método add retorna falso e isso é trado mostrando para o usuário*/
                usuarioDAO.add(new Usuario(usr,email,cel,pass,cidade , 0 , (float) 0.0));
                Stage stage = (Stage) cadastrarBnt.getScene().getWindow();
                stage.close();

            } catch (EmailRegistradoException e) {
                e.printStackTrace();
                erroLabel.setText("Email já registrado.");
                erroLabel.setVisible(true);
            }


        }else{
            erroLabel.setText("Erro nos dados do Cadastro");
            erroLabel.setVisible(true);
        }

        nomeUsuario.setText("");
        senhaUsuario.setText("");
        emailUsuario.setText("");
        celUsuario.setText("");

    }
}
