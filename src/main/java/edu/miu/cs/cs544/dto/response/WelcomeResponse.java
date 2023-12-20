package edu.miu.cs.cs544.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WelcomeResponse {

    public boolean healthy;
    public String message;

}
