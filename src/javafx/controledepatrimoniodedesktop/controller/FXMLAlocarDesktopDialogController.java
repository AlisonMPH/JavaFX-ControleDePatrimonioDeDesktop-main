
package javafx.controledepatrimoniodedesktop.controller;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.controledepatrimoniodedesktop.desktop.Alocacao;
import javafx.controledepatrimoniodedesktop.desktop.Desktop;
import javafx.controledepatrimoniodedesktop.desktop.Localizacao;
import javafx.controledepatrimoniodedesktop.desktop.Usuario;
import javafx.controledepatrimoniodedesktop.model.dao.DesktopDAO;
import javafx.controledepatrimoniodedesktop.model.dao.LocalizacaoDAO;
import javafx.controledepatrimoniodedesktop.model.dao.UsuarioDAO;
import javafx.controledepatrimoniodedesktop.model.database.Database;
import javafx.controledepatrimoniodedesktop.model.database.DatabaseFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class FXMLAlocarDesktopDialogController {
    
    @FXML
    private Button buttonConfirmar;

    @FXML
    private Button buttonCancelar;

    @FXML
    private ComboBox<Localizacao> comboBoxLocalizacao;

    @FXML
    private ComboBox<Usuario> comboBoxUsuario;

    @FXML
    private ComboBox<Desktop> comboBoxDesktop;
    
    //Atributos para manipulação de Banco de Dados
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final LocalizacaoDAO localizacaoDAO = new LocalizacaoDAO();
    private final DesktopDAO desktopDAO = new DesktopDAO();
    
    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Alocacao alocacao;
    
    private List<Desktop> listDesktop = new ArrayList<>();
    private ObservableList<Desktop> observableListDesktop;
    
    private List<Usuario> listUsuario = new ArrayList<>();
    private ObservableList<Usuario> observableListUsuario;
    
    private List<Localizacao> listLocalizacao = new ArrayList<>();
    private ObservableList<Localizacao> observableListLocalizacao;
    
    public void initialize(URL url, ResourceBundle rb) {
       
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Alocacao getAlocacao() {
        return this.alocacao;
    }

    public void setAlocacao(Alocacao alocacao) {
        this.alocacao = alocacao;
        this.textAlocacaoDesktop.setText(alocacao.getDesktop());
        this.comboBoxLocalizacao.setText(alocacao.getLocalizacao());
        this.comboBoxUsuario.setText(alocacao.getUsuario());
    }

    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }

    @FXML
    public void handleButtonConfirmar() {
        if (validarEntradaDeDados()) {
            alocacao.setDesktop(textAlocacaoDesktop.getText());
            alocacao.setLocalizacao(comboBoxLocalizacao.getText());
            alocacao.setUsuario(comboBoxUsuario.getText());
            
            buttonConfirmarClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    public void handleButtonCancelar() {
        getDialogStage().close();
    }

    //Validar entrada de dados para o cadastro
    private boolean validarEntradaDeDados() {
        String errorMessage = "";

        if (textAlocacaoDesktop.getText() == null || textAlocacaoDesktop.getText().length() == 0) {
            errorMessage += "Desktop inválido!\n";
        }
        if (textAlocacaoLocalizacao.getText() == null || textAlocacaoLocalizacao.getText().length() == 0) {
            errorMessage += "Localizacao inválida!\n";
        }
        if (textAlocacaoUsuario.getText() == null || textAlocacaoUsuario.getText().length() == 0) {
            errorMessage += "Usuario inválido!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostrando a mensagem de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro");
            alert.setHeaderText("Campos inválidos, por favor, corrija...");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }
    
    public void carregarComboBoxDesktop(){
        listDesktop = desktopDAO.listar();
        
        observableListDesktop = FXCollections.observableArrayList(listDesktop);
        comboBoxDesktop.setItems(observableListDesktop);
    }
    
    public void carregarComboBoxUsuario(){
        listUsuario = usuarioDAO.listar();
        
        observableListUsuario = FXCollections.observableArrayList(listUsuario);
        comboBoxUsuario.setItems(observableListUsuario);
    }
    
    public void carregarComboBoxLocalizacao(){
        listLocalizacao = localizacaoDAO.listar();
        
        observableListLocalizacao = FXCollections.observableArrayList(listLocalizacao);
        comboBoxLocalizacao.setItems(observableListLocalizacao);
    }

    
}

