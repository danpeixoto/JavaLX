package projeto.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import projeto.dao.ProdutoDAO;
import projeto.modelo.Produto;
import projeto.modelo.Usuario;
import projeto.servicos.DbConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;


public class ControladorJPrincipal implements Initializable{
    @FXML
    private TextField buscaField;

    @FXML
    private ComboBox<String> buscaBox;

    @FXML
    private Button buscaBnt;

    @FXML
    private Button novoProdutoBnt;

    @FXML
    private Button meusProdBnt;

    @FXML
    private TableView<Produto> tabelaProdutos;

    @FXML
    private TableColumn<Produto, Blob> imagem;

    @FXML
    private TableColumn<Produto, String> nomeCol;

    @FXML
    private TableColumn<Produto, String> vendedorCol;

    @FXML
    private TableColumn<Produto, String>  tipoCol;


    private Connection connection;
    private ObservableList<Produto> observableList;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connection = DbConnection.getConexao();
        atualizarTabelaProdutos();
        tabelaProdutos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tabelaProdutos.getSelectionModel().selectFirst();
        //inicializar a comboBox de tipos de busca , Nome Produto , Vendedor , Tipo Pro
    }


    public void atualizarTabelaProdutos(){
        ProdutoDAO produtoDAO = new ProdutoDAO(connection);
        List<Produto> listaProdutos = produtoDAO.getAll();
        observableList = FXCollections.observableArrayList();

        for(Produto p  : listaProdutos){
            observableList.add(p);
        }
        nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
        vendedorCol.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        tipoCol.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        tabelaProdutos.setItems(observableList);
    }

    public void atualizar(KeyEvent keyEvent){
        if(keyEvent.getCode() == KeyCode.F5){
            atualizarTabelaProdutos();
        }
    }
    public void gerarJanelaNovoProduto(ActionEvent e) throws IOException {
        Parent janelaNovoProduto = FXMLLoader.load(getClass().getResource("../view/JanelaRegistroProd.fxml"));
        Scene novoProdScene = new Scene(janelaNovoProduto , 432,318);
        Stage novoProdStage = new Stage();
        novoProdStage.setTitle("Cadatrar Novo Produto");
        novoProdStage.setScene(novoProdScene);
        novoProdStage.initModality(Modality.APPLICATION_MODAL);
        novoProdStage.show();
    }


}
