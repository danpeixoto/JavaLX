package projeto.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import projeto.dao.LoginDAO;
import projeto.dao.ProdutoDAO;
import projeto.dao.UsuarioDAO;
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
    private Button modUserBnt;

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
    static Usuario usuarioAtual;//permite qualquer classe do pacote utilizar o usuario atual
    private boolean mostrandoProdutosDoUsuarioAtual;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connection = DbConnection.getConexao();
        mostrandoProdutosDoUsuarioAtual = false;
        atualizarTabelaProdutos();
        tabelaProdutos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tabelaProdutos.getSelectionModel().selectFirst();
        //inicializar a comboBox de tipos de busca , Nome Produto , Vendedor , Tipo Produto

        ObservableList<String> tiposDeBusca = FXCollections.observableArrayList("Vendedor","Nome","Tipo");
        buscaBox.setItems(tiposDeBusca);

        UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
        usuarioAtual = usuarioDAO.getUsuarioByEmailSenha(LoginDAO.getEmail(), LoginDAO.getSenha());
    }


    public void mostrarProdutosUsuarioAtual(){
        ProdutoDAO produtoDAO = new ProdutoDAO(connection);
        ObservableList<Produto> produtosVendedor =
                FXCollections.observableArrayList(produtoDAO.getByUsuarios(usuarioAtual.getNome().toUpperCase()));
        tabelaProdutos.setItems(produtosVendedor);
        tabelaProdutos.getSelectionModel().selectFirst();
        mostrandoProdutosDoUsuarioAtual = true;

    }


    public void atualizarTabelaProdutos(){
        //criar método lidar com clique
        mostrandoProdutosDoUsuarioAtual = false;
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
        novoProdStage.showAndWait();//show and wait ,serve para impedir que o codigo abaixo seja executado antes da janela fechar
        atualizarTabelaProdutos();//atualiza a tabela com o novo produto
    }


    public void buscarProdutos(){

       if(buscaField.getText() != null) {
           if (buscaBox.getSelectionModel().getSelectedItem() == "Nome") {
               ProdutoDAO produtoDAO = new ProdutoDAO(connection);
               ObservableList<Produto> produtosNome =
                       FXCollections.observableArrayList(produtoDAO.getByNome(buscaField.getText().toUpperCase()));
               tabelaProdutos.setItems(produtosNome);
               tabelaProdutos.getSelectionModel().selectFirst();
           }else if(buscaBox.getSelectionModel().getSelectedItem() == "Vendedor"){
               ProdutoDAO produtoDAO = new ProdutoDAO(connection);
               ObservableList<Produto> produtosVendedor =
                       FXCollections.observableArrayList(produtoDAO.getByUsuarios(buscaField.getText().toUpperCase()));
               tabelaProdutos.setItems(produtosVendedor);
               tabelaProdutos.getSelectionModel().selectFirst();
           }else if(buscaBox.getSelectionModel().getSelectedItem() == "Tipo"){
               ProdutoDAO produtoDAO = new ProdutoDAO(connection);
               ObservableList<Produto> produtosTipo =
                       FXCollections.observableArrayList(produtoDAO.getByTipo(buscaField.getText().toUpperCase()));
               tabelaProdutos.setItems(produtosTipo);
               tabelaProdutos.getSelectionModel().selectFirst();
           }

            mostrandoProdutosDoUsuarioAtual=false;
       }
    }





    public void lidarComClique(MouseEvent mouseEvent){
        Produto produto = tabelaProdutos.getSelectionModel().getSelectedItem();

        if(produto != null){
            if(mouseEvent.getButton() == MouseButton.PRIMARY){
                mostrarProdutoSelecionado();
            }else if((mouseEvent.getButton() == MouseButton.SECONDARY) && (mostrandoProdutosDoUsuarioAtual == true)){
                ContextMenu contextMenu = new ContextMenu();
                MenuItem deletarMenu = new MenuItem("Deletar");
                deletarMenu.setOnAction(f->deletarProduto(produto));
                MenuItem modificarMenu = new MenuItem("Modificar");
                modificarMenu.setOnAction(e->modificarProduto(produto));

                contextMenu.getItems().setAll(deletarMenu , modificarMenu);
                contextMenu.show(tabelaProdutos.getScene().getWindow());//define o contextMenu quando esta mostrando
                //os produtos do usuario atual , se usasse "tabelaProdutos.setContextMenu(contextMenu);"
                //iria bugar porque iria mostrar o menu independente do lugar , mas usando o .show só mostra ele
                //e não gera o bug
            }


        }

    }

    public void deletarProduto(Produto p){
        ProdutoDAO produtoDAO = new ProdutoDAO(connection);
        produtoDAO.remove(p);
        atualizarTabelaProdutos();
    }


    public void modificarProduto(Produto p){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../view/JanelaModificarProduto.fxml"));
            Parent parent = fxmlLoader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();

            ControladorJModProduto cmd = fxmlLoader.getController();
            cmd.adicionarInformacoes(p);

            stage.setTitle("Modificar produto.");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.showAndWait();

            atualizarTabelaProdutos();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void mostrarProdutoSelecionado()  {

        try {
            Produto produto = tabelaProdutos.getSelectionModel().getSelectedItem();

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../view/JanelaProduto.fxml"));//define o caminho para o arquivo fxml

            Parent parent = fxmlLoader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Produto Selecionado");

            ControladorJanelaProduto controladorJanelaProduto = fxmlLoader.getController();
            controladorJanelaProduto.mostrarProduto(produto);

            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @FXML
    public void gerarJanelaModUser() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../view/JanelaModificarUsuario.fxml"));
            Parent janelaModUser = fxmlLoader.load();
            Scene novoProdScene = new Scene(janelaModUser);
            Stage novoProdStage = new Stage();
            novoProdStage.setTitle("Modificar Usuario");
            ControladorModificarCadastro controlador = fxmlLoader.getController();
            controlador.inserirInformacoes(usuarioAtual);
            novoProdStage.setScene(novoProdScene);
            novoProdStage.initModality(Modality.APPLICATION_MODAL);
            novoProdStage.showAndWait();//show and wait ,serve para impedir que o codigo abaixo seja executado antes da janela fechar
            atualizarTabelaProdutos();//atualiza a tabela com o novo produto
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
