package project.eyack.jolup.api.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.eyack.jolup.service.NoticeAndOpTimeService;
import project.eyack.jolup.vo.NoticeAndOpTime;
import project.eyack.jolup.vo.NoticeAndOpTimeInsert;


@Controller
@CrossOrigin
@RestController
public class NoticeController {

    private final NoticeAndOpTimeService service;

    @Autowired
    public NoticeController(NoticeAndOpTimeService noticeAndOpTimeService){
        this.service = noticeAndOpTimeService;
    }


    @GetMapping(value = "/noticeDB")
    public  @ResponseBody  String getNoticeAndOpTimeList()
    {
      System.out.println(service.getNoticeList());



        ObjectMapper mapper = new ObjectMapper();

        try {



            System.out.println(mapper.writeValueAsString(service.getNoticeList()));

            JsonNode myJsonNode = mapper.readTree(mapper.writeValueAsString(service.getNoticeList()));


            String temp = myJsonNode.toString().replace("\\","");
            String temp2 = temp.replace("\"[","[").replace("]\"","]");


            System.out.println(temp2);

            return temp2;
        }catch (JsonGenerationException e){
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

//        return service.getNoticeList();
        return "test";

    }




    @PostMapping(value = "/insertNotice")
    public String
     insertNotice(@RequestBody NoticeAndOpTime noticeAndOpTime)
    {



        ObjectMapper mapper = new ObjectMapper();


        try {
            System.out.println(mapper.writeValueAsString(noticeAndOpTime));
            String weekday = mapper.writeValueAsString(noticeAndOpTime.getWeekDay());
            String events = mapper.writeValueAsString(noticeAndOpTime.getEvents());
            NoticeAndOpTimeInsert noticeAndOpTimeInsert = NoticeAndOpTimeInsert.builder()
                    .weekDay(weekday)
                    .hpId(noticeAndOpTime.getHpId())
                    .adminId(noticeAndOpTime.getAdminId())
                    .noticeTitle(noticeAndOpTime.getNoticeTitle())
                    .noticeTextarea(noticeAndOpTime.getNoticeTextarea())
                    .events(events)
                    .startTime(noticeAndOpTime.getStartTime())
                    .endTime(noticeAndOpTime.getEndTime())
                    .build();


            service.insertNoticeAndOpTime(noticeAndOpTimeInsert);



        }catch (JsonGenerationException e){
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }



        return "test";

    }


}
