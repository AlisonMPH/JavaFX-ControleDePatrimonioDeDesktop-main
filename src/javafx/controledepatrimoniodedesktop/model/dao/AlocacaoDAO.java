
package javafx.controledepatrimoniodedesktop.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.controledepatrimoniodedesktop.desktop.Alocacao;


public class AlocacaoDAO {
    
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public boolean inserir(Alocacao alocacao) {
        String sql = ("INSERT INTO \"ALOCACAO\"(\"DESKTOP_ALOCACAO\",\"LOCALIZACAO_ALOCACAO\",\"USUARIO_ALOCACAO\") VALUES(?,?,?)");
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, alocacao.getDesktop());
            stmt.setString(2, alocacao.getLocalizacao());
            stmt.setString(3, alocacao.getUsuario());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AlocacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean alterar(Alocacao alocacao) {
        String sql = "UPDATE \"ALOCACAO\" SET \"DESKTOP_ALOCACAO\"=?,\"LOCALIZACAO_ALOCACAO\"=?,\"USUARIO_ALOCACAO\"=? WHERE \"ID_ALOCACAO\"=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, alocacao.getDesktop());
            stmt.setString(2, alocacao.getLocalizacao());
            stmt.setString(3, alocacao.getUsuario());
            stmt.setInt(4, alocacao.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AlocacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
     public List<Alocacao> listar() {
        String sql = "SELECT * FROM \"ALOCACAO\"";
        List<Alocacao> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Alocacao alocacao = new Alocacao();
                alocacao.setId(resultado.getInt("ID_ALOCACAO"));
                alocacao.setDesktop(resultado.getString("DESKTOP_ALOCACAO"));
                alocacao.setLocalizacao(resultado.getString("LOCALIZACAO_ALOCACAO"));
                alocacao.setUsuario(resultado.getString("USUARIO_ALOCACAO"));
                retorno.add(alocacao);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlocacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public Alocacao buscar(Alocacao alocacao) {
        String sql = "SELECT * FROM \"ALOCACAO\" WHERE \"ID\"=?";
        Alocacao retorno = new Alocacao();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, alocacao.getId());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                alocacao.setDesktop(resultado.getString("DESKTOP_ALOCACAO"));
                alocacao.setLocalizacao(resultado.getString("LOCALIZACAO_ALOCACAO"));
                alocacao.setUsuario(resultado.getString("USUARIO_ALOCACAO"));
                retorno = alocacao;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlocacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public List<Alocacao> listarRelatorio() {
        String sql = "SELECT * FROM \"ALOCACAO\"";
        
        List<Alocacao> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Alocacao alocacao = new Alocacao();
                alocacao.setDesktop(resultado.getString("DESKTOP_ALOCACAO"));
                alocacao.setLocalizacao(resultado.getString("LOCALIZACAO_ALOCACAO"));
                alocacao.setUsuario(resultado.getString("USUARIO_ALOCACAO"));
                retorno.add(alocacao);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlocacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    

    
}
