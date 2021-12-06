package project.eyack.jolup.vo;


import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class QnA {

    private String qnaContent;
    private String userId;
    private Timestamp qnaDate;


}
