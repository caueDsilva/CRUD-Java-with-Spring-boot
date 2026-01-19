package com.projeto.demo.business;

import com.projeto.demo.infrastructure.entitys.Usuario;
import com.projeto.demo.infrastructure.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository){
        this.repository = repository;
    }

    public void salvarUsuario(Usuario usuario){
        repository.saveAndFlush(usuario);
    }

    public Usuario buscarUsuarioPorEmail(String email){
        return repository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Email nao encontrado")
        );
    }

    public Usuario buscarUsuarioPorCPF(String CPF){
        return repository.findByCPF(CPF).orElseThrow(
                () -> new RuntimeException("CPF nao encontrado")
        );
    }

    public void deletarUsuarioPorEmail(String email){
        repository.deleteByEmail(email);
    }

    // METODO IMPORTANTE PARA ATUALIZAR
    public void updateUsuarioPorId(Integer id, Usuario usuario) {
        Usuario usuarioEntity = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Usuario não encontrado"));
        Usuario usuarioAtualizado = Usuario.builder()
                .email(usuario.getEmail() != null ? usuario.getEmail() : usuarioEntity.getEmail())
                .name(usuario.getName() != null ? usuario.getName(): usuarioEntity.getName())
                .CPF(usuario.getCPF() != null ? usuario.getCPF() : usuarioEntity.getCPF())
                .id(usuarioEntity.getId())
                .build();
        repository.saveAndFlush(usuarioAtualizado);
    }
}
