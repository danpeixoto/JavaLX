package projeto.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import projeto.dao.*;
import projeto.modelo.*;
import projeto.servicos.DbConnection;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ContoladorJAdmin implements Initializable {

    @FXML
    private TableView<Usuario> usuarios;

    @FXML
    private TableColumn<Usuario, Integer> codigoUsuario;

    @FXML
    private TableColumn<Usuario,String> nomeUsuario;

    @FXML
    private TableColumn<Usuario, String> email;

    @FXML
    private TableColumn<Usuario, String> celular;

    @FXML
    private TableColumn<Usuario, String> senha;

    @FXML
    private TableColumn<Usuario, Integer> cod_Cidade;

    @FXML
    private TableColumn<Usuario, String> situacaoUsuario;

    @FXML
    private TableView<Produto> produtos;

    @FXML
    private TableColumn<Produto, Integer> codigoProuto;

    @FXML
    private TableColumn<Produto, String> nomeProduto;

    @FXML
    private TableColumn<Produto, String> descProduto;

    @FXML
    private TableColumn<Produto, Double> preco;

    @FXML
    private TableColumn<Produto, String> cod_Usuario;

    @FXML
    private TableColumn<Produto, String> cod_Tipo;

    @FXML
    private TableView<Cidade> cidades;

    @FXML
    private TableColumn<Cidade, Integer> codigoCidade;

    @FXML
    private TableColumn<Cidade, String> nomeCidade;

    @FXML
    private TableColumn<Cidade, String> cep;

    @FXML
    private TableColumn<Cidade, String> cod_Estado;

    @FXML
    private TableColumn<Cidade, String> situacaoCidade;

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
    private TableView<TipoProduto> tipos;

    @FXML
    private TableColumn<TipoProduto, Integer> codigoTipo;

    @FXML
    private TableColumn<TipoProduto, String> tipo;

    @FXML
    private TableColumn<TipoProduto, String> situacaoTipo;

    private Connection conexao;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        conexao = DbConnection.getConexao();
        produtos.setId("produtos");
        estados.setId("estados");
        cidades.setId("cidades");
        usuarios.setId("usuarios");
        tipos.setId("tipos");
        atualizarTabelas();


    }

    private void inserirTipo(){

            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Novo tipo");
            dialog.setHeaderText("Insira novo tipo");
            dialog.setContentText("Tipo:");

            Optional<String> input = dialog.showAndWait();
            TipoDAO tipoDAO = new TipoDAO(conexao);
            input.ifPresent(tipo -> tipoDAO.add(new TipoProduto(tipo)) );
            atualizarTabelas();

    }

    private void atualizarTipo(TipoProduto tipoProduto){
        TextInputDialog dialog = new TextInputDialog(tipoProduto.getTipo());
        dialog.setTitle("Modificar tipo");
        dialog.setHeaderText("Modificar tipo");
        dialog.setContentText("Tipo:");

        Optional<String> input = dialog.showAndWait();
        TipoDAO tipoDAO = new TipoDAO(conexao);
        input.ifPresent(tipo -> tipoProduto.setTipo(tipo) );
        tipoDAO.update(tipoProduto);
        atualizarTabelas();
    }


    private void inserirEstado(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(estados.getScene().getWindow());
        dialog.setTitle("Inserir Estado");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../view/JanelaAdminEstado.fxml"));

        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);


        ContraladorJAdminEstado controlador = fxmlLoader.getController();

        Optional<ButtonType> opcao = dialog.showAndWait();
        if(opcao.isPresent() && opcao.get() == ButtonType.OK){
            controlador.addEstado();
        }
        dialog.close();
        atualizarTabelas();
    }

    private void atualizarEstado(Estado estado){

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(estados.getScene().getWindow());
        dialog.setTitle("Atualizar Estado");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../view/JanelaAdminEstado.fxml"));

        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);


        ContraladorJAdminEstado controlador = fxmlLoader.getController();
        controlador.inserirInformacao(estado);

        Optional<ButtonType> opcao = dialog.showAndWait();
        if(opcao.isPresent() && opcao.get() == ButtonType.OK){
            controlador.updateEstado();
        }
        dialog.close();
        atualizarTabelas();

    }

    private void inserirCidade(){
        if(estados.getItems() != null){


            ChoiceDialog<Estado> dialog = new ChoiceDialog<>();
            dialog.getItems().setAll(estados.getItems());
            dialog.setTitle("Estado da cidade");

            dialog.setContentText("Estado:");


            Optional<Estado> result = dialog.showAndWait();
            if (result.isPresent()){
                //não entra aqui se for cancelado
                dialog.close();
                Estado estadoAux = result.get();

                Dialog<ButtonType> dialog2 = new Dialog<>();
                dialog2.initOwner(cidades.getScene().getWindow());
                dialog2.setTitle("Inserir Cidade");

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../view/JanelaAdminCidade.fxml"));

                try{
                    dialog2.getDialogPane().setContent(fxmlLoader.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                dialog2.getDialogPane().getButtonTypes().add(ButtonType.OK);
                dialog2.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);


                ControladorJAdminCidade controlador = fxmlLoader.getController();


                Optional<ButtonType> opcao = dialog2.showAndWait();
                if(opcao.isPresent() && opcao.get() == ButtonType.OK){
                    controlador.addCidade(estadoAux);
                }
                dialog.close();
                atualizarTabelas();
            }


        }
    }

    private void atualizarCidade(Cidade cidade){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(cidades.getScene().getWindow());
        dialog.setTitle("Atualizar Cidade");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../view/JanelaAdminCidade.fxml"));

        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);


        ControladorJAdminCidade controlador = fxmlLoader.getController();
        controlador.inserirInformacao(cidade);

        Optional<ButtonType> opcao = dialog.showAndWait();
        if(opcao.isPresent() && opcao.get() == ButtonType.OK){
            controlador.updateCidade();
        }
        dialog.close();
        atualizarTabelas();
    }

    public void atualizarTabelas(){
        atualizarTabelaEstados();
        atualizarTabelaProdutos();
        atualizarTabelaUsuarios();
        atualizarTabelaTipos();
        atualizarTabelaCidades();
    }


    private void atualizarTabelaCidades(){
        CidadeDAO cidadeDAO = new CidadeDAO(conexao);
        List<Cidade> cidadeList = cidadeDAO.getAll();
        ObservableList<Cidade> observableList = FXCollections.observableList(cidadeList);

        codigoCidade.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        nomeCidade.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cep.setCellValueFactory(new PropertyValueFactory<>("cep"));
        situacaoCidade.setCellValueFactory(new PropertyValueFactory<>("situacao"));
        cod_Estado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        cidades.setItems(observableList);
    }

    private void atualizarTabelaTipos(){
        TipoDAO tipoDAO = new TipoDAO(conexao);
        List<TipoProduto> tipoProdutoList = tipoDAO.getAll();
        ObservableList<TipoProduto> observableList = FXCollections.observableList(tipoProdutoList);

        codigoTipo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        situacaoTipo.setCellValueFactory(new PropertyValueFactory<>("situacao"));

        tipos.setItems(observableList);

    }

    private void atualizarTabelaUsuarios(){
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
        List<Usuario> usuarioList = usuarioDAO.getAll();
        ObservableList<Usuario> usuarioObservableList = FXCollections.observableArrayList(usuarioList);

        codigoUsuario.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        nomeUsuario.setCellValueFactory(new PropertyValueFactory<>("nome"));
        celular.setCellValueFactory(new PropertyValueFactory<>("celular"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        senha.setCellValueFactory(new PropertyValueFactory<>("senha"));//só para estudo
        cod_Cidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        situacaoUsuario.setCellValueFactory(new PropertyValueFactory<>("situacao"));

        usuarios.setItems(usuarioObservableList);

    }

    private void atualizarTabelaProdutos(){
        ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
        List<Produto> listaProdutos = produtoDAO.getAll();
        ObservableList<Produto> produtoObservableList = FXCollections.observableArrayList(listaProdutos);


        nomeProduto.setCellValueFactory(new PropertyValueFactory<>("nome"));
        codigoProuto.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        descProduto.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        preco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        cod_Usuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        cod_Tipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        produtos.setItems(produtoObservableList);
    }

    private void atualizarTabelaEstados(){
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
    }





    @FXML
    void lidarCidade(KeyEvent event) {
        if((event.getCode() == KeyCode.DELETE) && (cidades.getSelectionModel().getSelectedItem() != null)){
            deletar(cidades);
        }else if(event.getCode() == KeyCode.A){
            inserirCidade();
        }else if((event.getCode() == KeyCode.E) && (cidades.getSelectionModel().getSelectedItem() != null)){
            atualizarCidade(cidades.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    void lidarEstado(KeyEvent event) {
        if((event.getCode() == KeyCode.DELETE) && (estados.getSelectionModel().getSelectedItem() != null)){
            deletar(estados);
        }else if(event.getCode() == KeyCode.A){
            inserirEstado();
        }else if((event.getCode() == KeyCode.E) && (estados.getSelectionModel().getSelectedItem() != null)){
            atualizarEstado(estados.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    void deletarProduto(KeyEvent event) {
        if((event.getCode() == KeyCode.DELETE) && (produtos.getSelectionModel().getSelectedItem() != null)){
            deletar(produtos);
        }
    }

    @FXML
    void lidarTipo(KeyEvent event) {
        if((event.getCode() == KeyCode.DELETE) && (tipos.getSelectionModel().getSelectedItem() != null)){
            deletar(tipos);
        }else if(event.getCode() == KeyCode.A){
            inserirTipo();
        }else if((event.getCode() == KeyCode.E ) && (tipos.getSelectionModel().getSelectedItem() != null)){
            atualizarTipo(tipos.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    void deletarUsuario(KeyEvent event) {
        if((event.getCode() == KeyCode.DELETE) && (usuarios.getSelectionModel().getSelectedItem() != null)){
            deletar(usuarios);
        }
    }

    private void deletar(TableView tableView) {

        Object object = tableView.getSelectionModel().getSelectedItem();


        if (object.getClass().getName() == "projeto.modelo.Usuario") {

            UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
            usuarioDAO.remove((Usuario) object);

        }else if (object.getClass().getName() == "projeto.modelo.Produto"){

            ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
            produtoDAO.remove((Produto) object);

        }else if(object.getClass().getName() == "projeto.modelo.TipoProduto"){

            TipoDAO tipoDAO = new TipoDAO(conexao);
            tipoDAO.remove((TipoProduto)object);

        }else if(object.getClass().getName() == "projeto.modelo.Cidade"){

            CidadeDAO cidadeDAO = new CidadeDAO(conexao);
            cidadeDAO.remove((Cidade) object);

        }else{

            EstadoDAO estadoDAO = new EstadoDAO(conexao);
            estadoDAO.remove((Estado) object);

        }

        atualizarTabelas();
    }

}
