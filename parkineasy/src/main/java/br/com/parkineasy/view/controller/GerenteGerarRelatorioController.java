package br.com.parkineasy.view.controller;

import br.com.parkineasy.App;
import br.com.parkineasy.model.Relatorio;
import br.com.parkineasy.repository.impl.GerenteRepositoryImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;
import java.util.ResourceBundle;

import static br.com.parkineasy.App.PARKINEASY_FOLDER;

public class GerenteGerarRelatorioController implements Initializable {

    private static YearMonth mesAno;
    GerenteRepositoryImpl gerenteRepository = new GerenteRepositoryImpl();
    @FXML
    private TableView<Relatorio> tableRelatorio;
    @FXML
    private TableColumn<Relatorio, Integer> colTicketRelatorio;
    @FXML
    private TableColumn<Relatorio, String> colVagaRelatorio;
    @FXML
    private TableColumn<Relatorio, Integer> colComprovanteRelatorio;
    @FXML
    private TableColumn<Relatorio, LocalDate> colDataEntradaRelatorio;
    @FXML
    private TableColumn<Relatorio, LocalTime> colHorarioEntradaRelatorio;
    @FXML
    private TableColumn<Relatorio, LocalDate> colDataSaidaRelatorio;
    @FXML
    private TableColumn<Relatorio, LocalTime> colHorarioSaidaRelatorio;
    @FXML
    private TableColumn<Relatorio, LocalDate> colDataPagamentoRelatorio;
    @FXML
    private TableColumn<Relatorio, LocalTime> colHorarioPagamentoRelatorio;
    @FXML
    private TableColumn<Relatorio, LocalTime> colTempoRelatorio;
    @FXML
    private TableColumn<Relatorio, BigDecimal> colValorRelatorio;
    @FXML
    private Label lbValorTotalRelatorio;

    public static void dateReceiver(YearMonth mesAn) {
        mesAno = mesAn;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<Relatorio> oblist = FXCollections.observableArrayList(gerenteRepository.gerarRelatorio(mesAno));


        colTicketRelatorio.setCellValueFactory(
                new PropertyValueFactory<>("codigoTicket"));
        colVagaRelatorio.setCellValueFactory(
                new PropertyValueFactory<>("codigoVaga"));
        colComprovanteRelatorio.setCellValueFactory(
                new PropertyValueFactory<>("codigoComprovante"));
        colDataEntradaRelatorio.setCellValueFactory(
                new PropertyValueFactory<>("dataEntrada"));
        colHorarioEntradaRelatorio.setCellValueFactory(
                new PropertyValueFactory<>("horaEntrada"));
        colDataSaidaRelatorio.setCellValueFactory(
                new PropertyValueFactory<>("dataSaida"));
        colHorarioSaidaRelatorio.setCellValueFactory(
                new PropertyValueFactory<>("horaSaida"));
        colDataPagamentoRelatorio.setCellValueFactory(
                new PropertyValueFactory<>("dataPagamento"));
        colHorarioPagamentoRelatorio.setCellValueFactory(
                new PropertyValueFactory<>("horaPagamento"));
        colTempoRelatorio.setCellValueFactory(
                new PropertyValueFactory<>("totalHoras"));
        colValorRelatorio.setCellValueFactory(
                new PropertyValueFactory<>("valorPago"));

        tableRelatorio.setItems(oblist);

        List<Relatorio> listaRelatorios = gerenteRepository.gerarRelatorio(mesAno);
        BigDecimal soma = BigDecimal.ZERO;
        for (int i = 0; i < listaRelatorios.size(); i++) {
            soma = soma.add(listaRelatorios.get(i).getValorPago());
        }
        lbValorTotalRelatorio.setText(soma.toString());

    }

    public void pressButtonVoltarEmissaoRelatorio(ActionEvent event) throws MalformedURLException {
        if (((Control) event.getSource()).getId().equals("btVoltarEmissaoRelatorio")) {
            URL url = Paths.get(PARKINEASY_FOLDER + "\\src\\main\\java\\br\\com\\parkineasy" +
                    "\\view\\fxml\\GerenteGerarRelatorio.fxml").toUri().toURL();
            App.nextScene("Geração de Relatório", 520, 400, url, event);
        }
    }


}
