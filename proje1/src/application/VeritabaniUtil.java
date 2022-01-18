package application;
import java.sql.*;

public class VeritabaniUtil {
	
	// Replace below database url, username and password with your actual database credentials
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/bprojesi";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "root";
    private static final String SELECT_QUERY = "SELECT * FROM kullanýcýlar WHERE KullaniciAdi = ? and Sifre = ?";
    private static final String INSERT_QUERY= "insert into kullanýcýlar(Ad,soyad,kullaniciAdi,sifre,dil) values (?,?,?,?,?)";
    private static final String hangiUrun_QUERY= "SELECT * FROM gida WHERE ID =";
    
    public boolean validate(String emailId, String password) throws SQLException {

        // Step 1: Establishing a Connection and 
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
            .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY)) {
            preparedStatement.setString(1, emailId);
            preparedStatement.setString(2, password);

            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }


        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        return false;
    }
    
    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
    public static boolean dbInsert(String ad,String soyad,String kullaniciAdi,String sifre,String dil) {

        // Step 1: Establishing a Connection and 
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
            .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, ad);
            preparedStatement.setString(2, soyad);
            preparedStatement.setString(3, kullaniciAdi);
            preparedStatement.setString(4, sifre);
            preparedStatement.setString(5, dil);

            System.out.println(preparedStatement);

           System.out.println(preparedStatement.executeUpdate()); 
           return true;


        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
            return false;

        }
    }

    public static boolean dbInsertGida( String urunadi,String marka,int miktar,int enerji,String saklamakosullari,String üretimyeri,String icindekiler) {
    	 try { 
    	Connection conn = DriverManager.getConnection(DATABASE_URL,DATABASE_USERNAME,DATABASE_PASSWORD); 
        Statement st = conn.createStatement(); 
        st.executeUpdate("INSERT INTO gida (urunadi,marka,miktar,enerji,saklamakosullari,uretimyeri,icindekiler)" + 
            " VALUES ('"+urunadi+"','"+marka+"','"+miktar+"','"+enerji+"','"+saklamakosullari+"','"+üretimyeri+"','"+icindekiler+"')"); 
        conn.close(); 
        return true;
    } catch (Exception e) { 
        System.err.println("Gida eklerken hata"+ e); 
        return false;
    }
    	 }
    
    public static boolean dbInsertBakimTemizlik(String  urunadi,String marka,int miktar,String saklamakosullari,String uyarilar,String icindekiler) {
   	 try { 
    	Connection conn = DriverManager.getConnection(DATABASE_URL,DATABASE_USERNAME,DATABASE_PASSWORD); 
        Statement st = conn.createStatement(); 
        st.executeUpdate("INSERT INTO bakimtemizlik (urunadi,marka,miktar,saklamakosullari,uyarilar,icindekiler)" + 
            " VALUES ('"+urunadi+"','"+marka+
            "','"+miktar+"','"+saklamakosullari+"','"+uyarilar+"','"+icindekiler+"')"); 
        conn.close(); 
        return true;
    } catch (Exception e) { 
        System.err.println("bakim eklerken hata "+ e); 
        return false;
    }
   	 
   	 }
    
}
