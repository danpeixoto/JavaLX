package projeto.dao;

import projeto.modelo.Produto;
import projeto.modelo.TipoProduto;
import projeto.modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO implements IDAO<Produto> {
    private static final String ADD_PRODUTO = "insert into tb_produto(nome_produto,desc_produto,val_preco,cod_usuario," +
            "cod_tipo_produto) values(?,?,?,?,?);";
    private static final String REMOVE_PRODUTO = "delete from tb_produto where cod_produto=?;";
    private static final String UPDATE_PRODUTO = "update tb_produto set nome_produto=?,desc_produto=?," +
            "val_preco=? " +
            "where cod_produto=?;";
    private static final String GETALL_PRODUTOS = "select * from tb_produto where cod_usuario=? order by cod_tipo_produto ASC;";
    private static final String GETALL_PRODUTOS_BY_TIPO = "select * from tb_produto where cod_tipo_produto=" +
            "(select cod_tipo from tb_tipo_produto where desc_tipo=?) order by cod_usuario ASC;";
    private static final String GETBYID_PRODUTO = "select * from tb_produto where cod_produto=?;";
    private static final String GETALL_PRODUTOS_BY_NOME = "select * from tb_produto where nome_produto=?" +
            " order by cod_usuario ASC , cod_tipo_produto ASC;";
    //////////////////////////////////////////////////////////////////////////////////////////////
    private static final String COLUNA_CODIGO_USUARIO = "cod_usuario";
    private static final String COLUNA_CODIGO_TIPO = "cod_tipo_produto";
    private static final String COLUNA_CODIGO = "cod_produto";
    private static final String COLUNA_NOME = "nome_produto";
    private static final String COlUNA_DESC = "desc_produto";
    private static final String COLUNA_PRECO = "val_preco";


    private Connection conexao;

    public ProdutoDAO(Connection connection) {
        this.conexao = connection;
    }

    @Override
    public void add(Produto produto) {
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(ADD_PRODUTO);

            preparedStatement.setString(1, produto.getNome().toUpperCase());
            preparedStatement.setString(2, produto.getDescricao().toUpperCase());
            preparedStatement.setDouble(3, produto.getPreco());
            preparedStatement.setInt(4, produto.getUsuario().getCodigo());
            preparedStatement.setInt(5, produto.getTipo().getCodigo());

            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Produto produto) {
        remove(produto.getCodigo());
    }

    @Override
    public void remove(int id) {
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(REMOVE_PRODUTO);

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Produto produto) {
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(UPDATE_PRODUTO);

            preparedStatement.setString(1, produto.getNome().toUpperCase());
            preparedStatement.setString(2, produto.getDescricao().toUpperCase());
            preparedStatement.setDouble(3, produto.getPreco());
            preparedStatement.setInt(4, produto.getCodigo());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Produto> getAll() {
        List<Produto> produtos = new ArrayList<>();
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
            TipoDAO tipoDAO = new TipoDAO(conexao);
            List<Usuario> usuarios = usuarioDAO.getAll();
            PreparedStatement preparedStatement = conexao.prepareStatement(GETALL_PRODUTOS);

            for (Usuario u : usuarios) {
                preparedStatement.setInt(1, u.getCodigo());
                ResultSet resultSet = preparedStatement.executeQuery();
                int i = 0;
                TipoProduto tipoProduto = null;
                while (resultSet.next()) {
                    if (i == 0) {
                        tipoProduto = tipoDAO.getById(resultSet.getInt(COLUNA_CODIGO_TIPO));
                        i++;
                    } else if (tipoProduto.getCodigo() != resultSet.getInt(COLUNA_CODIGO_TIPO)) {
                        tipoProduto = tipoDAO.getById(resultSet.getInt(COLUNA_CODIGO_TIPO));
                    }
                    Produto produtoAux = new Produto();

                    produtoAux.setCodigo(resultSet.getInt(COLUNA_CODIGO));
                    produtoAux.setNome(resultSet.getString(COLUNA_NOME));
                    produtoAux.setDescricao(resultSet.getString(COlUNA_DESC));
                    produtoAux.setPreco(resultSet.getDouble(COLUNA_PRECO));
                    produtoAux.setTipo(tipoProduto);
                    produtoAux.setUsuario(u);

                    produtos.add(produtoAux);
                }


                resultSet.close();
            }

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produtos;
    }

    @Override
    public Produto getById(int id) {
        Produto produtoBuscado = null;

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(GETBYID_PRODUTO);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                produtoBuscado = new Produto();
                UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
                Usuario usuario = usuarioDAO.getById(resultSet.getInt(COLUNA_CODIGO_USUARIO));

                TipoDAO tipoDAO = new TipoDAO(conexao);
                TipoProduto tipoProduto = tipoDAO.getById(resultSet.getInt(COLUNA_CODIGO_TIPO));

                produtoBuscado.setCodigo(resultSet.getInt(COLUNA_CODIGO));
                produtoBuscado.setNome(resultSet.getString(COLUNA_NOME));
                produtoBuscado.setDescricao(resultSet.getString(COlUNA_DESC));
                produtoBuscado.setPreco(resultSet.getDouble(COLUNA_PRECO));
                produtoBuscado.setTipo(tipoProduto);
                produtoBuscado.setUsuario(usuario);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produtoBuscado;
    }

    public List<Produto> getByNome(String nome){
        List<Produto> produtos = new ArrayList<>();
        try {
            TipoDAO tipoDAO = new TipoDAO(conexao);
            UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
            TipoProduto tipoProduto = null;
            Usuario usuario = null;
            PreparedStatement preparedStatement = conexao.prepareStatement(GETALL_PRODUTOS_BY_NOME);
            preparedStatement.setString(1, nome);
            ResultSet resultSet = preparedStatement.executeQuery();

            int i = 0;//variavel para auxiliar na criação dos produtos

            while (resultSet.next()) {

                if (i == 0) {
                    usuario = usuarioDAO.getById(resultSet.getInt(COLUNA_CODIGO_USUARIO));
                    tipoProduto = tipoDAO.getById(resultSet.getInt(COLUNA_CODIGO_TIPO));
                    i++;
                }
                if (tipoProduto.getCodigo() != resultSet.getInt(COLUNA_CODIGO_TIPO)) {
                    tipoProduto = tipoDAO.getById(resultSet.getInt(COLUNA_CODIGO_TIPO));
                }

                if (usuario.getCodigo() != resultSet.getInt(COLUNA_CODIGO_USUARIO)) {
                    usuario = usuarioDAO.getById(resultSet.getInt(COLUNA_CODIGO_USUARIO));
                }
                Produto produtoAux = new Produto();
                produtoAux.setCodigo(resultSet.getInt(COLUNA_CODIGO));
                produtoAux.setNome(resultSet.getString(COLUNA_NOME));
                produtoAux.setDescricao(resultSet.getString(COlUNA_DESC));
                produtoAux.setPreco(resultSet.getDouble(COLUNA_PRECO));
                produtoAux.setTipo(tipoProduto);
                produtoAux.setUsuario(usuario);

                produtos.add(produtoAux);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produtos;
    }
    public List<Produto> getByUsuarios(String usuarioBuscado) {
        List<Produto> produtos = new ArrayList<>();
        try {
            TipoDAO tipoDAO = new TipoDAO(conexao);
            UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
            List<Usuario> usuarioList = usuarioDAO.getUsuarioByNome(usuarioBuscado);

            PreparedStatement preparedStatement = conexao.prepareStatement(GETALL_PRODUTOS);

            for (Usuario u : usuarioList) {
                preparedStatement.setInt(1, u.getCodigo());
                ResultSet resultSet = preparedStatement.executeQuery();
                int i = 0;
                TipoProduto tipoProduto = null;
                while (resultSet.next()) {
                    if (i == 0) {
                        tipoProduto = tipoDAO.getById(resultSet.getInt(COLUNA_CODIGO_TIPO));
                        i++;
                    } else if (tipoProduto.getCodigo() != resultSet.getInt(COLUNA_CODIGO_TIPO)) {
                        tipoProduto = tipoDAO.getById(resultSet.getInt(COLUNA_CODIGO_TIPO));
                    }
                    Produto produtoAux = new Produto();

                    produtoAux.setCodigo(resultSet.getInt(COLUNA_CODIGO));
                    produtoAux.setNome(resultSet.getString(COLUNA_NOME));
                    produtoAux.setDescricao(resultSet.getString(COlUNA_DESC));
                    produtoAux.setPreco(resultSet.getDouble(COLUNA_PRECO));
                    produtoAux.setTipo(tipoProduto);
                    produtoAux.setUsuario(u);

                    produtos.add(produtoAux);
                }


                resultSet.close();
            }

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produtos;
    }

    public List<Produto> getByTipo(String tipoBuscado) {
        List<Produto> produtos = new ArrayList<>();
        try {
            TipoDAO tipoDAO = new TipoDAO(conexao);
            UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
            TipoProduto tipoProduto = null;
            Usuario usuario = null;
            PreparedStatement preparedStatement = conexao.prepareStatement(GETALL_PRODUTOS_BY_TIPO);
            preparedStatement.setString(1, tipoBuscado);
            ResultSet resultSet = preparedStatement.executeQuery();

            int i = 0;//variavel para auxiliar na criação dos produtos

            while (resultSet.next()) {
                /*se i == 0  ira retornar um tipo , pois vai ser o unico tipo , e ira retornar o 1° usuario
                 * depois sera comparado se o usuario mudou , a busca esta ordenada pelos usuarios para facilitar*/
                if (i == 0) {
                    tipoProduto = tipoDAO.getById(resultSet.getInt(COLUNA_CODIGO_TIPO));
                    usuario = usuarioDAO.getById(resultSet.getInt(COLUNA_CODIGO_USUARIO));
                    i++;
                } else if (usuario.getCodigo() != resultSet.getInt(COLUNA_CODIGO_USUARIO)) {
                    usuario = usuarioDAO.getById(resultSet.getInt(COLUNA_CODIGO_USUARIO));
                }

                Produto produtoAux = new Produto();
                produtoAux.setCodigo(resultSet.getInt(COLUNA_CODIGO));
                produtoAux.setNome(resultSet.getString(COLUNA_NOME));
                produtoAux.setDescricao(resultSet.getString(COlUNA_DESC));
                produtoAux.setPreco(resultSet.getDouble(COLUNA_PRECO));
                produtoAux.setTipo(tipoProduto);
                produtoAux.setUsuario(usuario);

                produtos.add(produtoAux);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produtos;
    }

}
