package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class FilesParsingTest {
    ClassLoader cl = FilesParsingTest.class.getClassLoader();

    @Test
    void pdfParseTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("files/sample.pdf")) {
            PDF pdf = new PDF(is);
            assertThat(pdf.text).contains("A Simple PDF File ");
        }
    }

    @Test
    void xlsParseTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("files/sample-simple-2.xls")) {
            XLS xls = new XLS(is);
            assertThat(xls.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue()).contains("test1");
            assertThat(xls.excel.getSheetAt(1).getRow(0).getCell(0).getStringCellValue()).contains("test2");
        }
    }


    @Test
    void csvParseTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("files/test.csv");
             CSVReader csvReader = new CSVReader(new InputStreamReader(is))) {
            List<String[]> csvContent = csvReader.readAll();
            assertThat(csvContent.get(0)[0]).contains("name");
            assertThat(csvContent.get(0)[1]).contains("age");
            assertThat(csvContent.get(1)[0]).contains("Kirill");
            assertThat(csvContent.get(1)[1]).contains("32");
            assertThat(csvContent.get(2)[0]).contains("Ivan");
            assertThat(csvContent.get(2)[1]).contains("28");
        }
    }

    @Test
    void zipParseTest() throws Exception {
        ZipFile zf = new ZipFile(new File("src/test/resources/zip/sample.zip"));
        ZipInputStream is = new ZipInputStream(cl.getResourceAsStream("zip/sample.zip"));
        ZipEntry entry;
        while ((entry = is.getNextEntry()) != null) {
            try (InputStream inputStream = zf.getInputStream(entry)) {
                if (entry.getName().contains(".pdf")) {
                    PDF pdf = new PDF(inputStream);
                    assertThat(pdf.text).contains("A Simple PDF File");
                } else if (entry.getName().contains(".xls")) {
                    XLS xls = new XLS(inputStream);
                    assertThat(xls.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue()).isEqualTo("test1");
                    assertThat(xls.excel.getSheetAt(1).getRow(0).getCell(0).getStringCellValue()).isEqualTo("test2");
                } else {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(is));
                    List<String[]> csvContent = csvReader.readAll();
                    assertThat(csvContent.get(0)[0]).contains("name");
                    assertThat(csvContent.get(0)[1]).contains("age");
                    assertThat(csvContent.get(1)[0]).contains("Kirill");
                    assertThat(csvContent.get(1)[1]).contains("32");
                    assertThat(csvContent.get(2)[0]).contains("Ivan");
                    assertThat(csvContent.get(2)[1]).contains("28");
                }
            }
        }
    }
}
