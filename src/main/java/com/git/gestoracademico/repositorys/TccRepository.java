package com.git.gestoracademico.repositorys;

import com.git.gestoracademico.models.Tcc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TccRepository extends JpaRepository<Tcc, Long> {
}
