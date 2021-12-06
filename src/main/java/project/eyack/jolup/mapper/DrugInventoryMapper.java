package project.eyack.jolup.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import project.eyack.jolup.vo.DrugInventory;
import project.eyack.jolup.vo.NoticeAndOpTimeInsert;
import project.eyack.jolup.vo.NoticeAndOpTimeSelect;
import project.eyack.jolup.vo.QnA;

import java.util.List;

@Repository
@Mapper
public interface DrugInventoryMapper {

    List<DrugInventory> getDrugList();

    int insertDrugInventory(DrugInventory drugInventory);

    List<QnA> getQnaList();
//    int insertEvent(Event event);
}
