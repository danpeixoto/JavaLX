package projeto.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import projeto.dao.CidadeDAO;
import projeto.dao.EstadoDAO;
import projeto.dao.UsuarioDAO;
import projeto.modelo.Cidade;
import projeto.modelo.Estado;
import projeto.modelo.Usuario;
import projeto.servicos.DbConnection;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;

public class ControladorModificarCadastro implements Initializable{

    @FXML
    private TextField nomeUsuario;

    @FXML
    private PasswordField senhaUsuario;

    @FXML
    private Button modificarBnt;

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
    private Usuario usuarioAux;

    public void initialize(URL location, ResourceBundle resources) {
        connection = DbConnection.getConexao();
        EstadoDAO estadoDAO = new EstadoDAO(connection);
        estadoList = estadoDAO.getAll();
        ObservableList<Estado> observableList = FXCollections.observableArrayList(estadoList);


        estados.setItems(observableList);//popula esta combobox com as uf dos estados que estao presentes no bd
    }


    public void gerarCidades(){
        CidadeDAO cidadeDAO = new CidadeDAO(connection);
        List<Cidade> cidadeList = cidadeDAO.getByEstado(estados.getSelectionModel().getSelectedItem());
        ObservableList<Cidade> observableList = FXCollections.observableArrayList(cidadeList);

        cidades.setItems(observableList);
    }


    @FXML
    public void modificarCadastro() {
        String usr = nomeUsuario.getText().trim().toUpperCase();
        String pass = senhaUsuario.getText().trim();
        String email = emailUsuario.getText().trim();
        String cel = celUsuario.getText().trim();
        Cidade cidade = cidades.getSelectionModel().getSelectedItem();
        if( (usr != null && !usr.isEmpty()) && (pass != null && pass.isEmpty()) &&(email != null && email.isEmpty())
                && (cel != null && cel.isEmpty()) &&  cidade != null ){
            UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
            usuarioAux.setNome(usr);
            usuarioAux.setSenha(pass);
            usuarioAux.setEmail(email);
            usuarioAux.setCelular(cel);
            usuarioAux.setCidade(cidade);
            usuarioDAO.update(usuarioAux);
            Stage stage = (Stage) modificarBnt.getScene().getWindow();
            stage.close();
        }else{
            erroLabel.setVisible(true);
        }
        nomeUsuario.setText("");
        senhaUsuario.setText("");
        emailUsuario.setText("");
        celUsuario.setText("");
    }

    public void inserirInformacoes(Usuario usuario){
        nomeUsuario.setText(usuario.getNome());
        senhaUsuario.setText(usuario.getSenha());
        emailUsuario.setText(usuario.getEmail());
        celUsuario.setText(usuario.getCelular());
        usuarioAux = usuario;
    }

}