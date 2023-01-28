package shop.mtcoding.test3.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.test3.model.User;
import shop.mtcoding.test3.model.UserRepository;
import shop.mtcoding.test3.util.Script;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserRepository userRepository;

    private final HttpSession session;

    @PostMapping("/login")
    @ResponseBody
    public String login(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user == null) {
            return Script.back("로그인 실패");
        }

        session.setAttribute("principal", user);
        return Script.href("/board/list");
    }

    @PostMapping("/join")
    @ResponseBody
    public String join(String username, String password, String email) {

        try {
            userRepository.insert(username, password, email);
        } catch (Exception e) {
            return Script.back("회원가입실패");
        }
        return Script.href("/loginForm");
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/user/updateForm")
    public String updateForm() {
        return "user/updateForm";
    }

    @GetMapping("/logout")
    @ResponseBody
    public String logout() {
        session.invalidate();
        return Script.href("/loginForm", "로그아웃");
    }

}
