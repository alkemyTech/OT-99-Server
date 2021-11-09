package com.alkemy.ong.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityRequest {

    public String name;
    public String content;
    public String image;
    public LocalDateTime updateDateTime;
}
