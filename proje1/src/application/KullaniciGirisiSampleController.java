package application;


import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class KullaniciGirisiSampleController {

    @FXML
    private ImageView qr_view;
    
    @FXML
    private Button qrOku;

    @FXML
    private Label urunAdi;

    @FXML
    private Label marka;

    @FXML
    private Label enKal;

    @FXML
    private Label uretimyer;

    @FXML
    private Label icindekiler;
    
    @FXML
    private Label saklamakos;

    @FXML
    private Label miktar;
    
    @FXML
    private Label lbl_uyarilar;
    
    @FXML
    private Button dil;
    
    @FXML
    private Button geri;
    
    @FXML
    void geri_click(ActionEvent event) {
    	
    	System.out.println("calýstý");
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
    void dil_click(ActionEvent event) throws IOException {
    	System.out.print(i);
  	  if(i==0) {
  		  //Turkcesi Buraya
  		  urunAdi.setText("Urun adi");
  		  marka.setText("Marka");
  		  enKal.setText("Enerji/Kalori");
  		  uretimyer.setText("Üretim Yeri");
  		  saklamakos.setText("Saklama Koþullarý");
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
		  enKal.setText("energy/calories");
		  uretimyer.setText("Production place");
		  saklamakos.setText("Storage Conditions");
		  icindekiler.setText("Ingredients");
		  lbl_uyarilar.setText("Warnings");
		  qrOku.setText("QRRead");
  		  dil.setText("TR");
  		  i=0;
  	  }

    }
    
    @FXML
    private AnchorPane anchorPane;

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/bprojesi";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "root";
    private static final String GIDA_QUERY= "SELECT * FROM gida WHERE urunadi ='";
    private static final String BAKIM_QUERY= "SELECT * FROM bakimtemizlik WHERE urunadi ='";

	

    @FXML
    void qrOku_click(ActionEvent event) throws SQLException {

    	VeritabaniUtil veritabaniutil=new VeritabaniUtil();
    	try {      
    		FileChooser fileChooser=new FileChooser();
    		fileChooser.setTitle("QR kod icin resim seciniz");
    		Stage stage=(Stage) anchorPane.getScene().getWindow();
    		
    		File qrkodyolu=fileChooser.showOpenDialog(stage);
    		
    		
            String decodedText = decodeQRCode(qrkodyolu);
            qr_view.setImage(new Image(qrkodyolu.toURI().toString()));
            if(decodedText == null) {
            	System.out.println("donusturulemedi");
            } else {
            	Pattern gida_pattern = Pattern.compile("gida_", Pattern.CASE_INSENSITIVE);
                Matcher gida_matcher = gida_pattern.matcher(decodedText);
            	if(gida_matcher.find()) {
            		String islenmis=decodedText.substring(5);
            		
            		// Open a connection
            		try(Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                            Statement stmt = conn.createStatement();
                            ResultSet rs = stmt.executeQuery(GIDA_QUERY+islenmis+"'");
                         ) {		      
                            while(rs.next()){
                            	if(i==0) {
                         			   urunAdi.setText("Urun adi: "+rs.getString("urunadi"));
                         			   marka.setText("Urunun markasi: "+rs.getString("marka"));
                         			   miktar.setText("miktarý: "+rs.getString("miktar"));
                         			   enKal.setText("Urunun Kalorisi(100gr): "+rs.getInt("enerji")+" cal "+ (rs.getInt("enerji"))*4.14+" joule");
                         			   icindekiler.setText("Urunun icerigi: "+rs.getString("icindekiler") );
                         			   saklamakos.setText("saklama kosullari: "+rs.getString("saklamakosullari"));
                         			   uretimyer.setText("Urunun uretim yeri: "+rs.getString("uretimyeri"));
                         			   }
                            	if(i==1)
                            	{
                            		
                            			urunAdi.setText("Urun adi: "+Api.translate("tr", "en", rs.getString("urunadi")));
                            			marka.setText("Urunun markasi: "+rs.getString("marka"));
                            			miktar.setText("miktarý: "+rs.getString("miktar"));
                            			enKal.setText("Urunun Kalorisi(100gr): "+rs.getInt("enerji")+" cal "+ (rs.getInt("enerji"))*4.14+" joule");
                            			icindekiler.setText("Urunun icerigi: "+rs.getString("icindekiler") );
                            			saklamakos.setText("saklama kosullari: "+rs.getString("saklamakosullari"));
                            			uretimyer.setText("Urunun uretim yeri: "+rs.getString("uretimyeri"));
                      			   	
                            	}
                            	
                            };
                            
                            
                         } catch (SQLException e) {
                            e.printStackTrace();
                         }
            	}  
            	Pattern bakim_pattern = Pattern.compile("bakim_", Pattern.CASE_INSENSITIVE);
                Matcher bakim_matcher = bakim_pattern.matcher(decodedText);
            	if(bakim_matcher.find()) {
            		String islenmis=decodedText.substring(6);
            	
            		try(Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                            Statement stmt = conn.createStatement();
                            ResultSet rs = stmt.executeQuery(BAKIM_QUERY+islenmis+"'");
                         ) {		      
                            while(rs.next()){
                         			   urunAdi.setText("Urun adi: "+rs.getString("urunadi"));
                         			   marka.setText("Urunun markasi: "+rs.getString("marka"));
                         			   miktar.setText("miktarý: "+rs.getString("miktar"));
                         			   icindekiler.setText("Urunun icerigi: "+rs.getString("icindekiler") );
                         			   saklamakos.setText("saklama kosullari: "+rs.getString("saklamakosullari"));
                         			   lbl_uyarilar.setText("uyarýlar:"+rs.getString("uyarýlar"));
                         			   };
                            
                            
                         } catch (SQLException e) {
                            e.printStackTrace();
                         }
            	}
            	
                
        		
            }
        } catch (IOException e) {
            System.out.println("Could not decode QR Code, IOException :: " + e.getMessage());
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
	 
	 //qr kod olusturma
	 
	 private static final String QR_CODE_IMAGE_PATH = "./MyQRCode.png";

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
	    //dosya sistemi acma
	    
	   
	    
	    
}
