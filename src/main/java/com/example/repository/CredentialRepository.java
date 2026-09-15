package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.models.Credential;


public interface CredentialRepository extends JpaRepository<Credential, Long>  {

}
