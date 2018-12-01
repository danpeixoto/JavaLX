package projeto.dao;

import projeto.exception.EmailRegistradoException;
import projeto.modelo.Cidade;
import projeto.modelo.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements IDAO<Usuario>{
    private static final String ADD_USUARIO = "insert into tb_usuario(nome_usuario,email_usuario,celular_usuario,senha_usuario," +
            "cod_cidade) values(?,?,?,?,?);";
    private static final String REMOVE_USUARIO = "delete from tb_usuario where cod_usuario=?;";
    private static final String UPDATE_USUARIO = "update tb_usuario set nome_usuario=?,email_usuario=?," +
            "celular_usuario=?,senha_usuario=?,cod_cidade=?, ind_situacao=? " +
            "where cod_usuario=?;";
    private static final String GETALL_USUARIOS_BY_CIDADE = "select * from tb_usuario where cod_cidade=?;";
    private static final String GET_USUARIO_BY_EMAIL ="select * from tb_usuario where email_usuario=? ;";
    private static final String GET_USUARIO_BY_SENHA_EMAIL ="select * from tb_usuario where email_usuario=? and senha_usuario=?;";
    private static final String GETALL_USUARIO_BY_NOME = "select * from tb_usuario where nome_usuario=?;";
    private static final String GETBYID_USUARIO = "select * from tb_usuario where cod_usuario=?;";
    private static final String CREDITAR_CONTA_USUARIO = "{call Creditar_Conta_Usuario(?,?)}";
    private static final String DEBITAR_CONTA_USUARIO = "{call Debitar_Conta_Usuario(?,?)}";
    private static final String DAR_LIKE_USUARIO = "{? = call Like_Usuario(?)}";
    private static final String DAR_DISLIKE_USUARIO = "{? = call Dislike_Usuario(?)}";

    //////////////////////////////////////////////////////////////////////////////////////////////
    private static final String COLUNA_CODIGO = "cod_usuario";
    private static final String COLUNA_NOME = "nome_usuario";
    private static final String COlUNA_EMAIL = "email_usuario";
    private static final String COLUNA_CELULAR = "celular_usuario";
    private static final String COlUNA_SENHA = "senha_usuario";
    private static final String COLUNA_SITUACAO = "ind_situacao";
    private static final String COLUNA_CODIGO_CIDADE = "cod_cidade";
    private static final String COLUNA_NOTA_USUARIO = "nota_usuario";
    private static final String COLUNA_SALDO_USUARIO = "val_saldo";
    private Connection conexao;

    public UsuarioDAO(Connection connection){
        this.conexao = connection;
    }


    @Override
    public void add(Usuario usuario) throws EmailRegistradoException {
        if(alreadyRegistered(usuario)){
            throw  new EmailRegistradoException("Email já registrado.");
        }
        try(PreparedStatement preparedStatement = conexao.prepareStatement(ADD_USUARIO)){

            preparedStatement.setString(1,usuario.getNome().toUpperCase());
            preparedStatement.setString(2,usuario.getEmail());
            preparedStatement.setString(3,usuario.getCelular());
            preparedStatement.setString(4,usuario.getSenha());
            preparedStatement.setInt(5,usuario.getCidade().getCodigo());
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();

        }

    }

    private boolean alreadyRegistered(Usuario usuario){
        try(PreparedStatement preparedStatement = conexao.prepareStatement(GET_USUARIO_BY_EMAIL)){
            preparedStatement.setString(1,usuario.getEmail());
            ResultSet resultSet = preparedStatement.executeQuery();


            if(resultSet.next())
                return true;
        }catch (SQLException e){
            e.printStackTrace();

        }
        return false;
    }
    @Override
    public void remove(Usuario usuario) {
        remove(usuario.getCodigo());
    }

    @Override
    public void remove(int id) {
        try{
            PreparedStatement preparedStatement = conexao.prepareStatement(REMOVE_USUARIO);

            preparedStatement.setInt(1,id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Usuario usuario) {

        try{
            PreparedStatement preparedStatement = conexao.prepareStatement(UPDATE_USUARIO);

            preparedStatement.setString(1,usuario.getNome().toUpperCase());
            preparedStatement.setString(2,usuario.getEmail());
            preparedStatement.setString(3,usuario.getCelular());
            preparedStatement.setString(4,usuario.getSenha());
            preparedStatement.setInt(5,usuario.getCidade().getCodigo());
            preparedStatement.setString(6,usuario.getSituacao());
            preparedStatement.setInt(7,usuario.getCodigo());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Usuario> getAll() {
        List<Usuario> usuarios = new ArrayList<>();

        try {

            CidadeDAO cidadeDAO = new CidadeDAO(conexao);
            List<Cidade> cidades = cidadeDAO.getAll();
            PreparedStatement preparedStatement = conexao.prepareStatement(GETALL_USUARIOS_BY_CIDADE);

            for (Cidade c : cidades) {
                preparedStatement.setInt(1, c.getCodigo());
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Usuario usuarioAux = new Usuario();

                    usuarioAux.setNome(resultSet.getString(COLUNA_NOME));
                    usuarioAux.setEmail(resultSet.getString(COlUNA_EMAIL));
                    usuarioAux.setCelular(resultSet.getString(COLUNA_CELULAR));
                    usuarioAux.setSenha(resultSet.getString(COlUNA_SENHA));
                    usuarioAux.setSituacao(resultSet.getString(COLUNA_SITUACAO));
                    usuarioAux.setCodigo(resultSet.getInt(COLUNA_CODIGO));
                    usuarioAux.setCidade(c);
                    usuarioAux.setNota(resultSet.getInt(COLUNA_NOTA_USUARIO));
                    usuarioAux.setSaldo(resultSet.getFloat(COLUNA_SALDO_USUARIO));

                    usuarios.add(usuarioAux);
                }

                resultSet.close();
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    @Override
    public Usuario getById(int id) {

        Usuario usuarioBuscado = null;

        try{
            PreparedStatement preparedStatement = conexao.prepareStatement(GETBYID_USUARIO);

            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                usuarioBuscado = new Usuario();
                CidadeDAO cidadeDAO = new CidadeDAO(conexao);
                Cidade cidade = cidadeDAO.getById(resultSet.getInt(COLUNA_CODIGO_CIDADE));

                usuarioBuscado.setNome(resultSet.getString(COLUNA_NOME));
                usuarioBuscado.setEmail(resultSet.getString(COlUNA_EMAIL));
                usuarioBuscado.setCelular(resultSet.getString(COLUNA_CELULAR));
                usuarioBuscado.setSenha(resultSet.getString(COlUNA_SENHA));
                usuarioBuscado.setSituacao(resultSet.getString(COLUNA_SITUACAO));
                usuarioBuscado.setCodigo(resultSet.getInt(COLUNA_CODIGO));
                usuarioBuscado.setCidade(cidade);
                usuarioBuscado.setNota(resultSet.getInt(COLUNA_NOTA_USUARIO));
                usuarioBuscado.setSaldo(resultSet.getFloat(COLUNA_SALDO_USUARIO));

            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarioBuscado;
    }

    public Usuario getUsuarioByEmailSenha(String email, String senha){
        Usuario usuarioBuscado = null;

        try{
            PreparedStatement preparedStatement = conexao.prepareStatement(GET_USUARIO_BY_SENHA_EMAIL);

            preparedStatement.setString(1,email);
            preparedStatement.setString(2,senha);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                usuarioBuscado = new Usuario();
                CidadeDAO cidadeDAO = new CidadeDAO(conexao);
                Cidade cidade = cidadeDAO.getById(resultSet.getInt(COLUNA_CODIGO_CIDADE));

                usuarioBuscado.setNome(resultSet.getString(COLUNA_NOME));
                usuarioBuscado.setEmail(resultSet.getString(COlUNA_EMAIL));
                usuarioBuscado.setCelular(resultSet.getString(COLUNA_CELULAR));
                usuarioBuscado.setSenha(resultSet.getString(COlUNA_SENHA));
                usuarioBuscado.setSituacao(resultSet.getString(COLUNA_SITUACAO));
                usuarioBuscado.setCodigo(resultSet.getInt(COLUNA_CODIGO));
                usuarioBuscado.setCidade(cidade);
                usuarioBuscado.setNota(resultSet.getInt(COLUNA_NOTA_USUARIO));
                usuarioBuscado.setSaldo(resultSet.getFloat(COLUNA_SALDO_USUARIO));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarioBuscado;
    }

    public List<Usuario> getUsuarioByNome(String nome){
        List<Usuario> usuarios = new ArrayList<>();

        try{
            PreparedStatement preparedStatement = conexao.prepareStatement(GETALL_USUARIO_BY_NOME);

            preparedStatement.setString(1,nome);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Usuario usuarioAux = new Usuario();
                CidadeDAO cidadeDAO = new CidadeDAO(conexao);
                Cidade cidade = cidadeDAO.getById(resultSet.getInt(COLUNA_CODIGO_CIDADE));

                usuarioAux.setNome(resultSet.getString(COLUNA_NOME));
                usuarioAux.setEmail(resultSet.getString(COlUNA_EMAIL));
                usuarioAux.setCelular(resultSet.getString(COLUNA_CELULAR));
                usuarioAux.setSenha(resultSet.getString(COlUNA_SENHA));
                usuarioAux.setSituacao(resultSet.getString(COLUNA_SITUACAO));
                usuarioAux.setCodigo(resultSet.getInt(COLUNA_CODIGO));
                usuarioAux.setCidade(cidade);
                usuarioAux.setNota(resultSet.getInt(COLUNA_NOTA_USUARIO));
                usuarioAux.setSaldo(resultSet.getFloat(COLUNA_SALDO_USUARIO));
                usuarios.add(usuarioAux);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    public void creditarSaldoUsuario(Usuario usuario , float valor){
        creditarSaldoUsuario(usuario.getCodigo() , valor);

    }

    public void creditarSaldoUsuario(int id , float valor){
        try {
            CallableStatement statement = conexao.prepareCall(CREDITAR_CONTA_USUARIO);
             statement.setFloat(1,valor);
             statement.setInt(2,id);
             statement.execute();
             statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void debitarSaldoUsuario(Usuario usuario , float valor){
       debitarSaldoUsuario(usuario.getCodigo() , valor);

    }

    public void debitarSaldoUsuario(int id , float valor){
        try {
            CallableStatement statement = conexao.prepareCall(DEBITAR_CONTA_USUARIO);
            statement.setFloat(1,valor);
            statement.setInt(2,id);
            statement.execute();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void like(Usuario usuario){
        try{
            CallableStatement statement = conexao.prepareCall(DAR_LIKE_USUARIO);
            statement.registerOutParameter(1 , Types.INTEGER);
            statement.setInt(2 , usuario.getCodigo());
            statement.execute();
            usuario.setNota(statement.getInt(1));
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dislike(Usuario usuario){
        try{
            CallableStatement statement = conexao.prepareCall(DAR_DISLIKE_USUARIO);
            statement.registerOutParameter(1 , Types.INTEGER);
            statement.setInt(2 , usuario.getCodigo());
            statement.execute();
            usuario.setNota(statement.getInt(1));
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
