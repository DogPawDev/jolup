package project.eyack.jolup.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NoticeAndOpTimeSelect {

    private String hpId;
    private String weekDay;
    private String noticeTitle;
    private String noticeTextarea;
    private String events;
    private String adminId;
    private String startTime;
    private String endTime;

    @Override
    public String toString() {
        return "NoticeAndOpTimeSelect{" +
                "hpId='" + hpId + '\'' +
                ", weekDay='" + weekDay + '\'' +
                ", noticeTitle='" + noticeTitle + '\'' +
                ", noticeTextarea='" + noticeTextarea + '\'' +
                ", events='" + events + '\'' +
                ", adminId='" + adminId + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
// 공지사항
