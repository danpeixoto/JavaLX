package projeto.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import projeto.dao.LoginDAO;
import projeto.dao.ProdutoDAO;
import projeto.dao.TipoDAO;
import projeto.dao.UsuarioDAO;
import projeto.modelo.Produto;
import projeto.modelo.TipoProduto;
import projeto.modelo.Usuario;
import projeto.servicos.DbConnection;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;

public class ControladorJRegistroProd implements Initializable {

    @FXML
    private TextField nomeProduto;

    @FXML
    private Button cadastrarBnt;

    @FXML
    private TextField precoProduto;

    @FXML
    private TextArea descProduto;

    @FXML
    private Button imagemPicker;//não sera utilizado por equanto

    @FXML
    private Label pathImg;//não sera utilizado por equanto

    @FXML
    private ComboBox<TipoProduto> tiposBox;

    @FXML
    private Label erroLabel;

    private static Usuario usuario;


    private Connection connection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connection = DbConnection.getConexao();
        usuario = ControladorJPrincipal.usuarioAtual;
        carregarTipos();
    }

    public void carregarTipos() {
        TipoDAO tipoDAO = new TipoDAO(connection);
        List<TipoProduto> tipoProdutoList = tipoDAO.getAll();
        ObservableList<TipoProduto> observableList = FXCollections.observableList(tipoProdutoList);
        tiposBox.setItems(observableList);
    }

    public void gerarNovoPodruto() {
        TipoProduto tipoSelecionado = tiposBox.getSelectionModel().getSelectedItem();
        if ((nomeProduto != null) && (precoProduto != null) && (descProduto != null) && (tipoSelecionado != null)) {
            Produto novoProduto = new Produto(nomeProduto.getText().toUpperCase(), descProduto.getText().toUpperCase(),
                    precoProduto.getText().replaceAll(",", "."), usuario,
                    tiposBox.getSelectionModel().getSelectedItem());

            ProdutoDAO produtoDAO = new ProdutoDAO(connection);
            produtoDAO.add(novoProduto);
            Stage stage = (Stage)cadastrarBnt.getScene().getWindow();
            stage.close();
        } else {
            erroLabel.setVisible(true);
        }


    }
}
