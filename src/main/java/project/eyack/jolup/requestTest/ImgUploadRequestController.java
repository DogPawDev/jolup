package project.eyack.jolup.requestTest;

import com.fasterxml.jackson.core.JsonParser;
import com.google.common.net.MediaType;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.GlobalHistogramBinarizer;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.GenericMultipleBarcodeReader;
import com.google.zxing.multi.MultipleBarcodeReader;
import com.google.zxing.oned.EAN13Reader;
import net.minidev.json.JSONObject;
import org.apache.tomcat.util.json.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Level;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class ImgUploadRequestController {


    @GetMapping(value = "/sel")
    public String sel(){
        EAN13Reader ean13Reader = new EAN13Reader();


        return "sel";
    }






    @GetMapping(value="/find2")
    public  String find2(@RequestParam("code") String code) throws IOException{

        URL url = new URL("http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList?ServiceKey=jkp77b%2FGBjh6kqF3EqxXowN2bi%2FV96WiIXpZJdPtd6CVmFr0plKxoFFWfjobZbzRKtBk7iqaIbG2Ju1bwNCjmw%3D%3D&type=json&itemName="+code);
        JSONObject responseJson = null;
        return "test";



    }



    @PostMapping(value = "/barcode")
    public String barcode2(@Valid @RequestParam("user") String user,
                          @Valid @RequestParam("content") String content,
                          @Valid @RequestParam("files")MultipartFile file){
        System.out.print("바코드 인식 API :");
        String test= null;

       // WebDriver driver = new ChromeDriver();
       // driver.get("https://nedrug.mfds.go.kr/searchDrug");
       // driver.findElement(By.xpath(""));
        // 바코드 조회하는거 공공 데이터에 정보 있으니까 활용하기 셀레니움 안해도 될듯


        try {


           FileOutputStream fos = new FileOutputStream("f:/img/"+file.getOriginalFilename());

           InputStream is = file.getInputStream();


            test = processStream(file.getInputStream());
            System.out.println(test);



            int readCount = 0;
           byte[] buffer = new byte[1024];
           while((readCount = is.read(buffer)) != -1){
               fos.write(buffer,0,readCount);
           }


           fos.close();

       }catch (Exception ex)
       {
           throw new RuntimeException("file save errer");
       }







       return "변환 결과 :" + test;
   }


    private static String processStream(InputStream is
                                     ) throws ServletException, IOException {
        String re = null;
        BufferedImage image;
        try {
            image = ImageIO.read(is);
        } catch (Exception e) {
            // Many possible failures from JAI, so just catch anything as a failure

            return "err";
        }
        if (image == null) {

            return "err";
        }
        try {
            int height = image.getHeight();
            int width = image.getWidth();


            re=processImage(image);
        } finally {
            image.flush();
            return re;

        }

    }

    private static String processImage(BufferedImage image) throws IOException, ServletException {

        LuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new GlobalHistogramBinarizer(source));
        Collection<Result> results = new ArrayList<>(1);

        try {

            com.google.zxing.Reader reader = new MultiFormatReader();
            ReaderException savedException = null;
            try {
                // Look for multiple barcodes
                MultipleBarcodeReader multiReader = new GenericMultipleBarcodeReader(reader);
                Result[] theResults = multiReader.decodeMultiple(bitmap);
                if (theResults != null) {
                    results.addAll(Arrays.asList(theResults));
                }
            } catch (ReaderException re) {
                savedException = re;
            }

            if (results.isEmpty()) {
                try {
                    // Look for pure barcode
                    Result theResult = reader.decode(bitmap);
                    if (theResult != null) {
                        results.add(theResult);
                    }
                } catch (ReaderException re) {
                    savedException = re;
                }
            }

            if (results.isEmpty()) {
                try {
                    // Look for normal barcode in photo
                    Result theResult = reader.decode(bitmap);
                    if (theResult != null) {
                        results.add(theResult);
                    }
                } catch (ReaderException re) {
                    savedException = re;
                }
            }

            if (results.isEmpty()) {
                try {
                    // Try again with other binarizer
                    BinaryBitmap hybridBitmap = new BinaryBitmap(new HybridBinarizer(source));
                    Result theResult = reader.decode(hybridBitmap);
                    if (theResult != null) {
                        results.add(theResult);
                    }
                } catch (ReaderException re) {
                    savedException = re;
                }
            }

            if (results.isEmpty()) {
                try {
                    throw savedException == null ? NotFoundException.getNotFoundInstance() : savedException;
                } catch (FormatException | ChecksumException e) {

                } catch (ReaderException e) { // Including NotFoundException

                }
                return "err";
            }

        } catch (RuntimeException re) {
            // Call out unexpected errors in the log clearly

            throw new ServletException(re);
        }

        return results.toString();
    }




}
