package project.eyack.jolup.service;

import org.springframework.stereotype.Service;
import project.eyack.jolup.vo.NoticeAndOpTime;
import project.eyack.jolup.vo.NoticeAndOpTimeInsert;
import project.eyack.jolup.vo.NoticeAndOpTimeSelect;

import java.util.List;


@Service
public interface NoticeAndOpTimeService {


    public List<NoticeAndOpTimeSelect> getNoticeList();

    public int insertNoticeAndOpTime(NoticeAndOpTimeInsert noticeAndOpTime);

}
