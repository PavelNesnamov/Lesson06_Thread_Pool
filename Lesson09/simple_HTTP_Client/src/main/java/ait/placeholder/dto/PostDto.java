package ait.placeholder.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(of = {"id"})
@ToString
@Getter
public class PostDto {
    private int userId;
    private int id;
    private String title;
    private String body;

}


