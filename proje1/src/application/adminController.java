package application;

import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

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
    private TextField txt3;

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
    private Button dil;
    
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
    	txt3.setPromptText("sonTT");
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
    	txt3.setPromptText("sonTT");
    	txt4.setPromptText("miktar");
    	txt5.setPromptText("enerji");
    	txt6.setPromptText("saklamakosullari");
    	txt7.setPromptText("uretimyeri");
    	txt8.setVisible(true);
    	txt8.setPromptText("icindekiler");

    	
    }

    @FXML
    void giris_click(ActionEvent event) throws ParseException {
    	
    	if(gida.isSelected()==true) {
    	    Date date1=(Date) new SimpleDateFormat("dd/MM/yyyy").parse(txt3.getText().toString());
    		VeritabaniUtil.dbInsertGida(txt1.getText().toString(), txt2.getText().toString(), date1, Integer.parseInt(txt4.getText().toString()),Integer.parseInt( txt5.getText().toString()), txt6.getText().toString(), txt7.getText().toString(), txt8.getText().toString());
    		
    	}
    	else if(bakim.isSelected()==true)
    	{
    		Date date2=(Date) new SimpleDateFormat("dd/MM/yyyy").parse(txt3.getText().toString());
    		VeritabaniUtil.dbInsertBakimTemizlik( txt1.getText().toString(), txt2.getText().toString(), date2, Integer.parseInt(txt4.getText().toString()), txt5.getText().toString(), txt6.getText().toString(), txt7.getText().toString());
    	}
    	else {
    		
    	}
    	
    	

    }

    @FXML
    void initialize() {
      

    }
}

