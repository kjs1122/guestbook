package org.zerock.guestbook.service;

import org.zerock.guestbook.dto.GuestbookDTO;
import org.zerock.guestbook.dto.PageRequestDTO;
import org.zerock.guestbook.dto.PageResultDTO;
import org.zerock.guestbook.entity.Guestbook;

public interface GuestbookService {

    // 게시글 조회 (페이징 처리)
    PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO resultDTO);
    // 게시글 등록
    Long register(GuestbookDTO dto);
    // 상세 글 조회
    GuestbookDTO read(Long gno);
    // 게시글 삭제
    void remove(Long gno);
    // 게시글 수정
    void modify(GuestbookDTO dto);

    // default 메소드
    /**
     * DTO -> Entity
     */
    default Guestbook dtoToEntity(GuestbookDTO dto) {
        Guestbook entity = Guestbook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();

        return entity;
    }

    /**
     *  Entity -> DTO
     */
    default GuestbookDTO entityToDto(Guestbook entity) {

        GuestbookDTO dto = GuestbookDTO.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();

        return dto;
    }
}
