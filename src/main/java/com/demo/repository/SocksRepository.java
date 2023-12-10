package com.demo.repository;

import com.demo.dto.SocksDto;
import com.demo.entity.Socks;
import com.demo.service.Operation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SocksRepository extends JpaRepository<Socks, Integer> {
    @Query(value = "SELECT id, color, cotton_part, quantity FROM Socks WHERE color =:color AND cotton_part =:cottonPart LIMIT 1", nativeQuery = true)
    Optional<Socks> findByColorAndCotton(@Param("color") String color,
                                         @Param("cottonPart") int cottonPart);

    @Query(value = "SELECT id, color, cotton_part, quantity FROM Socks WHERE color =:color AND cotton_part =:cottonPart LIMIT 1", nativeQuery = true)
    Optional<Socks> findByEquals(@Param("color") String color,
                                 @Param("cottonPart") int cottonPart);

    @Query(value = "SELECT id, color, cotton_part, quantity FROM Socks WHERE color =:color AND cotton_part >:cottonPart LIMIT 1", nativeQuery = true)
    Optional<Socks> findByMoreThen(@Param("color") String color,
                                   @Param("cottonPart") int cottonPart);

    @Query(value = "SELECT id, color, cotton_part, quantity FROM Socks WHERE color =:color AND cotton_part <:cottonPart LIMIT 1", nativeQuery = true)
    Optional<Socks> findByLessThen(@Param("color") String color,
                                   @Param("cottonPart") int cottonPart);


    @Query(value = "SELECT id, color, cotton_part, quantity FROM Socks WHERE color =:color LIMIT 1", nativeQuery = true)
    Optional<Socks> findByColor(@Param("color") String color);
}
