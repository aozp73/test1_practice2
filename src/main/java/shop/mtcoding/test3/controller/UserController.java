package shop.mtcoding.test3.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PostMapping("/user/{id}/update")
    @ResponseBody
    public String update(@PathVariable int id, String password, String email) {
        // 1. 유효성 검사
        if (password == null || password.isEmpty()) {
            return Script.back("password 미입력");
        }
        if (email == null || email.isEmpty()) {
            return Script.back("email 미입력");
        }

        // 2. 인증 체크
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            return Script.href("/loginForm", "로그인이 필요합니다.");
        }

        // 3. 권한 체크
        if (principal.getId() != id) {
            return Script.back("수정권한이 없습니다.");
        }

        // 4. 회원정보 수정
        int res = userRepository.updateById(id, password, email);
        if (res != 1) {
            return Script.back("회원정보수정 실패");
        }

        // 5. 세션 동기화
        User user = userRepository.findById(id);
        session.setAttribute("principal", user);

        return Script.href("/user/updateForm", "회원정보수정 완료");
    }

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

    @GetMapping("/user/updateForm")
    public String updateForm(Model model) {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            return "redirect:/loginForm";
        }

        User user = userRepository.findById(principal.getId());
        model.addAttribute("user", user);

        return "user/updateForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/logout")
    @ResponseBody
    public String logout() {
        session.invalidate();
        return Script.href("/loginForm", "로그아웃");
    }

}
