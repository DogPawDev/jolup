package project.eyack.jolup.api.barcodeSelect;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@CrossOrigin
public class BarcodeController {


    @GetMapping(value = "/home")
    public String barcode(){
        return "home";
    }

    @GetMapping(value = "/test")
    public String test(){return "test";}



}
