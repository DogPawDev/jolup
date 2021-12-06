package project.eyack.jolup.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import project.eyack.jolup.mapper.NoticeAndOpTimeMapper;
import project.eyack.jolup.vo.NoticeAndOpTime;
import project.eyack.jolup.vo.NoticeAndOpTimeInsert;
import project.eyack.jolup.vo.NoticeAndOpTimeSelect;

import java.util.List;

@Service
public class NoticeAndOpTimeServiceImpl implements NoticeAndOpTimeService{

    private final NoticeAndOpTimeMapper mapper;



    @Autowired
    public NoticeAndOpTimeServiceImpl(NoticeAndOpTimeMapper noticeAndOpTimeMapper){
        this.mapper = noticeAndOpTimeMapper;
    }


    @Override
    public List<NoticeAndOpTimeSelect> getNoticeList() {
        return mapper.getNoticeList();
    }

    @Override
    public int insertNoticeAndOpTime(NoticeAndOpTimeInsert noticeAndOpTime) {
        System.out.println("Ipl = " + noticeAndOpTime.toString());
        mapper.insertNoticeAndOpTime(noticeAndOpTime);
        return 1;
    }



}
