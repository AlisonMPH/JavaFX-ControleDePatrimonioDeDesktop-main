
package javafx.controledepatrimoniodedesktop.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.controledepatrimoniodedesktop.desktop.Localizacao;
import javafx.controledepatrimoniodedesktop.desktop.Usuario;

public class LocalizacaoDAO {
    
private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }


    public List<Localizacao> listar() {
        String sql = "SELECT * FROM \"LOCALIZACAO\"";
        List<Localizacao> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Localizacao localizacao = new Localizacao();
                localizacao.setId(resultado.getInt("ID_LOCALIZACAO"));
                localizacao.setNome(resultado.getString("NOME_LOCALIZACAO"));
                localizacao.setCapacidade(resultado.getInt("CAPACIDADE_LOCALIZACAO"));
                retorno.add(localizacao);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LocalizacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

   public Localizacao buscar(Localizacao localizacao) {
        String sql = "SELECT * FROM \"LOCALIZACAO\" WHERE \"ID_LOCALIZACAO\"=?";
        Localizacao retorno = new Localizacao();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, localizacao.getId());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                localizacao.setId(resultado.getInt("ID_LOCALIZACAO"));
                localizacao.setNome(resultado.getString("NOME_LOCALIZACAO"));
                localizacao.setCapacidade(resultado.getInt("CAPACIDADE_LOCALIZACAO"));
                
                retorno = localizacao;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LocalizacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }


}

