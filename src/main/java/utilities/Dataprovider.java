package utilities;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static utilities.Base.prop;

public class Dataprovider {

    //TO USE DATA PROVIDER USE IN THE TEST ANNOTATION @TEST(dataProvider="DD_NAME,dataProviderClass = "DataProviderPool.class") .
    // also in the test method write Public void TestName(String input,....) {}
    @DataProvider(name = "DP_NAME") // Get A Form
    public static Object[][] DPFromFile() {

        File tFile = new File( System.getProperty("user.dir") + "DDTFiles/" + (String)prop.get("appNameAndroid"));
        FileReader fileReader = null;
        List<String> lines = new ArrayList<String>();
        try {
            String line = null;
            fileReader = new FileReader( tFile );
            BufferedReader bufferedReader = new BufferedReader( fileReader );
            while ((line = bufferedReader.readLine()) != null ) {
                lines.add(line); //read all the file line to list
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int column_numbers = (lines.get(0).length() - lines.get(0).replace(",", "").length()) +1; //count the char "," to know how many columns
        Object[][] data = new Object[lines.size()][column_numbers];
        int count = 0 ; //file line counter
        for ( String lin : lines ) {
            StringTokenizer st = new StringTokenizer( lin, "," );
            for (int i =0 ; i <= st.countTokens(); i++) {
                data[count][i] = st.nextToken();
            }
            count++;
        }
        return data;
    }
}
