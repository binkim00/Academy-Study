package org.zerock.tourist_springboot.program.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zerock.tourist_springboot.program.domain.Program;
import org.zerock.tourist_springboot.program.repository.ProgramRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgramService {

    private final ProgramRepository programRepository;

    public List<Program> getAllPrograms() {
        return programRepository.findAll();
    }
}
