package com.example.api.medicos.controllers;

import com.example.api.medicos.domain.usuarios.DadosAuthParams;
import com.example.api.medicos.domain.usuarios.Usuarios;
import com.example.api.medicos.infra.security.DadosTokenJWT;
import com.example.api.medicos.infra.security.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager ;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity AuthLogin(@RequestBody @Valid DadosAuthParams data){
        var token = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var authentication = authenticationManager.authenticate(token);
        var tokenJWT = tokenService.gerarToken(((Usuarios) authentication.getPrincipal()));

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}
