package com.fabio.catalogo.respository;

import com.fabio.catalogo.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogoRepository extends JpaRepository<Musica, Long> {
    
}