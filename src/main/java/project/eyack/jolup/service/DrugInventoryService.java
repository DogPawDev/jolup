package project.eyack.jolup.service;

import org.springframework.stereotype.Service;
import project.eyack.jolup.vo.DrugInventory;
import project.eyack.jolup.vo.NoticeAndOpTimeInsert;
import project.eyack.jolup.vo.NoticeAndOpTimeSelect;
import project.eyack.jolup.vo.QnA;

import java.util.List;


@Service
public interface DrugInventoryService {


    public List<DrugInventory> getNoticeList();
    public List<QnA> getQnaList();
    public int insertDrugInventory(DrugInventory drugInventory);

}
