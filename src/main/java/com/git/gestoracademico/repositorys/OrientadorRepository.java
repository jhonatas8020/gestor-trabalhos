package com.git.gestoracademico.repositorys;

import com.git.gestoracademico.models.Orientador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrientadorRepository extends JpaRepository<Orientador, Long> {
}
