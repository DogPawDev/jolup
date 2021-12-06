package project.eyack.jolup.vo;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Builder
public class Event {
    private String title;
    private String start;
    private String end;


}
