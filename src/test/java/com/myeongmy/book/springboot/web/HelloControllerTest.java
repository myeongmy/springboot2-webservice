package com.myeongmy.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)   //테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자 실행(여기선 SpringRunner)
                               //스프링 부트 테스트와 JUnit 사이에 연결자 역할을 해줌
@WebMvcTest(controllers = HelloController.class)   //스프링 어노테이션 중 web(spring mvc) 기능에 집중한 어노테이션
                                                   //@Controllere, @ControllerAdvice 등을 사용할 수 있고, @Service, @Component, @Repository 등은 사용 불가

public class HelloControllerTest {
    @Autowired   // 스프링이 관리하는 자바 빈(Bean) 객체 주입
    private MockMvc mvc;      //웹 API를 테스트 할 때 사용, 이 클래스를 통해 http get, post 등에 대한 API 테스트 가능
    
    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))   //MockMvc 클래스를 이용해 get 방식으로 /hello 주소로 요청 보냄
        .andExpect(status().isOk())  //http 응답 헤더 status 검증
        .andExpect(content().string(hello));  //http 응답 body 내용 검증
    }

    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        //param은 Api를 요청할 때 파라미터를 보낼 때 사용(이 때, 파라미터는 문자열 형태로만 보낼 수 있다.)
        mvc.perform(get("/hello/dto").param("name", name).param("amount", Integer.toString(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));  //jsonPath: 응답 json값을 필드별로 검증할 때 사용
    }
}
