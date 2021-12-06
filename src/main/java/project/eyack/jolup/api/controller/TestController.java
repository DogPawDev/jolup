package project.eyack.jolup.api.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@CrossOrigin
public class TestController {


    //  엑셀 인식 페이지
    @GetMapping(value = "/ex")
    public String ex(){
        return "excel";
    }

    //  엑셀 인식 페이지
    @GetMapping(value = "/extest")
    public String extest(){
        return "exceltest";
    }


    //  바코드 인식 페이지
    @GetMapping(value = "/img")
    public String img(){
        return "img";
    }


    //  공지사항 및 운영시간 설정 페이지
    @GetMapping(value="/notice")
    public String noticde(){return "notice";}
    //  공지사항 및 운영시간 설정 페이지


    @GetMapping(value="/noticetest")
    public String noticdetest(){return "noticetest";}



    @GetMapping(value="/admin")
    public String admin(){return "admin";}



//    zxing 라이브러리 테스트 용
//    @GetMapping(value = "/home")
//    public String barcode(){
//        return "home";
//    }


//    테스트 용
//    @GetMapping(value = "/test")
//    public String test(){return "test";}




}
