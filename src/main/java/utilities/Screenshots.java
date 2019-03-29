package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Screenshots {

    public static void takeScreenshot(WebDriver driver, String fileName) throws IOException{
        String dateName = new SimpleDateFormat("yyyy.MMMMM.dd hh_mm_ss").format(new Date());
        fileName = fileName + dateName + ".png";
        String directory = "/Users/developer/Desktop/SecurerTest/src/main/java/utilities/Screenshots-Securer/";
        File sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourceFile,new File(directory +fileName));
    }

}
