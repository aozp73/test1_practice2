package shop.mtcoding.test3.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.test3.model.Board;
import shop.mtcoding.test3.model.BoardRepository;
import shop.mtcoding.test3.model.User;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final HttpSession session;

    private final BoardRepository boardRepository;

    @GetMapping("/board/list")
    public String list(Model model) {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            return "redirect:/loginForm";
        }

        List<Board> boardList = boardRepository.findByUserId(principal.getId());
        model.addAttribute("boardList", boardList);

        return "board/list";
    }
}
