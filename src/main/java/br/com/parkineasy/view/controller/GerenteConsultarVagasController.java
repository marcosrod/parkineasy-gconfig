package br.com.parkineasy.view.controller;

import br.com.parkineasy.App;
import br.com.parkineasy.model.Vaga;
import br.com.parkineasy.model.enums.TipoVaga;
import br.com.parkineasy.repository.impl.VagaRepositoryImpl;
import br.com.parkineasy.view.model.VagaTableRow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Control;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static br.com.parkineasy.App.PARKINEASY_FOLDER;

public class GerenteConsultarVagasController implements Initializable {

    VagaRepositoryImpl vagaRepository = new VagaRepositoryImpl();


    @FXML
    private TableView<VagaTableRow> tableConsultarVagas;
    @FXML
    private TableColumn<VagaTableRow, String> colAConsultarVagas;
    @FXML
    private TableColumn<VagaTableRow, String> colBConsultarVagas;
    @FXML
    private TableColumn<VagaTableRow, TipoVaga> colCConsultarVagas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<Vaga> vagas = vagaRepository.recuperarTodas();
        List<VagaTableRow> vagaTableRowList = new ArrayList<>();
        vagas.forEach(v -> vagaTableRowList.add(new VagaTableRow(v)));

        ObservableList<VagaTableRow> oblist = FXCollections.observableArrayList(vagaTableRowList);

        colAConsultarVagas.setCellValueFactory(
                new PropertyValueFactory<>("codigoVaga"));
        colBConsultarVagas.setCellValueFactory(
                new PropertyValueFactory<>("situacaoVaga"));
        colCConsultarVagas.setCellValueFactory(
                new PropertyValueFactory<>("tipoVaga"));

        tableConsultarVagas.setItems(oblist);

    }


    public void pressButtonConsultarVagas(ActionEvent event) throws MalformedURLException {
        if ("btVoltarPainelVagas".equals(((Control) event.getSource()).getId())) {
            URL url = Paths.get(PARKINEASY_FOLDER + "\\src\\main\\java\\br\\com\\parkineasy\\view" +
                    "\\fxml\\GerentePainelControle.fxml").toUri().toURL();
            App.nextScene("Painel de Controle do Estacionamento", 600, 400, url, event);
        }
    }
}
