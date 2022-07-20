package utilities;

import io.qameta.allure.Step;
import org.testng.annotations.DataProvider;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;   // Import the FileWriter class


public class ManageDDT extends CommonOps{

    /*
    @DataProvider(name = "Data-provider-users")
    public Object[][] getDataObject() {
        return getDataFromCSV(getData("DDTFile"));
    }

    public static List<String> readCSV(String CSVfile)    {
        List<String> lines = new ArrayList<>();
        File file = new File(CSVfile);
        try {
            lines =Files.readAllLines(file.toPath(), StandardCharsets.UTF_8); // LIST = LIST
        } catch (IOException e) {
            System.out.println("An error occurred." + e);
        }
        return lines;
    }


    @Step("reading  CSV file to array")
    public static String[][] getDataFromCSV(String filepath) {
        List<String> csvData = readCSV(filepath);
        try {
            if (csvData.size() == 0) return null; //if the file empty
            String[][] data = new String[csvData.size()][5];

            for (int i = 0; i < csvData.size(); i++) {
                data[i][0] = csvData.get(i).split(",")[0]; //id
                data[i][1] = csvData.get(i).split(",")[1]; //name
                data[i][2] = csvData.get(i).split(",")[2]; //stage
                data[i][3] = csvData.get(i).split(",")[3]; //possition
                data[i][4] = csvData.get(i).split(",")[4]; //message_sent

            }
            return data;
            }
        catch (ArrayIndexOutOfBoundsException E) {
           return null;
        }
    }

        @Step("Writing CSV file")
        public static void WriteToCSV(String fullName,String position) {
            try {
                File file = new File(getData("DDTFile"));
                FileWriter writer = new FileWriter(file, true);
               candidate_list = ManageDDT.getDataFromCSV(getData("DDTFile")); //refresh the users array if user was added

                if (candidate_list == null) { //if the file empty
                    writer.write("1" + "," + fullName + ",1," + position + "," + "no" + "\n");
                }
                else {
                    writer.write(Integer.parseInt(candidate_list[candidate_list.length - 1][0]) + 1 + "," + fullName + ",1" + "," + position + "," + "no" + "\n");
                }

                writer.close();
                System.out.println("Successfully wrote to the file.");

            } catch (IOException e) {
                System.out.println("An error occurred." + e);
            }
        }

    @Step("Updating CSV file")
    public static void UpdateCSV() {
        try {
            FileWriter myWriter = new FileWriter(getData("DDTFile"));
            //for (int i = 0 ; i < candidate_list.length ; i++) {
              //  myWriter.write(candidate_list[i][0] + ","  + candidate_list[i][1]  + "," + candidate_list[i][2] + "," + candidate_list[i][3] + "," + candidate_list[i][4]);
            //}
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred." + e);
        }

    }
        */
}
