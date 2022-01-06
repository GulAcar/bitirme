package application;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class kayitsizGirisSampleController {
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView qrviewv;

    @FXML
    private Label urunAdi;

    @FXML
    private Label marka;

    @FXML
    private Label sonTT;

    @FXML
    private Label enKal;

    @FXML
    private Label icindekiler;

    @FXML
    private Label uretimyer;
    
    @FXML
    private Label lbl_uyarilar;

    @FXML
    private Button qrOku;

    @FXML
    private Button dil;

    int i =0;
    @FXML
    void dil_click(ActionEvent event) {
    	
    	System.out.print(i);
    	  if(i==0) {
    		  //Turkcesi Buraya
    		  urunAdi.setText("Ürün Adý");
    		  marka.setText("Marka");
    		  sonTT.setText("Kullanici Adý");
    		  enKal.setText("enerji/kalori");
    		  uretimyer.setText("Üretim Yeri");
    		  icindekiler.setText("Ýçindekiler");
    		  lbl_uyarilar.setText("Uyarýlar");
    		  qrOku.setText("QROku");
    		  dil.setText("EN");
    		  i=1;
    		  
    	  }
    	  else if(i==1) {
    		  //Ingilizcesi buraya
    		urunAdi.setText("Name of the product");
  		    marka.setText("Brand");
  		    sonTT.setText("User name");
  		    enKal.setText("energy/calories");
  		    uretimyer.setText("Production place");
  		    icindekiler.setText("Ingredients");
  		    lbl_uyarilar.setText("Warnings");
  		    qrOku.setText("QRRead");
    		  dil.setText("TR");
    		  i=0;
    	  }

    }

    @FXML
    void qrOku_click(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert qrviewv != null : "fx:id=\"qrviewv\" was not injected: check your FXML file 'kayitsizGirisSample.fxml'.";
        assert urunAdi != null : "fx:id=\"urunAdi\" was not injected: check your FXML file 'kayitsizGirisSample.fxml'.";
        assert marka != null : "fx:id=\"marka\" was not injected: check your FXML file 'kayitsizGirisSample.fxml'.";
        assert sonTT != null : "fx:id=\"sonTT\" was not injected: check your FXML file 'kayitsizGirisSample.fxml'.";
        assert enKal != null : "fx:id=\"enKal\" was not injected: check your FXML file 'kayitsizGirisSample.fxml'.";
        assert icindekiler != null : "fx:id=\"icindekiler\" was not injected: check your FXML file 'kayitsizGirisSample.fxml'.";
        assert uretimyer != null : "fx:id=\"uretimyer\" was not injected: check your FXML file 'kayitsizGirisSample.fxml'.";
        assert qrOku != null : "fx:id=\"qrOku\" was not injected: check your FXML file 'kayitsizGirisSample.fxml'.";
        assert dil != null : "fx:id=\"dil\" was not injected: check your FXML file 'kayitsizGirisSample.fxml'.";

    }

}
