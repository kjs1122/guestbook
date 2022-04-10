package org.zerock.guestbook.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.guestbook.dto.GuestbookDTO;
import org.zerock.guestbook.dto.PageRequestDTO;
import org.zerock.guestbook.service.GuestbookService;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/guestbook")
public class GuestbookController {

    private final GuestbookService guestbookService;

    /**
     * 인덱스 -> 리스트로 이동
     */
    @GetMapping("/")
    public String index() {
        return "redirect:/guestbook/list";
    }

    /**
     * 리스트로 이동
     */
    @GetMapping("/list")
    public String list(PageRequestDTO pageRequestDTO, Model model) {

        log.info("list........................ {}", pageRequestDTO);

        model.addAttribute("result", guestbookService.getList(pageRequestDTO));

        return "guestbook/list";
    }

    /**
     * 새 글 등록폼으로 이동
     */
    @GetMapping("/register")
    public String register(@ModelAttribute("dto") GuestbookDTO dto) {
        log.info("register get .................");
        return "guestbook/register";
    }

    /**
     * 새 글 등록
     */
    @PostMapping("/register")
    public String registerPost(
            @Validated @ModelAttribute("dto") GuestbookDTO dto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (!(dto.getContent().length() >= 100)) {
            bindingResult.reject("contentLength", new Object[]{100}, null);
        }

        if (bindingResult.hasErrors()) {
            return "guestbook/register";
        }

        log.info("dto................{}", dto);

        redirectAttributes.addFlashAttribute("msg", guestbookService.register(dto));

        return "redirect:/guestbook/list";
    }

    /**
     *  리스트로 이동
     */
    @GetMapping("/read")
    public String read(
            Long gno,
            @ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO,
            Model model) {

        log.info("gno : {}", gno);

        model.addAttribute("dto", guestbookService.read(gno));

        return "guestbook/read";
    }
}
