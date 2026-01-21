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
    public void atualizarUsuarioPorId(Integer id, Usuario usuario) {
        Usuario usuarioEntity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        if (usuario.getEmail() != null) {
            usuarioEntity.setEmail(usuario.getEmail());
        }

        if (usuario.getName() != null) {
            usuarioEntity.setName(usuario.getName());
        }

        if (usuario.getCPF() != null) {
            usuarioEntity.setCPF(usuario.getCPF());
        }

        repository.save(usuarioEntity);
    }

}
