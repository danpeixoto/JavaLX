package projeto.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import projeto.modelo.Produto;

import java.io.IOException;
import java.sql.Blob;


public class ControladorJPrincipal {
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
    private TableView<Produto> tableView;

    @FXML
    private TableColumn<Produto, Blob> imagem;

    @FXML
    private TableColumn<Produto, String> nome;

    @FXML
    private TableColumn<Produto, String> vendedor;

    @FXML
    private TableColumn<Produto, String>  contato;

    @FXML
    private TableColumn<Produto, String>  cidade;


    public void gerarNovoProduto() throws IOException {
        Parent janelaNovoProduto = FXMLLoader.load(getClass().getResource("../view/JanelaRegistroProd.fxml"));
        Scene novoProdScene = new Scene(janelaNovoProduto , 432,318);
        Stage novoProdStage = new Stage();
        novoProdStage.setTitle("Cadatrar Novo Produto");
        novoProdStage.setScene(novoProdScene);
        novoProdStage.initModality(Modality.APPLICATION_MODAL);
        novoProdStage.show();
    }

}
