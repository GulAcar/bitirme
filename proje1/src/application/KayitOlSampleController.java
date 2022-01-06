package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.*;

public class KayitOlSampleController {

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private AnchorPane kayitol_Anchorpane;
  
  @FXML
  private Label lbl_kayýtol;

  @FXML
  private Label lbl_Ad;
  
  @FXML
  private Label lbl_kulAdi;

  @FXML
  private Label lbl_sifre;

  @FXML
  private Label lbl_sifreT;
  
  @FXML
  private Label lbl_dil;

  @FXML
  private TextField txt_Ad;

  @FXML
  private Label lbl_Soyad;

  @FXML
  private TextField txt_Soyad;

  @FXML
  private TextField txt_kulAdi;

  @FXML
  private TextField txt_sifre;

  @FXML
  private TextField txt_sifreTekrar;

  @FXML
  private ComboBox < String > combo1;

  @FXML
  private Button btn_kayitOl;
  
  @FXML
  private Button dil;

  int i =0;
  @FXML
  void dil_click(ActionEvent event) {
	  System.out.print(i);
	  if(i==0) {
		  //Turkcesi Buraya
		  lbl_Ad.setText("Ad");
		  lbl_Soyad.setText("Soyad");
		  lbl_kulAdi.setText("Kullanici Adý");
		  lbl_sifre.setText("Þifre");
		  lbl_sifreT.setText("Þifre Tekrar");
		  lbl_dil.setText("Dil");
		  btn_kayitOl.setText("Kayýt Ol");
		  lbl_kayýtol.setText("Kayýt Ol");
		  dil.setText("EN");
		  i=1;
		  
	  }
	  else if(i==1) {
		  //Ingilizcesi buraya
		  lbl_Ad.setText("Name");
		  lbl_Soyad.setText("Surname");
		  lbl_kulAdi.setText("Nickname");
		  lbl_sifre.setText("Password");
		  lbl_sifreT.setText("Password again");
		  lbl_dil.setText("Language");
		  btn_kayitOl.setText("Register");
		  lbl_kayýtol.setText("REGÝSTER");
		  dil.setText("TR");
		  i=0;
	  }

  }


  @FXML
  void btn_kayitOl_Action(ActionEvent event) {

    //anasayfaya acilsin(kullanýcý giriþ sayfasý açýlsýn)
    //kullanici giris sayfasi acilirken bu sayfa gizlensin.

    //txt_Ad.getText().trim().isEmpty() -> bu calisirsa bunu yap
    //txt_Ad.getText()=!null -> bunu da deneyebilirsin.

    VeritabaniUtil veritabaniUtil = new VeritabaniUtil();
    if (veritabaniUtil.dbInsert(txt_Ad.getText(), txt_Soyad.getText(), txt_kulAdi.getText(), txt_sifre.getText(), combo1.getSelectionModel().getSelectedItem())) {
      
    	
    	System.out.println(true);
      
      if (txt_sifre.getText().equals(txt_sifreTekrar.getText()) &&
        txt_sifreTekrar.getText().equals(txt_sifre.getText())) {
       
    	  if (veritabaniUtil.dbInsert(txt_Ad.getText(), txt_Soyad.getText(), txt_kulAdi.getText(), txt_sifre.getText(), combo1.getSelectionModel().getSelectedItem())) {
         
    		  try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("projeSample.fxml"));
            AnchorPane pane2 = (AnchorPane) loader.load();
            ProjeControllerClassName nesne = loader.getController();
            Scene scene2 = new Scene(pane2);

            Stage stage2 = new Stage();
            stage2.setScene(scene2);
            stage2.show();
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
    	  else {
          Alert alert = new Alert(AlertType.ERROR);
          alert.setTitle("HATA");
          alert.setHeaderText("HATA MESAJI");
          alert.setContentText("Veritabanýna Ulaþýlamýyor!");
          alert.showAndWait();
        }
      }
      else {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("HATA");
        alert.setHeaderText("HATA MESAJI");
        alert.setContentText("Þifreler Ayný Olmalýdýr!");
        alert.showAndWait();
      }
    }
    else {
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("HATA");
      alert.setHeaderText("HATA MESAJI");
      alert.setContentText("Tüm Satýrlar Doldurulmalýdýr!");
      alert.showAndWait();
    }
  }

  //Istersen silebilrsin
  @FXML
  void combo1_Action(ActionEvent event) {

  }

  @FXML
  void initialize() {
    //TODO:combobox degerleri buradan eklenecek.
    combo1.getItems().addAll(
      "tr",
      "ing",
      "alm"
    );

  }
}