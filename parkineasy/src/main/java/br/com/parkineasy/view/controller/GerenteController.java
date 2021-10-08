package br.com.parkineasy.view.controller;

import br.com.parkineasy.App;
import br.com.parkineasy.model.Entrada;
import br.com.parkineasy.repository.impl.GerenteRepositoryImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Control;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.YearMonth;
import java.util.regex.Pattern;

import static br.com.parkineasy.App.PARKINEASY_FOLDER;

public class GerenteController {

    private static String tfReimprimirTicket;
    GerenteRepositoryImpl gerenteRepository = new GerenteRepositoryImpl();
    @FXML
    private TextField tfUsernameLoginGerente;
    @FXML
    private TextField tfPasswordLoginGerente;
    @FXML
    private TextField tfCodigoReimprimirTicket;
    @FXML
    private TextArea taReimprimirTicket;
    @FXML
    private TextField tfMesAnoRelatorio;

    public void pressButtonCancelLogin(ActionEvent event) throws MalformedURLException {
        App.infoBox("Cancelando Login de Gerente!", "Login de Gerente", null);
        URL url = Paths.get(PARKINEASY_FOLDER + "\\src\\main\\java\\br\\com\\parkineasy\\view\\fxml" +
                "\\TelaInicial.fxml").toUri().toURL();
        App.nextScene("Seleção de Módulo", 520, 400, url, event);
    }

    public void pressButtonConfirmLogin(ActionEvent event) throws MalformedURLException {
        if (tfUsernameLoginGerente.getText().equals("") || tfPasswordLoginGerente.getText().equals("")) {
            App.infoBox("Todos os campos devem ser preenchidos!", "Login de Gerente", null);

        } else if (gerenteRepository.validarGerente(tfUsernameLoginGerente.getText(),
                tfPasswordLoginGerente.getText())) {
            App.infoBox("Gerente Autenticado Com Sucesso!", "Login de Gerente", null);
            URL url = Paths.get(PARKINEASY_FOLDER + "\\src\\main\\java\\br\\com\\parkineasy\\view" +
                    "\\fxml\\GerentePainelControle.fxml").toUri().toURL();
            App.nextScene("Painel de Controle do Estacionamento", 600, 400, url, event);
        } else {
            App.infoBox("O Usuário ou Senha Inseridos Estão Incorretos!", "Login de Gerente", null);
            tfUsernameLoginGerente.clear();
            tfPasswordLoginGerente.clear();
            tfUsernameLoginGerente.requestFocus();
        }

    }

    public void pressButtonMenuGerente(ActionEvent event) throws MalformedURLException {
        switch (((Control) event.getSource()).getId()) {
            case "btGerarRelatorioPainel": {
                URL url = Paths.get(PARKINEASY_FOLDER + "\\src\\main\\java\\br\\com\\parkineasy" +
                        "\\view\\fxml\\GerenteGerarRelatorio.fxml").toUri().toURL();
                App.nextScene("Geração de Relatório", 520, 400, url, event);
                break;
            }
            case "btConsultarVagasPainel": {
                URL url = Paths.get(PARKINEASY_FOLDER + "\\src\\main\\java\\br\\com\\parkineasy" +
                        "\\view\\fxml\\GerenteConsultarVagas.fxml").toUri().toURL();
                App.nextScene("Painel de Vagas do Estacionamento", 600, 600, url, event);
                break;
            }
            case "btReimprimirTicketPainel": {
                URL url = Paths.get(PARKINEASY_FOLDER + "\\src\\main\\java\\br\\com\\parkineasy" +
                        "\\view\\fxml\\GerenteReimprimirTicket.fxml").toUri().toURL();
                App.nextScene("Reimpressão de Ticket", 520, 400, url, event);
                break;
            }
            case "btSairPainel": {
                App.infoBox("Fazendo Logout...", "Painel de Controle do Estacionamento", null);
                URL url = Paths.get(PARKINEASY_FOLDER + "\\src\\main\\java\\br\\com\\parkineasy" +
                        "\\view\\fxml\\GerenteLogin.fxml").toUri().toURL();
                App.nextScene("Login da Gerência", 520, 400, url, event);
                break;
            }
        }
    }

    public void pressButtonGerarRelatorio(ActionEvent event) throws MalformedURLException {
        final String regex = "^[0-9]{4}-(0{1}[1-9]{1}|1{1}[0-2])$";
        switch (((Control) event.getSource()).getId()) {
            case "btConfirmarGerarRelatorio": {
                if (tfMesAnoRelatorio.getText().equals("")) {
                    App.infoBox("A Data Alvo Não Pode Ser Nula!", "Geração de Relatório", null);
                } else if (Pattern.matches(regex, tfMesAnoRelatorio.getText())) {
                    GerenteGerarRelatorioController.dateReceiver(YearMonth.parse(tfMesAnoRelatorio.getText()));
                    App.infoBox("Relatório Gerado Com Sucesso!", "Geração de Relatório", null);
                    URL url = Paths.get(PARKINEASY_FOLDER + "\\src\\main\\java\\br\\com\\parkineasy" +
                            "\\view\\fxml\\GerenteRelatorioEmitido.fxml").toUri().toURL();
                    App.nextScene("Emissão de Relatório", 950, 735, url, event);
                } else {
                    App.infoBox("A Data Alvo Inserida É Inválida! Utilize o Formato Requisitado. Exemplo: 2021-05",
                            "Geração de Relatório", null);
                }

                break;
            }

            case "btVoltarGerarRelatorio": {
                URL url = Paths.get(PARKINEASY_FOLDER + "\\src\\main\\java\\br\\com\\parkineasy" +
                        "\\view\\fxml\\GerentePainelControle.fxml").toUri().toURL();
                App.nextScene("Painel de Controle do Estacionamento", 600, 400, url, event);
                break;
            }
        }
    }

    public void pressButtonReimprimirTicket(ActionEvent event) throws MalformedURLException {
        switch (((Control) event.getSource()).getId()) {
            case "btConfirmarReimprimirTicket": {
                if (tfCodigoReimprimirTicket.getText().equals("")) {
                    App.infoBox("O Código da Vaga Não Pode Ser Vazio!", "Reimprimir Ticket", null);
                } else if (gerenteRepository.recuperarPorCodigoVaga(tfCodigoReimprimirTicket.getText()) != null) {
                    tfReimprimirTicket = tfCodigoReimprimirTicket.getText();
                    App.infoBox("Reimpressão de Ticket Realizada Com Sucesso!", "Reimprimir Ticket", null);
                    URL url = Paths.get(PARKINEASY_FOLDER + "\\src\\main\\java\\br\\com\\parkineasy" +
                            "\\view\\fxml\\GerenteTicketReimpresso.fxml").toUri().toURL();
                    App.nextScene("Reimpressão de Ticket", 600, 400, url, event);
                } else {
                    App.infoBox("O Código da Vaga Inserido É Inválido!", "Reimprimir Ticket", null);
                    tfCodigoReimprimirTicket.clear();
                    tfCodigoReimprimirTicket.requestFocus();
                }
                break;
            }
            case "btVoltarReimprimirTicket": {
                URL url = Paths.get(PARKINEASY_FOLDER + "\\src\\main\\java\\br\\com\\parkineasy" +
                        "\\view\\fxml\\GerentePainelControle.fxml").toUri().toURL();
                App.nextScene("Painel de Controle do Estacionamento", 600, 400, url, event);
                break;
            }
        }
    }

    public void fillTicketReimpresso() {
        Entrada entrada = gerenteRepository.recuperarPorCodigoVaga(tfReimprimirTicket);
        taReimprimirTicket.setText(entrada.toString());
    }

    public void pressButtonConfirmarReimpressao(ActionEvent event) throws MalformedURLException {
        URL url = Paths.get(PARKINEASY_FOLDER + "\\src\\main\\java\\br\\com\\parkineasy" +
                "\\view\\fxml\\GerenteReimprimirTicket.fxml").toUri().toURL();
        App.nextScene("Reimpressão de Ticket", 520, 400, url, event);
    }


}
