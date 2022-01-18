package application;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class kayitsizGirisSampleController {
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView qrviev;

    @FXML
    private Label urunAdi;

    @FXML
    private Label marka;

    @FXML
    private Label enKal;

    @FXML
    private Label sakkos;

    @FXML
    private Label uretimyer;

    @FXML
    private Label miktar;

    @FXML
    private Label uyarı;

    @FXML
    private Label icindekiler;
    
    @FXML
    private Button qrOku;

    @FXML
    private Button dil;
    
    @FXML
    private Button geri;
    
    @FXML
    private AnchorPane anchorpane;
    
    @FXML
    void geri_click(ActionEvent event) {
    	
    	 try {
    	  		FXMLLoader loader=new FXMLLoader(getClass().getResource("projeSample.fxml"));
				AnchorPane pane2 = (AnchorPane)loader.load();
				ProjeControllerClassName nesne=loader.getController();
				Scene scene2=new Scene(pane2);
				Stage stage2=new Stage();
				stage2.setScene(scene2);
				Stage primaryStage = (Stage)geri.getScene().getWindow();
				primaryStage.hide();
				stage2.show();
    			} catch(Exception e) {
    				e.printStackTrace();
    			}

    }
    
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/bprojesi";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "root";
    private static final String hangiUrun_QUERY= "SELECT * FROM yiyeceklerinozellikleri WHERE ID =";

    int i =0;
    @FXML
    void dil_click(ActionEvent event) {
    	
    	
    	/*Translator translate = Translator.getInstance();
    	String text = translate.translate("Hello!", Language.ENGLISH, Language.ROMANIAN);
    	System.out.println(text); // "Bună ziua
    	*/
    	//language için class oluşturulacak ve içine tarnslate apisi bağlanacak
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	System.out.print(i);
    	  if(i==0) {
    		  //Turkcesi Buraya
    		  urunAdi.setText("Ürün Adı");
    		  marka.setText("Marka");
    		  enKal.setText("enerji/kalori");
    		  uretimyer.setText("Üretim Yeri");
    		  icindekiler.setText("İçindekiler");
    		  uyarı.setText("Uyarılar");
    		  qrOku.setText("QROku");
    		  miktar.setText("Miktar");
    		  sakkos.setText("Saklama Koşulları");
    		  dil.setText("EN");
    		  i=1;
    		  
    	  }
    	  else if(i==1) {
    		  //Ingilizcesi buraya
    		urunAdi.setText("Name of the product");
  		    marka.setText("Brand");
  		    enKal.setText("energy/calories");
  		    uretimyer.setText("Production place");
  		    icindekiler.setText("Ingredients");
  		    uyarı.setText("Warnings");
  		  miktar.setText("Quantity");
		  sakkos.setText("Storage Conditions");
  		    qrOku.setText("QRRead");
    		  dil.setText("TR");
    		  i=0;
    	  }

    }

    @FXML
    void qrOku_click(ActionEvent event) throws IOException {
    	
    	VeritabaniUtil veritabaniutil=new VeritabaniUtil();
    	FileChooser fileChooser=new FileChooser();
		fileChooser.setTitle("QR kod icin resim seciniz");
		Stage stage=(Stage) anchorpane.getScene().getWindow();
		
		File qrkodyolu=fileChooser.showOpenDialog(stage);
		
		
		String decodedText = decodeQRCode(qrkodyolu);
		qrviev.setImage(new Image(qrkodyolu.toURI().toString()));
		if(decodedText == null) {
			System.out.println("donusturulemedi");
		} else {
			
			// Open a connection
		    try(Connection conn = DriverManager.getConnection(DATABASE_URL);
		       Statement stmt = conn.createStatement();
		       ResultSet rs = stmt.executeQuery(hangiUrun_QUERY+decodedText);
		    ) {		      
		       while(rs.next()){
		    			   urunAdi.setText("Urun adi: "+rs.getString("urunadi"));
		    			   marka.setText("Urunun markasi: "+rs.getString("marka"));
		    			   miktar.setText("miktarı: "+rs.getString("miktar"));
		    			   enKal.setText("Urunun Kalorisi(100gr): "+rs.getInt("enerji/kalori")+" cal "+ (rs.getInt("enerji/kalori"))*4.14+" joule");
		    			   icindekiler.setText("Urunun icerigi: "+rs.getString("icindekiler") );
		    			   sakkos.setText("saklama kosullari: "+rs.getString("saklamakosullari"));
		    			   uretimyer.setText("Urunun uretim yeri: "+rs.getString("uretimyeri"));
		    			   uyarı.setText("uyarılar:"+rs.getString("uyarılar"));
		    			   };
		       
		       
		    } catch (SQLException e) {
		       e.printStackTrace();
		    }
			
		}
    

    }

  //qr kod okuma
  	 private static String decodeQRCode(File qrCodeimage) throws IOException {
  	        BufferedImage bufferedImage = ImageIO.read(qrCodeimage);
  	        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
  	        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

  	        try {
  	            Result result = new MultiFormatReader().decode(bitmap);
  	            return result.getText();
  	        } catch (NotFoundException e) {
  	            System.out.println("There is no QR code in the image");
  	            return null;
  	        }
  	    }

	@FXML
    void initialize() {
        

    }

}
