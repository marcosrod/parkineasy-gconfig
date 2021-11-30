package br.com.parkineasy.view.controller;

import br.com.parkineasy.App;
import br.com.parkineasy.model.ComprovantePagamento;
import br.com.parkineasy.repository.impl.PagamentoRepositoryImpl;
import br.com.parkineasy.service.impl.PagamentoServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Control;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import static br.com.parkineasy.App.PARKINEASY_FOLDER;


public class ClientePagamentoController {

    private static Integer codigoTicket;
    PagamentoServiceImpl pagamentoService = new PagamentoServiceImpl();
    PagamentoRepositoryImpl pagamentoRepository = new PagamentoRepositoryImpl();
    @FXML
    private TextField tfCodigoInserirTicket;
    @FXML
    private TextField tfCodigoSaida;
    @FXML
    private TextArea taEmitirComprovante;

    public void validaInputPagamento(KeyEvent event) {
        String regex = "^[0-9]+$";
        String input = event.getCharacter();
        if (!Pattern.matches(regex, input)) {
            tfCodigoInserirTicket.setText("");
        }
    }

    public void pressBack(ActionEvent event) throws MalformedURLException {
        URL url =
                Paths.get(PARKINEASY_FOLDER + "\\src\\main\\java\\br\\com\\parkineasy\\view\\fxml\\ClienteMenu.fxml").toUri().toURL();
        App.nextScene("Menu do Cliente", 407, 375, url, event);
    }

    public void pressButtonConfirm(ActionEvent event) throws MalformedURLException {
        if (tfCodigoInserirTicket.getText().equals("")) {
            App.infoBox("O código do ticket não pode ser vazio!", "Inserção de Ticket", null);
        } else if (pagamentoRepository.conferirTicketEntrada(Integer.parseInt(tfCodigoInserirTicket.getText()))) {
            codigoTicket = Integer.parseInt(tfCodigoInserirTicket.getText());
            App.infoBox("Ticket Inserido Com Sucesso!", "Inserção de Ticket", null);
            URL url = Paths.get(PARKINEASY_FOLDER + "\\src\\main\\java\\br\\com\\parkineasy\\view\\fxml" +
                    "\\ClienteMetodoPagamento.fxml").toUri().toURL();
            App.nextScene("Seleção do Método de Pagamento", 600, 400, url, event);
        } else {
            App.infoBox("O Ticket Inserido É Inválido!", "Inserção de Ticket", null);
            tfCodigoInserirTicket.clear();
            tfCodigoInserirTicket.requestFocus();
        }

    }

    public void pressButtonMetodo(ActionEvent event) throws MalformedURLException {
        int metPagamento;
        switch (((Control) event.getSource()).getId()) {
            case "btCartaoMetodoPagamento":
                App.infoBox("Pagamento Via Cartão Selecionado!", "Seleção do Método de Pagamento", null);
                metPagamento = 1;
                pagamentoService.efetuarPagamento(codigoTicket, metPagamento);
                break;
            case "btDinheiroMetodoPagamento":
                App.infoBox("Pagamento Em Dinheiro Selecionado!", "Seleção do Método de Pagamento", null);
                metPagamento = 2;
                pagamentoService.efetuarPagamento(codigoTicket, metPagamento);
                break;
        }
        URL url = Paths.get(PARKINEASY_FOLDER + "\\src\\main\\java\\br\\com\\parkineasy\\view\\fxml" +
                "\\ClienteComprovanteEmitido.fxml").toUri().toURL();
        App.nextScene("Finalização do Pagamento - Emissão de Comprovante", 600, 400, url, event);
    }

    public void fillComprovanteField() {
        ComprovantePagamento comprovantePagamento = pagamentoRepository.mostrarComprovante(codigoTicket);
        taEmitirComprovante.setText(comprovantePagamento.toString());
    }

    public void pressButtonFinalPag(ActionEvent event) throws MalformedURLException {
        App.infoBox("Saindo Do Estacionamento!", "Redirecionando Para a Saída", null);
        URL url = Paths.get(PARKINEASY_FOLDER + "\\src\\main\\java\\br\\com\\parkineasy\\view\\fxml" +
                "\\ClienteInserirComprovanteSaida.fxml").toUri().toURL();
        App.nextScene("Saída do Estacionamento", 520, 400, url, event);
    }

    public void validaInputSaida(KeyEvent event) {
        String regex = "^[0-9]+$";
        String input = event.getCharacter();
        if (!Pattern.matches(regex, input)) {
            tfCodigoSaida.setText("");
        }
    }

    public void pressButtonConfirmSaida(ActionEvent event) throws MalformedURLException {
        if (tfCodigoSaida.getText().equals("")) {
            App.infoBox("O código do comprovante não pode ser vazio!", "Inserção de Comprovante", null);
        } else if (pagamentoRepository.conferirComprovanteDePagamento(Integer.parseInt(tfCodigoSaida.getText()))) {
            App.infoBox("Saída Confirmada - Agradecemos Pela Confiança!", "Inserção de Comprovante", null);
            URL url =
            Paths.get(PARKINEASY_FOLDER + "\\src\\main\\java\\br\\com\\parkineasy\\view\\fxml\\ClienteMenu.fxml").toUri().toURL();
            App.nextScene("Menu do Cliente", 407, 375, url, event);
        } else {
            App.infoBox("O Comprovante Inserido É Inválido!", "Inserção de Comprovante", null);
            tfCodigoSaida.clear();
            tfCodigoSaida.requestFocus();
        }

    }

    public void pressBackSaida(ActionEvent event) throws MalformedURLException {
        URL url = Paths.get(PARKINEASY_FOLDER + "\\src\\main\\java\\br\\com\\parkineasy\\view\\fxml" +
                "\\ClienteComprovanteEmitido.fxml").toUri().toURL();
        App.nextScene("Finalização do Pagamento - Emissão de Comprovante", 600, 400, url, event);
    }
}
