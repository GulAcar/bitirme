package application;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class adminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView image;

    @FXML
    private TextField txt1;

    @FXML
    private TextField txt2;

    @FXML
    private TextField txt4;

    @FXML
    private TextField txt5;

    @FXML
    private TextField txt6;
    
    @FXML
    private TextField txt7;

    @FXML
    private TextField txt8;
    
    @FXML
    private Button giris;
    
    @FXML
    private RadioButton gida;

    @FXML
    private RadioButton bakim;
    
    @FXML
    private Button geri;
    
    @FXML
    private Button dil;
    
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
    
    int i =0;
    @FXML
    void dil_click(ActionEvent event) {
    	
    	System.out.print(i);
  	  if(i==0) {
  		  //Turkcesi Buraya
  		  giris.setText("Giris");
  		  gida.setText("Gýda");
  		  bakim.setText("Bakým&Temizlik");
  		  dil.setText("Dil");
  		  dil.setText("EN");
  		  i=1;
  		  
  	  }
  	  else if(i==1) {
  		  //Ingilizcesi buraya
  		  giris.setText("Enter");
  		  gida.setText("Food");
		  bakim.setText("Maintenance&Cleaning");
  		  dil.setText("Language");
  		  dil.setText("TR");
  		  i=0;
  	  }

    }

    @FXML
    void bakim_click(ActionEvent event) {
    	gida.setSelected(false);
    	txt1.setPromptText("Ad");
    	txt2.setPromptText("marka");
    	txt4.setPromptText("miktar");
    	txt5.setPromptText("saklamakosullari");
    	txt6.setPromptText("uyarilar");
    	txt7.setPromptText("icindekiler");
    	txt8.setVisible(false);

    }
    
    @FXML
    void gida_click(ActionEvent event) {
    	bakim.setSelected(false);
    	txt1.setPromptText("Ad");
    	txt2.setPromptText("marka");
    	txt4.setPromptText("miktar");
    	txt5.setPromptText("enerji");
    	txt6.setPromptText("saklamakosullari");
    	txt7.setPromptText("uretimyeri");
    	txt8.setVisible(true);
    	txt8.setPromptText("icindekiler");

    	
    }

    @FXML
    void giris_click(ActionEvent event) throws ParseException, WriterException, IOException {
    	
    	if(gida.isSelected()==true) {
    	   
    		VeritabaniUtil.dbInsertGida(txt1.getText().toString(), txt2.getText().toString(), Integer.parseInt(txt4.getText().toString()),Integer.parseInt( txt5.getText().toString()), txt6.getText().toString(), txt7.getText().toString(), txt8.getText().toString());
    		System.out.println("database eklendi");
    		generateQRCodeImage("gida_"+txt1.getText().toString(), 300, 300, "./qrcode/gida/gida_"+txt1.getText().toString()+".png");
    		image.setImage(new Image(new File("./qrcode/gida/gida_"+txt1.getText()+".png".toString()).toURI().toString()));
    		System.out.println("qr kod eklendi");
    	}
    	else if(bakim.isSelected()==true)
    	{
    		
    		VeritabaniUtil.dbInsertBakimTemizlik( txt1.getText().toString(), txt2.getText().toString(),  Integer.parseInt(txt4.getText().toString()), txt5.getText().toString(), txt6.getText().toString(), txt7.getText().toString());
    		generateQRCodeImage("bakim_"+txt1.getText().toString(),300, 300, "./qrcode/bakim/bakim_"+txt1.getText().toString()+".png");
    		image.setImage(new Image(new File("./qrcode/bakim/bakim_"+txt1.getText()+".png".toString()).toURI().toString()));
    	}
    	else {
    		
    	}
    	
    	

    }

    @FXML
    void initialize() {
      

    }
    

    private static void generateQRCodeImage(String text, int width, int height, String filePath)
            throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }

    private byte[] getQRCodeImageByteArray(String text, int width, int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        byte[] pngData = pngOutputStream.toByteArray();
        return pngData;
    }

    
}

    
