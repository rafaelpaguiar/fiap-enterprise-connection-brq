package br.com.brq.candidatos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brq.candidatos.entities.Skill;

public interface SkillRepository extends JpaRepository<Skill, Integer> {

	List<Skill> findBySkillContainingIgnoreCase(String skill);
	
}
