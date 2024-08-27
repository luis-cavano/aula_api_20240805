package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.model.Usuario;
import application.service.TokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
    @Autowired
    private AuthenticationManager authManager;
    
    @Autowired
    private TokenService tokenService;

    @PostMapping        
    public String login(@RequestBody Usuario usuario) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(usuario.getNomeDeUsuario(), usuario.getSenha());
        Authentication authentication = authManager.authentication(token);
    
        return tokenService.generateToken(usuario);
    }
    

}
