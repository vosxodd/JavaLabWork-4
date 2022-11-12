package Lab4;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** Singleton-class for working with files. */
public class FileService {
    private static String PATH;
    private static FileService INSTANCE;

    private FileService(String path) {
        PATH = path;
    }

    public static FileService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FileService(PATH);
        }
        return INSTANCE;
    }

    public static void setPATH(String PATH) {
        FileService.PATH = PATH;
    }

    public void createFile(String path) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(path));
        setPATH(path);
        writer.close();
    }

    /** Add new record in csv file with given array of personal data. */
    public void addRecord(String[] data) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(PATH, true));
        writer.writeNext(data);
        writer.close();
    }

    /** Remove record in csv file with given row number (starts with 0). */
    public void removeRecord(int rowNumber) throws IOException, CsvException {
        CSVReader reader = new CSVReader(new FileReader(PATH));
        List<String[]> allElements = reader.readAll();
        int size = allElements.size();
        allElements.remove(rowNumber);
        // Decreasing all row numbers that come after the removed one
        List<String[]> firstPartOfElements = allElements.subList(0, rowNumber);
        List<String[]> secondPartOfElements = allElements.subList(rowNumber, size - 1);
        for (String[] line: secondPartOfElements) {
            line[0] = String.valueOf(Integer.parseInt(line[0]) - 1);
        }
        List<String[]> newList = new ArrayList<>(firstPartOfElements);
        newList.addAll(secondPartOfElements);
        FileWriter sw = new FileWriter(PATH);
        CSVWriter writer = new CSVWriter(sw);
        writer.writeAll(newList);
        writer.close();
    }

    /** Return true if the account with given email exists in csv file. Return false if it does not. */
    public boolean isAlreadyInFile(String email) throws IOException, CsvException {
        CSVReader reader = new CSVReader(new FileReader(PATH));
        List<String[]> allElements = reader.readAll();
        for (String[] line: allElements) {
            if (line[3].equals(email)) {
                return true;
            }
        }
        return false;
    }
}