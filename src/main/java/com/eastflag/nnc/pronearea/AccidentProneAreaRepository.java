package com.eastflag.nnc.pronearea;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccidentProneAreaRepository extends JpaRepository<AccidentProneArea, Integer> {
    @NotNull
    List<AccidentProneArea> findAll();
}
