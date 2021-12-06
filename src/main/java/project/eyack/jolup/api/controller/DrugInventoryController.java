package project.eyack.jolup.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.eyack.jolup.service.DrugInventoryService;
import project.eyack.jolup.vo.DrugInventory;
import project.eyack.jolup.vo.QnA;

import java.util.List;


@Controller
@CrossOrigin
@RestController
public class DrugInventoryController {

    private final DrugInventoryService service;

    @Autowired
    public DrugInventoryController(DrugInventoryService drugInventoryService){
        this.service = drugInventoryService;
    }


    @GetMapping(value = "/DrugList")
    public  @ResponseBody List<DrugInventory> getNoticeAndOpTimeList()
    {
        return service.getNoticeList();
    }


    @GetMapping(value = "/QnAList")
    public  @ResponseBody List<QnA> getNQnAList()
    {
        return service.getQnaList();
    }




    @PostMapping(value = "/insertDrug")
    public int insertNotice(@RequestBody DrugInventory drugInventory)
    {
        System.out.println(drugInventory.toString());
        return service.insertDrugInventory(drugInventory);
    }


}
