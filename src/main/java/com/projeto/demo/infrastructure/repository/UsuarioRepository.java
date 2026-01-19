package com.projeto.demo.infrastructure.repository;

import com.projeto.demo.infrastructure.entitys.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    /* Utiliza-se do Jpa para salvar deletar */
    /*Optional -> evita o null pointer exception, obriga a criar uma exeção para caso nao ache o que vc busca*/

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByCPF(String CPF);

    //@Transactional -> Significa que qualquer erro ele nao deleta o usuario
    @Transactional
    void deleteByEmail(String email);


}
