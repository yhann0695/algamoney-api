package com.algaworks.algamoneyapi.domain.security;

import com.algaworks.algamoneyapi.domain.model.Usuario;
import com.algaworks.algamoneyapi.domain.repository.IUsuarioRepository;
import com.algaworks.algamoneyapi.utils.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptinal = usuarioRepository.findByEmail(email);
        Usuario usuario = usuarioOptinal.orElseThrow(() -> new UsernameNotFoundException(Mensagens.MSG_USUARIO_SENHA_INVALIDA));

        return new User(email, usuario.getSenha(), getPermissoes(usuario));
    }

    private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario) {
        Set<SimpleGrantedAuthority> authorites = new HashSet<>();
        usuario.getPermissao().forEach(p -> authorites.add(new SimpleGrantedAuthority(p.getDescricao().toUpperCase(Locale.ROOT))));
        return authorites;
    }
}
