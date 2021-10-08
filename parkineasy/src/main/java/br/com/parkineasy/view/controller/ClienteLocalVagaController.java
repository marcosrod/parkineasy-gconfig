package br.com.parkineasy.view.controller;

import br.com.parkineasy.App;
import br.com.parkineasy.model.Vaga;
import br.com.parkineasy.model.enums.TipoVaga;
import br.com.parkineasy.repository.impl.ReservaRepositoryImpl;
import br.com.parkineasy.repository.impl.VagaRepositoryImpl;
import br.com.parkineasy.view.model.VagaTableRow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static br.com.parkineasy.App.PARKINEASY_FOLDER;

public class ClienteLocalVagaController implements Initializable {

    static VagaRepositoryImpl vagaRepository = new VagaRepositoryImpl();
    private static Integer tipo;
    private static String codigo;
    ReservaRepositoryImpl reservaRepository = new ReservaRepositoryImpl();
    @FXML
    private TableView<VagaTableRow> tableLocalVaga;
    @FXML
    private TableColumn<VagaTableRow, String> colCodigoLocalVaga;
    @FXML
    private TableColumn<VagaTableRow, String> colSituacaoLocalVaga;
    @FXML
    private TableColumn<VagaTableRow, TipoVaga> colTipoLocalVaga;

    public static void typeReceiver(Integer tip) {
        tipo = tip;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<Vaga> vagas = vagaRepository.recuperarTodasPorTipoESituacaoLivre(tipo);
        List<VagaTableRow> vagaTableRowList = new ArrayList<>();
        vagas.forEach(v -> vagaTableRowList.add(new VagaTableRow(v)));

        ObservableList<VagaTableRow> listaV = FXCollections.observableArrayList(vagaTableRowList);

        colCodigoLocalVaga.setCellValueFactory(
                new PropertyValueFactory<>("codigoVaga"));
        colSituacaoLocalVaga.setCellValueFactory(
                new PropertyValueFactory<>("situacaoVaga"));
        colTipoLocalVaga.setCellValueFactory(
                new PropertyValueFactory<>("tipoVaga"));

        tableLocalVaga.setItems(listaV);

        tableLocalVaga.setOnMousePressed(mouseEvent -> {
            codigo = tableLocalVaga.getSelectionModel().getSelectedItem().getCodigoVaga();
            reservaRepository.salvar(codigo);
        });

    }


    public void pressBack(ActionEvent event) throws MalformedURLException {
        URL url =
                Paths.get(PARKINEASY_FOLDER + "\\src\\main\\java\\br\\com\\parkineasy\\view\\fxml\\ClienteTipoVaga" +
                        ".fxml").toUri().toURL();
        App.nextScene("Seleção do Tipo de Vaga", 407, 330, url, event);
    }

    public void selectRow(MouseEvent mouse) throws MalformedURLException {
        URL url = Paths.get(PARKINEASY_FOLDER + "\\src\\main\\java\\br\\com\\parkineasy\\view\\fxml" +
                "\\ClienteTicketEmitido" +
                ".fxml").toUri().toURL();
        Parent root;
        try {
            root = FXMLLoader.load(url);
            Stage stage = new Stage();
            stage.setTitle("Emissão de Ticket");
            stage.setScene(new Scene(root, 600, 400));
            stage.setResizable(false);
            stage.show();
            ((Node) (mouse.getSource())).getScene().getWindow().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
