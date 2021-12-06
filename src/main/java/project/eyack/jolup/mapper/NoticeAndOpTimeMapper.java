package project.eyack.jolup.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import project.eyack.jolup.vo.Event;
import project.eyack.jolup.vo.NoticeAndOpTime;
import project.eyack.jolup.vo.NoticeAndOpTimeInsert;
import project.eyack.jolup.vo.NoticeAndOpTimeSelect;

import java.util.List;

@Repository
@Mapper
public interface NoticeAndOpTimeMapper {

    List<NoticeAndOpTimeSelect> getNoticeList();

    int insertNoticeAndOpTime(NoticeAndOpTimeInsert noticeAndOpTime);

//    int insertEvent(Event event);
}
