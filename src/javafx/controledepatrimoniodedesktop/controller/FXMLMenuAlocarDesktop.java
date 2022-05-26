
package javafx.controledepatrimoniodedesktop.controller;

import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FXMLMenuAlocarDesktop implements Initializable {
    
    @FXML
    private TableView<Alocacao> tableView;

    @FXML
    private Label labelDesktop;

    @FXML
    private Label labelLocalizacao;

    @FXML
    private Label labelUsuario;
    
    @FXML
    private Button buttonAlterar;

    @FXML
    private Button buttonInserir;

    @FXML
    private TableColumn<Alocacao, Integer> tableColumId;

    @FXML
    private TableColumn<Alocacao, String> tableColumDesktop;
    
    @FXML
    private TableColumn<Alocacao, String> tableColumLocalizacao;
    
    @FXML
    private TableColumn<Alocacao, String> tableColumUsuario;

    private List<Alocacao> listAlocacao;
    private ObservableList<Alocacao> observableListAlocacao;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final AlocacaoDAO alocacaoDAO = new AlocacaoDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alocacaoDAO.setConnection (connection);
        carregarTableViewAlocacao();
        selecionarItemTableViewAlocacao(null);
        tableView.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> selecionarItemTableViewAlocacao(newValue));
    }    
    
    /*@FXML
    public void showFXMLInserirDesktop() throws IOException{
        System.out.println("\nCarregando Tela FXMLInserirDesktop\n");
        Button a = (Button) FXMLLoader.load(getClass().getResource("FXMLAlocarDesktopDialog.fxml"));
        
    }*/
    public void carregarTableViewAlocacao() {
        tableColumId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumDesktop.setCellValueFactory(new PropertyValueFactory<>("desktop"));
        tableColumLocalizacao.setCellValueFactory(new PropertyValueFactory<>("localizacao"));
        tableColumUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        
        listAlocacao = alocacaoDAO.listar();

        observableListAlocacao = FXCollections.observableArrayList(listAlocacao);
        tableView.setItems(observableListAlocacao);
    }
    
    public void selecionarItemTableViewAlocacao(Alocacao alocacao) {
        if (alocacao != null) {
            labelDesktop.setText(alocacao.getDesktop());
            labelLocalizacao.setText(alocacao.getLocalizacao());
            labelUsuario.setText(alocacao.getUsuario());
        } else {
            labelDesktop.setText("");
            labelLocalizacao.setText("");
            labelUsuario.setText("");
        }
    }
    @FXML
    public void handleButtonInserir() throws IOException {
        Alocacao alocacao = new Alocacao();
        boolean buttonConfirmarClicked = showFXMLAlocarDesktopDialog(alocacao);
        if (buttonConfirmarClicked) {
            alocacaoDAO.inserir(alocacao);
            carregarTableViewAlocacao();
        }
    }

    @FXML
    public void handleButtonAlterar() throws IOException {
        Alocacao alocacao = tableView.getSelectionModel().getSelectedItem();//obtendo o desktop selecionado
        if (alocacao != null) {
            boolean buttonConfirmarClicked = showFXMLAlocarDesktopDialog(alocacao);
            if (buttonConfirmarClicked) {
                alocacaoDAO.alterar(alocacao);
                carregarTableViewAlocacao();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Escolha um desktop na Tabela!");
            alert.show();
        }
    }

    /*@FXML
    public void handleButtonRemoverAlocacao() throws IOException {
        Alocacao alocacao = tableView.getSelectionModel().getSelectedItem();
        if (alocacao != null) {
            desktopDAO.remover(alocacao);
            carregarTableViewAlocacao();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Escolha um desktop na Tabela!");
            alert.show();
        }
    }*/
    
    public boolean showFXMLAlocarDesktopDialog(Alocacao alocacao) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAlocarDesktopDialogController.class.getResource("/javafx/controledepatrimoniodedesktop/view/FXMLAlocarDesktopDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Alocacao de Desktop");
        //Especifica a modalidade para esta fase . Isso deve ser feito antes de fazer o estágio visível. A modalidade pode ser: Modality.NONE , Modality.WINDOW_MODAL , ou Modality.APPLICATION_MODAL 
        //dialogStage.initModality(Modality.WINDOW_MODAL);//WINDOW_MODAL (possibilita minimizar)
        
        //Especifica a janela do proprietário para esta página, ou null para um nível superior.
        //dialogStage.initOwner(null); //null deixa a Tela Principal livre para ser movida
        //dialogStage.initOwner(this.tableViewClientes.getScene().getWindow()); //deixa a tela de Preenchimento dos dados como prioritária
        
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        //Setando o cliente no Controller.
        FXMLAlocarDesktopDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setAlocacao(alocacao);

        //Mostra o Dialog e espera até que o usuário o feche
        dialogStage.setFocused(true);
        
        dialogStage.showAndWait();
        

        return controller.isButtonConfirmarClicked();
   

    }

}


