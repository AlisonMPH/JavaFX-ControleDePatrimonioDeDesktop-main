package javafx.controledepatrimoniodedesktop.controller;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.controledepatrimoniodedesktop.desktop.Alocacao;
import javafx.controledepatrimoniodedesktop.model.dao.AlocacaoDAO;
import javafx.controledepatrimoniodedesktop.model.database.Database;
import javafx.controledepatrimoniodedesktop.model.database.DatabaseFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLRelatorioController implements Initializable {

    @FXML
    private Button buttonImprimir;

    @FXML
    private TableColumn<Alocacao,String> tableColumDesktop;

    @FXML
    private TableColumn<Alocacao,String> tableColumLocalizacao;

    @FXML
    private TableColumn<Alocacao,String> tableColumMac;

    @FXML
    private TableColumn<Alocacao,String> tableColumModelo;

    @FXML
    private TableColumn<Alocacao,String> tableColumServicetag;

    @FXML
    private TableColumn<Alocacao,String> tableColumUsuario;

    @FXML
    private TableView<Alocacao> tableViewRelatorio;
    
    private List<Alocacao> listAlocacao;
    private ObservableList<Alocacao> observableListAlocacao;

    //Atributos para manipulação de Banco de Dados
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final AlocacaoDAO alocacaoDAO = new AlocacaoDAO();
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alocacaoDAO.setConnection (connection);
    }
    
    public void carregarTableViewProdutos() {
        tableColumDesktop.setCellValueFactory(new PropertyValueFactory<>("DESKTOP_ALOCACAO"));
        tableColumLocalizacao.setCellValueFactory(new PropertyValueFactory<>("LOCALIZACAO_ALOCACAO"));
        tableColumUsuario.setCellValueFactory(new PropertyValueFactory<>("USUARIO_ALOCACAO"));
        tableColumMac.setCellValueFactory(new PropertyValueFactory<>("MAC"));
        tableColumModelo.setCellValueFactory(new PropertyValueFactory<>("MODELO"));
        tableColumServicetag.setCellValueFactory(new PropertyValueFactory<>("SERVICETAG"));

        listAlocacao = alocacaoDAO.listarRelatorio();

        observableListAlocacao = FXCollections.observableArrayList(listAlocacao);
        tableViewRelatorio.setItems(observableListAlocacao);
    }

}
