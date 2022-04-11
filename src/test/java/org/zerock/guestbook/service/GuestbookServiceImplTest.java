package org.zerock.guestbook.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.guestbook.dto.GuestbookDTO;
import org.zerock.guestbook.dto.PageRequestDTO;
import org.zerock.guestbook.dto.PageResultDTO;
import org.zerock.guestbook.entity.Guestbook;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GuestbookServiceImplTest {

    @Autowired
    private GuestbookService guestbookService;

    @Test
    void register() {

        GuestbookDTO guestbookDTO = GuestbookDTO.builder()
                .title("Sample Title")
                .content("Sample Content")
                .writer("user0")
                .build();

        System.out.println(guestbookService.register(guestbookDTO));
    }

    @Test
    void testList() {

        PageRequestDTO requestDTO = PageRequestDTO.builder().page(1).size(10).build();
        PageResultDTO<GuestbookDTO, Guestbook> resultDTO = guestbookService.getList(requestDTO);


        List<Integer> list = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());
        for (Integer integer : list) {
            System.out.println(integer);
        }

    }

    @Test
    void testSearch() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .type("tc")
                .keyword("한글")
                .build();

        PageResultDTO<GuestbookDTO, Guestbook> resultDTO = guestbookService.getList(pageRequestDTO);

        System.out.println("resultDTO.isPrev() = " + resultDTO.isPrev());
        System.out.println("resultDTO.isNext() = " + resultDTO.isNext());
        System.out.println("resultDTO.getTotalPage() = " + resultDTO.getTotalPage());

        System.out.println("=================================================");
        for (GuestbookDTO guestbookDTO : resultDTO.getDtoList()) {
            System.out.println("guestbookDTO = " + guestbookDTO);
        }
        System.out.println("=================================================");
        resultDTO.getPageList().forEach(i -> System.out.println("i = " + i));
    }
}