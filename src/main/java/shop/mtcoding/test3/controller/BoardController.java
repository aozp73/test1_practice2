package shop.mtcoding.test3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BoardController {

    @GetMapping("/board/list")
    public String list() {
        return "board/list";
    }
}
