package com.example.mvc.basic.response;

import com.example.mvc.basic.HelloData;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@Slf4j
@Controller
public class ResponseBodyController {

    /**
     * 서블릿을 직접 다룰때처럼 HttpServletResponse 객체를 통해서 HTTP 메세지 바디에 직접 ok 응답 메세지 전달
     * @param response
     * @throws IOException
     */
    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(final HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }

    /**
     * ResponseEntity는 HttpEntity를 상속받았는데, HttpEntity는 Http 메세지의 헤더, 바디 정보를 가지고 있다
     * 추가로, ResponseEntity는 여기에 더해 HTTP 응답 코드를 설정할 수 있다.
     * HttpEntity, ResponseEntity(Http Status 추가)
     * @return
     */
    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    /**
     * @ResponseBody를 사용하면 view를 사용하지 않고, HTTP 메세지 컨버터를 통해서 HTTP 메세지를 직접 입력할 수 있다.
     * ResposneEntity도 동일한 방식으로 동작
     * @return
     */
    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3() {
        return "ok";
    }

    /**
     * HTTP 메세지 컨버터를 통해서 JSON 형식으로 변환되어서 반환
     * @return
     */
    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);

        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }

    /**
     * ResponseEntity는 HTTP 응답 코드를 설정할 수 있는데, @ResposneBody를 사용하면 이런 것을 설정하기가 까다롭다
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);

        return helloData;
    }
}
