package org.example.holder;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class SessionIdHolder {
    private String sessionId;
}
