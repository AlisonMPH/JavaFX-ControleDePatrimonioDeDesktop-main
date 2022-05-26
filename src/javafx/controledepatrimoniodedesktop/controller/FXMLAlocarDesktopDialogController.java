
package javafx.controledepatrimoniodedesktop.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.controledepatrimoniodedesktop.desktop.Alocacao;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

class FXMLAlocarDesktopDialogController {
    
    @FXML
    private Button buttonConfirmar;

    @FXML
    private Button buttonCancelar;

    @FXML
    private TextField textAlocacaoDesktop;

    @FXML
    private TextField textAlocacaoLocalizacao;

    @FXML
    private TextField textAlocacaoUsuario;
    
    private Stage dialogStage;
    private boolean buttonConfirmarClicked;
    private Alocacao alocacao;
    
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
        this.textAlocacaoLocalizacao.setText(alocacao.getLocalizacao());
        this.textAlocacaoUsuario.setText(alocacao.getUsuario());
    }

    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }

    @FXML
    public void handleButtonConfirmar() {
        if (validarEntradaDeDados()) {
            alocacao.setDesktop(textAlocacaoDesktop.getText());
            alocacao.setLocalizacao(textAlocacaoLocalizacao.getText());
            alocacao.setUsuario(textAlocacaoUsuario.getText());
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

    
}

