package tasklist.tasklistweb.service.impl;

import org.springframework.stereotype.Service;
import tasklist.tasklistweb.service.AuthService;
import tasklist.tasklistweb.web.dto.auth.JwtRequest;
import tasklist.tasklistweb.web.dto.auth.JwtResponse;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public JwtResponse login(JwtRequest loginRequest) {
        return null;
    }

    @Override
    public JwtResponse refresh(String refreshToken) {
        return null;
    }
}
