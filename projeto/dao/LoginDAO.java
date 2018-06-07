package projeto.dao;

import projeto.servicos.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    private Connection conexao;
    private static String email;
    private static String senha;

    private static final String LOGIN_USUARIO = "select * from tb_usuario where email_usuario=? and senha_usuario=?;";
    private static final String COLUNA_EMAIL = "email_usuario";
    private static final String COLUNA_SENHA = "senha_usuario";
    private static final String COLUNA_SITUACAO = "ind_situacao";

    public LoginDAO() {
        this.conexao = DbConnection.getConexao();
    }

    public static String getEmail() {
        return email;
    }

    public static String getSenha() {
        return senha;
    }

    public boolean loginAceito(String email, String senha) {

        boolean usuarioExiste = false;

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(LOGIN_USUARIO);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,senha);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                if(resultSet.getString(COLUNA_SITUACAO).equals("A")){
                    usuarioExiste = true;
                    email = resultSet.getString(COLUNA_EMAIL);
                    senha = resultSet.getString(COLUNA_SENHA);
                }
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarioExiste;
    }

    public boolean isConectado() {
        return this.conexao != null;
    }
}
