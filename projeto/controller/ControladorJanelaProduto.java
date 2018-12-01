package projeto.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import projeto.dao.ProdutoDAO;
import projeto.dao.UsuarioDAO;
import projeto.modelo.Produto;
import projeto.modelo.Usuario;
import projeto.servicos.DbConnection;

public class ControladorJanelaProduto {

    @FXML
    private Label nomeProdLabel;

    @FXML
    private Label precoLabel;

    @FXML
    private Label vendedorLabel;

    @FXML
    private Label erroLabel;

    @FXML
    private Label contatoLabel;

    @FXML
    private TextArea descField;

    @FXML
    private Label notaLabel;
    @FXML
    private Label avaliadoLabel;
    @FXML
    private Button comprarBotao;

    @FXML
    private Button likeButton;

    @FXML
    private Button dislikeButton;

    private Boolean vendedorAvaliado = false;
    private Boolean avaliadoLike = false ;
    private Boolean avaliadoDislike = false ;


    private Usuario usuarioAtual;
    private Usuario vendedorProduto;
    private Produto produtoAtual;


        public void mostrarProduto(Produto produto){

            nomeProdLabel.setText(produto.getNome());
            precoLabel.setText(String.valueOf(produto.getPreco()));
            vendedorLabel.setText(produto.getUsuario().getNome());
            contatoLabel.setText(produto.getUsuario().getCelular());
            descField.setText(produto.getDescricao());
            notaLabel.setText(String.valueOf(produto.getUsuario().getNota()));
            vendedorProduto = produto.getUsuario();
            produtoAtual = produto;

            if(produto.getQuantidade()<=0){
                desabilitarBotoes();
                erroLabel.setVisible(true);
                erroLabel.setText("Produto indisponível.");
            }

        }

        private void desabilitarBotoes(){
            likeButton.setDisable(true);
            dislikeButton.setDisable(true);
            comprarBotao.setDisable(true);
        }

        @FXML
        public void gerenciarCompra(){
            if((usuarioAtual.getSaldo()-produtoAtual.getPreco())>=0){
                UsuarioDAO usuarioDAO = new UsuarioDAO(DbConnection.getConexao());
                usuarioDAO.creditarSaldoUsuario(vendedorProduto, (float) produtoAtual.getPreco());
                usuarioDAO.debitarSaldoUsuario(usuarioAtual , (float) produtoAtual.getPreco());


                usuarioAtual.setSaldo((float) (usuarioAtual.getSaldo()-produtoAtual.getPreco()));
                produtoAtual.diminuirQuantidade();

                ProdutoDAO produtoDAO = new ProdutoDAO(DbConnection.getConexao());
                produtoDAO.update(produtoAtual);

                Stage stage = (Stage) comprarBotao.getScene().getWindow();
                stage.close();

            }else{
                erroLabel.setText("Saldo indisponível.");
                erroLabel.setVisible(true);
            }
        }


        public void setUsuarioAtual(Usuario usuarioAtual){
            this.usuarioAtual = usuarioAtual;

            if(usuarioAtual.getCodigo() == vendedorProduto.getCodigo()){
                desabilitarBotoes();
            }

        }


        @FXML
        public void gerenciarLike(){
            if((!vendedorAvaliado && !avaliadoLike) ||(vendedorAvaliado && avaliadoDislike) ){
                UsuarioDAO usuarioDAO = new UsuarioDAO(DbConnection.getConexao());
                usuarioDAO.like(vendedorProduto);
                vendedorAvaliado = true;
                avaliadoLike = true;
                avaliadoDislike = false;
                avaliadoLabel.setText("Vendedor avalido com like");
                avaliadoLabel.setVisible(true);
            }else{
                avaliadoLabel.setText("Vendedor já avalido com like");
                avaliadoLabel.setVisible(true);
            }
        }
        @FXML
        public void gerenciarDislike(){
            if((!vendedorAvaliado && !avaliadoDislike) ||(vendedorAvaliado && avaliadoLike) ){
                UsuarioDAO usuarioDAO = new UsuarioDAO(DbConnection.getConexao());
                usuarioDAO.dislike(vendedorProduto);
                vendedorAvaliado = true;
                avaliadoLike =false ;
                avaliadoDislike = true ;
                avaliadoLabel.setText("Vendedor avalido com dislike");
                avaliadoLabel.setVisible(true);
            }else{
                avaliadoLabel.setText("Vendedor já avalido com dislike");
                avaliadoLabel.setVisible(true);
            }
        }



}
