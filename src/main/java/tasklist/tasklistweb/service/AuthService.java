package tasklist.tasklistweb.service;


import tasklist.tasklistweb.web.dto.auth.JwtRequest;
import tasklist.tasklistweb.web.dto.auth.JwtResponse;

public interface AuthService {

    JwtResponse login(JwtRequest loginRequest);

    JwtResponse refresh(String refreshToken);
}
