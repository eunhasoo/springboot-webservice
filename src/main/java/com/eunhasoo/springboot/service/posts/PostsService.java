package com.eunhasoo.springboot.service.posts;

import com.eunhasoo.springboot.domain.posts.Posts;
import com.eunhasoo.springboot.domain.posts.PostsRepository;
import com.eunhasoo.springboot.web.dto.PostsResponseDto;
import com.eunhasoo.springboot.web.dto.PostsSaveRequestDto;
import com.eunhasoo.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent()); // 비즈니스 로직 처리는 domain에서 구현하도록 한다.
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id="+ id));
        return new PostsResponseDto(entity);
    }
}