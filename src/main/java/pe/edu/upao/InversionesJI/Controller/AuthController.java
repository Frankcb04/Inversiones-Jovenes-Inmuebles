package pe.edu.upao.InversionesJI.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upao.InversionesJI.Request.LoginRequest;
import pe.edu.upao.InversionesJI.Request.RegisterClienteRequest;
import pe.edu.upao.InversionesJI.Response.AuthResponse;
import pe.edu.upao.InversionesJI.Service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register/cliente")
    public ResponseEntity<AuthResponse> registerCliente(@RequestBody RegisterClienteRequest request){
        return ResponseEntity.ok(authService.registerCliente(request));
    }

}