package projeto.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import projeto.dao.EstadoDAO;
import projeto.dao.ProdutoDAO;
import projeto.modelo.Estado;
import projeto.modelo.Produto;
import projeto.servicos.DbConnection;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;

public class ContoladorJAdmin implements Initializable {

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
    private TableView<Produto> produtos;

    @FXML
    private TableColumn<?, ?> codigoProuto;

    @FXML
    private TableColumn<Produto, String> nomeProduto;

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
    private TableView<Estado> estados;

    @FXML
    private TableColumn<Estado, Integer> codigoEstado;

    @FXML
    private TableColumn<Estado, String> nomeEstado;

    @FXML
    private TableColumn<Estado, String> uf;

    @FXML
    private TableColumn<Estado, String> situacaoEstado;

    @FXML
    private TableView<?> tipos;

    @FXML
    private TableColumn<?, ?> codigoTipo;

    @FXML
    private TableColumn<?, ?> tipo;

    @FXML
    private TableColumn<?, ?> situacaoTipo;

    private Connection conexao;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        conexao = DbConnection.getConexao();
        EstadoDAO estadoDAO = new EstadoDAO(conexao);
        List<Estado> estadoss = estadoDAO.getAll();
        ObservableList<Estado> list = FXCollections.observableArrayList();

        for(Estado e : estadoss){
            list.add(e);
        }
        codigoEstado.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        nomeEstado.setCellValueFactory(new PropertyValueFactory<>("nome"));
        uf.setCellValueFactory(new PropertyValueFactory<>("uf"));
        situacaoEstado.setCellValueFactory(new PropertyValueFactory<>("situacao"));

        estados.setItems(list);

        ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
        List<Produto> listaProdutos = produtoDAO.getAll();
        ObservableList<Produto> list1 ;
        list1 = FXCollections.observableArrayList();

        for(Produto p  : listaProdutos){
            list1.add(p);
        }
        nomeProduto.setCellValueFactory(new PropertyValueFactory<>("nome"));
        produtos.setItems(list1);

    }
}
