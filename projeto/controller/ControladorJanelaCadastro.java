package projeto.controller;

import com.mysql.cj.protocol.Resultset;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import projeto.dao.EstadoDAO;
import projeto.modelo.Cidade;
import projeto.modelo.Estado;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControladorJanelaCadastro implements Initializable {

    @FXML
    private TextField nomeUsuario;

    @FXML
    private PasswordField senhaUsurio;

    @FXML
    private Button cadastrarBnt;

    @FXML
    private TextField emailUsuario;

    @FXML
    private TextField celUsuario;

    @FXML
    private ComboBox<String> estados;

    @FXML
    private ComboBox<String> cidades;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        EstadoDAO estadoDAO = new EstadoDAO();
        ObservableList<String> olEstados = FXCollections.observableArrayList();
        List<Estado> estadoAux = estadoDAO.getAll();
        for(Estado e : estadoAux){
            olEstados.add(e.getUf());
        }
        estados.setItems(olEstados);//popula esta combobox com as uf dos estados que estao presentes no bd
    }
}
