package com.axelav95.gestion_postulantes.application.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.axelav95.gestion_postulantes.infrastructure.persistence.entity.UsuarioEntity;
import com.axelav95.gestion_postulantes.infrastructure.persistence.jpa.UsuarioJpaRepository;
import com.axelav95.gestion_postulantes.infrastructure.security.JwtService;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioJpaRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthController(UsuarioJpaRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = passwordEncoder.encode(body.get("password"));
        String rol = body.getOrDefault("rol", "USER");

        UsuarioEntity usuario = new UsuarioEntity(username, password, rol);
        usuarioRepository.save(usuario);
        return Map.of("message", "Usuario registrado con éxito");
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        UsuarioEntity usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(password, usuario.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtService.generarToken(username);
        return Map.of("token", token);
    }
}