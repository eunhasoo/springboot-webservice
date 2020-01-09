package com.eunhasoo.springboot.service.posts;

import com.eunhasoo.springboot.domain.posts.Posts;
import com.eunhasoo.springboot.domain.posts.PostsRepository;
import com.eunhasoo.springboot.web.dto.PostsListResponseDto;
import com.eunhasoo.springboot.web.dto.PostsResponseDto;
import com.eunhasoo.springboot.web.dto.PostsSaveRequestDto;
import com.eunhasoo.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    // 게시글 저장
    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    // 게시글 수정
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts post = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        post.update(requestDto.getTitle(), requestDto.getContent()); // 비즈니스 로직 처리는 domain에서 구현하도록 한다.
        return id;
    }

    // 게시글 하나 조회
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));
        return new PostsResponseDto(entity);
    }

    // 게시글 전체 조회
    // readOnly : 트랜잭션 범위는 유지하되 조회 기능만 남겨두어 조회 속도 개선으로
    // 등록, 수정, 삭제 기능이 전혀 없는 서비스 메소드에서의 사용시 효과적이다.
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    // 게시글 하나 삭제
    @Transactional
    public void delete(Long id) {
        Posts post = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        postsRepository.delete(post);
        // postsRepository.deleteById(id)도 가능하다.
    }
}