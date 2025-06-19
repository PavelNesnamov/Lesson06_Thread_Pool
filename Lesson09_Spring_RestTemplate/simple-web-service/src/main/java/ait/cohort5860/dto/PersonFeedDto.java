package ait.cohort5860.dto;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonFeedDto {
    private String fullName;
    @Singular
    private List<String> foods;
}
