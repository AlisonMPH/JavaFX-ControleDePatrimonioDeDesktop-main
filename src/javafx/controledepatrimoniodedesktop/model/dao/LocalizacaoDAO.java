
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

}

