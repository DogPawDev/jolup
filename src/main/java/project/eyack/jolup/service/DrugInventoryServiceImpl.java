package project.eyack.jolup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.eyack.jolup.mapper.DrugInventoryMapper;
import project.eyack.jolup.mapper.NoticeAndOpTimeMapper;
import project.eyack.jolup.vo.DrugInventory;
import project.eyack.jolup.vo.NoticeAndOpTimeInsert;
import project.eyack.jolup.vo.NoticeAndOpTimeSelect;
import project.eyack.jolup.vo.QnA;

import java.util.List;

@Service
public class DrugInventoryServiceImpl implements DrugInventoryService{

    private final DrugInventoryMapper mapper;



    @Autowired
    public DrugInventoryServiceImpl(DrugInventoryMapper drugInventoryMapper){
        this.mapper = drugInventoryMapper;
    }


    @Override
    public List<DrugInventory> getNoticeList() {
        return mapper.getDrugList();
    }

    @Override
    public List<QnA> getQnaList() {
        return mapper.getQnaList();
    }

    @Override
    public int insertDrugInventory(DrugInventory drugInventory) {
        return mapper.insertDrugInventory(drugInventory);
    }
}
