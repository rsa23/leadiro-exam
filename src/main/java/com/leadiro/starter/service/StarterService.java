package com.leadiro.starter.service;

import org.springframework.stereotype.Service;

import com.leadiro.starter.service.starter.dto.Starter;

/**
 * An example Service interface definition.
 */
@Service
public interface StarterService {

    /**
     * Get a Starter by id.
     */
    Starter getStarter(int id);

}
