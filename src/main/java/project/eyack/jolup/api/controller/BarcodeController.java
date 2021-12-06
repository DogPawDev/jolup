package project.eyack.jolup.api.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.eyack.jolup.vo.Pill;
import project.eyack.jolup.vo.VideoBarcode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class BarcodeController {

//  수정 중  - 바코드 인식 후 실제 있 는 약인지 조회
    @PostMapping(value="/find")
    public @ResponseBody List<Pill> find(@RequestBody VideoBarcode code) throws IOException {


//        if(code.equals("undefined"))
//        {
////            return "code";
//        }


        List<Pill> resJsonData = new ArrayList<Pill>();

        for(int i=0; i<code.getPills().size(); i++){

            Document document = Jsoup.connect("https://nedrug.mfds.go.kr/searchDrug?sort=&sortOrder=false&searchYn=true&page=1&searchDivision=detail&itemName=&entpName=&ingrName1=&ingrName2=&ingrName3=&itemSeq=&stdrCodeName="+code.getPills().get(i).getBarcode()+"&atcCodeName=&indutyClassCode=&sClassNo=&narcoticKindCode=&cancelCode=&etcOtcCode=&makeMaterialGb=&searchConEe=AND&eeDocData=&searchConUd=AND&udDocData=&searchConNb=AND&nbDocData=&startPermitDate=&endPermitDate=").get();

            Pill tempData = new Pill();
            Elements elements = document.select("tbody");

            tempData.setBarcode(code.getPills().get(i).getBarcode());
            tempData.setManName(elements.select("td").select("span").get(3).text());
            tempData.setPillName(elements.select("td").select("span").get(1).text());
            tempData.setProductCode(elements.select("td").select("span").get(5).text());
            tempData.setType(code.getPills().get(i).getType());
            resJsonData.add(tempData);
        }

        return resJsonData;

//        return "{ \"barcode\" : \""+barcode+"\", \"company\":\""+manName+"\", \"name\": \""+pillName+"\", \"productCode\": \""+productCode+"\"     }";
    }


}
