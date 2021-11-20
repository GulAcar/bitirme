import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class KayitOlSampleController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane kayitol_Anchorpane;

    @FXML
    private TextField txt_adSoyad;

    @FXML
    private TextField txt_kulAdi;

    @FXML
    private TextField txt_posta;

    @FXML
    private TextField txt_sifre;

    @FXML
    private TextField txt_sifreTekrar;

    @FXML
    private Label lbl_adSoyad;

    @FXML
    private ComboBox<String> combo1;

    @FXML
    private Button btn_kayitOl;

    @FXML
    void btn_kayitOl_Action(ActionEvent event) {
    	
    	sql="insert into login(kulAdi, sifre, yetki) Values(?,?,?)";
    	try {
    		sorguifadesi=baglanti.prepareStatement(sql);
    		sorguifadesi.setString(1,txt_kulAdi.getText().trim());
    		sorguifadesi.setString(2,txt_sifre.getText().trim());
    		sorguifadesi.setString(3,"0");
    		sorguifadesi.executeUpdate();
    		/*lbl_sonuc.setText("eklendi");*/
    	
    	}
    	 catch(Exception e) {/*
    		lbl_sonuc.setText(e.getMessage().toString());*/
    	}

    }

    @FXML
    void combo1_Action(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }
}
