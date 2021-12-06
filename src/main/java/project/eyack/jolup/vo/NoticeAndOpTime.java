package project.eyack.jolup.vo;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Getter
@Setter

public class NoticeAndOpTime {

    private String hpId;
    private String[] weekDay;
    private String noticeTitle;
    private String noticeTextarea;
    private List<Event> events;
    private String startTime;
    private String endTime;
    private String adminId;


    @Override
    public String toString() {
        return "NoticeAndOpTime{" +
                "hpId='" + hpId + '\'' +
                ", weekDay=" + Arrays.toString(weekDay) +
                ", noticeTitle='" + noticeTitle + '\'' +
                ", noticeTextarea='" + noticeTextarea + '\'' +
                ", events=" + events +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", adminId='" + adminId + '\'' +
                '}';
    }
}
// 공지사항
