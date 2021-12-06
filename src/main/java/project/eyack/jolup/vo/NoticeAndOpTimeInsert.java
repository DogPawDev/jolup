package project.eyack.jolup.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NoticeAndOpTimeInsert {

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
        return "NoticeAndOpTimeInsert{" +
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
