package dao.impl;

import org.springframework.stereotype.Repository;

import dao.AnimalDAO;
import modelo.Animal;

@Repository
public abstract class AnimalDAOImpl extends JPADaoGenerico<Animal, Long> implements AnimalDAO {
	public AnimalDAOImpl() {
		super(Animal.class);
	}
}
