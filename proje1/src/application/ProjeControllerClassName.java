package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.sun.media.jfxmediaimpl.platform.Platform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.sql.*;

public class ProjeControllerClassName {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label kuladi_lbl;

    @FXML
    private Label sifre_lbl;

    @FXML
    private Button giris_btn;

    @FXML
    private Button kayitol_btn;

    @FXML
    private Button kayitolma_btn;

    @FXML
    private TextField txt_kul;

    @FXML
    private TextField txt_sifre;
    
    @FXML
    private Button dil;
    
    int i =0;
    @FXML
    void dil_click(ActionEvent event) {
    	
    	System.out.print(i);
  	    if(i==0) {
  		  //Turkcesi Buraya
  		  kuladi_lbl.setText("Kullanýcý Adý");
  		  sifre_lbl.setText("Þifre");
  		  giris_btn.setText("Giriþ");
  		  kayitol_btn.setText("Kayýt Ol");
  		  kayitolma_btn.setText("Kayýt Olmadam Devam Et");
  		  dil.setText("Dil");
  		  dil.setText("EN");
  		  i=1;
  		  
  	  }
  	  else if(i==1) {
  		  //Ingilizcesi buraya
  		  kuladi_lbl.setText("Nickname");
		  sifre_lbl.setText("Password");
		  giris_btn.setText("Login");
		  kayitol_btn.setText("Register");
		  kayitolma_btn.setText("Continue Without Registration");
		  dil.setText("Dil");
  		  dil.setText("TR");
  		  i=0;
  	  }

    }


    @FXML
    void giris_btn_click(ActionEvent event) throws SQLException {
    	
    System.out.println(txt_kul.getText());	
    System.out.println(txt_sifre.getText());
    
    	String sql="select*from login where kul_ad=? and sifre=?";
    	VeritabaniUtil dbUtil=new VeritabaniUtil();
    	if(dbUtil.validate(txt_kul.getText(),txt_sifre.getText()))
    	{
    		
    	try {
    		
    		FXMLLoader loader=new FXMLLoader(getClass().getResource("KullaniciGirisiSample.fxml"));
			AnchorPane pane2 = (AnchorPane)loader.load();
			KullaniciGirisiSampleController nesne=loader.getController();
			Scene scene2=new Scene(pane2);
			Stage stage2=new Stage();
			stage2.setScene(scene2);
            Stage primaryStage = (Stage)giris_btn.getScene().getWindow();
            primaryStage.hide();

			stage2.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
    	
    	else {
    		Alert alert=new Alert(AlertType.INFORMATION);
    		alert.setTitle("sayfa giriþ");
    		alert.setHeaderText("hata mesajý");
    		alert.setContentText("hatalý giriþ");
    		alert.showAndWait();
    	}

    }

    @FXML
    void kayitol_btn_click(ActionEvent event) {
    //dýs form olusturma	
    	try {
			AnchorPane pane1 = (AnchorPane)FXMLLoader.load(getClass().getResource("KayitOlSample.fxml"));
			Scene scene = new Scene(pane1,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	Stage stage1=new Stage();

   
    
    //dýs forma veri aktarma
    
    try {
		FXMLLoader loader=new FXMLLoader(getClass().getResource("KayitOlSample.fxml"));
		AnchorPane pane2 = (AnchorPane)loader.load();
		KayitOlSampleController nesne=loader.getController();
		Scene scene2=new Scene(pane2);
		
		Stage stage2=new Stage();
		stage2.setScene(scene2);
		stage2.show();
	} catch(Exception e) {
		e.printStackTrace();
		}
	}
    
    
    

    @FXML
    void kayitolma_btn_click(ActionEvent event) {
    	
    	try {
    		FXMLLoader loader=new FXMLLoader(getClass().getResource("kayitsizGirisSample.fxml"));
			AnchorPane pane2 = (AnchorPane)loader.load();
			kayitsizGirisSampleController nesne=loader.getController();
			Scene scene2=new Scene(pane2);
			
			Stage stage2=new Stage();
			stage2.setScene(scene2);
			stage2.show();
		} catch(Exception e) {
			e.printStackTrace();
		}

    }

    @FXML
    void initialize() {
        

    }
}


