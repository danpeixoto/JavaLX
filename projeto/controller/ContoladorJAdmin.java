package projeto.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ContoladorJAdmin {

    @FXML
    private TableView<?> usuarios;

    @FXML
    private TableColumn<?, ?> codigoUsuario;

    @FXML
    private TableColumn<?, ?> nomeUsuario;

    @FXML
    private TableColumn<?, ?> email;

    @FXML
    private TableColumn<?, ?> celular;

    @FXML
    private TableColumn<?, ?> senha;

    @FXML
    private TableColumn<?, ?> cod_Cidade;

    @FXML
    private TableColumn<?, ?> situacaoUsuario;

    @FXML
    private TableView<?> produtos;

    @FXML
    private TableColumn<?, ?> codigoProuto;

    @FXML
    private TableColumn<?, ?> nomeProduto;

    @FXML
    private TableColumn<?, ?> descProduto;

    @FXML
    private TableColumn<?, ?> preco;

    @FXML
    private TableColumn<?, ?> cod_Usuario;

    @FXML
    private TableColumn<?, ?> cod_Tipo;

    @FXML
    private TableView<?> cidades;

    @FXML
    private TableColumn<?, ?> codigoCidade;

    @FXML
    private TableColumn<?, ?> nomeCidade;

    @FXML
    private TableColumn<?, ?> cep;

    @FXML
    private TableColumn<?, ?> cod_Estado;

    @FXML
    private TableColumn<?, ?> situacaoCidade;

    @FXML
    private TableView<?> estados;

    @FXML
    private TableColumn<?, ?> codigoEstado;

    @FXML
    private TableColumn<?, ?> nomeEstado;

    @FXML
    private TableColumn<?, ?> uf;

    @FXML
    private TableColumn<?, ?> situacaoEstado;

    @FXML
    private TableView<?> tipos;

    @FXML
    private TableColumn<?, ?> codigoTipo;

    @FXML
    private TableColumn<?, ?> tipo;

    @FXML
    private TableColumn<?, ?> situacaoTipo;
}
