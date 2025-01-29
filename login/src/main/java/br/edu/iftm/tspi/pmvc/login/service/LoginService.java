package br.edu.iftm.tspi.pmvc.login.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import br.edu.iftm.tspi.pmvc.login.repository.LoginRepository;

import br.edu.iftm.tspi.pmvc.login.domain.Login;

@Service
public class LoginService {
    private LoginRepository repository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public LoginService(LoginRepository repository) {

        this.repository = repository;

    }

    public boolean verificaLoginSenha(Login loginDigitado) {

        Login loginBanco = repository.verificarLogin(loginDigitado);

        if (loginBanco == null) {

            return false;

        }

        return encoder.matches(loginDigitado.getSenha(), loginBanco.getSenha());

    }
}
