package com.upc.jara.repository;

import com.upc.jara.entity.ClientCine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<ClientCine, Long> {
        ClientCine findByJlDni(Long dni);


}
